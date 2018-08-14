package com.example.sendmail.uitl;

import android.app.Application;


public class CtxHelper {
    private static Application mApp;

    public static void init(Application application){
        mApp = application;
    }

    public static Application getApp(){
        return mApp;
    }
}
