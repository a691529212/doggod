package yktong.com.godofdog.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import java.util.List;

import yktong.com.godofdog.base.MyApp;
import yktong.com.godofdog.service.task.AddLinkman;
import yktong.com.godofdog.service.task.PraiseInSns;
import yktong.com.godofdog.service.task.QueryFriendNum;
import yktong.com.godofdog.util.SPUtil;
import yktong.com.godofdog.util.SupportUtil;
import yktong.com.godofdog.value.SpValue;

/**
 * Created by vampire on 2017/6/13.
 */

public class WeService extends AccessibilityService implements SpValue {
    private SupportUtil supportUtil;
    private AccessibilityService mService;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        mService = this;
        String version = getVersion(this);
        supportUtil = new SupportUtil(version);
        Log.d("WeService", version);
        Toast.makeText(mService, "微信版本 : " + version, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        String atyName = event.getClassName().toString();
        Log.d("WeService", "类名：" + atyName);
        int workState = (int) SPUtil.get(MyApp.getmContext(), WORK_STATE, STATE_NORMAL);
        Log.d("WeService", "workState:" + workState);
        switch (workState) {
            case STATE_NORMAL:
                // TODO: 2017/6/13  doNothing
                break;
            case STATE_ADD_LINK:
                // TODO: 2017/6/13 通讯录添加
                AddLinkman.getInstence().addFriendByLink(supportUtil, atyName, mService);
                break;
            case STATE_PRAISE:
                // TODO: 2017/6/16  点赞
                PraiseInSns.getInstance(supportUtil, mService).praiseInSns(atyName);
                break;
            case STATE_QUERY_FRIENDS_NUM:
                // 统计好友数量
                QueryFriendNum.getInstance(mService, supportUtil).statisticsNum(atyName);
                break;
        }
        // TODO: 2017/6/16  新增好友  通知栏监听
        Log.d("WeService", "event.getEventType():" + event.getEventType());
        for (CharSequence charSequence : event.getText()) {
            Log.d("WeService", "charSequence:" + charSequence);
        }
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                List<CharSequence> texts = event.getText();
                if (!texts.isEmpty()) {
                    for (CharSequence text : texts) {
                        String tex = (String) text;
                        Log.d("WeService", tex);
                        if (tex.contains("我通过了你的朋友验证请求，现在我们可以开始聊天了")) {
                            int i = tex.indexOf(":");
                            Log.d("WeService", tex.substring(0, i));
                            Log.d("WeService", "新加好友");
                            // TODO: 2017/6/19  好友统计
                        }
                    }

                }
                break;
        }
    }

    @Override
    public void onInterrupt() {

    }

    /**
     * 获取微信的版本号
     *
     * @param context
     * @return
     */
    private String getVersion(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);

        for (PackageInfo packageInfo : packageInfoList) {
            if ("com.tencent.mm".equals(packageInfo.packageName)) {
                return packageInfo.versionName;
            }
        }
        return "6.5.4";
    }
}
