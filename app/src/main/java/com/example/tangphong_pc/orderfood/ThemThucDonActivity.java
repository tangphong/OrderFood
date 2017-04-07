package com.example.tangphong_pc.orderfood;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tangphong_pc.orderfood.Custom_Adapter.Adapter_HienThiLoaiMonAnSpiner;
import com.example.tangphong_pc.orderfood.DAO.LoaiMonAnDAO;
import com.example.tangphong_pc.orderfood.DAO.MonAnDAO;
import com.example.tangphong_pc.orderfood.DTO.LoaiMonAnDTO;
import com.example.tangphong_pc.orderfood.DTO.MonAnDTO;

import java.io.IOException;
import java.util.List;

/**
 * Created by TangPhong_PC on 4/5/2017.
 */

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {
    public static int REQUESCODE_LoaiThucDon = 113;
    public static int REQUESCODE_MonAN = 123;
    String duongDan = "";
    Button imageButton;
    Spinner spinner;
    ImageView img;
    Button btnDongy, btnThoat;
    EditText txtTenMonAn, txtGiaMon;

    LoaiMonAnDAO loaiMonAnDAO;
    List<LoaiMonAnDTO> loaiMonAnDTOs;
    Adapter_HienThiLoaiMonAnSpiner adapter;
    MonAnDAO monAnDAO;
    MonAnDTO monAnDTO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themthucdon);

        imageButton = (Button) findViewById(R.id.ImbtnThemLoaiThucDon);
        spinner = (Spinner) findViewById(R.id.spiner);
        img = (ImageView) findViewById(R.id.imgHinhThucDon);
        btnDongy = (Button) findViewById(R.id.btndongy);
        btnThoat = (Button) findViewById(R.id.btnthoat);
        txtGiaMon = (EditText) findViewById(R.id.edtgiatien);
        txtTenMonAn = (EditText) findViewById(R.id.edtTenMon);


        btnDongy.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
        img.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        loaiMonAnDAO = new LoaiMonAnDAO(this);
        monAnDAO = new MonAnDAO(this);
        monAnDTO = new MonAnDTO();
        HienThiSpinerLoaiMonAn();

    }

    public void HienThiSpinerLoaiMonAn() {
        loaiMonAnDTOs = loaiMonAnDAO.DanhSachLoaiMonAn();
        adapter = new Adapter_HienThiLoaiMonAnSpiner(ThemThucDonActivity.this, loaiMonAnDTOs, R.layout.layout_custom_spiner_loaithucdon);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ImbtnThemLoaiThucDon:
                Intent intent = new Intent(ThemThucDonActivity.this, ThemLoaiThucDonActivity.class);
                startActivityForResult(intent, REQUESCODE_LoaiThucDon);
                break;
            case R.id.imgHinhThucDon:
                Intent imoHinh = new Intent();
                imoHinh.setType("image/*");
                imoHinh.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(imoHinh, "Chọn Ảnh Món"), REQUESCODE_MonAN);
                break;
            case R.id.btnthoat:
                finish();
                break;
            case R.id.btndongy:
                setBtnDongy();
                break;
        }
    }

    private void setBtnDongy() {
        int vitri = spinner.getSelectedItemPosition();
        int maloai = loaiMonAnDTOs.get(vitri).getMaLoai();
        String tenmon = txtTenMonAn.getText().toString();
        String giaTien = txtGiaMon.getText().toString();
        if (tenmon != null && giaTien != null && !tenmon.equals("") && !giaTien.equals("")) {
            monAnDTO.setMaLoai(maloai);
            monAnDTO.setGiaTien(giaTien);
            monAnDTO.setHinhAnh(duongDan);
            monAnDTO.setTenMonAn(tenmon);
            boolean ktra = monAnDAO.ThemMonAn(monAnDTO);
            if (ktra) {
                Toast.makeText(this, R.string.themthanhcong, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.themthatbai, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.nhapdulieudaydu, Toast.LENGTH_SHORT).show();
        }
        Log.d("vitri", loaiMonAnDTOs.get(vitri).getMaLoai() + "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESCODE_LoaiThucDon) {
            if (resultCode == Activity.RESULT_OK) {
                Intent dl = data;
                boolean ktr = dl.getBooleanExtra("kiemtraloaithucdon", false);
                if (ktr) {
                    HienThiSpinerLoaiMonAn();
                    Toast.makeText(this, R.string.themthanhcong, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.themthatbai, Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == REQUESCODE_MonAN) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    img.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                duongDan = data.getData().toString();
                // Log.d("kq", data.getData() + "");
                // img.setImageURI(data.getData());
            }
        }
    }
}
