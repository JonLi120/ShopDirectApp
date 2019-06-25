package com.twobytwoshop.ShopDirect.models.api.response;

import com.twobytwoshop.ShopDirect.models.UserInfo;

public class UserInfoResponse {

    private String code;
    private UserInfo data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }
}
