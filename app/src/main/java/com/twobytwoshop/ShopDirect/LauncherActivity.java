package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.twobytwoshop.ShopDirect.core.BaseActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LauncherActivity extends BaseActivity {

    @BindView(R.id.img_launcher)
    ImageView imgLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);

        new Handler().postDelayed(() -> {
            MainActivity.startActivity(this);
        }, 500);
    }
}
