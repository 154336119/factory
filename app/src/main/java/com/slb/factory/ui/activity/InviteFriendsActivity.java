package com.slb.factory.ui.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.slb.factory.MyConstants;
import com.slb.factory.R;
import com.slb.factory.weight.ShareDialog;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.utils.ActivityUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.slb.factory.MyConstants.url_yaoqinghaoyou;

public class InviteFriendsActivity extends BaseActivity {
    @BindView(R.id.RlWxInvite)
    RelativeLayout RlWxInvite;

    @Override
    protected String setToolbarTitle() {
        return "邀请好友";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.RlWxInvite)
    public void onViewClicked() {
        //分享参数
        UMWeb web = new UMWeb( MyConstants.h5Url +url_yaoqinghaoyou);
        web.setTitle("邀您加入工厂联盟");
        web.setDescription("来自工厂联盟APP");
        web.setThumb(new UMImage(this,"http://img.xikeqiche.com/share/200.jpg") );
        new ShareAction(this)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
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

}
