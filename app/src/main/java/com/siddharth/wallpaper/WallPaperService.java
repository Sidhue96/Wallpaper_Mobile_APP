package com.siddharth.wallpaper;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by siddharth on 18/4/17.
 */

public class WallPaperService extends Service {
    public String date;
    public String actualDate;
    public String actualName;
    public String name;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        actualDate = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        actualName = "w"+actualDate;
        dpChanger();

        final Handler h = new Handler();
        final int delay = 60000; //milliseconds

        h.postDelayed(new Runnable(){
            public void run(){
                //do something
                date = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
                name = "w"+date;
                dpChangerChecker();

                h.postDelayed(this, delay);
            }
        }, delay);

        return START_STICKY;
    }
    public void dpChangerChecker(){
        if(!name.equals(actualName)) {
            actualDate = date;
            actualName = name;
            dpChanger();
        }
    }
    public void dpChanger(){
        WallpaperManager myWallpaperManager;
        Drawable wallpaperDrawable;
        try {
            Context mContext = this;
            int checkexistence = mContext.getResources().getIdentifier(actualName, "drawable", "com.siddharth.wallpaper");
            int resID = getResources().getIdentifier(actualName, "drawable", "com.siddharth.wallpaper");
            if (checkexistence != 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), resID);
                Bitmap useThisBitmap = Bitmap.createBitmap(bitmap);
                       // myWallpaperManager.setResource(resID);
                myWallpaperManager = WallpaperManager.getInstance(WallPaperService.this);
                wallpaperDrawable = myWallpaperManager.getDrawable();
                myWallpaperManager.setBitmap(useThisBitmap);

            }
            else{
                MainActivity stopservice = new MainActivity();
                stopservice.stopwp();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Wallpaper Service stopped",Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
