package yktong.com.godofdog.service.task;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import yktong.com.godofdog.util.PerformClickUtils;
import yktong.com.godofdog.util.ShellUtils;
import yktong.com.godofdog.util.SupportUtil;

/**
 * Created by vampire on 2017/6/13.
 */

public class AddLinkman {
    private static AddLinkman instence;

    public static AddLinkman getInstence() {
        if (instence == null) {
            synchronized (AddLinkman.class) {
                if (instence == null) {
                    instence = new AddLinkman();
                }
            }
        }
        return instence;
    }

    public void addFriendByLink(SupportUtil supportUtil, String className, AccessibilityService mService) {
        Log.d("AddLinkman", "className.equals(supportUtil.getFMesssageConversationUI()):" + className.equals(supportUtil.getFMesssageConversationUI()));
        if (className.equals(supportUtil.getFMesssageConversationUI())) {
            //联系人添加
            AccessibilityNodeInfo rootInActiveWindow = mService.getRootInActiveWindow();
            Log.d("AddLinkman", "rootInActiveWindow==null:" + (rootInActiveWindow == null));
            if (rootInActiveWindow == null) {
                return;
            }
            List<AccessibilityNodeInfo> addBtnList = rootInActiveWindow.findAccessibilityNodeInfosByViewId(supportUtil.getFMesssageConversationUI_ADD_BTN_ID());
            Log.d("AddLinkman", "addBtnList.isEmpty():" + addBtnList.isEmpty());
            if (!addBtnList.isEmpty()) {
                for (AccessibilityNodeInfo nodeInfo : addBtnList) {
                    if (nodeInfo != null && nodeInfo.getText().equals("添加")) {
                        nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        sleep(1000);
                    }
                }

            }
            PerformClickUtils.findTextAndClick(mService, "接受");
            sleep(1000);
            AccessibilityNodeInfo lv = PerformClickUtils.getNode(mService, supportUtil.getFMesssageConversationUI_LV_ID());
            if (null != lv) {
                lv.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }

        } else if (className.equals(supportUtil.getContactInfoUI())) {
            PerformClickUtils.findViewIdAndClick(mService, supportUtil.getContactInfoUIaddBtnId(), "添加到通讯录");
            // 没有Root权限 无法设置招呼语
            String[] cleanEtCmd = new String[]{"input tap 670 369"};
            ShellUtils.execCommand(cleanEtCmd, true);
            PerformClickUtils.setText(mService, supportUtil.getSayHiContantEtId(), "你好，我们好像在哪见过。");
            // TODO: 2017/6/14 设置昵称
            PerformClickUtils.findViewIdAndClick(mService, supportUtil.getSayHiNicknameEtId());

        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
