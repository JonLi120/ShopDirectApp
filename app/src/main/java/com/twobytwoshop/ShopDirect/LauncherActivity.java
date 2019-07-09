package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.twobytwoshop.ShopDirect.core.BaseActivity;
import com.twobytwoshop.ShopDirect.core.ConstantConfig;

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

        ConstantConfig.setAppDevice(sp.getDeviceId());
        ConstantConfig.setAppKey("a12eff5e1db5f470731ee4071971d925");
        ConstantConfig.setAppVersionCode(BuildConfig.VERSION_NAME);
        String id = sp.getUUID();
        ConstantConfig.setAppUuid(id);

        Crashlytics.setUserIdentifier(id);

        new Handler().postDelayed(() -> {
            if (!sp.getUUID().isEmpty()) {
                MainActivity.startActivity(this);
            } else {
                LoginActivity.startActivity(this);
            }

            finish();
        }, 800);
    }
}
