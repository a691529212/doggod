package yktong.com.godofdog.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import yktong.com.godofdog.bean.EventbusValueBean;
import yktong.com.godofdog.notification.WindowNotification;
import yktong.com.godofdog.util.AccessWatcher;
import yktong.com.godofdog.value.SpValue;

/**
 * Created by vampire on 2017/6/13.
 */

public class MyService extends Service implements SpValue {
    private static final String TAG = "vampire_MyService";
    private Handler mHandler =
            new Handler(Looper.getMainLooper());
    private Context mContext;
    private WindowNotification notification;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(EventbusValueBean bean) {
        try {
            int task = (int) bean.getO();
            Log.d(TAG, "task:" + task);
            String content = "";
            switch (task) {
                case STATE_NORMAL:
                    content ="待机";
                    break;
                case STATE_ADD_LINK:
                    content="添加通讯录";
                    break;
                case STATE_PRAISE:
                    content = "点赞";
                    break;
                case STATE_QUERY_FRIENDS_NUM:
                    content = "统计好友数量";
                    break;
            }
            notification = new WindowNotification(mContext);
            Notification show = notification.setContent(content);
            startForeground(1, show);
        } catch (Exception e) {
            Log.e(TAG, "e:" + e);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        mContext = this;

        bindNotification();

        checkAccessibility();
    }


    /**
     * 绑定前台服务
     */
    private void bindNotification() {
        notification = new WindowNotification(mContext);

        Notification show = notification.show();
        startForeground(1, show);

        // TODO: 2017/6/19  动态设置当前执行任务内容 
    }

    /**
     * 检查辅助功能是否开启 每分钟检查一次
     */
    private void checkAccessibility() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!AccessWatcher.getInstence().isAccessibilitySettingsOn(mContext)) {
                    Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        }, 0, 60000);
    }


}
