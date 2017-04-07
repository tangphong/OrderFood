package com.example.tangphong_pc.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tangphong_pc.orderfood.DTO.NhanVienDTO;
import com.example.tangphong_pc.orderfood.Databasse.CreateDatabase;

/**
 * Created by TangPhong_PC on 4/2/2017.
 */

public class NhanVienDAO {
    SQLiteDatabase database;

    public NhanVienDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemNhanVien(NhanVienDTO nhanVienDTO) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_NhANVIEN_TEDN, nhanVienDTO.getTENDN());
        contentValues.put(CreateDatabase.TB_NhANVIEN_MATKHAU, nhanVienDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TB_NhANVIEN_GIOITINH, nhanVienDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TB_NhANVIEN_NGAYSINH, nhanVienDTO.getNGAYSINH());
        contentValues.put(CreateDatabase.TB_NhANVIEN_CMND, nhanVienDTO.getCMND());
        long ktra = database.insert(CreateDatabase.TB_NhANVIEN, null, contentValues);
        return ktra;
    }

    public boolean ktraNV() {
        String query = "SELECT *FROM NHANVIEN";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
    }

    public boolean KiemTraDangNhap(String TDN, String MK) {
        String query1 = " SELECT * FROM "+CreateDatabase.TB_NhANVIEN +" WHERE " +
                CreateDatabase.TB_NhANVIEN_TEDN + " = " + " '" + TDN + "' " + " AND "
                +CreateDatabase.TB_NhANVIEN_MATKHAU +" = "+ " '" + MK + "' ";
        Cursor cursor = database.rawQuery(query1, null);
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
    }
}
