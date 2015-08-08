
package me.iwf.photopicker.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DeviceInfoUtils {

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

}
