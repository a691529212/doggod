package yktong.com.godofdog.service.task;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import yktong.com.godofdog.manager.WorkManger;
import yktong.com.godofdog.util.PerformClickUtils;
import yktong.com.godofdog.util.SupportUtil;
import yktong.com.godofdog.value.SpValue;

import static yktong.com.godofdog.service.task.PraiseInSns.sleep;

/**
 * Created by vampire on 2017/6/16.
 */

public class QueryFriendNum {
    private static QueryFriendNum ourInstance;
    private AccessibilityService mService;
    private SupportUtil mSupportUtil;

    public static QueryFriendNum getInstance(AccessibilityService mService, SupportUtil mSupportUtil) {
        if (ourInstance == null) {
            synchronized (QueryFriendNum.class) {
                if (ourInstance == null) {
                    ourInstance = new QueryFriendNum(mService, mSupportUtil);
                }
            }
        }
        return ourInstance;
    }

    private QueryFriendNum(AccessibilityService mService, SupportUtil mSupportUtil) {
        this.mService = mService;
        this.mSupportUtil = mSupportUtil;
    }

    public void statisticsNum(String className) {
        if (className.equals(mSupportUtil.getLauncherUI())) {
            AccessibilityNodeInfo node = PerformClickUtils.getNode(mService, "com.tencent.mm:id/asa");
            if (node != null) {
                node.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                Log.d("QueryFriendNum", "滑动");
            }
            sleep(200);
            AccessibilityNodeInfo friendList = PerformClickUtils.getNode(mService, "com.tencent.mm:id/hy");
            if (friendList != null) {
                Log.d("QueryFriendNum", "friendList == null:" + (friendList == null));
                String total = PerformClickUtils.getText(mService, "com.tencent.mm:id/ae8");
                boolean isfanily = (null != total) && !total.isEmpty();
                while (!isfanily) {
                    friendList.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                    sleep(300);
                }
                if (isfanily) {
                    int indexOf = total.indexOf("位");
                    int num = Integer.parseInt(total.substring(0, indexOf));
                    Log.d("QueryFriendNum", "num:" + num);
                    Log.d("QueryFriendNum", total);
                    //统计完成  切换回常规状态
                    WorkManger.getInstence().doTask(SpValue.STATE_NORMAL);
                }

            }
        }
    }

}