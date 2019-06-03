package com.slb.factory.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.slb.factory.R;
import com.slb.factory.http.bean.Brand;
import com.slb.factory.http.bean.Seckill;
import com.slb.frame.utils.DateUtils;

import java.util.List;


/**
 *
 */
public class HomeLimitListAdapter extends BaseQuickAdapter<Seckill, BaseViewHolder> {
	private Context mContext;

	public HomeLimitListAdapter(List<Seckill> data, Context ctx) {
		super(R.layout.adapter_home_limited_time, data);
		this.mContext = ctx;
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, Seckill entity) {
		ImageView imageView = baseViewHolder.getView(R.id.IvImg);
		TextView tvOldAmount = baseViewHolder.getView(R.id.TvOldAmount);
		//数量
		TextView tvCount = baseViewHolder.getView(R.id.mTvNum);
		Glide.with(mContext)
				.load(entity.getHead_img())
				.error(com.slb.frame.R.mipmap.default_image)
				.placeholder(com.slb.frame.R.mipmap.default_image)
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(imageView);
		baseViewHolder.setText(R.id.TvName, entity.getProduct_name());
		baseViewHolder.setText(R.id.TvNewAmount, entity.getSeckill_price()+"");
		tvOldAmount.setText(entity.getOriginal_price()+"");
		tvOldAmount.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );

		Integer startTine = Integer.parseInt(DateUtils.sdateToTimestamp3(entity.getStart_time()));
		Integer curTime = Integer.parseInt(DateUtils.getTimestamp());

		Logger.d("startTine:",startTine);
		Logger.d("curTime:",curTime);

		if(curTime>startTine){
			//秒杀开始
			tvCount.setVisibility(View.VISIBLE);
			tvCount.setText(Html.fromHtml(mContext.getString(R.string.order_limit_num,entity.getTotal_stock()+"",entity.getRemain_stock()+"")));

			baseViewHolder.setText(R.id.mTvStateDes, "秒杀已开始");
			baseViewHolder.setTextColor(R.id.Btn, R.color.white);
			if(entity.getRemain_stock() == 0){
				//已抢光
				baseViewHolder.setText(R.id.Btn, "已抢光");
				baseViewHolder.setBackgroundRes(R.id.Btn,R.drawable.slt_btn_not_enable);
			}else{
				//未抢光
				baseViewHolder.setText(R.id.Btn, "立即抢购");
				baseViewHolder.setBackgroundRes(R.id.Btn,R.drawable.slt_btn_button_gradual);
			}
		}else{
			//未开始
			tvCount.setVisibility(View.GONE);
			baseViewHolder.setTextColor(R.id.Btn, R.color.btn_small_stroke);
			baseViewHolder.setBackgroundRes(R.id.Btn,R.drawable.slt_btn_bottom_stroke);
			baseViewHolder.setText(R.id.Btn, entity.getStart_time()+"开抢");
			baseViewHolder.setText(R.id.mTvStateDes, "距离开始仅剩");
		}
	}
}