package com.slb.factory.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.slb.factory.Base;
import com.slb.factory.MyConstants;
import com.slb.factory.R;
import com.slb.factory.http.bean.UpdateEntity;
import com.slb.factory.ui.contract.MainContract;
import com.slb.factory.ui.contract.UploadLicenseContract;
import com.slb.factory.ui.fragment.ShopCarFragment;
import com.slb.factory.ui.presenter.MainPresenter;
import com.slb.factory.util.ExitDoubleClick;
import com.slb.frame.http2.exception.ResponseExceptionEventArgs;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.frame.ui.fragment.BaseFragment;
import com.slb.factory.ui.fragment.HomeFragment;
import com.slb.factory.ui.fragment.MineFragment;
import com.slb.frame.utils.rx.RxBus;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity  extends BaseMvpActivity<MainContract.IView, MainContract.IPresenter>implements MainContract.IView , RadioGroup.OnCheckedChangeListener{
    private Subscription loginOutSub;
    public static final String RESPONSE_EXCEPTION_LOGINOUT = "ResponseExceptionEventArgs";
    private Observable<ResponseExceptionEventArgs> loginOutObservable;
    public static final int HOME_HOME = 0;
    public static final int HOME_BUY= 1;
    public static final int HOME_MINE = 2;
    @BindView(R.id.mainFrame)
    FrameLayout mainFrame;
    @BindView(R.id.bottomBar)
    RadioGroup bottomBar;
    private int prePosition;
    private BaseFragment[] mFragments = new BaseFragment[4];
    protected boolean hasToolbar() {
        return false;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean rxBusRegist() {
        return true;
    }

    @Override
    public MainContract.IPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getUpdateInfo();
        mPresenter.getConfig();
        if (savedInstanceState == null) {
            mFragments[HOME_HOME] = HomeFragment.newInstance();
            mFragments[HOME_BUY] = ShopCarFragment.newInstance();
            mFragments[HOME_MINE] = MineFragment.newInstance();
            loadMultipleRootFragment(R.id.mainFrame, HOME_HOME,
                    mFragments[HOME_HOME],
                    mFragments[HOME_BUY],
                    mFragments[HOME_MINE]);
        } else {
            mFragments[HOME_HOME] = findFragment(HomeFragment.class);
            mFragments[HOME_BUY] = findFragment(ShopCarFragment.class);
            mFragments[HOME_MINE] = findFragment(MineFragment.class);
        }
    }
    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
        StatusBarUtil.setLightMode(this);
        prePosition = 0;
        bottomBar.setOnCheckedChangeListener(this);
        bottomBar.check(R.id.rb_home);
        loginOutObservable = RxBus.getInstance().register(RESPONSE_EXCEPTION_LOGINOUT);
        loginOutSub = loginOutObservable.subscribeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .unsubscribeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseExceptionEventArgs>() {
                    @Override
                    public void call(ResponseExceptionEventArgs args) {
//                        PushAgent mPushAgent = PushAgent.getInstance(Base.getContext());
//                        mPushAgent.deleteAlias("xikeqiche", Base.getUserEntity().getToken(), new UTrack.ICallBack() {
//                            @Override
//                            public void onMessage(boolean b, String s) {
//
//                            }
//                        });
                        Base.setUserEntity(null);
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                showHideFragment(mFragments[0], mFragments[prePosition]);
                prePosition = 0;
                break;
            case R.id.rb_buy:
                showHideFragment(mFragments[1], mFragments[prePosition]);
                prePosition = 1;
                break;
            case R.id.rb_mine:
                showHideFragment(mFragments[2], mFragments[prePosition]);
                prePosition = 2;
                break;
        }
    }
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), getString(R.string.warning_loginout), Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
            int selct=intent.getIntExtra(MyConstants.HOME_SELECTED_FRAGMENT,2);
            if(selct==0){
                bottomBar.check(R.id.rb_home);
            }else if(selct == 2){
                bottomBar.check(R.id.rb_mine);
            }
    }

    @Override
    public void tipUpdate(final UpdateEntity entity) {
        showUpadateDialog("更新提示！", entity.getUpgrade_desc(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Uri uri = Uri.parse(entity.getUpgrade_url());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }


}
