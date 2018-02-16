package com.iswandisaputra.aplikasiimplicitintent.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.iswandisaputra.aplikasiimplicitintent.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by iswandisaputra on 2/12/18.
 */

public class MyFunction extends AppCompatActivity{

    Animation animation;
    Context c ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c=MyFunction.this;
    }

    public void pesan(String isipesan){
        Toast.makeText(c, isipesan, Toast.LENGTH_SHORT).show();
    }

    public void pindahclass(Class classtujuan){
        startActivity(new Intent(c, classtujuan));

    }

    public String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void myAnimation(EditText editText){
        animation = AnimationUtils.loadAnimation(c, R.anim.animasigetar);
        editText.setAnimation(animation);

    }
}
