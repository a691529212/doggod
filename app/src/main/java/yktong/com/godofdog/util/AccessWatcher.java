package yktong.com.godofdog.util;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import yktong.com.godofdog.base.MyApp;
import yktong.com.godofdog.service.WeService;

/**
 * Created by vampire on 2017/6/13.
 */

public class AccessWatcher {
    private static final String TAG = "AccessWatcher";
    private static AccessWatcher instence;

    private AccessWatcher() {
    }

    public static AccessWatcher getInstence() {
        if (instence == null) {
            synchronized (AccessWatcher.class) {
                if (instence == null) {
                    instence = new AccessWatcher();
                }
            }
        }
        return instence;
    }

    public boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;
        // TestService为对应的服务
        final String service = MyApp.getmContext().getPackageName() + "/" + WeService.class.getCanonicalName();
        Log.i(TAG, "service:" + service);
        try {
            accessibilityEnabled = Settings.Secure.getInt(mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
            Log.v(TAG, "accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "Error finding setting, default accessibility to not found: " + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            Log.v(TAG, "***ACCESSIBILITY IS ENABLED*** -----------------");
            String settingValue = Settings.Secure.getString(mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();

                    Log.v(TAG, "-------------- > accessibilityService :: " + accessibilityService + " " + service);
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        Log.v(TAG, "We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }
            }
        } else {
            Log.v(TAG, "***ACCESSIBILITY IS DISABLED***");
        }
        return false;
    }
}
