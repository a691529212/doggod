package yktong.com.godofdog.util;

import android.widget.Toast;

import yktong.com.godofdog.base.MyApp;

/**
 * Created by Vampire on 2017/4/21.
 */

public class SupportUtil {
    private final String VERSION_6325 = "6.3.25";
    private final String VERSION_657 = "6.5.7";
    private final String VERSION_654 = "6.5.4";
    private final String VERSION_658 = "6.5.8";
    private final String[] SUPPORT_VERSION = new String[]{
            VERSION_6325
    };

    public SupportUtil(String version) {
        if (version.equals(VERSION_6325)) {
            Ver6325();
        } else if (version.equals(VERSION_657)) {
            Ver657();
        } else if (version.equals(VERSION_654)) {
            Ver654();
        } else if (version.equals(VERSION_658)) {
            Ver658();
        } else {
            Toast.makeText(MyApp.getmContext(), "版本尚未适配", Toast.LENGTH_SHORT).show();
        }
    }


    // 好友选择界面
    private final String selectUI = "com.tencent.mm.ui.contact.SelectContactUI";
    // 聊天界面
    private final String chattingUI = "com.tencent.mm.ui.chatting.ChattingUI";
    // 群组详情界面
    private final String chatRoomInfo = "com.tencent.mm.plugin.chatroom.ui.ChatroomInfoUI";
    // 首页
    private final String launcherUI = "com.tencent.mm.ui.LauncherUI";
    //加载弹窗
    private final String progressDialog = "com.tencent.mm.ui.base.p";
    //弹出 Dialog
    private final String dialog = "com.tencent.mm.ui.base.h";
    //发起群聊弹框
    private final String createGroup = "android.widget.FrameLayout";
    // 搜索页面
    private final String searchUI = "com.tencent.mm.plugin.search.ui.FTSMainUI";
    // 用户信息页面
    private final String contactInfoUI = "com.tencent.mm.plugin.profile.ui.ContactInfoUI";
    //新的朋友页面
    private final String FMesssageConversationUI = "com.tencent.mm.plugin.subapp.ui.friend.FMessageConversationUI";
    //分享页面
    private final String snsUploadUi = "com.tencent.mm.plugin.sns.ui.SnsUploadUI";
    //选取谁可以看
    private final String snsLabelUi = "com.tencent.mm.plugin.sns.ui.SnsLabelUI";
    //添加朋友页面
    private final String addMoreFriendsUI = "com.tencent.mm.plugin.subapp.ui.pluginapp.AddMoreFriendsUI";
    // 添加好友详情信息页
    private String sayHiWithSnsPermissionUI = "com.tencent.mm.plugin.profile.ui.SayHiWithSnsPermissionUI";
    // 朋友圈页
    private String snsUi = "com.tencent.mm.plugin.sns.ui.En_424b8e16";


    private String dialogHintTextId;
    private String contactInfoUiNickNameId;
    private String contactInfoUIGenderId;
    private String fTSMainUICleanEtId;
    private String fTSMainUIItemId;
    private String fTSMainUISearchEtId;
    private String launcherPagerId;
    private String launcherNewFriendId;
    private String FMesssageConversationUI_LV_ID;
    private String FMesssageConversationUI_ADD_BTN_ID;
    private String whoCanSeeItemId;
    private String nickNameTvId;
    private String searchEditId;
    private String noSuchTextId;
    private String checkItemIvId;
    private String actionMoreItemId;
    private String wxqqNumberViewId;
    private String searchItemId;
    private String textId;
    private String contactInfoUIaddBtnId;
    private String sayHiContantEtId;
    private String sayHiNicknameEtId;
    private String snsListViewId; // 朋友圈列表ID
    private String snsCommentBtnId; //展开评论选项
    private String snsPraiseBtnId; // 点赞


    public String getCreateGroup() {
        return createGroup;
    }

    private String selectUIListViewId = "com.tencent.mm:id/bd3";
    private String selectUICheckBoxId = "com.tencent.mm:id/lg";
    private String selectUIItemId = "com.tencent.mm:id/a5s";
    private String selectUINickNameId = "com.tencent.mm:id/ib";
    private String chattingUIMessageId = "com.tencent.mm:id/h7";
    private String groupInfoListViewId = "android:id/list";
    private String sendRequestBtnId = "com.tencent.mm:id/eu";
    private String moreAcionBtnId = "com.tencent.mm:id/dq";
    private String createGroupLaunchBtnId = "com.tencent.mm:id/aes";
    private String quiteGroupBtnId = "android:id/title";
    private String makeSureQuiteId = "com.tencent.mm:id/dr";


    private void Ver6325() {
        selectUIListViewId = "com.tencent.mm:id/bd3";
        selectUICheckBoxId = "com.tencent.mm:id/lg";
        selectUIItemId = "com.tencent.mm:id/a5s";
        selectUINickNameId = "com.tencent.mm:id/ib";
        chattingUIMessageId = "com.tencent.mm:id/h7";
        groupInfoListViewId = "android:id/list";
        sendRequestBtnId = "com.tencent.mm:id/eu";
        moreAcionBtnId = "com.tencent.mm:id/dq";
        createGroupLaunchBtnId = "com.tencent.mm:id/aes";
        quiteGroupBtnId = "android:id/title";
        makeSureQuiteId = "com.tencent.mm:id/dr";
    }


    private void Ver654() {
        dialogHintTextId = "com.tencent.mm:id/bpn";
        contactInfoUiNickNameId = "com.tencent.mm:id/la";
        contactInfoUIGenderId = "com.tencent.mm:id/ad_";
        fTSMainUICleanEtId = "com.tencent.mm:id/gs";
        fTSMainUISearchEtId = "com.tencent.mm:id/gr";
        fTSMainUIItemId = "com.tencent.mm:id/axr";
        launcherPagerId = "com.tencent.mm:id/ak";
        launcherNewFriendId = "com.tencent.mm:id/aue";
        FMesssageConversationUI_LV_ID = "com.tencent.mm:id/auv";
        FMesssageConversationUI_ADD_BTN_ID = "com.tencent.mm:id/aup"; //添加按钮 text 值添加
        whoCanSeeItemId = "com.tencent.mm:id/cok";
        sendRequestBtnId = "com.tencent.mm:id/gd";
        nickNameTvId = "com.tencent.mm:id/gh";
        searchEditId = "com.tencent.mm:id/gr";
        noSuchTextId = "com.tencent.mm:id/ez";
        searchItemId = "com.tencent.mm:id/axr";
//        selectUICheckBoxId = "com.tencent.mm:id/lg";
        checkItemIvId = "com.tencent.mm:id/cl1";
        actionMoreItemId = "com.tencent.mm:id/lg";
        wxqqNumberViewId = "com.tencent.mm:id/bql";
        moreAcionBtnId = "com.tencent.mm:id/f_";
        searchItemId = "com.tencent.mm:id/iz";
        textId = "com.tencent.mm:id/ax2";
    }

    private void Ver657() {
        selectUIListViewId = "com.tencent.mm:id/es";
        selectUICheckBoxId = "com.tencent.mm:id/nh";
        selectUIItemId = "com.tencent.mm:id/a_c";
        selectUINickNameId = "com.tencent.mm:id/j2";
        chattingUIMessageId = "com.tencent.mm:id/if";
        groupInfoListViewId = "android:id/list";
        sendRequestBtnId = "com.tencent.mm:id/gd";
        moreAcionBtnId = "com.tencent.mm:id/f_";
        createGroupLaunchBtnId = "com.tencent.mm:id/lm";
        quiteGroupBtnId = "android:id/title";
        makeSureQuiteId = "com.tencent.mm:id/abz";
    }

    private void Ver658() {
        moreAcionBtnId = "com.tencent.mm:id/fh";
        actionMoreItemId = "com.tencent.mm:id/mj";
        wxqqNumberViewId = "ccom.tencent.mm:id/bv0";
        searchEditId = "com.tencent.mm:id/gz";
        searchItemId = "com.tencent.mm:id/ja";
        fTSMainUICleanEtId = "com.tencent.mm:id/h0";
        textId = "com.tencent.mm:id/azw";

        FMesssageConversationUI_ADD_BTN_ID = "com.tencent.mm:id/axf";
        contactInfoUIaddBtnId = "com.tencent.mm:id/aej";
        sayHiContantEtId = "com.tencent.mm:id/ce8";
        sayHiNicknameEtId = "com.tencent.mm:id/ceb";
        sendRequestBtnId = "com.tencent.mm:id/gl";

        snsListViewId = "com.tencent.mm:id/csl";
        snsCommentBtnId = "com.tencent.mm:id/coz";
        snsPraiseBtnId = "com.tencent.mm:id/co0";


    }

    public String getSearchItemId() {
        return searchItemId;
    }

    public String getDialog() {
        return dialog;
    }

    public String getSendRequestBtnId() {
        return sendRequestBtnId;
    }

    public String getMoreAcionBtnId() {
        return moreAcionBtnId;
    }

    public String getCreateGroupLaunchBtnId() {
        return createGroupLaunchBtnId;
    }

    public String getQuiteGroupBtnId() {
        return quiteGroupBtnId;
    }

    public String getSearchUI() {
        return searchUI;
    }

    public String getContactInfoUI() {
        return contactInfoUI;
    }

    public String getMakeSureQuiteId() {
        return makeSureQuiteId;
    }

    public String getSelectUI() {
        return selectUI;
    }

    public String getChattingUI() {
        return chattingUI;
    }

    public String getChatRoomInfo() {
        return chatRoomInfo;
    }

    public String getLauncherUI() {
        return launcherUI;
    }

    public String getProgressDialog() {
        return progressDialog;
    }

    public String getSelectUIListViewId() {
        return selectUIListViewId;
    }

    public String getSelectUICheckBoxId() {
        return selectUICheckBoxId;
    }

    public String getSelectUIItemId() {
        return selectUIItemId;
    }

    public String getSelectUINickNameId() {
        return selectUINickNameId;
    }

    public String getChattingUIMessageId() {
        return chattingUIMessageId;
    }

    public String getGroupInfoListViewId() {
        return groupInfoListViewId;
    }

    public String getDialogHintTextId() {
        return dialogHintTextId;
    }

    public String getContactInfoUiNickNameId() {
        return contactInfoUiNickNameId;
    }

    public String getContactInfoUIGenderId() {
        return contactInfoUIGenderId;
    }

    public String getfTSMainUICleanEtId() {
        return fTSMainUICleanEtId;
    }

    public String getfTSMainUIItemId() {
        return fTSMainUIItemId;
    }

    public String getfTSMainUISearchEtId() {
        return fTSMainUISearchEtId;
    }

    public String getLauncherPagerId() {
        return launcherPagerId;
    }

    public String getLauncherNewFriendId() {
        return launcherNewFriendId;
    }

    public String getFMesssageConversationUI() {
        return FMesssageConversationUI;
    }

    public String getFMesssageConversationUI_LV_ID() {
        return FMesssageConversationUI_LV_ID;
    }

    public String getFMesssageConversationUI_ADD_BTN_ID() {
        return FMesssageConversationUI_ADD_BTN_ID;
    }

    public String getSnsUploadUi() {
        return snsUploadUi;
    }

    public String getSnsLabelUi() {
        return snsLabelUi;
    }

    public String getWhoCanSeeItemId() {
        return whoCanSeeItemId;
    }

    public String getNickNameTvId() {
        return nickNameTvId;
    }

    public String getSearchEditId() {
        return searchEditId;
    }

    public String getNoSuchTextId() {
        return noSuchTextId;
    }

    public String getCheckItemIvId() {
        return checkItemIvId;
    }

    public String getAddMoreFriendsUI() {
        return addMoreFriendsUI;
    }

    public String getActionMoreItemId() {
        return actionMoreItemId;
    }

    public String getWxqqNumberViewId() {
        return wxqqNumberViewId;
    }

    public String getTextId() {
        return textId;
    }

    public String getContactInfoUIaddBtnId() {
        return contactInfoUIaddBtnId;
    }

    public String getSayHiWithSnsPermissionUI() {
        return sayHiWithSnsPermissionUI;
    }

    public String getSayHiContantEtId() {
        return sayHiContantEtId;
    }

    public String getSayHiNicknameEtId() {
        return sayHiNicknameEtId;
    }

    public String getSnsUi() {
        return snsUi;
    }

    public String getSnsListViewId() {
        return snsListViewId;
    }

    public String getSnsCommentBtnId() {
        return snsCommentBtnId;
    }

    public String getSnsPraiseBtnId() {
        return snsPraiseBtnId;
    }
}
