package com.example.tangphong_pc.orderfood.DTO;

/**
 * Created by TangPhong_PC on 4/4/2017.
 */

public class BanAnDTO  {
    int MaBan;
    String TenBan;

    public boolean isDuocClick() {
        return duocClick;
    }

    public void setDuocClick(boolean duocClick) {
        this.duocClick = duocClick;
    }

    boolean duocClick;

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String tenBan) {
        TenBan = tenBan;
    }
}
