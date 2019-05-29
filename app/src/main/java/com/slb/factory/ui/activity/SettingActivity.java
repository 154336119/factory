package com.slb.factory.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slb.factory.R;
import com.slb.frame.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by juan on 2018/9/5.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.TvVersion)
    TextView TvVersion;
    @BindView(R.id.RlAbout)
    RelativeLayout RlAbout;
    @BindView(R.id.TvCacheNum)
    TextView TvCacheNum;
    @BindView(R.id.RlClearCache)
    RelativeLayout RlClearCache;
    @BindView(R.id.RlAgreement)
    RelativeLayout RlAgreement;
    @BindView(R.id.RlDeclare)
    RelativeLayout RlDeclare;

    @Override
    protected String setToolbarTitle() {
        return getString(R.string.Settings);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.RlAbout, R.id.RlClearCache, R.id.RlAgreement, R.id.RlDeclare})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.RlAbout:
                break;
            case R.id.RlClearCache:
                break;
            case R.id.RlAgreement:
                break;
            case R.id.RlDeclare:
                break;
        }
    }
}
