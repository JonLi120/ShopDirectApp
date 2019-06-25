package com.twobytwoshop.ShopDirect.models;

public class ShowLoadingBean {
    private boolean isShow;
    private String content;

    public ShowLoadingBean(boolean isShow) {
        this.isShow = isShow;
    }

    public ShowLoadingBean(boolean isShow, String content) {
        this.isShow = isShow;
        this.content = content;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
