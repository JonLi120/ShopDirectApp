package com.twobytwoshop.ShopDirect.models.api.response;

public class ProductInfoResponse {

    /**
     * code : 100
     * data : {"PID":"99","sn":"C910092","fixprice":"1986","price":"1266","title":"ENZY+ DEEPSEA WATER R+NHA  MASK (25G/90PCS)","content":"<p>18 * 5 pcs<\/p>\n\n<h3>&nbsp;<\/h3>\n\n<p><strong>Enzy+ Deepsea Water<\/strong><\/p>\n\n<p>&nbsp;<\/p>\n\n<p>Enzy+ Deepsea Water is the second project launched under the Enzy&reg; Complete Treatment, named Beauty Care which is a series of products specializing in skin care. It is exclusively formulated by extracting Superoxide Dismutase (S.O.D) from 150 plant-based materials and live yeasts, as well as 41 types of mineral extracted from deep sea water. Enzy+ DeepSea Water focuses on enzymes, emphasizing on first line defense of the skin from irritation and allergies.<\/p>\n\n<p>&nbsp;<\/p>\n\n<p>&nbsp;<\/p>\n\n<p><strong>Scientific Glowing Mask<\/strong><\/p>\n\n<p>&nbsp;<\/p>\n\n<p><strong>New Formulations<\/strong><\/p>\n\n<p>&nbsp;<\/p>\n\n<ul>\n\t<li><strong>Resveratrol<\/strong> from plant extractions<\/li>\n<\/ul>\n\n<p>&nbsp;<\/p>\n\n<p>Combat skin aging by defending against free-radicals and UV rays, effectively reduce melanin production.<\/p>\n\n<p>&nbsp;<\/p>\n\n<p>&nbsp;<\/p>\n\n<ul>\n\t<li><strong>Active nano-hyaluronic acid<\/strong> with strong penetrating effect<\/li>\n<\/ul>\n\n<p>&nbsp;<\/p>\n\n<p>Advanced nano-technology allows active nano-hyaluronic acid molecule to cross skin layers, easily reach the dermis, and gives you maximum hydrating effect.<\/p>\n\n<p>&nbsp;<\/p>\n\n<p>Instant skin whitening and hydrating effect!<\/p>\n\n<p>&nbsp;<\/p>\n\n<p>&nbsp;<\/p>\n\n<p>&nbsp;<\/p>\n\n<p><strong>Benefits<\/strong><\/p>\n\n<p>&nbsp;<\/p>\n\n<ul>\n\t<li>Skin soothing.<\/li>\n\t<li>Active Nano Hyaluronic Acid prolongs hydrating effect, creating ultimate elastic and glowing skin.<\/li>\n\t<li>Enhances formation of collagen.<\/li>\n\t<li>Enhances skin firmness.<\/li>\n\t<li>Resveratrol brightens skin instantly, defend against pigmentation.<\/li>\n<\/ul>\n\n<p>&nbsp;<\/p>\n\n<p>&nbsp;<\/p>\n\n<p>&nbsp;<\/p>\n\n<p><strong>Directions for use<\/strong><\/p>\n\n<p>&nbsp;<\/p>\n\n<p>Take out the mask sheet and unfold it. Fit it and cover your face. After 15 minutes, gently remove the mask sheet. Use 2-3 times a week.<\/p>\n","main_image":"/content/product/d6f8a84a38893516ed26549dd5c29328.jpg","images":"[\"\\/global_assets\\/images\\/placeholders\\/placeholder.jpg\"]","tags":"獨家,分類","pp":"2400","cp":"258"}
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
        private String PID;
        private String sn;
        private String fixprice;
        private String price;
        private String exchange;
        private String title;
        private String content;
        private String main_image;
        private String images;
        private String tags;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMain_image() {
            return main_image;
        }

        public void setMain_image(String main_image) {
            this.main_image = main_image;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
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
