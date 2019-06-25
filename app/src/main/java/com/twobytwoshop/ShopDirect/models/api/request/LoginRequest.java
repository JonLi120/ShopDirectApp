package com.twobytwoshop.ShopDirect.models.api.request;

public class LoginRequest {
    private String uuid;
    private String password;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequest(String uuid, String password) {
        this.uuid = uuid;
        this.password = password;
    }
}
