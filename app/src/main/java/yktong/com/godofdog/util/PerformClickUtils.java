package yktong.com.godofdog.util;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import static java.lang.Thread.sleep;


public class PerformClickUtils {


    /**
     * 在当前页面查找文字内容并点击
     *
     * @param text
     */
    public static void findTextAndClick(AccessibilityService accessibilityService, String text) {

        AccessibilityNodeInfo accessibilityNodeInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        }
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null && (text.equals(nodeInfo.getText()) || text.equals(nodeInfo.getContentDescription()))) {
                    Log.d("PerformClickUtils", "vampire-click");
                    performClick(nodeInfo);
                    break;
                }
            }
        }
    }


    /**
     * 检查viewId进行点击
     *
     * @param accessibilityService
     * @param id
     */
    public static void findViewIdAndClick(AccessibilityService accessibilityService, String id) {

        AccessibilityNodeInfo accessibilityNodeInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        }
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        }
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null) {
                    performClick(nodeInfo);
                    break;
                }
            }
        }
    }

    public static void findViewIdAndClick(AccessibilityService accessibilityService, String id, int posion) {

        AccessibilityNodeInfo accessibilityNodeInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        }
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        }
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            AccessibilityNodeInfo accessibilityNodeInfo1 = nodeInfoList.get(posion);
            if (accessibilityNodeInfo1 != null) {
                performClick(accessibilityNodeInfo1);
            }
        }
    }

    public static void findViewIdAndClick(AccessibilityService accessibilityService, String id, String content) {

        AccessibilityNodeInfo accessibilityNodeInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        }
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        }
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null && nodeInfo.getContentDescription().toString().contains(content)) {
                    performClick(nodeInfo);
                    break;
                }
            }
        }
    }

    public static void findViewIdAndClick(AccessibilityService accessibilityService, String id, boolean turn) {

        AccessibilityNodeInfo accessibilityNodeInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        }
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        }
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            if (turn) {
//                for (int i = nodeInfoList.size()-1; i > 0; i--) {
//                    if (nodeInfoList.get(i) != null) {
//                        performClick(nodeInfoList.get(i));
//                        break;
//                    }
//                }
                performClick(nodeInfoList.get(11));
            } else {
                for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                    if (nodeInfo != null) {
                        performClick(nodeInfo);
                        break;
                    }
                }
            }

        }
    }

    /**
     * 在当前页面查找对话框文字内容并点击
     *
     * @param text1 默认点击text1
     * @param text2
     */
    public static void findDialogAndClick(AccessibilityService accessibilityService, String text1, String text2) {

        AccessibilityNodeInfo accessibilityNodeInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        }
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> dialogWait = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text1);
        List<AccessibilityNodeInfo> dialogConfirm = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text2);
        if (!dialogWait.isEmpty() && !dialogConfirm.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : dialogWait) {
                if (nodeInfo != null && text1.equals(nodeInfo.getText())) {
                    performClick(nodeInfo);
                    break;
                }
            }
        }

    }

    //模拟点击事件
    public static void performClick(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }
        if (nodeInfo.isClickable()) {
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        } else {
            performClick(nodeInfo.getParent());
        }
    }

    //模拟返回事件
    public static void performBack(AccessibilityService service) {
        if (service == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
        }
    }

    public static void setText(AccessibilityService service, String id, String text) {
        AccessibilityNodeInfo accessibilityNodeInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            accessibilityNodeInfo = service.getRootInActiveWindow();
        }
        if (accessibilityNodeInfo == null) {
            return;
        }
        List<AccessibilityNodeInfo> nodeInfoList = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        }
        if (null == nodeInfoList || nodeInfoList.isEmpty()) {
            return;
        } else {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null) {
                    setText(service, nodeInfo, text);
                    break;
                }
            }
        }
    }

    /**
     * 设置文本
     */
    public static void setText(AccessibilityService service, AccessibilityNodeInfo node, String reply) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle args = new Bundle();
            args.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                    reply);
            Bundle temp = new Bundle();
            temp.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "");
            node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, temp);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args);
        } else {
            node.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
            node.performAction(AccessibilityNodeInfo.ACTION_CUT);
            ClipData data = ClipData.newPlainText("reply", reply);
            ClipboardManager clipboardManager = (ClipboardManager) service.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setPrimaryClip(data);
            node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
            node.performAction(AccessibilityNodeInfo.ACTION_PASTE); // 执行粘贴
        }
    }

    public static String getText(AccessibilityService service, String wightId) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return null;
        }
        List<AccessibilityNodeInfo> wightList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(wightId);
        if (!wightId.isEmpty()) {
            for (AccessibilityNodeInfo wight : wightList) {
                if (wight != null) {
                    return wight.getText().toString();
                }
            }
        }
        return null;
    }

    public static String getDesc(AccessibilityService service, String wightId) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo != null) {
            List<AccessibilityNodeInfo> wightList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(wightId);
            if (!wightList.isEmpty()) {
                for (AccessibilityNodeInfo wight : wightList) {
                    if (wight != null) {
                        return wight.getContentDescription().toString();
                    }
                }
            }
        }
        return "";
    }

    public static AccessibilityNodeInfo getNode(AccessibilityService service, String id) {
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        if (nodeInfo == null) {
            return null;
        }
        List<AccessibilityNodeInfo> nodeList = nodeInfo.findAccessibilityNodeInfosByViewId(id);
        for (AccessibilityNodeInfo accessibilityNodeInfo : nodeList) {
            if (accessibilityNodeInfo != null) {
                return accessibilityNodeInfo;
            }
        }
        return null;
    }
}
