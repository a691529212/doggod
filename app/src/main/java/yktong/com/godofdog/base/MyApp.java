package yktong.com.godofdog.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by vampire on 2017/6/13.
 */

public class MyApp extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getmContext() {
        return mContext;
    }
}
