package com.slb.factory.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.slb.frame.utils.ImageLoadUtil;
import com.slb.frame.utils.ScreenUtils;

import java.text.DecimalFormat;
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
		ViewGroup.LayoutParams linearParams =(ViewGroup.LayoutParams) imageView.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
		linearParams.height = ScreenUtils.getScreenWidth(mContext)/2;
		linearParams.width = ScreenUtils.getScreenWidth(mContext)/2;
		imageView.setLayoutParams(linearParams);

		TextView tvOldAmount = baseViewHolder.getView(R.id.TvOldAmount);
		ImageLoadUtil.loadImage(mContext,entity.getHead_img(),imageView);

		baseViewHolder.setText(R.id.TvDes, entity.getName());
		baseViewHolder.setText(R.id.TvNewAmount,"￥"+ new DecimalFormat(".00").format(entity.getDiscount_price()));
		tvOldAmount.setText("￥"+new DecimalFormat(".00").format(entity.getOriginal_price()));
		tvOldAmount.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
	}
}
