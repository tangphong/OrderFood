package com.example.tangphong_pc.orderfood.Custom_Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tangphong_pc.orderfood.DAO.LoaiMonAnDAO;
import com.example.tangphong_pc.orderfood.DTO.LoaiMonAnDTO;
import com.example.tangphong_pc.orderfood.R;

import java.util.List;

/**
 * Created by TangPhong_PC on 4/7/2017.
 */

public class Adapter_HienThiLoaiMonAnThucDon extends BaseAdapter {
    int layout;
    List<LoaiMonAnDTO> loaiMonAnDTOs;
    Context context;
    ViewHoler viewHoler;
    LoaiMonAnDAO loaiMonAnDAO;

    public Adapter_HienThiLoaiMonAnThucDon(int layout, List<LoaiMonAnDTO> loaiMonAnDTOs, Context context) {
        this.layout = layout;
        this.loaiMonAnDTOs = loaiMonAnDTOs;
        this.context = context;
        loaiMonAnDAO = new LoaiMonAnDAO(context);
    }

    @Override
    public int getCount() {
        return loaiMonAnDTOs.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiMonAnDTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return loaiMonAnDTOs.get(position).getMaLoai();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            viewHoler = new ViewHoler();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, parent, false);

            viewHoler.img_loaimonan = (ImageView) view.findViewById(R.id.img_hienthimonan);
            viewHoler.textView_TenLoaiMonAn = (TextView) view.findViewById(R.id.txt_tenloaiMonAn);

            view.setTag(viewHoler);

        } else {
            viewHoler = (ViewHoler) view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO = loaiMonAnDTOs.get(position);
        int maLoai = loaiMonAnDTO.getMaLoai();
        String hinhanh = loaiMonAnDAO.DanhSachLoaiMonAnCoHinh(maLoai);
        Uri uri = Uri.parse(hinhanh);

        viewHoler.img_loaimonan.setImageURI(uri);
        viewHoler.textView_TenLoaiMonAn.setText(loaiMonAnDTO.getTenLoai());

        return view;
    }

    public class ViewHoler {
        ImageView img_loaimonan;
        TextView textView_TenLoaiMonAn;
    }
}
