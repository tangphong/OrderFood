package com.example.tangphong_pc.orderfood.FragMent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tangphong_pc.orderfood.Custom_Adapter.AdapterHienThiDanhSachMonAn;
import com.example.tangphong_pc.orderfood.DAO.LoaiMonAnDAO;
import com.example.tangphong_pc.orderfood.DAO.MonAnDAO;
import com.example.tangphong_pc.orderfood.DTO.MonAnDTO;
import com.example.tangphong_pc.orderfood.R;

import java.util.List;

/**
 * Created by TangPhong_PC on 4/7/2017.
 */

public class HienThiDanhSachMonAnTLFragment extends Fragment {
    GridView grv;
    AdapterHienThiDanhSachMonAn adapterHienThiDanhSachMonAn;
    List<MonAnDTO> monAnDTOs;
    MonAnDAO monAnDAO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.layout_hienthimonantheoloai,container,false);
        Bundle bundle= getArguments();
        int maloai=0;
        if (bundle!=null){
            maloai = bundle.getInt("maloai");
            Log.d("maloai",maloai+"");
        }
        myView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()==event.ACTION_DOWN){
                    getFragmentManager().popBackStack("Loại Món Ăn", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });
        monAnDAO = new MonAnDAO(getActivity());
        grv= (GridView) myView.findViewById(R.id.gvmonan);
        monAnDTOs=monAnDAO.DanhSachMonAnTheoLoai(maloai);
        adapterHienThiDanhSachMonAn = new AdapterHienThiDanhSachMonAn(getActivity(),R.layout.layout_custom_hienthi_danhsachmonan,monAnDTOs);
        grv.setAdapter(adapterHienThiDanhSachMonAn);
        adapterHienThiDanhSachMonAn.notifyDataSetChanged();
        return myView;
    }
}
