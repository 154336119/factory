package com.slb.factory.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewActivity;
import com.nanchen.compresshelper.CompressHelper;
import com.orhanobut.logger.Logger;
import com.slb.factory.Base;
import com.slb.factory.MyConstants;
import com.slb.factory.R;
import com.slb.factory.event.ImageDelectEvent;
import com.slb.factory.event.OrderNumRefreshEvent;
import com.slb.factory.event.OrderRefreshEvent;
import com.slb.factory.http.bean.AgencyVoucherShownType;
import com.slb.factory.http.bean.old.InvestorProofEntity;
import com.slb.factory.ui.adapter.ImagePickerAdapter;
import com.slb.factory.ui.adapter.ImagePickerAdapter1;
import com.slb.factory.ui.adapter.base.ImagePickerAdapter2;
import com.slb.factory.ui.contract.UploadlProofsContract;
import com.slb.factory.ui.presenter.UploadProofsPresenter;
import com.slb.factory.util.ImageCompareUtil;
import com.slb.factory.util.LocalImageLoader;
import com.slb.factory.weight.MyGridLayoutManager;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.frame.utils.ImageLoadUtil;
import com.slb.frame.utils.ImagePickerUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leo.permission.PermissionRequest;
import cn.leo.permission.PermissionRequestFailedCallback;

import static com.slb.factory.MyConstants.REQUEST_CODE_PROOF_IMG_PICK;
import static com.slb.factory.ui.activity.SuccessActivity.TYPE_102;

public class UploadProofsActivity
        extends BaseMvpActivity<UploadlProofsContract.IView, UploadlProofsContract.IPresenter>
        implements UploadlProofsContract.IView, ImagePickerAdapter2.OnRecyclerViewItemClickListener {
    @BindView(R.id.mRvProofs)
    RecyclerView mRvProofs;
    @BindView(R.id.btnComfirm)
    Button btnComfirm;
    private ImagePickerAdapter2 mAdapter;
    private ImagePicker mImagePicker;
    /** 营业执照*/
    private AgencyVoucherShownType mSource = AgencyVoucherShownType.UPLOAD;
    private String mQnToken;
    private String mOrderID;

    @Override
    public void getIntentExtras() {
        super.getIntentExtras();
        mOrderID = getIntent().getStringExtra("orderId");
    }

    @Override
    protected String setToolbarTitle() {
        return "上传转账凭证";
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mImagePicker = ImagePickerUtils.cardSetting(this);
        mAdapter = new ImagePickerAdapter2(this, new ArrayList<String>());
        MyGridLayoutManager netLayoutManager = new MyGridLayoutManager(this, 3);
        netLayoutManager.setScrollEnabled(false);
        mRvProofs.setLayoutManager(netLayoutManager);
        mRvProofs.setHasFixedSize(true);
        mRvProofs.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setMixCount(3);
        mPresenter.getPicToken(Base.getUserEntity().getToken());
    }

    @Override
    public UploadlProofsContract.IPresenter initPresenter() {
        return new UploadProofsPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_upload_proofs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @OnClick(R.id.btnComfirm)
    public void onViewClicked() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i=0;i<mAdapter.getImages().size();i++){
            sb.append("\"");
            sb.append(mAdapter.getImages().get(i));
            sb.append("\"");
            if(i !=mAdapter.getImages().size()-1){
                sb.append(",");
            }
        }
        sb.append("]");
        mPresenter.uploadlProofs(mOrderID,sb.toString());
    }

    @PermissionRequest({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    private void choosePic(){
        mImagePicker.setImageLoader(new ImageLoadUtil());
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, REQUEST_CODE_PROOF_IMG_PICK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_PROOF_IMG_PICK) {
                List<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                try {
                    if (images != null || images.size() > 0) {
                        File file = CompressHelper.getDefault(UploadProofsActivity.this).compressToFile(new File(images.get(0).path));
                        mPresenter.uploadQiNiu(file,mQnToken);
                    }
                }catch (Exception e){
                    showMsg(getString(R.string.image_error));
                }

            }
        }
        btnComfirm.setEnabled(true);
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case MyConstants.IMAGE_ITEM_ADD:
                //添加图片
                if(TextUtils.isEmpty(mQnToken)){
                    showMsg("获取token出错");
                    return;
                }
                choosePic();
                break;
            default:
                //打开预览
                mImagePicker.setImageLoader(new LocalImageLoader());
                //查看
                Intent intentPreview;
                if (mSource == AgencyVoucherShownType.SEE) {
                    intentPreview = new Intent(this, ImagePreviewActivity.class);
                } else {
                    intentPreview = new Intent(this, ImagePreviewActivity.class);
                }
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, ImagePickerUtils.getImageItemForStr(mAdapter.getImages()));
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, MyConstants.REQUEST_CODE_PROOF_IMG_PREVIEW);
        }
    }


    @Override
    public void uploadImageSuccess() {
        Bundle bundle = new Bundle();
        bundle.putInt(MyConstants.TYPE,TYPE_102);
        ActivityUtil.next(this,SuccessActivity.class,bundle,true);
        RxBus.get().post(new OrderRefreshEvent());
        RxBus.get().post(new OrderNumRefreshEvent());
    }

    @Override
    public void getPicTokenSuccess(String token) {
        mQnToken = token;
    }

    @Override
    public void uploadQiNiuSuccess(String img) {
        List<String> list =  mAdapter.getImages();
        list.add(img);
        mAdapter.setImages(list);
    }


    @Override
    public void onDeleteClick() {
        if(mAdapter.getImages().size()==0){
            btnComfirm.setEnabled(false);
        }else{
            btnComfirm.setEnabled(true);
        }
    }

    @PermissionRequestFailedCallback
    private void failed(String[] failedPermissions) {
        showToastMsg("获取权限失败，操作无法完成");
    }

}
