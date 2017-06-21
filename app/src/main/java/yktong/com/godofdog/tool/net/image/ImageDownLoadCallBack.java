package yktong.com.godofdog.tool.net.image;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by vampire on 2017/6/19.
 */

public interface ImageDownLoadCallBack {
    void onDownLoadSuccess(File file);
    void onDownLoadSuccess(Bitmap bitmap);

    void onDownLoadFailed();
}
