package com.slb.factory.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slb.factory.R;
import com.slb.factory.util.FileUtils;
import com.slb.frame.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * Created by juan on 2018/9/5.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.TvVersion)
    TextView TvVersion;
    @BindView(R.id.RlAbout)
    RelativeLayout RlAbout;
    @BindView(R.id.TvCacheNum)
    TextView TvCacheNum;
    @BindView(R.id.RlClearCache)
    RelativeLayout RlClearCache;
    @BindView(R.id.RlAgreement)
    RelativeLayout RlAgreement;

    @Override
    protected String setToolbarTitle() {
        return getString(R.string.Settings);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.RlAbout, R.id.RlClearCache, R.id.RlAgreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.RlAbout:
                break;
            case R.id.RlClearCache:
//                showDialog("提示！", "确定删除缓存？",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//                                Observable.create(new ObservableOnSubscribe<String>() {
//
//                                    @Override
//                                    public void subscribe(ObservableEmitter<String> emitter) {
//                                        FileUtils.cleanApplicationData(SettingActivity.this);
//                                        try {
//                                            String s = FileUtils
//                                                    .getCacheSize(SettingActivity.this);
//                                            emitter.onNext(s);
//                                            emitter.onComplete();
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }).subscribeOn(Schedulers.io())
//                                        .observeOn(AndroidSchedulers.mainThread())
//                                        .subscribe(new Observer<String>() {
//
//                                            @Override
//                                            public void onSubscribe(Disposable d) {
//                                                showProgressDialog();
//
//                                            }
//
//                                            @Override
//                                            public void onNext(String s) {
//                                                tvCache.setText(s);
//                                            }
//
//                                            @Override
//                                            public void onError(Throwable e) {
//                                                removeProgressDialog();
//                                            }
//
//                                            @Override
//                                            public void onComplete() {
//                                                removeProgressDialog();
//                                            }
//                                        });
//
//                            }
//                        });
                break;
            case R.id.RlAgreement:
                break;
        }
    }
}
