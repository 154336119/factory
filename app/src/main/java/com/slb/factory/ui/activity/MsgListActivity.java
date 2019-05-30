package com.slb.factory.ui.activity;

import com.slb.factory.R;
import com.slb.factory.ui.fragment.MsgListFragment;
import com.slb.frame.ui.activity.BaseActivity;

/**
 * 刁剑
 * Created on 2017/1/3.
 * 注释:
 */

public class MsgListActivity extends BaseActivity/*extends BaseBrvahRefreshActivity<MsgListContract.IView,MsgListContract.IPresenter,Object,MsgEntity> implements MsgListContract.IView*/{
    @Override
    protected boolean hasToolbar() {
        return true;
    }
    @Override
    protected String setToolbarTitle() {
        return "消息";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_for_fragment_container;
    }

    @Override
    public void initView() {
        super.initView();
        loadRootFragment(R.id.container, MsgListFragment.newInstance());
    }

}
