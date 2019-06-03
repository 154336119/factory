package com.slb.factory.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slb.factory.R;
import com.slb.factory.http.bean.Brand;
import com.slb.factory.http.bean.Goods;

import java.util.List;


/**
 *
 */
public class HomeBrandsListAdapter extends BaseQuickAdapter<Brand, BaseViewHolder> {
	private Context mContext;

	public HomeBrandsListAdapter(List<Brand> data, Context ctx) {
		super(R.layout.adapter_home_hot_selling_brand, data);
		this.mContext = ctx;
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, Brand entity) {
		ImageView imageView = baseViewHolder.getView(R.id.IvImg);
		Glide.with(mContext)
				.load(entity.getIcon())
				.error(com.slb.frame.R.mipmap.default_image)
				.placeholder(com.slb.frame.R.mipmap.default_image)
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(imageView);
		baseViewHolder.setText(R.id.TvName, entity.getName());

	}
}
