package yktong.com.godofdog.tool.datebase;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.litesuits.orm.LiteOrm;


import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import yktong.com.godofdog.base.MyApp;


/**
 * Created by Vampire 2016-11-12 14:28:27
 */
public class DBTool {
    private static final String TAG = "DBTool";

    private static DBTool ourInstance;

    private LiteOrm liteOrm;

    private Handler handler;

    public static ExecutorService threadPool = Executors.newFixedThreadPool(getNumCores() + 1);

    public static DBTool getInstance() {
        if (ourInstance == null) {
            synchronized (DBTool.class) {
                if (ourInstance == null) {
                    ourInstance = new DBTool();
                }
            }
        }
        return ourInstance;
    }

    private DBTool() {
        liteOrm = LiteOrm.newSingleInstance(MyApp.getmContext(), "ShareForWeChat.db");
        handler = new Handler(Looper.getMainLooper());
    }



    // 写一个类作为handler的runnable
    class HandlerRunnable implements Runnable {
        List lists;
        String answer;
        QueryList queryList;
        QueryListener queryListener;

        //构造方法
        public <T> HandlerRunnable(QueryList queryList, String answer) {
            this.queryList = queryList;
            this.answer = answer;
        }

        //构造方法
        public <T> HandlerRunnable(QueryListener queryListener, List<T> lists) {
            this.queryListener = queryListener;
            this.lists = lists;
        }

        @Override
        public void run() {
            if (queryList == null) {
                try {
                    queryListener.onQueryListener(lists);
                } catch (NullPointerException e) {
                    Log.d(TAG, "e:" + e);
                }
            } else {
                try {
                    queryList.onQueryListener(answer);
                } catch (NullPointerException e) {
                    Log.e(TAG, "e:" + e);
                }
            }

        }
    }

    //将查询数据回传的接口
    public interface QueryListener {
        <T> void onQueryListener(List<T> list);
    }

    //将查询数据回传的接口
    public interface QueryList {
        <T> void onQueryListener(String answer);
    }

    // 获取CPU 核数
    private static int getNumCores() {
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            Log.d("dbTool", "CPU Count: " + files.length);
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e) {
            //Print exception
            Log.d("dbTool", "CPU Count: Failed.");
            e.printStackTrace();
            //Default to return 1 core
            return 1;
        }
    }


}
