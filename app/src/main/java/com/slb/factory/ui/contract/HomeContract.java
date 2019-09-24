package com.slb.factory.ui.contract;

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.slb.factory.http.bean.Brand;
import com.slb.factory.http.bean.Goods;
import com.slb.factory.http.bean.Seckill;
import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

import java.util.List;

/**
 * Created by Gifford on 2018/1/5.
 */

public class HomeContract {
	public interface IView extends IBaseLoadingDialogView {
		void setBannerListData(List<String> entity);
		void setHotBrandListData(List<Brand> entity);
		void setSeckillListData(List<Seckill> entity);
		void setHotGoodListData(List<Goods> entity);
		void setRefreshFooter(RefreshFooter footer);
		//添加商品数据
		void addGoodsListData(List<Goods> entity);
		void finishRefresh(boolean success);
		void finishLoadmore(boolean success);
		void resetNoMoreData();
		void finishLoadmoreWithNoMoreData();
	}
	public interface IPresenter<T> extends IBaseFragmentPresenter<T> {
		void onLoadMore();
		void onRefresh();
	}
}
