package yktong.com.godofdog.manager;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.DataOutputStream;

import yktong.com.godofdog.base.MyApp;
import yktong.com.godofdog.bean.EventbusValueBean;
import yktong.com.godofdog.util.SPUtil;
import yktong.com.godofdog.util.ShellUtils;
import yktong.com.godofdog.value.SpValue;

/**
 * Created by vampire on 2017/6/13.
 */

public class WorkManger implements SpValue {
    private static WorkManger instence;

    private WorkManger() {
    }

    public static WorkManger getInstence() {
        if (instence == null) {
            synchronized (WorkManger.class) {
                if (instence == null) {
                    instence = new WorkManger();
                }
            }
        }
        return instence;
    }

    public void doTask(int task) {
        boolean isRoot = getRootAhth();
        int state = (int) SPUtil.get(MyApp.getmContext(), WORK_STATE, STATE_NORMAL);
//        if (state != STATE_NORMAL && task != STATE_NORMAL && state != task) {
//            // 如果当前状态不是默认状态
//            SPUtil.putAndApply(MyApp.getmContext(), WORK_STATE, task);
//        } else if (state == STATE_NORMAL) {
        switch (task) {
            case STATE_ADD_LINK:
                if (isRoot) {
                    String[] cmd = new String[]{"am start -n com.tencent.mm/com.tencent.mm.plugin.subapp.ui.friend.FMessageConversationUI"};
                    ShellUtils.execCommand(cmd, true);
                } else {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.plugin.subapp.ui.friend.FMessageConversationUI"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApp.getmContext().startActivity(intent);
                }
                break;
            case STATE_PRAISE:
                //点赞
                String[] cmd = new String[]{"am start -n com.tencent.mm/com.tencent.mm.plugin.sns.ui.En_424b8e16"};
                ShellUtils.execCommand(cmd, true);
                break;
            case STATE_QUERY_FRIENDS_NUM:
                // 统计好友数
                String[] query = new String[]{"am start -n com.tencent.mm/com.tencent.mm.ui.LauncherUI"};
                ShellUtils.execCommand(query, true);
                break;


        }
        SPUtil.putAndApply(MyApp.getmContext(), WORK_STATE, task);
        EventBus.getDefault().post(new EventbusValueBean(task));
//        }

    }

    //判断 是否获取Root权限
    public synchronized boolean getRootAhth() {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();
            int exitValue = process.waitFor();
            if (exitValue == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.d("*** DEBUG ***", "Unexpected error - Here is what I know: "
                    + e.getMessage());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
