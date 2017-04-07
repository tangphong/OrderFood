package com.example.tangphong_pc.orderfood.FragMent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.tangphong_pc.orderfood.Custom_Adapter.Adapter_HienThiLoaiMonAnThucDon;
import com.example.tangphong_pc.orderfood.DAO.LoaiMonAnDAO;
import com.example.tangphong_pc.orderfood.DTO.LoaiMonAnDTO;
import com.example.tangphong_pc.orderfood.R;
import com.example.tangphong_pc.orderfood.ThemThucDonActivity;
import com.example.tangphong_pc.orderfood.TrangChuAcitity;

import java.util.List;

/**
 * Created by TangPhong_PC on 4/5/2017.
 */

public class HienThiThucDonFragment extends Fragment {
    GridView gridView;
    Adapter_HienThiLoaiMonAnThucDon adapter;
    List<LoaiMonAnDTO> loaiMonAnDTOs;
    LoaiMonAnDAO loaiMonAnDAO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Thực Đơn");
        loaiMonAnDAO = new LoaiMonAnDAO(getActivity());
        loaiMonAnDTOs = loaiMonAnDAO.DanhSachLoaiMonAn();
        gridView = (GridView) view.findViewById(R.id.gvthucdon);
        adapter = new Adapter_HienThiLoaiMonAnThucDon(R.layout.layout_custom_hienthiloaimonan, loaiMonAnDTOs, getActivity());
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(Click_Item);
        return view;
    }

    AdapterView.OnItemClickListener Click_Item = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int maloai = loaiMonAnDTOs.get(position).getMaLoai();
            HienThiDanhSachMonAnTLFragment fg = new HienThiDanhSachMonAnTLFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("maloai",maloai);
            fg.setArguments(bundle);
            changeFragment(fg);
        }
    };

    private void changeFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, fragment).addToBackStack("Loại Món Ăn");
        transaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemBanAn = menu.add(1, R.id.itthucdon, 1, R.string.thucdon);
        itThemBanAn.setIcon(R.drawable.logodangnhap);
        itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }
    // khi click item trên toolbarr thì sẽ nhảy vào đây

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itthucdon:
                Toast.makeText(getActivity(), "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ThemThucDonActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
