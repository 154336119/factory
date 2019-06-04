package com.slb.factory.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.jaeger.library.StatusBarUtil;
import com.slb.factory.R;
import com.slb.factory.util.ExitDoubleClick;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.ui.fragment.BaseFragment;
import com.slb.factory.ui.fragment.HomeFragment;
import com.slb.factory.ui.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mFragments[HOME_HOME] = HomeFragment.newInstance();
            mFragments[HOME_BUY] = MineFragment.newInstance();
            mFragments[HOME_MINE] = MineFragment.newInstance();
            loadMultipleRootFragment(R.id.mainFrame, HOME_HOME,
                    mFragments[HOME_HOME],
                    mFragments[HOME_BUY],
                    mFragments[HOME_MINE]);
        } else {
            mFragments[HOME_HOME] = findFragment(HomeFragment.class);
            mFragments[HOME_BUY] = findFragment(MineFragment.class);
            mFragments[HOME_MINE] = findFragment(MineFragment.class);
        }
    }
    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
//        StatusBarUtil.setLightMode(this);
        prePosition = 0;
        bottomBar.setOnCheckedChangeListener(this);
        bottomBar.check(R.id.rb_home);
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

    @Override
    public void onBackPressedSupport() {
        if (this.getSupportFragmentManager().getBackStackEntryCount() == 0) {
            ExitDoubleClick.getInstance(this).doDoubleClick(3000, null);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

}
