package com.example.tangphong_pc.orderfood.Custom_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tangphong_pc.orderfood.DTO.BanAnDTO;
import com.example.tangphong_pc.orderfood.R;

import java.util.List;

/**
 * Created by TangPhong_PC on 4/4/2017.
 */

public class BanAn_Adapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    int layout;
    List<BanAnDTO> banAnDTOList;
    ViewHolderBanAn viewHolderBanAn;


    public BanAn_Adapter(Context context, int layout, List<BanAnDTO> banAnDTOList) {
        this.context = context;
        this.layout = layout;
        this.banAnDTOList = banAnDTOList;
    }

    @Override
    public int getCount() {
        return banAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return banAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return banAnDTOList.get(position).getMaBan();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = convertView;
        if (myView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myView = inflater.inflate(R.layout.layout_custom_itemgridview, parent, false);
            viewHolderBanAn = new ViewHolderBanAn();
            viewHolderBanAn.imBanan = (ImageView) myView.findViewById(R.id.img_banAn);
            viewHolderBanAn.imthanhtoan = (ImageView) myView.findViewById(R.id.img_thanhtoan);
            viewHolderBanAn.imgoimon = (ImageView) myView.findViewById(R.id.img_goimon);
            viewHolderBanAn.imanBt = (ImageView) myView.findViewById(R.id.img_anBt);
            viewHolderBanAn.textTenBan = (TextView) myView.findViewById(R.id.txtBanAn);
            myView.setTag(viewHolderBanAn);
        } else {
            viewHolderBanAn = (ViewHolderBanAn) myView.getTag();
        }

        if (banAnDTOList.get(position).isDuocClick()) {
            viewHolderBanAn.imgoimon.setVisibility(View.VISIBLE);
            viewHolderBanAn.imthanhtoan.setVisibility(View.VISIBLE);
            viewHolderBanAn.imanBt.setVisibility(View.VISIBLE);
        } else {
            viewHolderBanAn.imgoimon.setVisibility(View.INVISIBLE);
            viewHolderBanAn.imthanhtoan.setVisibility(View.INVISIBLE);
            viewHolderBanAn.imanBt.setVisibility(View.INVISIBLE);
        }
        BanAnDTO banAnDTO = banAnDTOList.get(position);
        viewHolderBanAn.textTenBan.setText(banAnDTO.getTenBan());

        viewHolderBanAn.imBanan.setOnClickListener(this);
        viewHolderBanAn.imanBt.setOnClickListener(this);
        viewHolderBanAn.imBanan.setTag(position);
        return myView;
    }


    public class ViewHolderBanAn {
        ImageView imBanan, imthanhtoan, imgoimon, imanBt;
        TextView textTenBan;
    }

    @Override
    public void onClick(View v) {
        viewHolderBanAn = (ViewHolderBanAn) ((View) v.getParent()).getTag();
        switch (v.getId()) {
            case R.id.img_banAn:
                viewHolderBanAn.imgoimon.setVisibility(View.VISIBLE);
                viewHolderBanAn.imthanhtoan.setVisibility(View.VISIBLE);
                viewHolderBanAn.imanBt.setVisibility(View.VISIBLE);
                int vitri = (int) v.getTag();
                banAnDTOList.get(vitri).setDuocClick(true);
                break;
            case R.id.img_anBt:
                Toast.makeText(context, "Clikc", Toast.LENGTH_SHORT).show();
                viewHolderBanAn.imgoimon.setVisibility(View.INVISIBLE);
                viewHolderBanAn.imthanhtoan.setVisibility(View.INVISIBLE);
                viewHolderBanAn.imanBt.setVisibility(View.INVISIBLE);
                break;
        }

    }
}
