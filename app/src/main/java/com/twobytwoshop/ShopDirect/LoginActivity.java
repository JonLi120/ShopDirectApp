package com.twobytwoshop.ShopDirect;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.twobytwoshop.ShopDirect.core.BaseActivity;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.viewmodel.LoginViewModel;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_img)
    ImageView loginImg;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.register_btn)
    Button registerBtn;
    @BindView(R.id.login_frame_layout)
    FrameLayout loginFrameLayout;
    private FragmentTransaction ft;
    protected LoginViewModel viewModel;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setupViewModel();

        loginBtn.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorYellow), PorterDuff.Mode.SRC);
        registerBtn.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorGreen), PorterDuff.Mode.SRC);

    }

    private void setupViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(this);
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);

        viewModel.showLoading.observe(this, bean -> {
            if (bean.isShow()) {
                showLoadingDialog(bean.getContent());
            } else {
                dismissLoadingDialog();
            }
        });

        viewModel.getStatus().observe(this, map -> {
            assert map != null;
            String tag = (String) map.get("tag");
            if ("login".equals(tag)) {
                if ("100".equals(map.get("code"))) {
                    sp.setUUID((String) map.get("uuid"));
                    startMainActivity();
                }
            } else if ("check_register".equals(tag)){
                if ("100".equals(map.get("code"))) {
                    startSignUpVerificationFragment();
                    return;
                }
            } else if ("insert_register".equals(tag)) {
                if ("100".equals(map.get("code"))) {
                    sp.setUUID((String) map.get("uuid"));
                    startMainActivity();
                    Toast.makeText(this, "Register Success.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            Toast.makeText(this, (String) map.get("content"), Toast.LENGTH_LONG).show();
        });
    }

    @OnClick({R.id.login_btn, R.id.register_btn})
    public void onClick(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.login_btn:
                LoginFragment fragment = LoginFragment.newInstance();
                ft.add(R.id.login_frame_layout, fragment, LoginFragment.class.getSimpleName());
                ft.addToBackStack(null);
                ft.commit();
                break;
            case R.id.register_btn:
                SignUpFragment fragment1 = SignUpFragment.newInstance();
                ft.add(R.id.login_frame_layout, fragment1, SignUpFragment.class.getSimpleName());
                ft.addToBackStack(null);
                ft.commit();
                break;
        }
    }

    public void startSignUpVerificationFragment() {
        String sponsor = "", name = "", passport = "", birth = "", phone = "",
                spName = "", spIC = "", state = "", postcode = "", address = "";
        int gender = 0;

        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(SignUpFragment.class.getSimpleName());
        if (currentFragment instanceof SignUpFragment) {
            sponsor = ((SignUpFragment) currentFragment).sponsor;
            name = ((SignUpFragment) currentFragment).name;
            passport = ((SignUpFragment) currentFragment).passport;
            birth = ((SignUpFragment) currentFragment).birth;
            phone = ((SignUpFragment) currentFragment).phone;
            spName = ((SignUpFragment) currentFragment).spouseName;
            spIC = ((SignUpFragment) currentFragment).spouseIC;
            state = ((SignUpFragment) currentFragment).state;
            postcode = ((SignUpFragment) currentFragment).postcode;
            address = ((SignUpFragment) currentFragment).address;
            gender = ((SignUpFragment) currentFragment).selectedGender;
        }

        ft = getSupportFragmentManager().beginTransaction();
        VerifySignUpFragment fragment = VerifySignUpFragment
                .newInstance(sponsor, name, passport, gender, birth, phone, spName, spIC, state, postcode, address);
        ft.add(R.id.login_frame_layout, fragment, VerifySignUpFragment.class.getSimpleName());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void startMainActivity() {
        sp.setLogin(true);
        MainActivity.startActivity(this);
        finish();
    }
}
