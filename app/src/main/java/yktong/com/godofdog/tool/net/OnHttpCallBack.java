package yktong.com.godofdog.tool.net;


public interface OnHttpCallBack<T> {
    void onSuccess(T response);
    void onError(Throwable e);
}
