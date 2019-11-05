package com.example.sendmail;

import android.app.Activity;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.example.sendmail.uitl.CrashHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 16/10/21.
 */

public abstract class BaseApplication extends MultiDexApplication {

    private Activity currentActivity;
    private static BaseApplication app = null;
    private boolean feedBackFlag = false;//全局存储是否是上报故障

    private boolean lockFlag = false;//全局存储是否上锁完成任务

    private List<Activity> activityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        CrashHandler.getInstance().init(this);
    }


    public static BaseApplication getApp() {
        if (null == app) {
            Log.e("Mail", "AppDelegate.app is null !!!");
        }
        return app;
    }


    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public void exit() {
        for (int i = 0; i < activityList.size(); i++) {
            if (activityList.get(i) != null) {
                activityList.get(i).finish();
            }
        }
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public boolean isLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(boolean lockFlag) {
        this.lockFlag = lockFlag;
    }

}
