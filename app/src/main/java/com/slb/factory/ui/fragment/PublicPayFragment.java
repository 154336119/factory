package com.slb.factory.ui.fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.slb.factory.R;
import com.slb.frame.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 刁剑
 * Created on 2017/12/9
 * 注释:
 */
public class PublicPayFragment extends BaseFragment {

    @BindView(R.id.BtnPublic1)
    TextView BtnPublic1;
    @BindView(R.id.BtnPublic2)
    TextView BtnPublic2;
    @BindView(R.id.BtnPublic3)
    TextView BtnPublic3;
    @BindView(R.id.BtnPublic4)
    TextView BtnPublic4;
    @BindView(R.id.BtnGoHome)
    Button BtnGoHome;
    @BindView(R.id.BtnGoUpload)
    Button BtnGoUpload;

    public static PublicPayFragment newInstance() {
        PublicPayFragment fragment = new PublicPayFragment();
        return fragment;
    }

    Unbinder unbinder;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected boolean hasToolbar() {
        return false;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_choise_pay_public;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.BtnPublic1, R.id.BtnPublic2, R.id.BtnPublic3, R.id.BtnPublic4, R.id.BtnGoHome, R.id.BtnGoUpload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BtnPublic1:
                CopyToClipboard(_mActivity.getString(R.string.public_1));
                break;
            case R.id.BtnPublic2:
                CopyToClipboard(_mActivity.getString(R.string.public_2));
                break;
            case R.id.BtnPublic3:
                CopyToClipboard(_mActivity.getString(R.string.public_3));
                break;
            case R.id.BtnPublic4:
                CopyToClipboard(_mActivity.getString(R.string.public_4));
                break;
            case R.id.BtnGoHome:
                break;
            case R.id.BtnGoUpload:
                break;
        }
    }

    public void CopyToClipboard( String text){
           ClipboardManager clip = (ClipboardManager)_mActivity.getSystemService(_mActivity.CLIPBOARD_SERVICE);
            clip.setText(text); // 复制
            showToastMsg("复制成功");
    }
}
