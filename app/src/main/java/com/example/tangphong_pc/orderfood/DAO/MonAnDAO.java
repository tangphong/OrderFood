package com.example.tangphong_pc.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tangphong_pc.orderfood.DTO.MonAnDTO;
import com.example.tangphong_pc.orderfood.Databasse.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangPhong_PC on 4/6/2017.
 */

public class MonAnDAO {
    SQLiteDatabase database;

    public MonAnDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemMonAn(MonAnDTO monAnDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_MONAN_TENMONAN, monAnDTO.getTenMonAn());
        contentValues.put(CreateDatabase.TB_MONAN_MALOAI, monAnDTO.getMaLoai());
        contentValues.put(CreateDatabase.TB_MONAN_GIATIEN, monAnDTO.getGiaTien());
        contentValues.put(CreateDatabase.TB_MONAN_HINHANH, monAnDTO.getHinhAnh());
        long ktr = database.insert(CreateDatabase.TB_MONAN, null, contentValues);
        if (ktr != 0) {
            return true;
        } else return false;
    }

    public List<MonAnDTO> DanhSachMonAnTheoLoai(int maLoai) {

        List<MonAnDTO> monAnDTOs = new ArrayList<MonAnDTO>();
        String truyVan = "SELECT * FROM " + CreateDatabase.TB_MONAN + " WHERE " + CreateDatabase.TB_MONAN_MALOAI + " = '" + maLoai+"'";

        Cursor cursor = database.rawQuery(truyVan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MonAnDTO monAnDTO = new MonAnDTO();
            monAnDTO.setHinhAnh(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANH)));
            monAnDTO.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));
            monAnDTO.setGiaTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN)));
            monAnDTO.setMaMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MAMON)));
            monAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MALOAI)));
            monAnDTOs.add(monAnDTO);
            Log.d("dulieu",monAnDTO.getHinhAnh());
            cursor.moveToNext();
        }
        return monAnDTOs;
    }

}