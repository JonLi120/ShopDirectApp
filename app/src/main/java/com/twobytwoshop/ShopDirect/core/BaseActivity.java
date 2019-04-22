package com.twobytwoshop.ShopDirect.core;

import android.os.Bundle;

import com.twobytwoshop.ShopDirect.utils.SharedPreferencesUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected BaseApplication ba;
    protected SharedPreferencesUtil sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ba = BaseApplication.getInstance();
        sp = SharedPreferencesUtil.getInstance();
    }
}
