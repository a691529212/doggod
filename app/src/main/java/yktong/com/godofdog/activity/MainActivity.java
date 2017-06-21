package yktong.com.godofdog.activity;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;

import yktong.com.godofdog.R;
import yktong.com.godofdog.adapter.MainAdapter;
import yktong.com.godofdog.base.BaseActivity;
import yktong.com.godofdog.fragment.MainFragment;
import yktong.com.godofdog.fragment.ManagementFragment;
import yktong.com.godofdog.fragment.MarketingFragment;
import yktong.com.godofdog.fragment.UserFragment;
import yktong.com.godofdog.service.MyService;
import yktong.com.godofdog.util.InsertLinkMan;
import yktong.com.godofdog.value.Strings;

public class MainActivity extends BaseActivity implements Strings {

    private AlertDialog dialog;

    @Override

    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ViewPager viewPager = bindView(R.id.main_vp);
        TabLayout tabLayout = bindView(R.id.main_table);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new MarketingFragment());
        fragments.add(new ManagementFragment());
        fragments.add(new UserFragment());
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        initTableLayout(viewPager, tabLayout);
    }

    private void initTableLayout(ViewPager viewPager, TabLayout tabLayout) {
        for (int i = 0; i < TITLE.length; i++) {
            TabLayout.Tab tabAt = tabLayout.getTabAt(i);
            Drawable image;
            if (tabAt.isSelected()) {
                image = mContext.getResources().getDrawable(SELECTED[i]);
            } else {
                image = mContext.getResources().getDrawable(UNSELECTED[i]);
            }
            tabAt.setIcon(image);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            viewPager.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                for (int i = 0; i < TITLE.length; i++) {
                    TabLayout.Tab tabAt = tabLayout.getTabAt(i);
                    Drawable image;
                    if (tabAt.isSelected()) {
                        image = mContext.getResources().getDrawable(SELECTED[i]);
                    } else {
                        image = mContext.getResources().getDrawable(UNSELECTED[i]);
                    }
                    tabAt.setIcon(image);
                }
            });
        }
        tabLayout.setTabTextColors(0xff9c9c9c, 0xff1296db);
    }

    @Override
    protected void initData() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                showDialogTipUserRequestPermission(new String[]{Manifest.permission.WRITE_CONTACTS});
            }
        }

        String[] nums = new String[]{"18911990051", "18911990054", "18911990055", "18911990059", "18911990063", "18911990140", "18911990156", "18911990182", "18911990205", "18911990223"
        ,"18911990229","18911990231"};
        for (String num : nums) {
            InsertLinkMan.insert(num, "ykt-" + num);
        }

    }


    // 提示用户该请求权限的弹出框
    private void showDialogTipUserRequestPermission(String[] permissions) {

        new AlertDialog.Builder(this)
                .setTitle("联系人修改权限不可用")
                .setMessage("由于需要导入联系人；\n否则，您将无法正常使用微信好友添加")
                .setPositiveButton("立即开启", (dialog1, which) -> startRequestPermission(permissions))
                .setNegativeButton("取消", (dialog12, which) -> finish()).setCancelable(false).show();
        Log.d("MainActivity", "permissions.length:" + permissions.length);
        for (String permission : permissions) {
            Log.d("MainActivity---", permission);
        }
    }

    // 开始提交请求权限
    private void startRequestPermission(String[] permissions) {
        ActivityCompat.requestPermissions(this, permissions, 321);
    }

    // 用户权限 申请 的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("MainActivity", " result" + permissions[0]);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!b) {
                        // 用户还是想用我的 APP 的
                        // 提示用户去应用设置界面手动开启权限
                        showDialogTipUserGoToAppSettting();
                        Log.d("MainActivity", "!b");
                    } else{
                        finish();
                    }
                } else {
                    Log.d("MainActivity", "else");
//                    showDialogTipUserGoToAppSettting();
                    goToAppSetting();

//                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // 提示用户去应用设置界面手动开启权限

    private void showDialogTipUserGoToAppSettting() {

        dialog = new AlertDialog.Builder(this)
                .setTitle("联系人权限不可用")
                .setMessage("请在-应用设置-权限-中，允许获取联系人权限")
                .setPositiveButton("立即开启", (dialog12, which) -> {
                    // 跳转到应用设置界面
                    goToAppSetting();
                })
                .setNegativeButton("取消", (dialog1, which) -> finish()).setCancelable(false).show();
    }

    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);

        startActivityForResult(intent, 321);
    }
}
