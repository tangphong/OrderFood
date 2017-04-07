package com.example.tangphong_pc.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tangphong_pc.orderfood.DTO.BanAnDTO;
import com.example.tangphong_pc.orderfood.Databasse.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangPhong_PC on 4/4/2017.
 */

public class BanAnDAO {

    SQLiteDatabase database;

    public BanAnDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemBanAn(String tenBanAn) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN, tenBanAn);
        contentValues.put(CreateDatabase.TB_BANAN_TRANGTHAI, "false");
        long ktr = database.insert(CreateDatabase.TB_BANAN, null, contentValues);
        if (ktr != 0) {
            return true;
        }
        return false;

    }

    public List<BanAnDTO> LayDanhSachBanAn() {
        List<BanAnDTO> list = new ArrayList<BanAnDTO>();
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_BANAN;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //  lấy dữ liệu ra đã
            BanAnDTO banAnDTO = new BanAnDTO();
            banAnDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_MABAN)));
            banAnDTO.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TENBAN)));
            list.add(banAnDTO);
            cursor.moveToNext();
        }
        return list;
    }

}
