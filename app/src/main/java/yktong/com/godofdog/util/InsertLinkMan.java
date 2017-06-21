package yktong.com.godofdog.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import yktong.com.godofdog.base.MyApp;


/**
 * Created by Vampire on 2017/4/28.
 */

public class InsertLinkMan {
    private static final String TAG = "vampire-addLinkMan";
    // 向通讯录插入联系人信息
    // 通过contentprovider插入联系人到通讯录
    // uriuri三部分组成
    private static Uri idUri = Uri.parse("content://com.android.contacts/raw_contacts");
    private static Uri dataUri = Uri.parse("content://com.android.contacts/data");

    public static void insert(String num, String name) {
        Log.d("addFriendFragment", "indtert");
//        FileUtils.writeFile("/storage/emulated/0/Download/测试.txt", num + "|" + name + "\n", true);
        ContentResolver resolver = MyApp.getmContext().getContentResolver();// 创建ContentResolver对象
        ContentValues cv = new ContentValues();// 创建contentvalues对象
        // 首先向raw_contacts表中插入一条数据(_id),自动生成需要获取到子id值
        try {
            Uri uri = resolver.insert(idUri, cv);
            long id = ContentUris.parseId(uri);// 从当前的uri中把主键提取出来
            cv.put("raw_contact_id", id);// id的key,value
            cv.put("mimetype", "vnd.android.cursor.item/phone_v2");
            cv.put("data1", num);// 手机号码
            resolver.insert(dataUri, cv);
            cv.clear();
            // 添加姓名
            cv.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, id);
            cv.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            cv.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);

            Log.d(TAG, name);
            resolver.insert(dataUri, cv);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.toString());
        }

    }
}
