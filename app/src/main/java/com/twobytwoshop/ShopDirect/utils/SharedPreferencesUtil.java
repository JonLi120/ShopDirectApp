package com.twobytwoshop.ShopDirect.utils;

import com.twobytwoshop.ShopDirect.core.BaseApplication;
import com.twobytwoshop.ShopDirect.core.SharedPreferencesHelper;

public class SharedPreferencesUtil {
    private static SharedPreferencesUtil util;
    private SharedPreferencesHelper helper;
    private String SP_KEY_LOGIN = "login";

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

    public void setLogin(Boolean isLogin) {
        helper.put(SP_KEY_LOGIN, isLogin);
    }

    public Boolean getLogin() {
        return helper.getBoolean(SP_KEY_LOGIN, false);
    }
}
