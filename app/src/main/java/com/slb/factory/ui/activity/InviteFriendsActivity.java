package com.slb.factory.ui.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.slb.factory.R;
import com.slb.frame.ui.activity.BaseActivity;

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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.RlWxInvite)
    public void onViewClicked() {
    }
}
