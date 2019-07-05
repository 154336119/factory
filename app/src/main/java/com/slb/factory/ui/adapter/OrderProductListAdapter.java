package com.slb.factory.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.slb.factory.R;
import com.slb.factory.http.bean.ProductEntity;
import com.slb.factory.ui.adapter.base.CommonBaseAdapter;

import java.text.DecimalFormat;


/**
 *
 */
public class OrderProductListAdapter extends CommonBaseAdapter<ProductEntity> {

	public OrderProductListAdapter(Context context) {
		super(context);
	}
	private ViewHolder mHolder;
	@SuppressWarnings("deprecation")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (null == convertView) {
			mHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.adapter_mylist_order_product, parent, false);
			mHolder.mTvProductName = (TextView) convertView.findViewById(R.id.mTvProductName);
			mHolder.mTvColor = (TextView) convertView.findViewById(R.id.mTvColor);
			mHolder.mTvMoney = (TextView) convertView.findViewById(R.id.mTvMoney);
			mHolder.TvNum = (TextView) convertView.findViewById(R.id.TvNum);
			mHolder.Line = (View) convertView.findViewById(R.id.Line);
			mHolder.IvImg = (ImageView) convertView.findViewById(R.id.IvImg);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		ProductEntity entity = getItem(position);
		if(!TextUtils.isEmpty(entity.getName())){
			mHolder.mTvProductName.setText(entity.getName());
		}else{
			mHolder.mTvProductName.setText("暂无");
		}
		if(!TextUtils.isEmpty(entity.getSpec_value())){
			mHolder.mTvColor.setText(entity.getSpec_value());
		}else{
			mHolder.mTvColor.setText("暂无");
		}
		if(entity.getSingle_price()!=null){
			mHolder.mTvMoney.setText("¥"+new DecimalFormat("0.00").format(entity.getSingle_price()));
		}else{
			mHolder.mTvMoney.setText("暂无");
		}
		if(entity.getNum()!=null){
			mHolder.TvNum.setText("×"+entity.getNum());
		}else{
			mHolder.TvNum.setText("暂无");
		}
		if(!TextUtils.isEmpty(entity.getHead_img())){
			Glide.with(mContext)
					.load(entity.getHead_img())
					.error(com.slb.frame.R.mipmap.default_image)
					.placeholder(com.slb.frame.R.mipmap.default_image)
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					.into(mHolder.IvImg);
		}else{
			Glide.with(mContext)
					.load("")
					.error(com.slb.frame.R.mipmap.default_image)
					.placeholder(com.slb.frame.R.mipmap.default_image)
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					.into(mHolder.IvImg);
		}


		if(position == getCount()){
			mHolder.Line.setVisibility(View.INVISIBLE);
		}else{
			mHolder.Line.setVisibility(View.VISIBLE);
		}
		return convertView;
	}
	static class ViewHolder {
		private TextView mTvProductName;
		private TextView mTvColor;
		private TextView mTvMoney;
		private TextView TvNum;
		private View Line;
		private ImageView IvImg;
	}

}
