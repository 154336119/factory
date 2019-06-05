package com.slb.factory.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.slb.factory.Base;
import com.slb.factory.MyConstants;
import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.bean.OrderEntity;
import com.slb.factory.ui.activity.WebViewActivity;
import com.slb.factory.ui.adapter.OrderAdapter;
import com.slb.factory.ui.contract.OrderAllContract;
import com.slb.factory.ui.presenter.OrderAllPresenter;
import com.slb.frame.http2.retrofit.HttpResult;
import com.slb.frame.utils.ActivityUtil;

import danfei.shulaibao.widget.refresh.BaseBrvahRefreshFragment;
import rx.Observable;

public class OrderAllStatusFragment
		extends BaseBrvahRefreshFragment<OrderAllContract.IView,OrderAllContract.IPresenter, Object,OrderEntity>
		implements OrderAllContract.IView{

	private Integer mStatus=0;
	com.slb.factory.http.bean.OrderEntity OrderEntity;
	public static OrderAllStatusFragment newInstance(Integer status) {
		Bundle args = new Bundle();
		args.putInt(MyConstants.BUNDLE_ORDER_STATUS,status);
		OrderAllStatusFragment fragment = new OrderAllStatusFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected boolean rxBusRegist() {
		return true;
	}

	@Override
	protected void setIntentData() {
		super.setIntentData();
		mStatus=getArguments().getInt(MyConstants.BUNDLE_ORDER_STATUS);
	}

	@Override
	public OrderAllContract.IPresenter initPresenter() {
		return new OrderAllPresenter();
	}


	@Override
	protected RecyclerView.Adapter setAdapter() {
		return new OrderAdapter(mList,mStatus,_mActivity);
	}

	@Override
	protected Observable<HttpResult<Object, OrderEntity>> requestHttp() {
		return RetrofitSerciveFactory.provideComService().getOrderList(Base.getUserEntity().getToken(),mStatus,PAGE_SIZE, mCurrentPage);
	}

	@Override
	protected void onItemClickListener(View view, RecyclerView.Adapter adapter, int position) {
		OrderEntity entity=mList.get(position);
		Bundle bundle = new Bundle();
		bundle.putString("url", MyConstants.h5Url + MyConstants.url_dingdanxiangqing+entity.getId());
		bundle.putString("title", "订单详情");
		ActivityUtil.next(_mActivity, WebViewActivity.class,bundle,false);
	}

	@Override
	protected void onItemChildClickListener(View view, RecyclerView.Adapter adapter, int position) {
		super.onItemChildClickListener(view, adapter, position);
	}

}
