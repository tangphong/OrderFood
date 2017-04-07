package com.example.tangphong_pc.orderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tangphong_pc.orderfood.DAO.BanAnDAO;

/**
 * Created by TangPhong_PC on 4/4/2017.
 */

public class ThemBanAnActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edthembanAn;
    Button btnThembanAn;
    BanAnDAO banAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thembanan);

        edthembanAn= (EditText) findViewById(R.id.edtThemBanAn);
        btnThembanAn= (Button) findViewById(R.id.btnThembanAn);
        btnThembanAn.setOnClickListener(this);
        banAnDAO = new BanAnDAO(this);

    }

    @Override
    public void onClick(View v) {
        String tenBA=edthembanAn.getText().toString();
        if (tenBA!=null||tenBA.equals("")){
         boolean ktr=   banAnDAO.ThemBanAn(tenBA);
            Intent intent = new Intent();
            intent.putExtra("ketquathem",ktr);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }
    }
}
