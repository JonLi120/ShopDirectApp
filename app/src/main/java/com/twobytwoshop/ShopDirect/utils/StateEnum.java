package com.twobytwoshop.ShopDirect.utils;

import java.util.ArrayList;

public enum StateEnum {
    JOHOR(11),KEDAH(1),KELANTAN(2),KUALA_LUMPUR(16),LABUAN(14),MELAKA(10),NEGERI_SEMBILAN(9),
    PAHANG(6),PERAK(7),PERLIS(3),PULAU_LANGKAWI(15),PULAU_PINANG(4),PUTRAJAYA(20),SABAH(12),SARAWAK(13)
    ,SELANGOR(8),TERENGGANU(5),VIC(21),OTHERS(19);

    private int num;

    StateEnum(int num) {
        this.num = num;
    }

    public static int getStateNumber(String name) {
        name = name.replace(" ", "_");
        return valueOf(name).num;
    }

    public static ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        for (StateEnum se :values()) {
            list.add(se.name().replace("_", " "));
        }
        return list;
    }
}
