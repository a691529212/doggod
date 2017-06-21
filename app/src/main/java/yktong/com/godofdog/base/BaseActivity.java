package yktong.com.godofdog.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;


public abstract class BaseActivity extends FragmentActivity {
    private static final String TAG = "Vampire_BaseActivity";
    protected View rootView;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (setLayout() != 0) {
            rootView = LayoutInflater.from(this).inflate(setLayout(), null);
            setContentView(rootView);//绑定布局
        } else {
            //没有绑定布局,弹出错误日志
            Log.e("BaseAty", "Activity:" + this.getClass().getSimpleName() + " 没有绑定布局");
        }
        mContext = this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        initView();
        initData();
    }


    protected abstract int setLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
