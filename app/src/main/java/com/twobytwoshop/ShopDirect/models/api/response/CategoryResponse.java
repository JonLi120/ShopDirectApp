package com.twobytwoshop.ShopDirect.models.api.response;

import java.util.List;

public class CategoryResponse {

    /**
     * code : 100
     * data : [{"CAID":"7","title":"限時優惠促銷配套"},{"CAID":"6","title":"加盟商品"},{"CAID":"5","title":"護膚系列"},{"CAID":"4","title":"保健系列"}]
     */

    private String code;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * CAID : 7
         * title : 限時優惠促銷配套
         */

        private String CAID;
        private String title;

        public String getCAID() {
            return CAID;
        }

        public void setCAID(String CAID) {
            this.CAID = CAID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
