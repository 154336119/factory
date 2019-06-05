package com.slb.factory.ui.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.slb.factory.MyConstants;
import com.slb.factory.R;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.utils.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteFriendsActivity extends BaseActivity {
    @BindView(R.id.RlWxInvite)
    RelativeLayout RlWxInvite;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.RlWxInvite)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putString("url", MyConstants.h5Url + MyConstants.url_yaoqinghaoyou );
        bundle.putString("title", "工厂联盟");
        ActivityUtil.next(this, WebViewActivity.class,bundle,false);
    }
}
