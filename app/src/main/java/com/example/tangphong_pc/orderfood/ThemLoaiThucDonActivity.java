package com.example.tangphong_pc.orderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tangphong_pc.orderfood.DAO.LoaiMonAnDAO;

/**
 * Created by TangPhong_PC on 4/5/2017.
 */

public class ThemLoaiThucDonActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDongY;
    EditText edtTenLoai;
    LoaiMonAnDAO loaiMonAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themloaithucdon);
        btnDongY = (Button) findViewById(R.id.btnThemLoaiThucDon);
        edtTenLoai = (EditText) findViewById(R.id.edtThemLoaiThucDon);

        btnDongY.setOnClickListener(this);
        //getActionBar().setTitle("A");

        loaiMonAnDAO = new LoaiMonAnDAO(this);
    }

    @Override
    public void onClick(View v) {
        String tenloai = edtTenLoai.getText().toString();
        if (tenloai.equals("")) {
            Toast.makeText(this, R.string.nhapdulieudaydu, Toast.LENGTH_SHORT).show();
        } else {
            boolean ktr = loaiMonAnDAO.ThemLoaiMonAn(tenloai);
            Toast.makeText(this, R.string.themthanhcong, Toast.LENGTH_SHORT).show();
            Intent intent =  new Intent();
            intent.putExtra("kiemtraloaithucdon",ktr);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }

    }
}
