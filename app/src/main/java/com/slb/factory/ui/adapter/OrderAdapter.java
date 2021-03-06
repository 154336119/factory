package com.slb.factory.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slb.factory.R;
import com.slb.factory.http.bean.OrderEntity;
import com.slb.factory.weight.MyListView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Gifford on 2017/12/2.
 */

public class OrderAdapter extends BaseQuickAdapter<OrderEntity, BaseViewHolder> {
	private int orderState;
	private Context mContext;

	public OrderAdapter(List<OrderEntity> data,int orderState, Context ctx) {
		super(R.layout.adapter_order_item,data);
		this.orderState= orderState;
		this.mContext=ctx;
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, OrderEntity entity) {
		baseViewHolder.setText(R.id.mTvOrderNo,"订单编号："+entity.getOrder_code());
		baseViewHolder.setText(R.id.mTvDate,entity.getCreate_time());
		baseViewHolder.setText(R.id.mTvOrderMoney,"¥"+new DecimalFormat("0.00").format(entity.getPay_money()));
		if(orderState == 0){
			//已下单 - 上传凭证
			baseViewHolder.setVisible(R.id.mTvActionUploadProofs,true);
			baseViewHolder.setVisible(R.id.LLActionState3,false);
			baseViewHolder.setVisible(R.id.mTvActionTisp,false);
		}else if(orderState == 3){
			//待收货 - 查看物流,确认收货
			baseViewHolder.setVisible(R.id.LLActionState3,true);
			baseViewHolder.setVisible(R.id.mTvActionUploadProofs,false);
			baseViewHolder.setVisible(R.id.mTvActionTisp,false);
		}else if(orderState == 1){
			//待发货
			 if(entity.getState() == 2){
				//待发货
				baseViewHolder.setVisible(R.id.LLActionState3,false);
				baseViewHolder.setVisible(R.id.mTvActionUploadProofs,false);
				baseViewHolder.setVisible(R.id.mTvActionTisp,true);
				baseViewHolder.setText(R.id.mTvActionTisp,"待发货");
			}else if(entity.getState() == 6){
				//待发货
				baseViewHolder.setVisible(R.id.LLActionState3,false);
				baseViewHolder.setVisible(R.id.mTvActionUploadProofs,false);
				baseViewHolder.setVisible(R.id.mTvActionTisp,true);
				baseViewHolder.setText(R.id.mTvActionTisp,"申请退款中");
			}
		}else if(orderState == 4){
			//已完成
			baseViewHolder.setVisible(R.id.LLActionState3,false);
			baseViewHolder.setVisible(R.id.mTvActionUploadProofs,false);
			baseViewHolder.setVisible(R.id.mTvActionTisp,true);
			baseViewHolder.setText(R.id.mTvActionTisp,"该订单已完成");
		}else if(orderState == 5){
			//已取消
			baseViewHolder.setVisible(R.id.LLActionState3,false);
			baseViewHolder.setVisible(R.id.mTvActionUploadProofs,false);
			baseViewHolder.setVisible(R.id.mTvActionTisp,true);
			baseViewHolder.setText(R.id.mTvActionTisp,"该订单已取消");
		}
		MyListView myListView = baseViewHolder.getView(R.id.MyListView);
		OrderProductListAdapter myListAdapter = new OrderProductListAdapter(mContext);
		myListAdapter.setList(entity.getProductList());
		myListView.setAdapter(myListAdapter);

		baseViewHolder.addOnClickListener(R.id.mTvActionUploadProofs);
		baseViewHolder.addOnClickListener(R.id.mTvActionSeeEms);
		baseViewHolder.addOnClickListener(R.id.mTvActionConfirm);
	}


}
