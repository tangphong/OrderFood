package com.example.tangphong_pc.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.tangphong_pc.orderfood.FragMent.HienThiBanAnFragment;
import com.example.tangphong_pc.orderfood.FragMent.HienThiThucDonFragment;

/**
 * Created by TangPhong_PC on 4/3/2017.
 */

public class TrangChuAcitity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textViewNavigation;
    HienThiBanAnFragment hienThiBanAnFragment;
    HienThiThucDonFragment hienThiThucDonFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        navigationView = (NavigationView) findViewById(R.id.Navigation_i);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // thằng navigation n chứa thằng header mà header k thể find bằng cái kia. phải sử dụng inflateHeaderView
        View myView = navigationView.inflateHeaderView(R.layout.layout_header_trangchu_navigation);
        textViewNavigation = (TextView) myView.findViewById(R.id.tvTenNVNavigation);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.mo, R.string.dong) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        // nhận dữ liệu khi mà thằng user login
        Intent intent = getIntent();
        String tenDn = intent.getStringExtra("tendn");
        textViewNavigation.setText(tenDn);

        //

        hienThiBanAnFragment = new HienThiBanAnFragment();
        hienThiThucDonFragment = new HienThiThucDonFragment();
        changeFragment(hienThiBanAnFragment);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ittrangchu:
                changeFragment(hienThiBanAnFragment);
                item.setChecked(true);
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.itthucdon:
                changeFragment(hienThiThucDonFragment);
                item.setChecked(true);
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
        return false;
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tranHienThiBanAn = manager.beginTransaction();
        tranHienThiBanAn.replace(R.id.content, fragment);
        tranHienThiBanAn.commit();
    }
}
