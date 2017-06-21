package yktong.com.godofdog.tool.net;


public interface NetInterface {

    <T> void startRequest(String type, String url,
                          Class<T> tClass
            , OnHttpCallBack<T> callBack);

}
