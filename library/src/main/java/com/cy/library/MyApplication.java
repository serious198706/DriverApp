package com.cy.library;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by 岩 on 2014/8/12.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
    }
}
