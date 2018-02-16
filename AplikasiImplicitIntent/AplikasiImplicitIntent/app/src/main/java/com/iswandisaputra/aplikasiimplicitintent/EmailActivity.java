package com.iswandisaputra.aplikasiimplicitintent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.iswandisaputra.aplikasiimplicitintent.helper.MyFunction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmailActivity extends MyFunction {

    @BindView(R.id.edtto)
    EditText edtto;
    @BindView(R.id.edtsubject)
    EditText edtsubject;
    @BindView(R.id.edtmessage)
    EditText edtmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
    }

    //untuk menampilkan menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //method untuk pemilihan menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        String to = edtto.getText().toString();
        String sub = edtsubject.getText().toString();
        String pesan = edtmessage.getText().toString();

        if (id==R.id.mn_send){
            if (TextUtils.isEmpty(to)){
                edtto.setError("tujuan tidak boleh kosong");
                edtto.requestFocus();
                myAnimation(edtto);
            }else if (TextUtils.isEmpty(sub)){
                edtsubject.setError("subject tidak boleh kosong");
                edtsubject.requestFocus();
                myAnimation(edtsubject);
            }else{
                edtmessage.setError("pesan tidak boleh kosong");
                edtmessage.requestFocus();
                myAnimation(edtmessage);

            }
        }

        return super.onOptionsItemSelected(item);

    }
}
