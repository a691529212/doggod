package yktong.com.godofdog.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import yktong.com.godofdog.R;


public abstract class BaseFragment extends Fragment {
    private static final String TAG = "Vampire_BaseFragment";

    protected Context mContext;
    private CustomProgressDialog mProgressDialog;
    //Fragment 当前状态是否可见
    protected boolean isVisible;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new CustomProgressDialog(getActivity(), "正在努力加载...", R.drawable.runing_man);
        }
        mProgressDialog.show();
    }

    public void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.hide();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(setLayout(), container, false);
    }

    protected abstract
    @LayoutRes
    int setLayout();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }


    protected abstract void initView();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    protected <T extends View> T bindView(int id) {

        return (T) getView().findViewById(id);
    }

    protected <T extends View> T bindView(View view, int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
