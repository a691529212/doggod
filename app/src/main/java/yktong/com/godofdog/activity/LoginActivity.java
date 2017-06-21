package yktong.com.godofdog.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import yktong.com.godofdog.R;
import yktong.com.godofdog.base.BaseActivity;

/**
 * Created by vampire on 2017/6/20.
 */

public class LoginActivity extends BaseActivity {

    private EditText companyIdEt; //企业ID
    private EditText phoneEt; // 手机号
    private EditText codeEt; // 验证码
    private Button requesCodeBtn; // 请求验证码
    private Button loginBtn;  // 登录

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        bindView();
        phoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int len = s.length();
                if (len == 11) {
                    requesCodeBtn.setBackgroundResource(R.drawable.shape_login);
                } else {
                    requesCodeBtn.setBackgroundResource(R.drawable.shape_login_un);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    protected void initData() {

    }

    // 绑定布局
    private void bindView() {
        companyIdEt = bindView(R.id.company_id_et);
        phoneEt = bindView(R.id.login_phone_et);
        codeEt = bindView(R.id.login_code_et);
        requesCodeBtn = bindView(R.id.login_request_code_btn);
        loginBtn = bindView(R.id.login_btn);
    }

}
