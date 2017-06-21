package yktong.com.godofdog.service.task;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import yktong.com.godofdog.util.PerformClickUtils;
import yktong.com.godofdog.util.SupportUtil;

/**
 * Created by vampire on 2017/6/16.
 */

public class PraiseInSns {
    private static PraiseInSns ourInstance;
    private SupportUtil mSupportUtil;
    private AccessibilityService mService;

    public static PraiseInSns getInstance(SupportUtil supportUtil, AccessibilityService mService) {
        if (ourInstance == null) {
            synchronized (PraiseInSns.class) {
                if (ourInstance == null) {
                    ourInstance = new PraiseInSns(supportUtil, mService);
                }
            }
        }
        return ourInstance;
    }

    private PraiseInSns(SupportUtil supportUtil, AccessibilityService mService) {
        this.mService = mService;
        this.mSupportUtil = supportUtil;
    }

    public void praiseInSns(String className) {
        if (className.equals(mSupportUtil.getSnsUi())) {
            Log.d("PraiseInSns", "朋友圈页面");
            AccessibilityNodeInfo rootInActiveWindow = mService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return;
            }
            praise(rootInActiveWindow);
            AccessibilityNodeInfo node = PerformClickUtils.getNode(mService, mSupportUtil.getSnsListViewId());
            if (null != node) {
                node.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);// 向下滚动
                praise(rootInActiveWindow);
            }

        }

    }

    private void praise(AccessibilityNodeInfo rootInActiveWindow) {
        List<AccessibilityNodeInfo> commentBtnList = rootInActiveWindow.findAccessibilityNodeInfosByViewId(mSupportUtil.getSnsCommentBtnId());
        if (null != commentBtnList && !commentBtnList.isEmpty()) {
            for (AccessibilityNodeInfo commentBtn : commentBtnList) {
                commentBtn.performAction(AccessibilityNodeInfo.ACTION_CLICK);//点击评论  弹出选项
                sleep(500);
                String text = PerformClickUtils.getText(mService, "com.tencent.mm:id/co2");
                if (null!=text && text.equals("赞")) {
                    PerformClickUtils.findViewIdAndClick(mService, mSupportUtil.getSnsPraiseBtnId());
                }

            }
        }
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
