package com.quliantrip.qulian.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.quliantrip.qulian.global.QulianApplication;

/**
 * Created by yuly on 2015/11/9.
 */
public class CommonHelp {
    public static void runOnUIThread(Runnable r) {
		QulianApplication.getMainHandler().post(r);
	}

	public static void runOnUIThread(Runnable r, long delayMillis) {
		QulianApplication.getMainHandler().postDelayed(r, delayMillis);
	}

    //该方法表示把自己从ViewParent中移除
    public static void removeSelfFromParent(View child) {
        if(child!=null){
            ViewParent parent = child.getParent();
            if(parent!=null && parent instanceof ViewGroup){
                ViewGroup group = (ViewGroup) parent;
                group.removeView(child);//将子VIew从父View中移除
            }
        }
    }

    public static Resources getResources() {
        return QulianApplication.getContext().getResources();
    }

    public static String getString(int id) {
        return getResources().getString(id);
    }


    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    public static int getColor(int id) {
        return getResources().getColor(id);
    }

    public static Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }


    public static float getDimens(int id) {
        return getResources().getDimension(id);
    }

    /**
     * dip转化为px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转化为dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private static final String CONFIG_NAME = "configQulian";

    //保存和获取boolean类型到sp中
    public static void saveSp(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME,
                context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getSp(Context context, String key,
                                boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME,
                context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    //保存和获取String类型到sp中
    public static void saveStringSp(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME,context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getStringSp(Context context, String key,
                                     String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME,
                context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        if("".equals(str))
            return defValue;
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    public static SharedPreferences getPreferences() {
        SharedPreferences pre = QulianApplication.getContext().getSharedPreferences("creativelocker.pref",
                Context.MODE_MULTI_PROCESS);
        return pre;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

}
