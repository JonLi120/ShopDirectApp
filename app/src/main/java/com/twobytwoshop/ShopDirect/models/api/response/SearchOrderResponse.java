package com.twobytwoshop.ShopDirect.models.api.response;

import java.util.List;

public class SearchOrderResponse {
    /**
     * code : 100
     * data : [{"OID":"20190624090147287","UID":"10005252","status":"100","pay_status":"100","pay_way":"20","pay_report":"","tran_status":"100","tran_way":"0","tran_data":"","pickup":"0","discount_code":"","discount_content":"[]","product_price":"173.55","tran_price":"19","discount_price":"0","total_price":"192.55","total_point":"0","agent":"0","agent_bonus":"0","is_agent_buy":"1","point":"0","uppoint":"0","ap":"5.85","cp":"0","spread":"0","firstkind":"0","note":"","remove":"0","update_datetime":"2019-06-24 17:01:47","build_datetime":"2019-06-24 17:01:47","o_name":"123","account":"joseph.ncare@gmail.com","agent_account":null}]
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
         * OID : 20190624090147287
         * UID : 10005252
         * status : 100
         * pay_status : 100
         * pay_way : 20
         * pay_report :
         * tran_status : 100
         * tran_way : 0
         * tran_data :
         * pickup : 0
         * discount_code :
         * discount_content : []
         * product_price : 173.55
         * tran_price : 19
         * discount_price : 0
         * total_price : 192.55
         * total_point : 0
         * agent : 0
         * agent_bonus : 0
         * is_agent_buy : 1
         * point : 0
         * uppoint : 0
         * ap : 5.85
         * cp : 0
         * spread : 0
         * firstkind : 0
         * note :
         * remove : 0
         * update_datetime : 2019-06-24 17:01:47
         * build_datetime : 2019-06-24 17:01:47
         * o_name : 123
         * account : joseph.ncare@gmail.com
         * agent_account : null
         */

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
        private String o_name;
        private String account;
        private Object agent_account;

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

        public String getO_name() {
            return o_name;
        }

        public void setO_name(String o_name) {
            this.o_name = o_name;
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
    }
}
