package com.example.tangphong_pc.orderfood.Custom_Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tangphong_pc.orderfood.DTO.MonAnDTO;
import com.example.tangphong_pc.orderfood.R;

import java.util.List;

/**
 * Created by TangPhong_PC on 4/7/2017.
 */

public class AdapterHienThiDanhSachMonAn extends BaseAdapter {
    Context context;
    int layOut;
    List<MonAnDTO> monAnDTOs;
    ViewHolderHienThiDanhSachMonAn viewHolderHienThiDanhSachMonAn;

    public AdapterHienThiDanhSachMonAn(Context context, int layOut, List<MonAnDTO> monAnDTOs) {
        this.context = context;
        this.layOut = layOut;
        this.monAnDTOs = monAnDTOs;
    }

    @Override
    public int getCount() {
        return monAnDTOs.size();
    }

    @Override
    public Object getItem(int position) {
        return monAnDTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return monAnDTOs.get(position).getMaMonAn();
    }

    public class ViewHolderHienThiDanhSachMonAn {
        ImageView img_anhmonan;
        TextView txtTenMonAn, txtGiaTien;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderHienThiDanhSachMonAn = new ViewHolderHienThiDanhSachMonAn();
            view = inflater.inflate(layOut, parent, false);

            viewHolderHienThiDanhSachMonAn.img_anhmonan = (ImageView) view.findViewById(R.id.img_hienthidstenmonan);
            viewHolderHienThiDanhSachMonAn.txtGiaTien = (TextView) view.findViewById(R.id.txt_giatien);
            viewHolderHienThiDanhSachMonAn.txtTenMonAn = (TextView) view.findViewById(R.id.txt_tenmonan);
            view.setTag(viewHolderHienThiDanhSachMonAn);
        } else {
            viewHolderHienThiDanhSachMonAn = (ViewHolderHienThiDanhSachMonAn) view.getTag();
        }

        MonAnDTO monAnDTO = monAnDTOs.get(position);
        String hinhanh = monAnDTO.getHinhAnh().toString();
        if (hinhanh == null || hinhanh.equals("")) {
            viewHolderHienThiDanhSachMonAn.img_anhmonan.setImageResource(R.drawable.logodangnhap);
        } else {
            Uri uri = Uri.parse(hinhanh);
            viewHolderHienThiDanhSachMonAn.img_anhmonan.setImageURI(uri);
        }

        viewHolderHienThiDanhSachMonAn.txtGiaTien.setText("Giá: " + monAnDTO.getGiaTien());
        viewHolderHienThiDanhSachMonAn.txtTenMonAn.setText(monAnDTO.getTenMonAn());


        return view;
    }
}
