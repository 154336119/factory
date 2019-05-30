package com.slb.factory.ui.contract;

import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

/**
 * Created by Gifford on 2017/12/2.
 */

public class OrderAllContract {
	public interface IView extends IBaseLoadingDialogView {

	}
	public interface IPresenter<T> extends IBaseFragmentPresenter<T> {

	}
}
