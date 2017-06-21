package yktong.com.godofdog.tool.net;/**
 * Created by tarena on 2016/11/9.
 */

/**
 * created by Vampire
 * on: 2016/11/9 上午8:00
 */
public interface PostFiel {
    <T> void startRequest(String url, Class<T> bean, OnHttpCallBack<T> callBack, String postValue, String postHead);
}
