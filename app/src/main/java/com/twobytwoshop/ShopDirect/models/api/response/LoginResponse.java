package com.twobytwoshop.ShopDirect.models.api.response;

import com.twobytwoshop.ShopDirect.models.UserInfo;

public class LoginResponse {

    /**
     * code : 100
     * data : {"uuid":"10005252","level":"premium","sponsor":"10004436","name":"FENG SUN SUN",
     * "email":"joseph.ncare@gmail.com","Mobile_Nos":"012-3789887","country":"","state":"11",
     * "postcode":"84000","address":"165, JLN MAWAR 8 TAMAN MAWAR 84000 MUAR JOHOR","PCP":"0",
     * "accumulate_CP":"316294","point":47685,"e_wallet":"0","bonus":"477","spread":"1.7",
     * "cp":"3497","mdid":"175","CODE":"SMS009"}
     */

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

    @Override
    public String toString() {
        return "LoginResponse{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
