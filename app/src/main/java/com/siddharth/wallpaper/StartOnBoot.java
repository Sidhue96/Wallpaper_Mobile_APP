package com.siddharth.wallpaper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by siddharth on 19/4/17.
 */

public class StartOnBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startServiceIntent = new Intent(context, WallPaperService.class);
        context.startService(startServiceIntent);

    }
}
