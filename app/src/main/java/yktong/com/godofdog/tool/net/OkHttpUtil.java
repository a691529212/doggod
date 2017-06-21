package yktong.com.godofdog.tool.net;


import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import yktong.com.godofdog.tool.thread.ThreadPool;

/*
 * Created by vampire on 2016年11月07日16:50:59.
 */

public class OkHttpUtil implements NetInterface {
    private OkHttpClient mClient;
    //这样定义的handler对象无论在哪里创建的 都是属于主线程的
    private Handler mHandler =
            new Handler(Looper.getMainLooper());
    private Gson mGson;
    public static final String GET = "get";
    public static final String POST = "post";

    public OkHttpUtil() {
        super();
        mGson = new Gson();
        //获取系统的sd卡
        File path =
                Environment.getExternalStorageDirectory();
        //初始化okhttp
        mClient = new OkHttpClient.Builder()
                //设置缓存位置 以及缓存大小
                .cache(new Cache(path, 10 * 1024 * 1024))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public <T> void startRequest(final String type, final String url, final Class<T> tClass, final OnHttpCallBack<T> callBack) {
        Log.d("OkHttpUtil", url);
        ThreadPool.thredP.execute(() -> {
            Log.d("OkHttpUtil", Thread.currentThread().getName());
            if (type.equals(POST)) {
                Request request;
                //有的请求需要加body
                FormBody.Builder builder = new FormBody.Builder();
                RequestBody requestBody = builder.build();
                request = new Request
                        .Builder().url(url)
                        .post(requestBody)
                        .build();
                Log.d("OkHttpUtil", url);
                mClient.newCall(request).enqueue(new Callback() {

                    private Object result;

                    @Override
                    public void onFailure(Call call, final IOException e) {
                        mHandler.post(() -> {
                            callBack.onError(e);
                            Log.d("OkHttpUtil", "请求失败" + e.toString());
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string().trim();
                        Log.e("OkHttpUtil", str);

                        try {
                            result = mGson.fromJson(str, tClass);
                        } catch (Exception e) {
                            Log.d("OkHttpUtil", e.toString());
                        }
                        mHandler.post(() -> callBack.onSuccess((T) result));

                    }

                });
            } else {
                Request request;
                request = new Request
                        .Builder().url(url)
                        .build();

                mClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        mHandler.post(() -> {
                            Log.d("OkHttpUtil", "请求失败" + e.toString() + "\n" + url);
                            callBack.onError(e);
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("OkHttpUtil", response.toString());
                        String str = response.body().string().trim();
                        try {
                            final T result = mGson.fromJson(str, tClass);
                            Log.d("OkHttpUtil", tClass.getName());
                            mHandler.post(() -> callBack.onSuccess(result));
                        } catch (JsonSyntaxException e) {
                            Log.d("OkHttpUtil", "e:" + e);
                            e.printStackTrace();
                        }

                    }

                });
            }

        });
    }

}


