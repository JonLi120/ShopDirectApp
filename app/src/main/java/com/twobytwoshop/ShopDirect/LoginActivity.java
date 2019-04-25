package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.twobytwoshop.ShopDirect.core.BaseActivity;
import com.twobytwoshop.ShopDirect.utils.LoginFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                LoginFragment fragment = LoginFragment.newInstance();
                ft.add(R.id.login_frame_layout, fragment, LoginFragment.class.getSimpleName());
                ft.addToBackStack(null);
                ft.commit();
                break;
        }
    }
}
