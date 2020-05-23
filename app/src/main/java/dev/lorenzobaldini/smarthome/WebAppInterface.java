package dev.lorenzobaldini.smarthome;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    private static Context mContext;
    private static int lastBrightnessValue = 0;
    private final static String filePath = "/sys/devices/omapdss/display0/backlight/panel/brightness";

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public static void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public static void turnOffLCD() {
        try {
            lastBrightnessValue = Integer.parseInt(Utils.execCmd("su -c cat " + filePath).trim());
            Process su = Runtime.getRuntime().exec("su -c echo 0 > " + filePath);
        } catch (Exception e){
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @JavascriptInterface
    public static void turnOnLCD() {
        try {
            String cmd = "su -c echo " + Integer.toString(lastBrightnessValue) + " > " + filePath;
            Process su = Runtime.getRuntime().exec(cmd);
        } catch (Exception e){
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}