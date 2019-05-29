package com.slb.factory.ui.presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.slb.factory.Base;
import com.slb.factory.http.bean.UserEntity;
import com.slb.factory.http.callback.DialogCallback;
import com.slb.factory.http.dns.DnsFactory;
import com.slb.factory.http.model.LzyResponse;
import com.slb.factory.http.service.ComServiceUrl;
import com.slb.factory.ui.contract.LoginContract;
import com.slb.factory.ui.contract.WxPayContract;
import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;
import com.slb.frame.ui.presenter.AbstractBasePresenter;


/**
 * Created by Gifford on 2017/11/7.
 */

public class WxPayPresenter extends AbstractBaseFragmentPresenter<WxPayContract.IView>
		implements WxPayContract.IPresenter<WxPayContract.IView>{

}
