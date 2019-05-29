package com.slb.factory.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.slb.frame.ui.fragment.BaseMvpFragment;
import com.slb.frame.utils.ActivityUtil;
import com.slb.frame.utils.ConvertUtils;
import com.slb.frame.utils.ScreenUtils;
import com.slb.factory.Base;
import com.slb.factory.R;
import com.slb.factory.event.ObdConnectStateEvent;
import com.slb.factory.event.ObdServiceStateEvent;
import com.slb.factory.ui.activity.old.AddDeviceListActivity;
import com.slb.factory.ui.activity.old.EmissionTestActivity;
import com.slb.factory.ui.activity.old.FreezeFrameActivity;
import com.slb.factory.ui.activity.old.NewMode5Activity;
import com.slb.factory.ui.activity.old.NewMode6Activity;
import com.slb.factory.ui.activity.old.NewReadErrorCodeActivity;
import com.slb.factory.ui.activity.old.TroubleLightSActivity;
import com.slb.factory.ui.contract.old.HomeContract;
import com.slb.factory.ui.presenter.old.HomePresenter;
import com.slb.factory.util.BluetoothUtil;
import com.slb.factory.util.SharedPreferencesUtils;
import com.slb.factory.util.config.BizcContant;
import com.slb.factory.weight.CustomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.slb.factory.util.BluetoothUtil.isRunning;


public class HomeFragment
        extends BaseMvpFragment<HomeContract.IView, HomeContract.IPresenter>
        implements HomeContract.IView {

    Unbinder unbinder;


    @Override
    protected boolean hasToolbar() {
        return false;
    }

    public static HomeFragment newInstance() {
        HomeFragment instance = new HomeFragment();
        return instance;
    }

    @Override
    public HomeContract.IPresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void initView(View view) {
        super.initView(view);
    }

}
