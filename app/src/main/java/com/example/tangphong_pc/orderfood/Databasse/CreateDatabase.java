package com.example.tangphong_pc.orderfood.Databasse;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TangPhong_PC on 4/2/2017.
 */

public class CreateDatabase extends SQLiteOpenHelper {

    public static String TB_NhANVIEN = "NHANVIEN";
    public static String TB_MONAN = "TB_MONAN";
    public static String TB_LOAIMONAN = "TB_LOAIMONAN";
    public static String TB_BANAN = "TB_BANAN";
    public static String TB_GOIMON = "TB_GOIMON";
    public static String TB_CHITIETGOIMON = "TB_CHITIETGOIMON";

    public static String TB_NhANVIEN_MANV = "MANV";
    public static String TB_NhANVIEN_TEDN = "TENDANGNHAP";
    public static String TB_NhANVIEN_MATKHAU = "MATKHAU";
    public static String TB_NhANVIEN_GIOITINH = "GIOITINH";
    public static String TB_NhANVIEN_NGAYSINH = "NGAYSINH";
    public static String TB_NhANVIEN_CMND = "CMND";


    public static String TB_MONAN_MAMON = "MAMON";
    public static String TB_MONAN_TENMONAN = "TENMONAN";
    public static String TB_MONAN_GIATIEN = "GIATIEN";
    public static String TB_MONAN_MALOAI = "MALOAI";
    public static String TB_MONAN_HINHANH = "HINHANH";

    public static String TB_LOAIMONAN_MALOAI = "MALOAI";
    public static String TB_LOAIMONAN_TENLOAI = "TENLOAI";


    public static String TB_BANAN_MABAN = "MABAN";
    public static String TB_BANAN_TENBAN = "TENBAN";
    public static String TB_BANAN_TRANGTHAI = "TINHTRANG";


    public static String TB_GOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_GOIMON_MANV = "MANV";
    public static String TB_GOIMON_NGAYGOI = "NGAYGOI";
    public static String TB_GOIMON_TINHTRANG = "TINHTRANG";
    public static String TB_GOIMON_MABAN = "MABAN";


    public static String TB_CHITIETGOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_CHITIETGOIMON_MAMONAN = "MAMONAN";
    public static String TB_CHITIETGOIMON_SOLUONG = "SOLUONG";


    public CreateDatabase(Context context) {
        super(context, "OrderFood", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbNHANVIEN = "CREATE TABLE " + TB_NhANVIEN + "( " + TB_NhANVIEN_MANV + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_NhANVIEN_TEDN + " TEXT, " + TB_NhANVIEN_MATKHAU + " TEXT, " + TB_NhANVIEN_GIOITINH + " TEXT, "
                + TB_NhANVIEN_NGAYSINH + " TEXT, " + TB_NhANVIEN_CMND + " INTEGER " + ")";
        String tbBANAN = " CREATE TABLE " + TB_BANAN + "( " + TB_BANAN_MABAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_BANAN_TENBAN + " TEXT, " + TB_BANAN_TRANGTHAI + " TEXT " + " )";
        String tbMONAN = "CREATE TABLE " + TB_MONAN + " ( " + TB_MONAN_MAMON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_MONAN_TENMONAN + " TEXT, " + TB_MONAN_GIATIEN + " TEXT, " + TB_MONAN_MALOAI + " TEXT, " + TB_MONAN_HINHANH + " TEXT " + " )";
        String tbLOAIMONAN = "CREATE TABLE " + TB_LOAIMONAN + "(" + TB_LOAIMONAN_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TB_LOAIMONAN_TENLOAI + " TEXT " + " )";
        String tbGOIMON = "CREATE TABLE " + TB_GOIMON + " ( " + TB_GOIMON_MAGOIMON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_GOIMON_MABAN + " INTEGER, " + TB_GOIMON_MANV + " INTEGER, " + TB_GOIMON_NGAYGOI + " TEXT, " + TB_GOIMON_TINHTRANG + " TEXT " + " )";
        String tbCHITIETGOIMON = "CREATE TABLE " + TB_CHITIETGOIMON + " ( " + TB_CHITIETGOIMON_MAGOIMON + " INTEGER, " + TB_CHITIETGOIMON_MAMONAN + " INTEGER, "
                + TB_CHITIETGOIMON_SOLUONG + " INTEGER, "
                + " PRIMARY KEY (" + TB_CHITIETGOIMON_MAMONAN + " , " + TB_CHITIETGOIMON_MAGOIMON + " ) ) ";

        db.execSQL(tbNHANVIEN);
        db.execSQL(tbBANAN);
        db.execSQL(tbCHITIETGOIMON);
        db.execSQL(tbGOIMON);
        db.execSQL(tbLOAIMONAN);
        db.execSQL(tbMONAN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // phương thức mở kết nối dt
    public SQLiteDatabase open() {
        return this.getWritableDatabase();
    }
}
