package com.iswandisaputra.aplikasiimplicitintent;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iswandisaputra.aplikasiimplicitintent.helper.MyFunction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends MyFunction {
    @BindView(R.id.ring)
    Button ring;
    @BindView(R.id.vibrate)
    Button vibrate;
    @BindView(R.id.silent)
    Button silent;
//deklarasi variable scr global
    AudioManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);
        //inisialisasi atau link variabel dgn iD di layout
        manager = (AudioManager)getSystemService(AUDIO_SERVICE);
    }

    @OnClick({R.id.ring, R.id.vibrate, R.id.silent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ring:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //nougat
                    manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_UNMUTE, 0);
                    pesan("dalam mode normal/ring");

                } else {
                    manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    pesan("dalam mode normal/ring");
                }

                break;
            case R.id.vibrate:
                manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                pesan("dalam mode getar");

                break;
            case R.id.silent:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //nougat
                    manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_MUTE, 0);
                } else {
                    manager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    pesan("dalam mode silent");
                }
                break;
        }
    }
}
