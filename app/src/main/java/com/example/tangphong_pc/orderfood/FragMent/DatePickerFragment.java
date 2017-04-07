package com.example.tangphong_pc.orderfood.FragMent;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.tangphong_pc.orderfood.R;

/**
 * Created by TangPhong_PC on 4/2/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //lấy ngày tháng năm ra
        java.util.Calendar calendar= java.util.Calendar.getInstance();
        int iNam = calendar.get(java.util.Calendar.YEAR);
        int iThang = calendar.get(java.util.Calendar.MONTH);
        int iNgay = calendar.get(java.util.Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(),this,iNgay,iThang,iNam);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText editText= (EditText) getActivity().findViewById(R.id.txtNGAYSINH);
        String ns= dayOfMonth+"/"+(month+1)+"/"+year;
        editText.setText(ns);
    }
}
