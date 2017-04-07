package com.example.tangphong_pc.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tangphong_pc.orderfood.DAO.NhanVienDAO;

/**
 * Created by TangPhong_PC on 4/2/2017.
 */

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDangKy, btnDangNhap;
    EditText edTenDN, edMatKhau;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        btnDangNhap = (Button) findViewById(R.id.bntDangNhap);
        edTenDN = (EditText) findViewById(R.id.txtTENDN);
        edMatKhau = (EditText) findViewById(R.id.txtMatKhau);

        btnDangKy.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        // btnDangKy.setVisibility(View.GONE);
        nhanVienDAO = new NhanVienDAO(this);
        hienthiBTN();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangKy:
               setBtnDangKy();
                break;
            case R.id.bntDangNhap:
                // code kiểm tra đăng nhập
                setBtnDangNhap();
                break;
        }
    }

    private void setBtnDangKy() {
        Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
        startActivity(intent);
    }
    private  void setBtnDangNhap(){
        String tdn= edTenDN.getText().toString();
        String  mk = edMatKhau.getText().toString();
        boolean kt = nhanVienDAO.KiemTraDangNhap(tdn,mk);
       if (kt){
           Toast.makeText(this, "Đăng Nhâp Thành Công", Toast.LENGTH_SHORT).show();
           Intent iTrangChu= new Intent(DangNhapActivity.this,TrangChuAcitity.class);
            iTrangChu.putExtra("tendn",tdn);
            startActivity(iTrangChu);


       }
        else {
           Toast.makeText(this, "Đăng Nhâp Không Thành Công", Toast.LENGTH_SHORT).show();
       }
    }

    private void hienthiBTN() {
        if (nhanVienDAO.ktraNV()) {
            btnDangNhap.setVisibility(View.VISIBLE);
            btnDangKy.setVisibility(View.GONE);
        } else {
            btnDangNhap.setVisibility(View.GONE);
            btnDangKy.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hienthiBTN();
    }
}
