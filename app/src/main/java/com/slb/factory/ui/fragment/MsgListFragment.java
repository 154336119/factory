package com.slb.factory.ui.fragment;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;


import com.slb.factory.R;
import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.bean.MsgEntity;
import com.slb.factory.ui.adapter.MsgListAdapter;
import com.slb.factory.ui.contract.MsgListContract;
import com.slb.factory.ui.presenter.MsgListPresenter;
import com.slb.frame.http2.retrofit.HttpResult;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

import danfei.shulaibao.widget.AdapterDefaultEnum;
import danfei.shulaibao.widget.refresh.BaseBrvahRefreshFragment;
import rx.Observable;

public class MsgListFragment extends BaseBrvahRefreshFragment<MsgListContract.IView, MsgListContract.IPresenter, Object, MsgEntity> implements MsgListContract.IView {
    private MsgListAdapter mMsgListAdapter = new MsgListAdapter(mList = new ArrayList<>());

    public static MsgListFragment newInstance() {
        return new MsgListFragment();
    }

    @Override
    protected boolean hasToolbar() {
        return false;
    }


    @Override
    protected boolean rxBusRegist() {
        return true;
    }

    @Override
    protected RecyclerView.Adapter setAdapter() {
        return mMsgListAdapter;
    }

    @Override
    protected AdapterDefaultEnum setAdapterDefaultEnum() {
        return AdapterDefaultEnum.NO_MESSAGE;
    }

    @Override
    protected Observable<HttpResult<Object, MsgEntity>> requestHttp() {
        return RetrofitSerciveFactory.provideComService().getMsgList(PAGE_SIZE, mCurrentPage);
    }

    @Override
    public MsgListContract.IPresenter initPresenter() {
        return new MsgListPresenter();
    }


    @Override
    public void successJump() {

    }


    @Override
    protected RecyclerView.ItemDecoration setItemDecoration() {
        return new HorizontalDividerItemDecoration.Builder(_mActivity)
                .color(Color.argb(1, 238, 238, 238)).sizeResId(R.dimen.list_divider_0dp).build();
        }

        }
