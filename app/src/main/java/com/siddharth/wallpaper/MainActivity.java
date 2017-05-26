package com.siddharth.wallpaper;


import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private MediaPlayer hbd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,WallPaperService.class);
        startService(intent);
        hbd = MediaPlayer.create(this,R.raw.happy_birthday);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hbd.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hbd.pause();
    }

    public void stopwp(){
        Intent intent = new Intent(this,WallPaperService.class);
        stopService(intent);
    }

}
