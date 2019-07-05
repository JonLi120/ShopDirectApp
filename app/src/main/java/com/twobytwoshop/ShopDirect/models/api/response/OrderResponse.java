package com.twobytwoshop.ShopDirect.models.api.response;

public class OrderResponse {

    /**
     * code : 100
     * OID : 20190704103155441
     * pay : card
     * url : http://api.ncare-net.com/v1/webview/orderpay/20190704103155441
     */

    private String code;
    private String OID;
    private String pay;
    private String url;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
