package com.twobytwoshop.ShopDirect.utils;

import com.twobytwoshop.ShopDirect.core.BaseApplication;
import com.twobytwoshop.ShopDirect.core.SharedPreferencesHelper;

public class SharedPreferencesUtil {
    private static SharedPreferencesUtil util;
    private SharedPreferencesHelper helper;
    private String SP_KEY_LOGIN = "login";
    private String SP_KEY_UUID = "uuid";
    private String SP_KEY_ORDER_STATUS = "order_status"; // 1:一般商品，2:贈品
    private String SP_KEY_MDID = "mdid";
    private String SP_KEY_CODE = "mdid_code";
    private String SP_KEY_NAME = "name";
    private String SP_KEY_DEVICE_ID = "device_id";

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

    public void setUUID(String uuid) {
        helper.put(SP_KEY_UUID, uuid);
    }

    public String getUUID() {
        return helper.getString(SP_KEY_UUID, "");
    }

    public void setOrderStatus(int status) {
        helper.put(SP_KEY_ORDER_STATUS, status);
    }

    public int getOrderStatus() {
        return helper.getInt(SP_KEY_ORDER_STATUS, 0);
    }

    public void setMDID(int mdid) {
        helper.put(SP_KEY_MDID, mdid);
    }

    public int getMDID() {
        return helper.getInt(SP_KEY_MDID, 0);
    }

    public void setCode(String code) {
        helper.put(SP_KEY_CODE, code);
    }

    public String getCode() {
        return helper.getString(SP_KEY_CODE, "");
    }

    public void setName(String name) {
        helper.put(SP_KEY_NAME, name);
    }

    public String getName() {
        return helper.getString(SP_KEY_NAME, "");
    }

    public void setDeviceId(String id) {
        helper.put(SP_KEY_DEVICE_ID, id);
    }

    public String getDeviceId() {
        return helper.getString(SP_KEY_DEVICE_ID, "");
    }
}
