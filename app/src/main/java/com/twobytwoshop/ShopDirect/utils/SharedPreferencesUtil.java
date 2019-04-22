package com.twobytwoshop.ShopDirect.utils;

import com.twobytwoshop.ShopDirect.core.BaseApplication;
import com.twobytwoshop.ShopDirect.core.SharedPreferencesHelper;

public class SharedPreferencesUtil {
    private static SharedPreferencesUtil util;
    private SharedPreferencesHelper helper;

    public static SharedPreferencesUtil getInstance() {
        if (util == null) {
            util = new SharedPreferencesUtil();
        }
        return util;
    }

    private SharedPreferencesUtil() {
        util = this;
        helper = BaseApplication.getInstance().getSharedPreferencesHelper();
    }
}
