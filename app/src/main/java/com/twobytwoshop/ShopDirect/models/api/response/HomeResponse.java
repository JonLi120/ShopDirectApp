package com.twobytwoshop.ShopDirect.models.api.response;

import java.util.List;

public class HomeResponse {

    /**
     * code : 100
     * data : {"products":[{"PID":"116","sn":"C710040R","fixprice":"110","price":"65","title":"ENZY+ DEEPSEA WATER R+NHA  MASK (25G/5PCS)","main_image":"/content/product/dd208ebc8983b6ecb64b58329eb02a0b.jpg","pp":"133","cp":"13"},{"PID":"115","sn":"C710030","fixprice":"180","price":"109","title":"ENZY+ DEEPSEA WATER R+NHA  ESSENCE (50ML/1BTL)","main_image":"/content/product/3acf8998ab18bfad1ea16a7a5224e354.jpg","pp":"216","cp":"30"},{"PID":"114","sn":"C710020","fixprice":"180","price":"109","title":"ENZY+ DEEPSEA WATER  TONER (120ML/1BTL)","main_image":"/content/product/a134025349445ed83da1ffc18dd44801.jpg","pp":"216","cp":"30"},{"PID":"113","sn":"C710010","fixprice":"150","price":"99","title":"ENZY+ DEEPSEA WATER  CLEANSER (120ML/1BTL)","main_image":"/content/product/36ce7e0e9539178556de2a94be681d52.jpg","pp":"200","cp":"29"},{"PID":"102","sn":"C910095","fixprice":"1860","price":"1146","title":"ENZY+ DEEPSEA WATER SKIN CARE SET (3SETS)","main_image":"/content/product/64dc4071cb580725eb6698156b53ce6a.jpg","pp":"2400","cp":"315"},{"PID":"101","sn":"C910094","fixprice":"1620","price":"984","title":"ENZY+ DEEPSEA WATER R+NHA  ESSENCE (50ML/9BTLS)","main_image":"/content/product/b6855c7b1a347a7486805b2d3f2c195a.jpg","pp":"1950","cp":"270"},{"PID":"100","sn":"C910093","fixprice":"1620","price":"984","title":"ENZY+ DEEPSEA WATER  TONER (120ML/9BTLS)","main_image":"/content/product/6ad9969097c158f89893e32b720c59c7.jpg","pp":"1950","cp":"270"},{"PID":"99","sn":"C910092","fixprice":"1986","price":"1170","title":"ENZY+ DEEPSEA WATER R+NHA  MASK (25G/90PCS)","main_image":"/content/product/d6f8a84a38893516ed26549dd5c29328.jpg","pp":"2400","cp":"258"}],"image":{"link":"https://2by2shop.com/product?CAID=6","src":"/content/content/94d81d30d762ea5d87db9a5c9b382725.jpg"}}
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
         * products : [{"PID":"116","sn":"C710040R","fixprice":"110","price":"65","title":"ENZY+ DEEPSEA WATER R+NHA  MASK (25G/5PCS)","main_image":"/content/product/dd208ebc8983b6ecb64b58329eb02a0b.jpg","pp":"133","cp":"13"},{"PID":"115","sn":"C710030","fixprice":"180","price":"109","title":"ENZY+ DEEPSEA WATER R+NHA  ESSENCE (50ML/1BTL)","main_image":"/content/product/3acf8998ab18bfad1ea16a7a5224e354.jpg","pp":"216","cp":"30"},{"PID":"114","sn":"C710020","fixprice":"180","price":"109","title":"ENZY+ DEEPSEA WATER  TONER (120ML/1BTL)","main_image":"/content/product/a134025349445ed83da1ffc18dd44801.jpg","pp":"216","cp":"30"},{"PID":"113","sn":"C710010","fixprice":"150","price":"99","title":"ENZY+ DEEPSEA WATER  CLEANSER (120ML/1BTL)","main_image":"/content/product/36ce7e0e9539178556de2a94be681d52.jpg","pp":"200","cp":"29"},{"PID":"102","sn":"C910095","fixprice":"1860","price":"1146","title":"ENZY+ DEEPSEA WATER SKIN CARE SET (3SETS)","main_image":"/content/product/64dc4071cb580725eb6698156b53ce6a.jpg","pp":"2400","cp":"315"},{"PID":"101","sn":"C910094","fixprice":"1620","price":"984","title":"ENZY+ DEEPSEA WATER R+NHA  ESSENCE (50ML/9BTLS)","main_image":"/content/product/b6855c7b1a347a7486805b2d3f2c195a.jpg","pp":"1950","cp":"270"},{"PID":"100","sn":"C910093","fixprice":"1620","price":"984","title":"ENZY+ DEEPSEA WATER  TONER (120ML/9BTLS)","main_image":"/content/product/6ad9969097c158f89893e32b720c59c7.jpg","pp":"1950","cp":"270"},{"PID":"99","sn":"C910092","fixprice":"1986","price":"1170","title":"ENZY+ DEEPSEA WATER R+NHA  MASK (25G/90PCS)","main_image":"/content/product/d6f8a84a38893516ed26549dd5c29328.jpg","pp":"2400","cp":"258"}]
         * image : {"link":"https://2by2shop.com/product?CAID=6","src":"/content/content/94d81d30d762ea5d87db9a5c9b382725.jpg"}
         */

        private ImageBean image;
        private List<ProductsBean> products;

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ImageBean {
            /**
             * link : https://2by2shop.com/product?CAID=6
             * src : /content/content/94d81d30d762ea5d87db9a5c9b382725.jpg
             */

            private String link;
            private String src;

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }
        }

        public static class ProductsBean {
            /**
             * PID : 116
             * sn : C710040R
             * fixprice : 110
             * price : 65
             * title : ENZY+ DEEPSEA WATER R+NHA  MASK (25G/5PCS)
             * main_image : /content/product/dd208ebc8983b6ecb64b58329eb02a0b.jpg
             * pp : 133
             * cp : 13
             */

            private String PID;
            private String sn;
            private String fixprice;
            private String price;
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
}
