package yktong.com.godofdog.fragment;

import android.view.View;
import android.widget.Button;

import yktong.com.godofdog.R;
import yktong.com.godofdog.base.BaseFragment;
import yktong.com.godofdog.manager.WorkManger;
import yktong.com.godofdog.value.SpValue;

/**
 * Created by vampire on 2017/6/13.
 */

public class MainFragment extends BaseFragment implements SpValue {
    @Override
    protected int setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        Button addLinkmanBtn = bindView(R.id.btn_add_linkman);
        Button praiseBtn = bindView(R.id.btn_praise);
        Button queryNumBtn = bindView(R.id.btn_query_num);
        addLinkmanBtn.setOnClickListener(v -> WorkManger.getInstence().doTask(SpValue.STATE_ADD_LINK));
        praiseBtn.setOnClickListener(v -> WorkManger.getInstence().doTask(SpValue.STATE_PRAISE));
        queryNumBtn.setOnClickListener(v -> WorkManger.getInstence().doTask(SpValue.STATE_QUERY_FRIENDS_NUM));
    }

    @Override
    protected void initData() {

    }
}
