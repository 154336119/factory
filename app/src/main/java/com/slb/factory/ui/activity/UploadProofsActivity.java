package com.slb.factory.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewActivity;
import com.nanchen.compresshelper.CompressHelper;
import com.slb.factory.BizsConstant;
import com.slb.factory.R;
import com.slb.factory.http.bean.AgencyVoucherShownType;
import com.slb.factory.http.bean.OssRemoteFile;
import com.slb.factory.ui.adapter.ImagePickerAdapter;
import com.slb.factory.ui.adapter.ImagePickerAdapter1;
import com.slb.factory.ui.contract.UploadlProofsContract;
import com.slb.factory.ui.presenter.UploadProofsPresenter;
import com.slb.factory.util.ImageCompareUtil;
import com.slb.factory.util.LocalImageLoader;
import com.slb.factory.weight.MyGridLayoutManager;
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

import static com.slb.factory.BizsConstant.REQUEST_CODE_PROOF_IMGPICK;
import static com.slb.factory.BizsConstant.REQUEST_CODE_PROOF_IMG_PICK;

public class UploadProofsActivity
        extends BaseMvpActivity<UploadlProofsContract.IView, UploadlProofsContract.IPresenter>
        implements UploadlProofsContract.IView, ImagePickerAdapter1.OnRecyclerViewItemClickListener {
    @BindView(R.id.mRvProofs)
    RecyclerView mRvProofs;
    @BindView(R.id.btnComfirm)
    Button btnComfirm;
    private ImagePickerAdapter1 mAdapter;
    private ImagePicker mImagePicker;
    private List<File> mProofList = new ArrayList<>();
    private AgencyVoucherShownType mSource = AgencyVoucherShownType.UPLOAD;

    @Override
    protected String setToolbarTitle() {
        return "上传转账凭证";
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mImagePicker = ImagePickerUtils.cardSetting(this);
        mAdapter = new ImagePickerAdapter1(this, mProofList);
        MyGridLayoutManager netLayoutManager = new MyGridLayoutManager(this, 3);
        netLayoutManager.setScrollEnabled(false);
        mRvProofs.setLayoutManager(netLayoutManager);
        mRvProofs.setHasFixedSize(true);
        mRvProofs.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setMixCount(3);
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
//                        mPresenter.uploadImageFile(file.getPath());
                        mProofList.add(file);
                        mAdapter.setImages(mProofList);

                    }
                }catch (Exception e){
                    showMsg(getString(R.string.image_error));
                }

            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            if (data != null && requestCode == BizsConstant.REQUEST_CODE_PROOF_IMG_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                ImageCompareUtil.getFileImages(mProofList, images);
                mAdapter.setImages(mProofList);
            }
        }
        btnComfirm.setEnabled(true);
    }

//    @Subscribe(tags = {@Tag(RxBusTag.ELIGIBLE_CHILD_RECYCLER_CLICK)})
//    public void ChildRecyclerClick(ChildRecyclerClickEventArgs eventArgs){
//        for(int i=0;i<mEligibleList.size();i++){
//            if(mEligibleList.get(i).getCode().equals(eventArgs.getEntity().getCode())){
//                mLargeIndex=i;
//                break;
//            }
//        }
//        mSmallIndex=eventArgs.getIndex();
//        mCurrAdapter=eventArgs.getAdapter();
//        switch (mSmallIndex){
//            case BizsConstant.IMAGE_ITEM_ADD:
//                choosePic();
//                break;
//            default:
//                //打开预览
//                mImagePicker.setImageLoader(new LocalImageLoader());
//                Intent intentPreview;
//                if(mSource == AgencyVoucherShownType.SEE){
//                    intentPreview = new Intent(_mActivity, ImagePreviewActivity.class);
//                }else{
//                    intentPreview = new Intent(_mActivity, ImagePreviewDelActivity.class);
//                }
//                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, ImagePickerUtils.getImageItemForStr(ImageCompareUtil.convert2Str(eventArgs.getEntity().getImgList())));
//                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, mSmallIndex);
//                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
//                startActivityForResult(intentPreview, REQUEST_CODE_PROOF_IMGPREVIEW);
//                break;
//        }
//    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case BizsConstant.IMAGE_ITEM_ADD:
                //添加图片
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
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, ImagePickerUtils.getImageItemForStr(ImageCompareUtil.convert2Str1(mProofList)));
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, BizsConstant.REQUEST_CODE_PROOF_IMG_PREVIEW);
        }
    }
}
