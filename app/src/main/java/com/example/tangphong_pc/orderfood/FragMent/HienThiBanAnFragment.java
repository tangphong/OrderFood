package com.example.tangphong_pc.orderfood.FragMent;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.tangphong_pc.orderfood.Custom_Adapter.BanAn_Adapter;
import com.example.tangphong_pc.orderfood.DAO.BanAnDAO;
import com.example.tangphong_pc.orderfood.DTO.BanAnDTO;
import com.example.tangphong_pc.orderfood.R;
import com.example.tangphong_pc.orderfood.ThemBanAnActivity;
import com.example.tangphong_pc.orderfood.TrangChuAcitity;

import java.util.List;

/**
 * Created by TangPhong_PC on 4/4/2017.
 */

public class HienThiBanAnFragment extends Fragment implements View.OnClickListener {
    public static int REQUESCODE = 1111;
    GridView gvHienThiBanAn;
    List<BanAnDTO> banAnDTOs;
    BanAnDAO banAnDAO;
    BanAn_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View myview = inflater.inflate(R.layout.hienthibananfragment, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Trang Chủ");
        gvHienThiBanAn = (GridView) myview.findViewById(R.id.gvBanAn);
        LoadDanhSachBanAn();
        return myview;
    }

    private void LoadDanhSachBanAn() {
        // lấy danh sách bàn ăn;
        banAnDAO = new BanAnDAO(getActivity());
        banAnDTOs = banAnDAO.LayDanhSachBanAn();
        adapter = new BanAn_Adapter(getActivity(), R.layout.layout_custom_itemgridview, banAnDTOs);
        gvHienThiBanAn.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemBanAn = menu.add(1, R.id.itThemBanAn, 1, R.string.themBanAn);
        itThemBanAn.setIcon(R.drawable.thembanan);
        itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itThemBanAn:
                Intent intent = new Intent(getActivity(), ThemBanAnActivity.class);
                startActivityForResult(intent, REQUESCODE);
                break;
        }
        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESCODE) {
            if (resultCode == Activity.RESULT_OK) {
                Intent intent = data;
                boolean ktra = intent.getBooleanExtra("ketquathem", false);
                if (ktra) {
                    LoadDanhSachBanAn();
                    Toast.makeText(getActivity(), getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

    }
}
