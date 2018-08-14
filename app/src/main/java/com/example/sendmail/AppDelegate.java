package com.example.sendmail;

import com.example.sendmail.uitl.CtxHelper;

/**
 * Created by paul on 16/10/11.
 */

public class AppDelegate extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        initCtx();

    }


    protected void initCtx() {
        CtxHelper.init(this);
    }



}
