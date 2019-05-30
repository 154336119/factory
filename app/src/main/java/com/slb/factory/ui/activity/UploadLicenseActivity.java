package com.slb.factory.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewActivity;
import com.nanchen.compresshelper.CompressHelper;
import com.slb.factory.Base;
import com.slb.factory.BizsConstant;
import com.slb.factory.R;
import com.slb.factory.http.bean.AgencyVoucherShownType;
import com.slb.factory.http.bean.old.InvestorProofEntity;
import com.slb.factory.ui.contract.UploadLicenseContract;
import com.slb.factory.ui.presenter.UploadLicensePresenter;
import com.slb.factory.util.LocalImageLoader;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.frame.utils.ImageLoadUtil;
import com.slb.frame.utils.ImagePickerUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leo.permission.PermissionRequest;

public class UploadLicenseActivity extends BaseMvpActivity<UploadLicenseContract.IView, UploadLicenseContract.IPresenter>
        implements UploadLicenseContract.IView {
    @BindView(R.id.mTvWarning1)
    TextView mTvWarning1;
    @BindView(R.id.IvCard1)
    ImageView IvCard1;
    @BindView(R.id.iv_delete1)
    ImageView ivDelete1;
    @BindView(R.id.BtnCard1)
    RelativeLayout BtnCard1;
    @BindView(R.id.btnComfirm)
    Button btnComfirm;
    private ImagePicker mImagePicker;
    AgencyVoucherShownType mSource;
    /** 营业执照*/
    private InvestorProofEntity mIdCard1 = new InvestorProofEntity();
    private String mQnToken;
    @Override
    public void getPicTokenSuccess(String token) {
        mQnToken = token;
    }

    @Override
    protected String setToolbarTitle() {
        return "提交使用申请";
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mImagePicker = ImagePickerUtils.cardSetting(this);
        mPresenter.getPicToken(Base.getUserEntity().getToken());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_upload_license;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.iv_delete1, R.id.BtnCard1, R.id.btnComfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_delete1:
                delCard(mIdCard1,IvCard1,ivDelete1,R.mipmap.image_add);
                break;
            case R.id.BtnCard1:
                if(TextUtils.isEmpty(mIdCard1.getMaterialValue().getUrl())){
                    choosePic();
                }else{
                    checkPic(mIdCard1);
                }
                break;
            case R.id.btnComfirm:
                mPresenter.uploadLicense(Base.getUserEntity().getToken(),mIdCard1.getLocalurl());
                break;
        }
    }
    @PermissionRequest({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    public void choosePic() {
        mImagePicker.setImageLoader(new ImageLoadUtil());
        mImagePicker.setMultiMode(false);
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, BizsConstant.REQUEST_CODE_PROOF_IMG_PICK);
    }

    private void checkPic(InvestorProofEntity entity){
        //打开预览
        List<String> images=new ArrayList<>();
        images.add(entity.getMaterialValue().getUrl());
        mImagePicker.setImageLoader(new LocalImageLoader());
        Intent intentPreview;
        if(mSource == AgencyVoucherShownType.SEE){
            intentPreview = new Intent(this, ImagePreviewActivity.class);
        }else{
            intentPreview = new Intent(this, ImagePreviewActivity.class);
        }
        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, ImagePickerUtils.getImageItemForStr(images));
        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
        startActivityForResult(intentPreview, BizsConstant.REQUEST_CODE_PROOF_IMG_PREVIEW);
    }

    private void delCard(InvestorProofEntity mIdCard,ImageView IvCard,ImageView ivdelete,int rid ){
        mIdCard.getMaterialValue().setUrl(null);
        mIdCard.setLocalurl(null);
        mIdCard.setLocalImg(false);
        ivdelete.setVisibility(View.GONE);
        IvCard.setImageResource(rid);
        btnComfirm.setEnabled(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null  && requestCode == BizsConstant.REQUEST_CODE_PROOF_IMG_PICK) {
                List<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if(images!=null && images.size()==1){
                    File file = null;
                    try {
                        file = CompressHelper.getDefault(this).compressToFile(new File(images.get(0).path));
                    }catch (Exception e){
                        showToastMsg(getString(R.string.image_error));
                        return;
                    }
                    List<InvestorProofEntity> tempProofs=new ArrayList<>();
                    mIdCard1.setLocalImg(true);
                    mIdCard1.setLocalurl(file.getPath());
                    mIdCard1.getMaterialValue().setUrl(file.getPath());
                    IvCard1.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));

                    if(mIdCard1.isLocalImg()){
                        tempProofs.add(mIdCard1);
                        //还未调试
                        mPresenter.uploadQiNiu(file,mQnToken);
                    }
                    ivDelete1.setVisibility(View.VISIBLE);
                    btnComfirm.setEnabled(true);
                }
            }

        }
//        else if(resultCode == ImagePicker.RESULT_CODE_BACK) {
//            if (data != null  && requestCode == BizsConstant.REQUEST_CODE_PROOF_IMG_PREVIEW){
//                List<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
//                if(images==null || images.size()==0){
//                    delCard(mIdCard1,IvCard1,ivDelete1,R.mipmap.image_add);
//                }
//            }
//        }
    }

    @Override
    public void uploadImageSuccess() {

    }

    @Override
    public UploadLicenseContract.IPresenter initPresenter() {
        return new UploadLicensePresenter();
    }

    @Override
    public void uploadQiNiuSuccess(String img) {
        mIdCard1.setLocalImg(false);
        mIdCard1.setLocalurl(img);
    }
}
