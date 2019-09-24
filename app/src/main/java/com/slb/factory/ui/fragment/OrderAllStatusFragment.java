package com.slb.factory.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.slb.factory.Base;
import com.slb.factory.MyConstants;
import com.slb.factory.R;
import com.slb.factory.event.OrderRefreshEvent;
import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.bean.OrderEntity;
import com.slb.factory.ui.activity.UploadProofsActivity;
import com.slb.factory.ui.activity.WebViewActivity;
import com.slb.factory.ui.adapter.OrderAdapter;
import com.slb.factory.ui.contract.OrderAllContract;
import com.slb.factory.ui.presenter.OrderAllPresenter;
import com.slb.frame.http2.retrofit.HttpResult;
import com.slb.frame.utils.ActivityUtil;

import danfei.shulaibao.widget.refresh.BaseBrvahRefreshFragment;
import rx.Observable;

import static com.slb.factory.MyConstants.url_token;

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
		OrderEntity orderEntity = (OrderEntity)mAdapter.getData().get(position);
		if(view.getId() == R.id.mTvActionUploadProofs){
			//立即支付
			Bundle bundle = new Bundle();
			bundle.putString("url", MyConstants.h5Url + MyConstants.url_dingdanxiangqing + orderEntity.getId());
			bundle.putString("title", "订单详情");
			ActivityUtil.next(_mActivity, WebViewActivity.class,bundle,false);
		}else if(view.getId() == R.id.mTvActionSeeEms){
			//查看物流
			Bundle bundle = new Bundle();
			bundle.putString("url", MyConstants.h5Url + MyConstants.url_chakanwuliu + orderEntity.getId());
			bundle.putString("title", "查看物流");
			ActivityUtil.next(_mActivity, WebViewActivity.class,bundle,false);
		}else if(view.getId() == R.id.mTvActionConfirm){
			//确认收货
			showDialog(orderEntity.getId());
		}

	}

	@Subscribe
	public void orderRefresh(OrderRefreshEvent event){
		onRefresh();
	}


	public void showDialog(final long id){
		AlertDialog.Builder builder = new AlertDialog.Builder(_mActivity);
		builder.setMessage("是否确认收货");
		builder.setTitle("温馨提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mPresenter.orderFinish(id+"");
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
}
