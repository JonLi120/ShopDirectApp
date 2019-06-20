package com.twobytwoshop.ShopDirect;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.twobytwoshop.ShopDirect.core.BaseActivity;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
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

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginBtn.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorYellow), PorterDuff.Mode.SRC);
        registerBtn.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorGreen), PorterDuff.Mode.SRC);

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

    public void startSignUpVerificationFragment(String sponsor, String name, String passport,
                                                int gender, String birth, String phone) {
        ft = getSupportFragmentManager().beginTransaction();
        VerifySignUpFragment fragment = VerifySignUpFragment
                .newInstance(sponsor, name, passport, gender, birth, phone);
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
