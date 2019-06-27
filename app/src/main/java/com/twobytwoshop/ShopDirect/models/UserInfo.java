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
     * email : joseph.ncare@gmail.com
     * Mobile_Nos : 012-3789887
     * country :
     * state : 11
     * postcode : 84000
     * address : 165, JLN MAWAR 8 TAMAN MAWAR 84000 MUAR JOHOR
     * PCP : 0
     * accumulate_CP : 316294
     * point : 47685
     * e_wallet : 0
     * bonus : 477
     * spread : 1.7
     * cp : 3497
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
    private String Mobile_Nos;
    private String country;
    private String state;
    private String postcode;
    private String address;
    private String PCP;
    private String accumulate_CP;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "uuid='" + uuid + '\'' +
                ", level='" + level + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", Mobile_Nos='" + Mobile_Nos + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", postcode='" + postcode + '\'' +
                ", address='" + address + '\'' +
                ", PCP='" + PCP + '\'' +
                ", accumulate_CP='" + accumulate_CP + '\'' +
                ", point=" + point +
                ", e_wallet='" + e_wallet + '\'' +
                ", bonus='" + bonus + '\'' +
                ", spread='" + spread + '\'' +
                ", cp='" + cp + '\'' +
                ", mdid='" + mdid + '\'' +
                ", CODE='" + CODE + '\'' +
                '}';
    }
}
