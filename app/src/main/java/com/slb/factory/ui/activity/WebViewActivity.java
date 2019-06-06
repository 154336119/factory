package com.slb.factory.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.slb.factory.MyConstants;
import com.slb.factory.R;
import com.slb.factory.http.bean.WebBean;
import com.slb.factory.weight.MyWebView;
import com.slb.factory.weight.ShareDialog;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.utils.ActivityUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WebViewActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String url;
    private String title;
    private String price;
    private boolean isRightBtnShare = false;

    private UMWeb mUMWeb;

    private int payType; //支付什么类型 1,会员开通发起支付，2，酒店预订发起支付，3、优惠买单（或）团购套餐发起支付
    private String restId;

    public void setMybackListener(){
        setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                    return;
                }
               finish();
            }
        });
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isRightBtnShare){
            getMenuInflater().inflate(R.menu.menu_share, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showShareDialog(mUMWeb);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getIntentExtras() {
        super.getIntentExtras();
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        isRightBtnShare = getIntent().getBooleanExtra("isRightBtnShare",false);

        isRightBtnShare = getIntent().getBooleanExtra("isRightBtnShare",false);

        if(isRightBtnShare){
            UMWeb web = new UMWeb(getIntent().getStringExtra("shareUrl"));
            web.setTitle(getIntent().getStringExtra("shareTitle"));
            web.setDescription(getIntent().getStringExtra("shareSubTitle"));
            web.setThumb(new UMImage(WebViewActivity.this,getIntent().getStringExtra("shareLogo")) );
            mUMWeb = web;
        }
    }

    @Override
    protected String setToolbarTitle() {
        return title;
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageView(this, null);
        StatusBarUtil.setLightMode(this);
        setMybackListener();
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultFontSize((int) getResources().getDimension(R.dimen.tv15));
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false); //设置内置的缩放控件。
        settings.setSupportMultipleWindows(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setSupportZoom(true);
        settings.setAllowFileAccess(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setGeolocationEnabled(true);
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationDatabasePath(dir);

        mWebView.setLongClickable(true);
        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
//              return shouldOverrideUrlByMJ(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

            //设置响应js 的Confirm()函数
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Confirm");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                });
                b.create().show();
                return true;
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress != 100) {
                    progressbar.setVisibility(View.VISIBLE);
                } else {
                    progressbar.setVisibility(View.GONE);
                }
            }
        });
        mWebView.addJavascriptInterface(new JavaScriptObject(), "jsAndroid");
        mWebView.loadUrl(url);
    }
    private class JavaScriptObject {
        @JavascriptInterface
        public void appPush(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            Bundle bundle = new Bundle();
            bundle.putString("url",MyConstants.h5Url + data.url);
            bundle.putString("title",data.title);
            bundle.putBoolean("isRightBtnShare",data.isRightBtnShare);

            if(!TextUtils.isEmpty(data.shareTitle)){
                bundle.putString("shareTitle",data.shareTitle);
            }
            if(!TextUtils.isEmpty(data.shareSubTitle)){
                bundle.putString("shareSubTitle",data.shareSubTitle);
            }
            if(!TextUtils.isEmpty(data.shareUrl)){
                bundle.putString("shareUrl",data.shareUrl);
            }
            if(!TextUtils.isEmpty(data.shareLogo)){
                bundle.putString("shareLogo",data.shareLogo);
            }

            ActivityUtil.next(WebViewActivity.this, WebViewActivity.class,bundle,false);
        }



        @JavascriptInterface
        public void appReturn(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            Intent intent = new Intent();
            intent.putExtra("isRefresh", data.isRefresh);
            setResult(100, intent);
            finish();
        }

        @JavascriptInterface
        public void appShare(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);

            UMImage image = new UMImage(WebViewActivity.this, R.mipmap.logo);
            final UMWeb web = new UMWeb(data.url);
            web.setTitle(data.title);
            web.setThumb(image);
            web.setDescription(data.content);
        }

        @JavascriptInterface
        public void appClose(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            finish();
            finish();
        }

        @JavascriptInterface
        public void appLink(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            //linkType（首页：index，个人中心：my , 上传凭证：upProve ,登录：login） 。
            if ("index".equals(data.linkType)) {
                Bundle bundle = new Bundle();
                bundle.putInt(MyConstants.HOME_SELECTED_FRAGMENT,0);
                ActivityUtil.next(WebViewActivity.this, MainActivity.class,bundle,true);
                finish();
            } else if ("my".equals(data.linkType)) {
                Bundle bundle = new Bundle();
                bundle.putInt(MyConstants.HOME_SELECTED_FRAGMENT,2);
                ActivityUtil.next(WebViewActivity.this, MainActivity.class,bundle,true);
                finish();
            } else if ("upProve".equals(data.linkType)) {
                Bundle bundle = new Bundle();
                bundle.putString("orderId",data.orderId);
                ActivityUtil.next(WebViewActivity.this, UploadProofsActivity.class,bundle,true);
                finish();
            } else if ("login".equals(data.linkType)) {
                ActivityUtil.next(WebViewActivity.this, LoginActivity.class);
                finish();
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView == null) {
            return;
        }
        mWebView.onPause();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, intent);
        if (resultCode == 100)
            if (intent.getBooleanExtra("isRefresh", false))
                mWebView.loadUrl(this.url);
        }

    /**
     * 测试
     */
    public void showShareDialog(final UMWeb web){
        ShareDialog dialog = new ShareDialog();
        dialog.setOnButtonClick(new ShareDialog.OnButtonClick() {
            @Override
            public void onShare(int type) {
                SHARE_MEDIA share_media  = SHARE_MEDIA.WEIXIN;
                if(type == ShareDialog.VX_FRIEND){
                    share_media = SHARE_MEDIA.WEIXIN;
                }else if(type == ShareDialog.VX_CIRCLE){
                    share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                }
                new ShareAction(WebViewActivity.this)
                        .setPlatform(share_media)//传入平台
                        .withMedia(web)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        })
                        .share();
            }
        });
        dialog.show(getSupportFragmentManager(), "Dialog");
    }
}
