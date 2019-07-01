package com.twobytwoshop.ShopDirect.models.api.response;

import java.util.List;

public class CarResponse {

    /**
     * code : 100
     * data : {"product_price":104.13,"discount_code":"","order_sm_price":104.13,"point":0,"uppoint":0,"agent":"0","agent_bonus":0,"order_pay_point":0,"firstkind":0,"ap_sum":3.51,"order_product":[{"PID":"25","dtype":"product","CAID":"6","image":"/content/product/34114c8f9573af723fc5b9b2a15956b4.jpg","title":"APPETON MULTIVITAMIN LYSINE WITH PREBIOTICS","qty":"3","price":34.71,"ap":"1.17","oprice":"35.88","point":0,"fare":"15","nextfare":"1","cp":"1","spread":"0.8","discount":"0","level_price":"{\"105\":\"35.88\",\"110\":\"35.88\",\"115\":\"35.88\",\"120\":\"35.88\",\"125\":\"35.88\",\"130\":\"35.88\"}","cfare":"1"}],"discount_list":[],"tran_price":17,"order_price":121.13,"cp":0,"spread":0,"order_kind":"product"}
     */

    private String code;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * product_price : 104.13
         * discount_code :
         * order_sm_price : 104.13
         * point : 0
         * uppoint : 0
         * agent : 0
         * agent_bonus : 0
         * order_pay_point : 0
         * firstkind : 0
         * ap_sum : 3.51
         * order_product : [{"PID":"25","dtype":"product","CAID":"6","image":"/content/product/34114c8f9573af723fc5b9b2a15956b4.jpg","title":"APPETON MULTIVITAMIN LYSINE WITH PREBIOTICS","qty":"3","price":34.71,"ap":"1.17","oprice":"35.88","point":0,"fare":"15","nextfare":"1","cp":"1","spread":"0.8","discount":"0","level_price":"{\"105\":\"35.88\",\"110\":\"35.88\",\"115\":\"35.88\",\"120\":\"35.88\",\"125\":\"35.88\",\"130\":\"35.88\"}","cfare":"1"}]
         * discount_list : []
         * tran_price : 17
         * order_price : 121.13
         * cp : 0
         * spread : 0
         * order_kind : product
         */

        private double product_price;
        private String discount_code;
        private double order_sm_price;
        private int point;
        private int uppoint;
        private String agent;
        private int agent_bonus;
        private int order_pay_point;
        private int firstkind;
        private double ap_sum;
        private int tran_price;
        private double order_price;
        private int cp;
        private int spread;
        private String order_kind;
        private List<OrderProductBean> order_product;
        private List<?> discount_list;

        public double getProduct_price() {
            return product_price;
        }

        public void setProduct_price(double product_price) {
            this.product_price = product_price;
        }

        public String getDiscount_code() {
            return discount_code;
        }

        public void setDiscount_code(String discount_code) {
            this.discount_code = discount_code;
        }

        public double getOrder_sm_price() {
            return order_sm_price;
        }

        public void setOrder_sm_price(double order_sm_price) {
            this.order_sm_price = order_sm_price;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getUppoint() {
            return uppoint;
        }

        public void setUppoint(int uppoint) {
            this.uppoint = uppoint;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public int getAgent_bonus() {
            return agent_bonus;
        }

        public void setAgent_bonus(int agent_bonus) {
            this.agent_bonus = agent_bonus;
        }

        public int getOrder_pay_point() {
            return order_pay_point;
        }

        public void setOrder_pay_point(int order_pay_point) {
            this.order_pay_point = order_pay_point;
        }

        public int getFirstkind() {
            return firstkind;
        }

        public void setFirstkind(int firstkind) {
            this.firstkind = firstkind;
        }

        public double getAp_sum() {
            return ap_sum;
        }

        public void setAp_sum(double ap_sum) {
            this.ap_sum = ap_sum;
        }

        public int getTran_price() {
            return tran_price;
        }

        public void setTran_price(int tran_price) {
            this.tran_price = tran_price;
        }

        public double getOrder_price() {
            return order_price;
        }

        public void setOrder_price(double order_price) {
            this.order_price = order_price;
        }

        public int getCp() {
            return cp;
        }

        public void setCp(int cp) {
            this.cp = cp;
        }

        public int getSpread() {
            return spread;
        }

        public void setSpread(int spread) {
            this.spread = spread;
        }

        public String getOrder_kind() {
            return order_kind;
        }

        public void setOrder_kind(String order_kind) {
            this.order_kind = order_kind;
        }

        public List<OrderProductBean> getOrder_product() {
            return order_product;
        }

        public void setOrder_product(List<OrderProductBean> order_product) {
            this.order_product = order_product;
        }

        public List<?> getDiscount_list() {
            return discount_list;
        }

        public void setDiscount_list(List<?> discount_list) {
            this.discount_list = discount_list;
        }

        public static class OrderProductBean {
            /**
             * PID : 25
             * dtype : product
             * CAID : 6
             * image : /content/product/34114c8f9573af723fc5b9b2a15956b4.jpg
             * title : APPETON MULTIVITAMIN LYSINE WITH PREBIOTICS
             * qty : 3
             * price : 34.71
             * ap : 1.17
             * oprice : 35.88
             * point : 0
             * fare : 15
             * nextfare : 1
             * cp : 1
             * spread : 0.8
             * discount : 0
             * level_price : {"105":"35.88","110":"35.88","115":"35.88","120":"35.88","125":"35.88","130":"35.88"}
             * cfare : 1
             */

            private String PID;
            private String dtype;
            private String CAID;
            private String image;
            private String title;
            private String qty;
            private double price;
            private String ap;
            private String oprice;
            private int point;
            private String fare;
            private String nextfare;
            private String cp;
            private String spread;
            private String discount;
            private String level_price;
            private String cfare;

            public String getPID() {
                return PID;
            }

            public void setPID(String PID) {
                this.PID = PID;
            }

            public String getDtype() {
                return dtype;
            }

            public void setDtype(String dtype) {
                this.dtype = dtype;
            }

            public String getCAID() {
                return CAID;
            }

            public void setCAID(String CAID) {
                this.CAID = CAID;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getQty() {
                return qty;
            }

            public void setQty(String qty) {
                this.qty = qty;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getAp() {
                return ap;
            }

            public void setAp(String ap) {
                this.ap = ap;
            }

            public String getOprice() {
                return oprice;
            }

            public void setOprice(String oprice) {
                this.oprice = oprice;
            }

            public int getPoint() {
                return point;
            }

            public void setPoint(int point) {
                this.point = point;
            }

            public String getFare() {
                return fare;
            }

            public void setFare(String fare) {
                this.fare = fare;
            }

            public String getNextfare() {
                return nextfare;
            }

            public void setNextfare(String nextfare) {
                this.nextfare = nextfare;
            }

            public String getCp() {
                return cp;
            }

            public void setCp(String cp) {
                this.cp = cp;
            }

            public String getSpread() {
                return spread;
            }

            public void setSpread(String spread) {
                this.spread = spread;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getLevel_price() {
                return level_price;
            }

            public void setLevel_price(String level_price) {
                this.level_price = level_price;
            }

            public String getCfare() {
                return cfare;
            }

            public void setCfare(String cfare) {
                this.cfare = cfare;
            }
        }
    }
}
