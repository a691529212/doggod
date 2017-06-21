package yktong.com.godofdog.tool.net;


import okhttp3.OkHttpClient;


public class NetTool extends OkHttpClient implements NetInterface {
    private static NetTool ourInstance;
    private NetInterface mNetInterface;

    public static NetTool getInstance() {
        if (ourInstance == null) {
            synchronized (NetTool.class) {
                if (ourInstance == null) {
                    ourInstance = new NetTool();
                }
            }
        }
        return ourInstance;
    }

    private NetTool() {
        mNetInterface = new OkHttpUtil();
    }

    @Override
    public <T> void startRequest(String type, String url, Class<T> bean, OnHttpCallBack<T> callBack) {
        mNetInterface.startRequest(type, url, bean, callBack);
    }


}
