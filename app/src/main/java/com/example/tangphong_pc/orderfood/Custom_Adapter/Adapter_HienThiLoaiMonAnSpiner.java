package com.example.tangphong_pc.orderfood.Custom_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tangphong_pc.orderfood.DAO.LoaiMonAnDAO;
import com.example.tangphong_pc.orderfood.DTO.LoaiMonAnDTO;
import com.example.tangphong_pc.orderfood.R;

import java.util.List;

/**
 * Created by TangPhong_PC on 4/5/2017.
 */

public class Adapter_HienThiLoaiMonAnSpiner extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiMonAnDTO> loaiMonAnDTOs;
    ViewHolder_LoaiMonAn viewHolder_loaiMonAn;

    public Adapter_HienThiLoaiMonAnSpiner(Context context, List<LoaiMonAnDTO> loaiMonAnDTOs, int layout) {
        this.context = context;
        this.loaiMonAnDTOs = loaiMonAnDTOs;
        this.layout = layout;
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
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            viewHolder_loaiMonAn = new ViewHolder_LoaiMonAn();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_custom_spiner_loaithucdon, parent, false);
            viewHolder_loaiMonAn.txtTenLoai = (TextView) view.findViewById(R.id.tvTenLoai);
            view.setTag(viewHolder_loaiMonAn);
        } else {
            viewHolder_loaiMonAn = (ViewHolder_LoaiMonAn) view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO = loaiMonAnDTOs.get(position);
        viewHolder_loaiMonAn.txtTenLoai.setText(loaiMonAnDTO.getTenLoai());
        viewHolder_loaiMonAn.txtTenLoai.setTag(loaiMonAnDTO.getMaLoai());
        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            viewHolder_loaiMonAn = new ViewHolder_LoaiMonAn();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_custom_spiner_loaithucdon, parent, false);
            viewHolder_loaiMonAn.txtTenLoai = (TextView) view.findViewById(R.id.tvTenLoai);
            view.setTag(viewHolder_loaiMonAn);
        } else {
            viewHolder_loaiMonAn = (ViewHolder_LoaiMonAn) view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO = loaiMonAnDTOs.get(position);
        viewHolder_loaiMonAn.txtTenLoai.setText(loaiMonAnDTO.getTenLoai());
        viewHolder_loaiMonAn.txtTenLoai.setTag(loaiMonAnDTO.getMaLoai());
        return view;
    }

    public class ViewHolder_LoaiMonAn {
        TextView txtTenLoai;
    }
}
