package com.twobytwoshop.ShopDirect.models.api.response;

public class WalletResponse {

    /**
     * code : 100
     * from : {"url":"https://www.mobile88.com/ePayment/entry.asp","MerchantCode":"M17491","PaymentId":"","RefNo":"20190629174101895","Amount":10000,"Currency":"MYR","ProdDesc":"2by2shop ProdDesc","UserName":"FENG SUN SUN","UserEmail":"XX@C.C","UserContact":"111111111111111111","Remark":"","Lang":"UTF-8","SignatureType":"SHA256","Signature":"e18c9588d717eecb99413ed7f33047bc2bb98a5d64a74fc21598e1bc5561d33b","ResponseURL":"http://api.ncare-net.com/v1/wallet/report/20190629174101895","BackendURL":"https://2by2shop.com/wallet/ipay88_report/20190629174101895"}
     */

    private String code;
    private FromBean from;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public FromBean getFrom() {
        return from;
    }

    public void setFrom(FromBean from) {
        this.from = from;
    }

    public static class FromBean {
        /**
         * url : https://www.mobile88.com/ePayment/entry.asp
         * MerchantCode : M17491
         * PaymentId :
         * RefNo : 20190629174101895
         * Amount : 10000
         * Currency : MYR
         * ProdDesc : 2by2shop ProdDesc
         * UserName : FENG SUN SUN
         * UserEmail : XX@C.C
         * UserContact : 111111111111111111
         * Remark :
         * Lang : UTF-8
         * SignatureType : SHA256
         * Signature : e18c9588d717eecb99413ed7f33047bc2bb98a5d64a74fc21598e1bc5561d33b
         * ResponseURL : http://api.ncare-net.com/v1/wallet/report/20190629174101895
         * BackendURL : https://2by2shop.com/wallet/ipay88_report/20190629174101895
         */

        private String url;
        private String MerchantCode;
        private String PaymentId;
        private String RefNo;
        private int Amount;
        private String Currency;
        private String ProdDesc;
        private String UserName;
        private String UserEmail;
        private String UserContact;
        private String Remark;
        private String Lang;
        private String SignatureType;
        private String Signature;
        private String ResponseURL;
        private String BackendURL;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMerchantCode() {
            return MerchantCode;
        }

        public void setMerchantCode(String MerchantCode) {
            this.MerchantCode = MerchantCode;
        }

        public String getPaymentId() {
            return PaymentId;
        }

        public void setPaymentId(String PaymentId) {
            this.PaymentId = PaymentId;
        }

        public String getRefNo() {
            return RefNo;
        }

        public void setRefNo(String RefNo) {
            this.RefNo = RefNo;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int Amount) {
            this.Amount = Amount;
        }

        public String getCurrency() {
            return Currency;
        }

        public void setCurrency(String Currency) {
            this.Currency = Currency;
        }

        public String getProdDesc() {
            return ProdDesc;
        }

        public void setProdDesc(String ProdDesc) {
            this.ProdDesc = ProdDesc;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getUserEmail() {
            return UserEmail;
        }

        public void setUserEmail(String UserEmail) {
            this.UserEmail = UserEmail;
        }

        public String getUserContact() {
            return UserContact;
        }

        public void setUserContact(String UserContact) {
            this.UserContact = UserContact;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getLang() {
            return Lang;
        }

        public void setLang(String Lang) {
            this.Lang = Lang;
        }

        public String getSignatureType() {
            return SignatureType;
        }

        public void setSignatureType(String SignatureType) {
            this.SignatureType = SignatureType;
        }

        public String getSignature() {
            return Signature;
        }

        public void setSignature(String Signature) {
            this.Signature = Signature;
        }

        public String getResponseURL() {
            return ResponseURL;
        }

        public void setResponseURL(String ResponseURL) {
            this.ResponseURL = ResponseURL;
        }

        public String getBackendURL() {
            return BackendURL;
        }

        public void setBackendURL(String BackendURL) {
            this.BackendURL = BackendURL;
        }
    }
}
