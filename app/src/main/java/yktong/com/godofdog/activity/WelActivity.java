package yktong.com.godofdog.activity;

import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

import yktong.com.godofdog.R;
import yktong.com.godofdog.base.BaseActivity;

/**
 * Created by vampire on 2017/6/13.
 */

public class WelActivity extends BaseActivity {
    @Override
    protected int setLayout() {
        return R.layout.activity_bac;
    }

    @Override
    protected void initView() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelActivity.this, MainActivity.class);
                mContext.startActivity(intent);
                finish();
            }
        }, 3000);
    }

    @Override
    protected void initData() {

    }
}
