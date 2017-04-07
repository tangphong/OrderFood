package com.example.tangphong_pc.orderfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tangphong_pc.orderfood.DAO.NhanVienDAO;
import com.example.tangphong_pc.orderfood.DTO.NhanVienDTO;
import com.example.tangphong_pc.orderfood.Databasse.CreateDatabase;
import com.example.tangphong_pc.orderfood.FragMent.DatePickerFragment;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    EditText edTENDN, edMatKhau, edNGAYSINH, edCMND;
    Button btnDongY, btnThoat;
    RadioGroup grGioiTinh;
    String gioiTinh;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky_main);
        CreateDatabase createDatabase = new CreateDatabase(this);
        createDatabase.getWritableDatabase();
        edTENDN = (EditText) findViewById(R.id.txtTENDN);
        edMatKhau = (EditText) findViewById(R.id.txtMatKhau);
        edNGAYSINH = (EditText) findViewById(R.id.txtNGAYSINH);
        edCMND = (EditText) findViewById(R.id.txtCMND);

        btnDongY = (Button) findViewById(R.id.bntDONGY);
        btnThoat = (Button) findViewById(R.id.btnTHOAT);
        btnDongY.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
        grGioiTinh = (RadioGroup) findViewById(R.id.rgGioiTinh);

        edNGAYSINH.setOnFocusChangeListener(this);

        nhanVienDAO = new NhanVienDAO(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bntDONGY:
                btnDONGY_Click();
                break;
            case R.id.btnTHOAT:
                finish();
                break;
        }
    }

    private void btnDONGY_Click() {
        String sTenDN = edTENDN.getText().toString();
        String sMatKhau = edMatKhau.getText().toString();
        switch (grGioiTinh.getCheckedRadioButtonId()) {
            case R.id.rdNAM:
                gioiTinh = "Nam";
                break;
            case R.id.rdNu:
                gioiTinh = "Nữ";
                break;
        }
        String ngaySinh = edNGAYSINH.getText().toString();
        int cmnd = Integer.parseInt(edCMND.getText().toString());
        if (sTenDN.equals("")|| sTenDN == null ||cmnd<0) {
            Toast.makeText(DangKyActivity.this, getResources().getString(R.string.nhaptendangnhap).toString(), Toast.LENGTH_SHORT).show();
        } else {
            NhanVienDTO nhanVienDTO = new NhanVienDTO();
            nhanVienDTO.setCMND(cmnd);
            nhanVienDTO.setGIOITINH(gioiTinh);
            nhanVienDTO.setMATKHAU(sMatKhau);
            nhanVienDTO.setNGAYSINH(ngaySinh);
            nhanVienDTO.setTENDN(sTenDN);
         long ktr=   nhanVienDAO.ThemNhanVien(nhanVienDTO);
            if (ktr!=0){
                Toast.makeText(this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
            }
        }


    }
// nếu focus vào editext thì show dialog
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id) {
            case R.id.txtNGAYSINH:
                if (hasFocus) {
                    //tạo fragment lên cho chọn ngày sinh:
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(), "NgaySinh");
                }
                break;
        }
    }
}
