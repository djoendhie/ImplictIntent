package com.iswandisaputra.aplikasiimplicitintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iswandisaputra.aplikasiimplicitintent.helper.MyFunction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CallPhoneActivity extends MyFunction {

    @BindView(R.id.btncall)
    Button btncall;
    @BindView(R.id.btntampilcall)
    Button btntampilcall;
    @BindView(R.id.btnlistcontact)
    Button btnlistcontact;
    @BindView(R.id.edtnumber)
    EditText edtnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btncall, R.id.btntampilcall, R.id.btnlistcontact})
    public void onViewClicked(View view) {
        String nohp = edtnumber.getText().toString();
        switch (view.getId()) {
            case R.id.btncall:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + nohp)));
                break;
            case R.id.btntampilcall:
                startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+nohp)));
                break;
            case R.id.btnlistcontact:
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(i,1);
                break;
        }
    }


    //method untuk menangkap result dari sebuah action
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
   if (requestCode==1&&resultCode==RESULT_OK){
       Cursor cursor =null;
       try{
           Uri uri = data.getData();
           cursor= getContentResolver().query(uri,new String[]{
                   ContactsContract.CommonDataKinds.Phone.NUMBER},null,null,null);
           if (cursor!=null&&cursor.moveToNext()){
               String phone =cursor.getString(0);
               edtnumber.setText(phone);
           }
       }catch (Exception e){
           e.printStackTrace();
           pesan("gagal memilih no hp"+e.getMessage());
       }
   }
    }

}
