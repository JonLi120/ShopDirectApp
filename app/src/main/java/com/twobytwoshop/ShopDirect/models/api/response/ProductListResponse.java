package com.twobytwoshop.ShopDirect.models.api.response;

import java.util.List;

public class ProductListResponse {

    /**
     * code : 100
     * data : [{"PID":"116","sn":"C710040R","fixprice":"110","price":"85","title":"ENZY+ DEEPSEA WATER R+NHA  MASK (25G/5PCS)","main_image":"/content/product/dd208ebc8983b6ecb64b58329eb02a0b.jpg","pp":"133","cp":"13"},{"PID":"115","sn":"C710030","fixprice":"180","price":"150","title":"ENZY+ DEEPSEA WATER R+NHA  ESSENCE (50ML/1BTL)","main_image":"/content/product/3acf8998ab18bfad1ea16a7a5224e354.jpg","pp":"216","cp":"30"},{"PID":"114","sn":"C710020","fixprice":"180","price":"150","title":"ENZY+ DEEPSEA WATER  TONER (120ML/1BTL)","main_image":"/content/product/a134025349445ed83da1ffc18dd44801.jpg","pp":"216","cp":"30"},{"PID":"113","sn":"C710010","fixprice":"150","price":"130","title":"ENZY+ DEEPSEA WATER  CLEANSER (120ML/1BTL)","main_image":"/content/product/36ce7e0e9539178556de2a94be681d52.jpg","pp":"200","cp":"29"},{"PID":"102","sn":"C910095","fixprice":"1860","price":"1260","title":"ENZY+ DEEPSEA WATER SKIN CARE SET (3SETS)","main_image":"/content/product/64dc4071cb580725eb6698156b53ce6a.jpg","pp":"2400","cp":"315"},{"PID":"101","sn":"C910094","fixprice":"1620","price":"1080","title":"ENZY+ DEEPSEA WATER R+NHA  ESSENCE (50ML/9BTLS)","main_image":"/content/product/b6855c7b1a347a7486805b2d3f2c195a.jpg","pp":"1950","cp":"270"},{"PID":"100","sn":"C910093","fixprice":"1620","price":"1080","title":"ENZY+ DEEPSEA WATER  TONER (120ML/9BTLS)","main_image":"/content/product/6ad9969097c158f89893e32b720c59c7.jpg","pp":"1950","cp":"270"},{"PID":"99","sn":"C910092","fixprice":"1986","price":"1266","title":"ENZY+ DEEPSEA WATER R+NHA  MASK (25G/90PCS)","main_image":"/content/product/d6f8a84a38893516ed26549dd5c29328.jpg","pp":"2400","cp":"258"},{"PID":"98","sn":"C910091","fixprice":"1350","price":"990","title":"ENZY+ DEEPSEA WATER  CLEANSER (120ML/9BTLS)","main_image":"/content/product/65f0e6e924b13bffa49ecbcc8eed92cc.jpg","pp":"1800","cp":"264"},{"PID":"33","sn":"C720040R","fixprice":"331","price":"256.00","title":"ENZY+ DEEPSEA WATER R+NHA  MASK (25G/15PCS)","main_image":"/content/product/b55cb6a6ddf65f1ca7473054242c6630.jpg","pp":"400","cp":"43"},{"PID":"31","sn":"C720020","fixprice":"540","price":"450.00","title":"ENZY+ DEEPSEA WATER  TONER (120ML/3BTL)","main_image":"/content/product/6fcaa6a009bcdb62cb2b0143bdda01b5.jpg","pp":"650","cp":"90"},{"PID":"29","sn":"C720030","fixprice":"540","price":"450.00","title":"ENZY+ DEEPSEA WATER R+NHA  ESSENCE (50ML/3BTL)","main_image":"/content/product/42b88b980a623623051f1ef0a0c08f58.jpg","pp":"650","cp":"90"},{"PID":"28","sn":"C720010","fixprice":"450","price":"390.00","title":"ENZY+ DEEPSEA WATER  CLEANSER (120ML/3BTL)","main_image":"/content/product/c66f8ef159dcd858fbbc1ed46f57f8b9.jpg","pp":"600","cp":"88"},{"PID":"2","sn":"C720050R","fixprice":"620","price":"515.00","title":"ENZY+ DEEPSEA WATER SKIN CARE SET","main_image":"/content/product/c3fcb034da9735996d9031456efd3aba.jpg","pp":"800","cp":"105"}]
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
         * PID : 116
         * sn : C710040R
         * fixprice : 110
         * price : 85
         * exchange: 1500
         * title : ENZY+ DEEPSEA WATER R+NHA  MASK (25G/5PCS)
         * main_image : /content/product/dd208ebc8983b6ecb64b58329eb02a0b.jpg
         * pp : 133
         * cp : 13
         */

        private String PID;
        private String sn;
        private String fixprice;
        private String price;
        private String exchange;
        private String title;
        private String main_image;
        private String pp;
        private String cp;

        public String getPID() {
            return PID;
        }

        public void setPID(String PID) {
            this.PID = PID;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getFixprice() {
            return fixprice;
        }

        public void setFixprice(String fixprice) {
            this.fixprice = fixprice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMain_image() {
            return main_image;
        }

        public void setMain_image(String main_image) {
            this.main_image = main_image;
        }

        public String getPp() {
            return pp;
        }

        public void setPp(String pp) {
            this.pp = pp;
        }

        public String getCp() {
            return cp;
        }

        public void setCp(String cp) {
            this.cp = cp;
        }
    }
}
