package com.slb.factory.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.slb.factory.Base;
import com.slb.factory.MyConstants;
import com.slb.factory.R;
import com.slb.factory.event.OrderNumRefreshEvent;
import com.slb.factory.http.bean.ConfigEntity;
import com.slb.factory.http.bean.UserEntity;
import com.slb.factory.ui.activity.InviteFriendsActivity;
import com.slb.factory.ui.activity.MsgListActivity;
import com.slb.factory.ui.activity.OrderListActiivty;
import com.slb.factory.ui.activity.SettingActivity;
import com.slb.factory.ui.activity.WebViewActivity;
import com.slb.factory.ui.contract.MineContract;
import com.slb.factory.ui.presenter.MinePresenter;
import com.slb.factory.util.SharedPreferencesUtils;
import com.slb.factory.util.config.BizcContant;
import com.slb.frame.ui.fragment.BaseMvpFragment;
import com.slb.frame.utils.ActivityUtil;
import com.slb.frame.utils.ImageLoadUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


public class MineFragment
        extends BaseMvpFragment<MineContract.IView, MineContract.IPresenter>
        implements MineContract.IView {

    Unbinder unbinder;
    @BindView(R.id.TvHead)
    CircleImageView TvHead;
    @BindView(R.id.TvName)
    TextView TvName;
    @BindView(R.id.IvSetting)
    ImageView IvSetting;
    @BindView(R.id.IvMsg)
    ImageView IvMsg;
    @BindView(R.id.RlOrderList)
    RelativeLayout RlOrderList;
    @BindView(R.id.mTvYiXiaDanCount)
    TextView mTvYiXiaDanCount;
    @BindView(R.id.BtnOrder)
    RelativeLayout BtnOrder;
    @BindView(R.id.mTvDaiFaHuoCount)
    TextView mTvDaiFaHuoCount;
    @BindView(R.id.BtnOrderSend)
    RelativeLayout BtnOrderSend;
    @BindView(R.id.mTvDaiShouHuoCount)
    TextView mTvDaiShouHuoCount;
    @BindView(R.id.BtnOrderReceive)
    RelativeLayout BtnOrderReceive;
    @BindView(R.id.mTvDoneCount)
    TextView mTvDoneCount;
    @BindView(R.id.BtnOrderDone)
    RelativeLayout BtnOrderDone;
    @BindView(R.id.mTvCancelCount)
    TextView mTvCancelCount;
    @BindView(R.id.btnOrderCancel)
    RelativeLayout btnOrderCancel;
    @BindView(R.id.RlAddr)
    RelativeLayout RlAddr;
    @BindView(R.id.RlInvitation)
    RelativeLayout RlInvitation;
    @BindView(R.id.RlOptinion)
    RelativeLayout RlOptinion;
    @BindView(R.id.Tv01)
    TextView Tv01;
    @BindView(R.id.mViewBaseLine)
    View mViewBaseLine;
    @BindView(R.id.Tv02)
    TextView Tv02;
    @BindView(R.id.mViewBaseLine2)
    View mViewBaseLine2;
    @BindView(R.id.Tv03)
    TextView Tv03;
    @BindView(R.id.mViewBaseLine3)
    View mViewBaseLine3;
    @BindView(R.id.Tv04)
    TextView Tv04;
    @BindView(R.id.mViewBaseLine4)
    View mViewBaseLine4;
    @BindView(R.id.Tv05)
    TextView Tv05;
    @BindView(R.id.mViewBaseLine5)
    View mViewBaseLine5;
    @BindView(R.id.RlPhone)
    RelativeLayout RlPhone;


    @Override
    protected boolean hasToolbar() {
        return false;
    }


    public static MineFragment newInstance() {
        MineFragment instance = new MineFragment();
        return instance;
    }

    @Override
    public MineContract.IPresenter initPresenter() {
        return new MinePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        if (!TextUtils.isEmpty(Base.getUserEntity().getNick_name())) {
            TvName.setText(Base.getUserEntity().getNick_name());
        }
        if (!TextUtils.isEmpty(Base.getUserEntity().getLogo())) {
            ImageLoadUtil.loadImage(_mActivity, Base.getUserEntity().getLogo(), TvHead);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.IvSetting, R.id.IvMsg, R.id.RlOrderList, R.id.BtnOrder, R.id.BtnOrderSend
            , R.id.BtnOrderReceive, R.id.BtnOrderDone, R.id.btnOrderCancel
            , R.id.RlAddr, R.id.RlInvitation, R.id.RlOptinion,R.id.RlPhone,R.id.TvHead})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.IvSetting:
                //设置
                ActivityUtil.next(_mActivity, SettingActivity.class);
                break;
            case R.id.IvMsg:
                //消息中心
                ActivityUtil.next(_mActivity, MsgListActivity.class);
                break;
            case R.id.RlOrderList:
                ActivityUtil.next(_mActivity, OrderListActiivty.class);
                break;
            case R.id.BtnOrder:
                bundle.putInt("POS", 0);
                ActivityUtil.next(_mActivity, OrderListActiivty.class, bundle, false);
                break;
            case R.id.BtnOrderSend:
                bundle.putInt("POS", 1);
                ActivityUtil.next(_mActivity, OrderListActiivty.class, bundle, false);
                break;
            case R.id.BtnOrderReceive:
                bundle.putInt("POS", 2);
                ActivityUtil.next(_mActivity, OrderListActiivty.class, bundle, false);
                break;
            case R.id.BtnOrderDone:
                bundle.putInt("POS", 3);
                ActivityUtil.next(_mActivity, OrderListActiivty.class, bundle, false);
                break;
            case R.id.btnOrderCancel:
                bundle.putInt("POS", 4);
                ActivityUtil.next(_mActivity, OrderListActiivty.class, bundle, false);
                break;
            case R.id.RlAddr:
                bundle.putString("url", MyConstants.h5Url + MyConstants.url_shouhuodizhiguanli + Base.getUserEntity().getToken());
                bundle.putString("title", "收货地址管理");
                ActivityUtil.next(_mActivity, WebViewActivity.class, bundle, false);
                break;
            case R.id.RlInvitation:
                ActivityUtil.next(_mActivity, InviteFriendsActivity.class, bundle, false);
                break;
            case R.id.RlOptinion:
                bundle.putString("url", MyConstants.h5Url + MyConstants.url_yijianfankui + Base.getUserEntity().getToken());
                bundle.putString("title", "意见反馈");
                ActivityUtil.next(_mActivity, WebViewActivity.class, bundle, false);
                break;
            case R.id.TvHead:
                bundle.putString("url", MyConstants.h5Url + MyConstants.url_person + Base.getUserEntity().getToken());
                bundle.putString("title", "个人信息");
                ActivityUtil.next(_mActivity, WebViewActivity.class, bundle, false);
                break;
            case R.id.RlPhone:
                String configJsonStr = (String) SharedPreferencesUtils.getParam(Base.getContext(), BizcContant.SP_CONFIG, "");
                ConfigEntity entity = JSONObject.parseObject(configJsonStr, ConfigEntity.class);
                if(entity!=null && !TextUtils.isEmpty(entity.getKefu())){
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + entity.getKefu());
                    intent.setData(data);
                    startActivity(intent);
                }
//                Intent intent = new Intent(Intent.ACTION_DIAL);
////                Uri data = Uri.parse("tel:" + phoneNum);
////                intent.setData(data);
////                startActivity(intent);
                break;

        }
    }

    @Override
    public void getOrderNumSuccess(Integer num) {
        if (num != null && num > 0) {
            mTvYiXiaDanCount.setVisibility(View.VISIBLE);
            mTvYiXiaDanCount.setText(num + "");
        } else {
            mTvYiXiaDanCount.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mPresenter.getOurderNum();
        }
    }

    @Subscribe
    public void numRefresh(OrderNumRefreshEvent event) {
        mPresenter.getOurderNum();
    }

    @Override
    protected boolean rxBusRegist() {
        return true;
    }
}
