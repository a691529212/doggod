package yktong.com.godofdog.notification;/**
 * Created by tarena on 2016/12/19.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import yktong.com.godofdog.R;
import yktong.com.godofdog.activity.MainActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * created by Vampire
 * on: 2016/12/19 上午11:22
 */
public class WindowNotification {
    private static final String TAG = "PointNotification-vampire";
    private Context context;
    private Notification.Builder builder;

    public WindowNotification(Context context) {
        this.context = context;
    }

    public Notification show() {
        builder = new Notification.Builder(context);
        Intent mIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        // 点击消失
        builder.setAutoCancel(true);
        builder.setContentTitle("品蝶～");
        builder.setContentText("vampire");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        // 设置滑动不取消
        notification.flags = Notification.FLAG_NO_CLEAR;
        // 设置单一通知
//        notificationManager.notify(0, notification);
        return notification;
    }

    public Notification setContent(String content) {
        builder = new Notification.Builder(context);
        Intent mIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        // 点击消失
        builder.setAutoCancel(true);
        builder.setContentTitle("品蝶～");
        builder.setContentText(content);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        // 设置滑动不取消
        notification.flags = Notification.FLAG_NO_CLEAR;
        // 设置单一通知
//        notificationManager.notify(0, notification);
        return notification;
    }
}
