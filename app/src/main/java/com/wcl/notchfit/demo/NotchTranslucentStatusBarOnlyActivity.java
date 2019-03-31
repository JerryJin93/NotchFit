package com.wcl.notchfit.demo;

import android.os.Bundle;
import android.view.ViewGroup;

import com.wcl.notchfit.NotchFit;
import com.wcl.notchfit.args.NotchProperty;
import com.wcl.notchfit.args.NotchScreenType;
import com.wcl.notchfit.core.OnNotchCallBack;
import com.wcl.notchfit.utils.SizeUtils;

/**
 * Author: Jerry
 * Generated at: 2019/3/31 4:01 PM
 * GitHub: https://github.com/JerryJin93
 * Blog:
 * WeChat: enGrave93
 * Version: 1.0.0
 * Description: 沉浸式刘海适配，不包括底部Navigation Bar
 */
public class NotchTranslucentStatusBarOnlyActivity extends NotchBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ViewGroup listParent = findViewById(R.id.ll_parent);
        NotchFit.fit(this, NotchScreenType.TRANSLUCENT_STATUS_BAR_ONLY, new OnNotchCallBack() {
            @Override
            public void onNotchReady(NotchProperty notchProperty) {
                int fitSize;
                if(notchProperty.isNotchEnable()){
                    fitSize = notchProperty.getNotchHeight();
                }
                else {
                    fitSize = SizeUtils.getStatusBarHeight(NotchTranslucentStatusBarOnlyActivity.this);
                }

                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) listParent.getLayoutParams();
                marginLayoutParams.topMargin = fitSize;
                listParent.requestLayout();
            }
        });
    }
}
