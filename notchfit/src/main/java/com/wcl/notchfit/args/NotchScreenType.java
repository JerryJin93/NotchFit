package com.wcl.notchfit.args;

/**
 * 屏幕显示模式
 * Created by wangchunlong on 2018/10/24.
 */
public enum NotchScreenType {
    /**
     * 全屏显示
     */
    FULL_SCREEN,
    /**
     * 沉浸式显示
     */
    TRANSLUCENT,
    /**
     * 沉浸式显示，只限于顶部状态栏，不包括底部Navigation Bar
     */
    TRANSLUCENT_STATUS_BAR_ONLY,
    /**
     * 用户自定义activity显示样式
     */
    CUSTOM
}
