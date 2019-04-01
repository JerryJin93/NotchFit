package com.wcl.notchfit.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by wangchunlong on 2018/10/24.
 */

public class ActivityUtils {


    public static final String TAG = ActivityUtils.class.getSimpleName();

    /**
     * 设置全屏显示
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setFullScreen(Activity activity){
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * 判断是否是全屏
     * @param activity
     * @return
     */
    public static boolean isFullScreen(Activity activity){
        if ( (activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN)
                == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
            return true;
        }

       if((activity.getWindow().getDecorView().getSystemUiVisibility() & View.SYSTEM_UI_FLAG_FULLSCREEN)
                == View.SYSTEM_UI_FLAG_FULLSCREEN){
            return true;
       }

       if(activity.getTheme() != null) {
           TypedValue typedValue = new TypedValue();
           activity.getTheme().obtainStyledAttributes(
                   new int[]{android.R.attr.windowFullscreen}).getValue(0, typedValue);
           if (typedValue.type == TypedValue.TYPE_INT_BOOLEAN) {
               if (typedValue.data != 0) {
                   return true;
               }
           }
       }
       return false;
    }
    /**
     * 沉浸式状态栏
     *
     * @param activity 需要设置的activity
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setTranslucent(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * 沉浸式状态栏，只限于顶部状态栏，不包括底部Navigation Bar
     *
     * @param activity 需要设置的activity
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setTranslucentOnlyForStatusBar(Activity activity) {
        if (activity == null) {
            return;
        }
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    /**
     * 获取Activity内容父视图
     * @param activity
     * @return
     */
    public static View getContentRootView(Activity activity){
//        int decor_content_parent_id = 0;
//        try {
//            String R_PackagePath = NotchConfig.R_PackagePath;
//            if(TextUtils.isEmpty(NotchConfig.R_PackagePath)){
//                R_PackagePath = activity.getPackageName();
//            }
//            Class<?> androidId = Class.forName(R_PackagePath + ".R$id");
//            Field decor_content_parent = androidId.getField("decor_content_parent");
//            decor_content_parent.setAccessible(true);
//            decor_content_parent_id = (int) decor_content_parent.get(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LogUtils.i("R file path is not correct!!!(R资源文件获取失败，可通过查看AndroidManifests文件中的package属性是否与applicationId一致！)");
//        }
//        if(decor_content_parent_id != 0){
//            return activity.findViewById(decor_content_parent_id);
//        }
//        return null;

        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView().getRootView();
        View contentRootView = null;
        int childViewCount = rootView.getChildCount();
        for (int index = 0; index < childViewCount; index++){
            if(contentRootView == null){
                contentRootView = rootView.getChildAt(index);
                continue;
            }
            View indexChildView = rootView.getChildAt(index);
            if(indexChildView.getHeight() > contentRootView.getHeight()){
                contentRootView = indexChildView;
            }
        }

        return contentRootView;
    }
}
