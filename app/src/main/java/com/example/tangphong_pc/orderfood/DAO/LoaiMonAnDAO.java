package com.example.tangphong_pc.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tangphong_pc.orderfood.DTO.LoaiMonAnDTO;
import com.example.tangphong_pc.orderfood.Databasse.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangPhong_PC on 4/5/2017.
 */

public class LoaiMonAnDAO {
    SQLiteDatabase database;

    public LoaiMonAnDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemLoaiMonAn(String tenLoai) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_LOAIMONAN_TENLOAI, tenLoai);
        long ktr = database.insert(CreateDatabase.TB_LOAIMONAN, null, contentValues);
        if (ktr != 0) {
            return true;
        }
        return false;
    }

    public List<LoaiMonAnDTO> DanhSachLoaiMonAn() {
        List<LoaiMonAnDTO> loaiMonAnDTOs = new ArrayList<LoaiMonAnDTO>();
        String truyVan = "SELECT * FROM " + CreateDatabase.TB_LOAIMONAN;
        Cursor cursor = database.rawQuery(truyVan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            LoaiMonAnDTO loaiMonAnDTO = new LoaiMonAnDTO();
            loaiMonAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_LOAIMONAN_MALOAI)));
            loaiMonAnDTO.setTenLoai(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_LOAIMONAN_TENLOAI)));
            loaiMonAnDTOs.add(loaiMonAnDTO);
            cursor.moveToNext();
        }
        return loaiMonAnDTOs;
    }

    public String DanhSachLoaiMonAnCoHinh(int maLoai) {

        String hinhanh = "";

        String truyVan = "SELECT * FROM " + CreateDatabase.TB_MONAN + " WHERE " + CreateDatabase.TB_MONAN_MALOAI +
                " = '" + maLoai + "'" + " AND " + CreateDatabase.TB_MONAN_HINHANH + " !='' ORDER BY " + CreateDatabase.TB_MONAN_MAMON + " LIMIT 1 ";

        Cursor cursor = database.rawQuery(truyVan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            hinhanh = (cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANH)));
            cursor.moveToNext();
        }
        return hinhanh;
    }


}
