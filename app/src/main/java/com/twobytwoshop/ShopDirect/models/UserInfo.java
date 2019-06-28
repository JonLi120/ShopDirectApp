package com.twobytwoshop.ShopDirect.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserInfo {

    /**
     * uuid : 10005252
     * level : premium
     * sponsor : 10004436
     * name : FENG SUN SUN
     * email : 123@cc.cc
     * NRIC_Passport_No : 760805016926
     * gender : M
     * marital : S
     * birthday : 1922-12-12
     * wechatID : www1111
     * Home_Nos : 222222222
     * Office_Nos : 33333333
     * Mobile_Nos : 44444444
     * country :
     * state : 12
     * postcode : 12341
     * address : 中正1段
     * PCP : 0
     * accumulate_CP : 316294
     * Bank_Name : PUBLIC BANK BERHAD
     * Account_No : 4869001923
     * spouse_name : TEY LAI HUAT
     * spouse_ic : 680726106253
     * spouse_birthday : 1968-07-26
     * beneficiary_name : TEY LAI HUAT
     * beneficiary_ic : 680726-10-6253
     * beneficiary_Relationship : HUSBAND
     * point : 47685
     * e_wallet : 49229.8
     * bonus : 0
     * spread : 0
     * cp : 0
     * mdid : 175
     * CODE : SMS009
     */

    @PrimaryKey
    @NonNull
    private String uuid;
    private String level;
    private String sponsor;
    private String name;
    private String email;
    private String NRIC_Passport_No;
    private String gender;
    private String marital;
    private String birthday;
    private String wechatID;
    private String Home_Nos;
    private String Office_Nos;
    private String Mobile_Nos;
    private String country;
    private String state;
    private String postcode;
    private String address;
    private String PCP;
    private String accumulate_CP;
    private String Bank_Name;
    private String Account_No;
    private String spouse_name;
    private String spouse_ic;
    private String spouse_birthday;
    private String beneficiary_name;
    private String beneficiary_ic;
    private String beneficiary_Relationship;
    private int point;
    private String e_wallet;
    private String bonus;
    private String spread;
    private String cp;
    private String mdid;
    private String CODE;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNRIC_Passport_No() {
        return NRIC_Passport_No;
    }

    public void setNRIC_Passport_No(String NRIC_Passport_No) {
        this.NRIC_Passport_No = NRIC_Passport_No;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWechatID() {
        return wechatID;
    }

    public void setWechatID(String wechatID) {
        this.wechatID = wechatID;
    }

    public String getHome_Nos() {
        return Home_Nos;
    }

    public void setHome_Nos(String Home_Nos) {
        this.Home_Nos = Home_Nos;
    }

    public String getOffice_Nos() {
        return Office_Nos;
    }

    public void setOffice_Nos(String Office_Nos) {
        this.Office_Nos = Office_Nos;
    }

    public String getMobile_Nos() {
        return Mobile_Nos;
    }

    public void setMobile_Nos(String Mobile_Nos) {
        this.Mobile_Nos = Mobile_Nos;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPCP() {
        return PCP;
    }

    public void setPCP(String PCP) {
        this.PCP = PCP;
    }

    public String getAccumulate_CP() {
        return accumulate_CP;
    }

    public void setAccumulate_CP(String accumulate_CP) {
        this.accumulate_CP = accumulate_CP;
    }

    public String getBank_Name() {
        return Bank_Name;
    }

    public void setBank_Name(String Bank_Name) {
        this.Bank_Name = Bank_Name;
    }

    public String getAccount_No() {
        return Account_No;
    }

    public void setAccount_No(String Account_No) {
        this.Account_No = Account_No;
    }

    public String getSpouse_name() {
        return spouse_name;
    }

    public void setSpouse_name(String spouse_name) {
        this.spouse_name = spouse_name;
    }

    public String getSpouse_ic() {
        return spouse_ic;
    }

    public void setSpouse_ic(String spouse_ic) {
        this.spouse_ic = spouse_ic;
    }

    public String getSpouse_birthday() {
        return spouse_birthday;
    }

    public void setSpouse_birthday(String spouse_birthday) {
        this.spouse_birthday = spouse_birthday;
    }

    public String getBeneficiary_name() {
        return beneficiary_name;
    }

    public void setBeneficiary_name(String beneficiary_name) {
        this.beneficiary_name = beneficiary_name;
    }

    public String getBeneficiary_ic() {
        return beneficiary_ic;
    }

    public void setBeneficiary_ic(String beneficiary_ic) {
        this.beneficiary_ic = beneficiary_ic;
    }

    public String getBeneficiary_Relationship() {
        return beneficiary_Relationship;
    }

    public void setBeneficiary_Relationship(String beneficiary_Relationship) {
        this.beneficiary_Relationship = beneficiary_Relationship;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getE_wallet() {
        return e_wallet;
    }

    public void setE_wallet(String e_wallet) {
        this.e_wallet = e_wallet;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getMdid() {
        return mdid;
    }

    public void setMdid(String mdid) {
        this.mdid = mdid;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }
}
