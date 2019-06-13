package com.slb.factory.ui.fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.slb.factory.MyConstants;
import com.slb.factory.R;
import com.slb.factory.http.bean.PayTypeEntity;
import com.slb.factory.ui.activity.MainActivity;
import com.slb.factory.ui.activity.OrderListActiivty;
import com.slb.frame.ui.fragment.BaseFragment;
import com.slb.frame.utils.ActivityUtil;

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
    @BindView(R.id.TvBankCompany)
    TextView TvBankCompany;
    @BindView(R.id.TvBankTax)
    TextView TvBankTax;
    @BindView(R.id.TvBankAccount)
    TextView TvBankAccount;
    @BindView(R.id.TvBankName)
    TextView TvBankName;
    PayTypeEntity payTypeEntity;
    public static PublicPayFragment newInstance(PayTypeEntity entity) {
        PublicPayFragment fragment = new PublicPayFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("PayTypeEntity",entity);
        fragment.setArguments(bundle);
        return fragment;
    }

    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payTypeEntity = getArguments().getParcelable("PayTypeEntity");
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
        TvBankCompany.setText(payTypeEntity.getBankCompany());
        TvBankTax.setText(payTypeEntity.getBankTax());
        TvBankAccount.setText(payTypeEntity.getBankAccount());
        TvBankName.setText(payTypeEntity.getBankName());
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.BtnPublic1, R.id.BtnPublic2, R.id.BtnPublic3, R.id.BtnPublic4, R.id.BtnGoHome, R.id.BtnGoUpload})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.BtnPublic1:
                CopyToClipboard(TvBankCompany.getText().toString());
                break;
            case R.id.BtnPublic2:
                CopyToClipboard(TvBankTax.getText().toString());
                break;
            case R.id.BtnPublic3:
                CopyToClipboard(TvBankAccount.getText().toString());
                break;
            case R.id.BtnPublic4:
                CopyToClipboard(TvBankName.getText().toString());
                break;
            case R.id.BtnGoHome:
                bundle.putInt(MyConstants.HOME_SELECTED_FRAGMENT,0);
                ActivityUtil.next(_mActivity, MainActivity.class,bundle,true);
                _mActivity.finish();
                break;
            case R.id.BtnGoUpload:
                bundle.putInt("POS",0);
                ActivityUtil.next(_mActivity, OrderListActiivty.class,bundle,true);
                break;
        }
    }

    public void CopyToClipboard( String text){
           ClipboardManager clip = (ClipboardManager)_mActivity.getSystemService(_mActivity.CLIPBOARD_SERVICE);
            clip.setText(text); // 复制
            showToastMsg("复制成功");
    }
}
