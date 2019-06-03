package com.slb.factory.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slb.factory.R;
import com.slb.factory.http.bean.Goods;
import com.slb.factory.http.bean.OrderEntity;
import com.slb.factory.http.bean.ProductEntity;
import com.slb.factory.ui.adapter.base.CommonBaseAdapter;
import com.slb.factory.weight.MyListView;
import com.slb.frame.utils.DateUtils;

import java.util.List;


/**
 *
 */
public class HomeGoodsListAdapter extends BaseQuickAdapter<Goods, BaseViewHolder> {
	private Context mContext;

	public HomeGoodsListAdapter(List<Goods> data, Context ctx) {
		super(R.layout.adapter_home_not_goods, data);
		this.mContext = ctx;
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, Goods entity) {
		ImageView imageView = baseViewHolder.getView(R.id.IvImg);
		TextView tvOldAmount = baseViewHolder.getView(R.id.TvOldAmount);
		Glide.with(mContext)
				.load(entity.getHead_img())
				.error(com.slb.frame.R.mipmap.default_image)
				.placeholder(com.slb.frame.R.mipmap.default_image)
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(imageView);

		baseViewHolder.setText(R.id.TvDes, entity.getName());
		baseViewHolder.setText(R.id.TvNewAmount, entity.getDiscount_price()+"");
		tvOldAmount.setText(entity.getOriginal_price()+"");
		tvOldAmount.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
	}
}
