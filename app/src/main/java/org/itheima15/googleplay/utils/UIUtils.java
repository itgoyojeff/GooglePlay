package org.itheima15.googleplay.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.TypedValue;

import java.io.File;


/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.utils
 *  @文件名:   UIUtils
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/22 11:28
 *  @描述：    用来提供和资源，上下文相关工具
 */
public class UIUtils {

    /**
     * 全局的上下文
     */
    private static Context mBaseContext;
    private static Handler mHandler;

    /**
     * 初始化工具
     * @param application
     */
    public static void init(Application application) {
        mBaseContext = application;

        //在主线程中new
        mHandler = new Handler();
    }

    public static Context getContext() {
        return mBaseContext;
    }

    public static void post(Runnable task) {
        mHandler.post(task);
    }

    public static void postDelayed(Runnable task, long delayed) {
        mHandler.postDelayed(task, delayed);
    }

    public static void removeCallbacks(Runnable task) {
        mHandler.removeCallbacks(task);
    }

    public static Resources getResources() {
        return mBaseContext.getResources();
    }

    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }

    public static File getCacheDir() {
        return getContext().getCacheDir();
    }

    /**
     * dp --> px
     * @param dp
     * @return
     */
    public static float dp2px(float dp) {
        float dimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                                                    dp,
                                                    getResources().getDisplayMetrics());
        return dimension;
    }

    public static float px2dp(float px) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                         px,
                                         getResources().getDisplayMetrics());
    }

    public static String getString(int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }
}
