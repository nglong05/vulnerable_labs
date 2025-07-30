package com.example.cc5lab;

import java.util.HashMap;
import java.util.Map;

public enum Drink {
    // Kem Mixue
    SUPER_SUNDAE_SOCOLA("Super Sundae Socola"),
    SUPER_SUNDAE("Super Sundae"),
    TRAN_CHAU_DUONG_DEN("Trân Châu Đường Đen"),
    SUPER_SUNDAE_XOAI("Super Sundae Xoài"),
    SUPER_SUNDAE_DAU_TAY("Super Sundae Dâu Tây"),
    HONG_TRA_KEM("Hồng Trà Kem"),
    SUA_KEM_LAC_DAO("Sữa Kem Lạc Đào"),
    SUA_KEM_LAC_DAU_TAY("Sữa Kem Lạc Dâu Tây"),
    TRA_KEM_BON_MUA("Trà Kem Bốn Mùa"),
    SUPER_SUNDAAE("Super Sundae"),
    LO_HOI_KEM_CAM_LOC_XOAY("Lô Hội Kem Cam Lốc Xoáy"),

    // Trà Mixue
    TRA_HOA_QUA("Trà Hoa Quả"),
    TRA_DAO_BON_MUA("Trà Đào Bốn Mùa"),
    NUOC_CHANH_TUOI_LANH("Nước Chanh Tươi Lạnh"),
    TRA_CHANH_LO_HOI("Trà Chanh Lô Hội"),
    DUONG_CHI_CAM_LO("Đường Chi Cam Lộ"),
    HONG_TRA_CHANH("Hồng Trà Chanh"),
    TRA_DAO_BIG_SIZE("Trà Đào Big Size"),
    HONG_TRA_MAT_ONG("Hồng Trà Mật Ong"),
    HONG_TRA_KEM_MIXUE("Hồng Trà Kem Mixue"),
    TRA_DAO_TU_KY_XUAN("Trà Đào Tư Kỳ Xuân"),
    TRA_O_LONG_BON_MUA("Trà Ô Long Bốn Mùa"),
    TRA_O_LONG_KIWI("Trà Ô Long Kiwi"),

    // Trà sữa Mixue
    TRA_SUA_DUONG_CHI_CAM_LO("Trà Sữa Đường Chi Cam Lộ"),
    TRA_SUA_3Q("Trà Sữa 3Q"),
    TRA_SUA_TRAN_CHAU_DUONG_DEN("Trà Sữa Trân Châu Đường Đen"),
    TRA_SUA_BA_VUONG("Trà Sữa Bá Vương"),
    TRA_SUA_NUONG("Trà Sữa Nướng"),
    TRA_SUA_THACH_DUA("Trà Sữa Thạch Dừa"),
    TRA_SUA_2J("Trà Sữa 2J"),
    TRA_SUA_TRAN_CHAU("Trà Sữa Trân Châu");

    private final String displayName;

    Drink(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    // 🔥 Map để tra ngược từ displayName -> enum
    private static final Map<String, Drink> DISPLAY_NAME_MAP = new HashMap<>();

    static {
        for (Drink d : values()) {
            DISPLAY_NAME_MAP.put(d.displayName, d);
        }
    }

    public static Drink fromDisplayName(String name) {
        Drink d = DISPLAY_NAME_MAP.get(name);
        if (d == null) throw new IllegalArgumentException("Không tồn tại đồ uống tên: " + name);
        return d;
    }
}

