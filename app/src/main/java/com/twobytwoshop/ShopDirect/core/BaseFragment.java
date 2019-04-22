package com.twobytwoshop.ShopDirect.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.twobytwoshop.ShopDirect.utils.SharedPreferencesUtil;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected BaseApplication ba = BaseApplication.getInstance();
    protected SharedPreferencesUtil sp = SharedPreferencesUtil.getInstance();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }
}
