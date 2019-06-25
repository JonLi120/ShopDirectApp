package com.twobytwoshop.ShopDirect.core;

import android.os.Bundle;

import com.twobytwoshop.ShopDirect.LoadingDialog;
import com.twobytwoshop.ShopDirect.utils.SharedPreferencesUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BaseActivity extends AppCompatActivity {

    protected BaseApplication ba;
    protected SharedPreferencesUtil sp;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ba = BaseApplication.getInstance();
        sp = SharedPreferencesUtil.getInstance();
    }

    public void showLoadingDialog(String content) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(LoadingDialog.class.getSimpleName());
        if (fragment == null && dialog == null) {
            dialog = LoadingDialog.newInstance(content);
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager(), LoadingDialog.class.getSimpleName());
        } else {
            dialog.setContent(content);
        }
    }

    public void dismissLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
