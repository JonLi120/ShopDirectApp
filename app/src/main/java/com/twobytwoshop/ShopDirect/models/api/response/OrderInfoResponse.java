package com.twobytwoshop.ShopDirect.models.api.response;

import java.util.List;

public class OrderInfoResponse {

    private String code;
    private DataBeanX data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {

        private String OID;
        private String UID;
        private String status;
        private String pay_status;
        private String pay_way;
        private String pay_report;
        private String tran_status;
        private String tran_way;
        private String tran_data;
        private String pickup;
        private String discount_code;
        private String discount_content;
        private String product_price;
        private String tran_price;
        private String discount_price;
        private String total_price;
        private String total_point;
        private String agent;
        private String agent_bonus;
        private String is_agent_buy;
        private String point;
        private String uppoint;
        private String ap;
        private String cp;
        private String spread;
        private String firstkind;
        private String note;
        private String remove;
        private String update_datetime;
        private String build_datetime;
        private DataBean data;
        private String account;
        private Object agent_account;
        private Object agent_name;
        private String AgencyID;
        private List<ProductListBean> product_list;

        public String getOID() {
            return OID;
        }

        public void setOID(String OID) {
            this.OID = OID;
        }

        public String getUID() {
            return UID;
        }

        public void setUID(String UID) {
            this.UID = UID;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getPay_way() {
            return pay_way;
        }

        public void setPay_way(String pay_way) {
            this.pay_way = pay_way;
        }

        public String getPay_report() {
            return pay_report;
        }

        public void setPay_report(String pay_report) {
            this.pay_report = pay_report;
        }

        public String getTran_status() {
            return tran_status;
        }

        public void setTran_status(String tran_status) {
            this.tran_status = tran_status;
        }

        public String getTran_way() {
            return tran_way;
        }

        public void setTran_way(String tran_way) {
            this.tran_way = tran_way;
        }

        public String getTran_data() {
            return tran_data;
        }

        public void setTran_data(String tran_data) {
            this.tran_data = tran_data;
        }

        public String getPickup() {
            return pickup;
        }

        public void setPickup(String pickup) {
            this.pickup = pickup;
        }

        public String getDiscount_code() {
            return discount_code;
        }

        public void setDiscount_code(String discount_code) {
            this.discount_code = discount_code;
        }

        public String getDiscount_content() {
            return discount_content;
        }

        public void setDiscount_content(String discount_content) {
            this.discount_content = discount_content;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getTran_price() {
            return tran_price;
        }

        public void setTran_price(String tran_price) {
            this.tran_price = tran_price;
        }

        public String getDiscount_price() {
            return discount_price;
        }

        public void setDiscount_price(String discount_price) {
            this.discount_price = discount_price;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getTotal_point() {
            return total_point;
        }

        public void setTotal_point(String total_point) {
            this.total_point = total_point;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public String getAgent_bonus() {
            return agent_bonus;
        }

        public void setAgent_bonus(String agent_bonus) {
            this.agent_bonus = agent_bonus;
        }

        public String getIs_agent_buy() {
            return is_agent_buy;
        }

        public void setIs_agent_buy(String is_agent_buy) {
            this.is_agent_buy = is_agent_buy;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getUppoint() {
            return uppoint;
        }

        public void setUppoint(String uppoint) {
            this.uppoint = uppoint;
        }

        public String getAp() {
            return ap;
        }

        public void setAp(String ap) {
            this.ap = ap;
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

        public String getFirstkind() {
            return firstkind;
        }

        public void setFirstkind(String firstkind) {
            this.firstkind = firstkind;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getRemove() {
            return remove;
        }

        public void setRemove(String remove) {
            this.remove = remove;
        }

        public String getUpdate_datetime() {
            return update_datetime;
        }

        public void setUpdate_datetime(String update_datetime) {
            this.update_datetime = update_datetime;
        }

        public String getBuild_datetime() {
            return build_datetime;
        }

        public void setBuild_datetime(String build_datetime) {
            this.build_datetime = build_datetime;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public Object getAgent_account() {
            return agent_account;
        }

        public void setAgent_account(Object agent_account) {
            this.agent_account = agent_account;
        }

        public Object getAgent_name() {
            return agent_name;
        }

        public void setAgent_name(Object agent_name) {
            this.agent_name = agent_name;
        }

        public String getAgencyID() {
            return AgencyID;
        }

        public void setAgencyID(String AgencyID) {
            this.AgencyID = AgencyID;
        }

        public List<ProductListBean> getProduct_list() {
            return product_list;
        }

        public void setProduct_list(List<ProductListBean> product_list) {
            this.product_list = product_list;
        }

        public static class DataBean {
            /**
             * OID : 20190624090147287
             * o_name : 123
             * o_first_name :
             * o_last_name :
             * o_email : 332
             * o_phone : 123
             * o_tax :
             * r_name : 312
             * r_first_name :
             * r_last_name :
             * r_email : 123
             * r_phone : 123
             * r_tax :
             * r_country : 123
             * r_city : 123
             * r_area : 321
             * r_zipcode : 123
             * r_address : 3213
             */

            private String OID;
            private String o_name;
            private String o_first_name;
            private String o_last_name;
            private String o_email;
            private String o_phone;
            private String o_tax;
            private String r_name;
            private String r_first_name;
            private String r_last_name;
            private String r_email;
            private String r_phone;
            private String r_tax;
            private String r_country;
            private String r_city;
            private String r_area;
            private String r_zipcode;
            private String r_address;

            public String getOID() {
                return OID;
            }

            public void setOID(String OID) {
                this.OID = OID;
            }

            public String getO_name() {
                return o_name;
            }

            public void setO_name(String o_name) {
                this.o_name = o_name;
            }

            public String getO_first_name() {
                return o_first_name;
            }

            public void setO_first_name(String o_first_name) {
                this.o_first_name = o_first_name;
            }

            public String getO_last_name() {
                return o_last_name;
            }

            public void setO_last_name(String o_last_name) {
                this.o_last_name = o_last_name;
            }

            public String getO_email() {
                return o_email;
            }

            public void setO_email(String o_email) {
                this.o_email = o_email;
            }

            public String getO_phone() {
                return o_phone;
            }

            public void setO_phone(String o_phone) {
                this.o_phone = o_phone;
            }

            public String getO_tax() {
                return o_tax;
            }

            public void setO_tax(String o_tax) {
                this.o_tax = o_tax;
            }

            public String getR_name() {
                return r_name;
            }

            public void setR_name(String r_name) {
                this.r_name = r_name;
            }

            public String getR_first_name() {
                return r_first_name;
            }

            public void setR_first_name(String r_first_name) {
                this.r_first_name = r_first_name;
            }

            public String getR_last_name() {
                return r_last_name;
            }

            public void setR_last_name(String r_last_name) {
                this.r_last_name = r_last_name;
            }

            public String getR_email() {
                return r_email;
            }

            public void setR_email(String r_email) {
                this.r_email = r_email;
            }

            public String getR_phone() {
                return r_phone;
            }

            public void setR_phone(String r_phone) {
                this.r_phone = r_phone;
            }

            public String getR_tax() {
                return r_tax;
            }

            public void setR_tax(String r_tax) {
                this.r_tax = r_tax;
            }

            public String getR_country() {
                return r_country;
            }

            public void setR_country(String r_country) {
                this.r_country = r_country;
            }

            public String getR_city() {
                return r_city;
            }

            public void setR_city(String r_city) {
                this.r_city = r_city;
            }

            public String getR_area() {
                return r_area;
            }

            public void setR_area(String r_area) {
                this.r_area = r_area;
            }

            public String getR_zipcode() {
                return r_zipcode;
            }

            public void setR_zipcode(String r_zipcode) {
                this.r_zipcode = r_zipcode;
            }

            public String getR_address() {
                return r_address;
            }

            public void setR_address(String r_address) {
                this.r_address = r_address;
            }
        }

        public static class ProductListBean {

            private String no;
            private String OID;
            private String PID;
            private String dtype;
            private String CAID;
            private Object SEID;
            private String firstkind;
            private String category;
            private String series;
            private String title;
            private String content;
            private String sn;
            private Object item;
            private String fixprice;
            private String price;
            private String ap;
            private String oprice;
            private String final_price;
            private String exchange;
            private String level_price;
            private Object point_scale;
            private String point;
            private String uppoint;
            private String main_image;
            private String images;
            private String cha;
            private String chb;
            private String subsn;
            private String count;
            private String product_price;

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public String getOID() {
                return OID;
            }

            public void setOID(String OID) {
                this.OID = OID;
            }

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

            public Object getSEID() {
                return SEID;
            }

            public void setSEID(Object SEID) {
                this.SEID = SEID;
            }

            public String getFirstkind() {
                return firstkind;
            }

            public void setFirstkind(String firstkind) {
                this.firstkind = firstkind;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getSeries() {
                return series;
            }

            public void setSeries(String series) {
                this.series = series;
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

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public Object getItem() {
                return item;
            }

            public void setItem(Object item) {
                this.item = item;
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

            public String getFinal_price() {
                return final_price;
            }

            public void setFinal_price(String final_price) {
                this.final_price = final_price;
            }

            public String getExchange() {
                return exchange;
            }

            public void setExchange(String exchange) {
                this.exchange = exchange;
            }

            public String getLevel_price() {
                return level_price;
            }

            public void setLevel_price(String level_price) {
                this.level_price = level_price;
            }

            public Object getPoint_scale() {
                return point_scale;
            }

            public void setPoint_scale(Object point_scale) {
                this.point_scale = point_scale;
            }

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getUppoint() {
                return uppoint;
            }

            public void setUppoint(String uppoint) {
                this.uppoint = uppoint;
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

            public String getCha() {
                return cha;
            }

            public void setCha(String cha) {
                this.cha = cha;
            }

            public String getChb() {
                return chb;
            }

            public void setChb(String chb) {
                this.chb = chb;
            }

            public String getSubsn() {
                return subsn;
            }

            public void setSubsn(String subsn) {
                this.subsn = subsn;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getProduct_price() {
                return product_price;
            }

            public void setProduct_price(String product_price) {
                this.product_price = product_price;
            }
        }
    }
}
