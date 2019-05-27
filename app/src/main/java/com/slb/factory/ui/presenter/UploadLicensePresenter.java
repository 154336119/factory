package com.slb.factory.ui.presenter;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;
import com.slb.factory.http.dns.DnsFactory;
import com.slb.factory.ui.contract.UploadLicenseContract;
import com.slb.frame.ui.presenter.AbstractBasePresenter;

import java.io.File;

/**
 * Created by Gifford on 2017/11/29.
 */

public class UploadLicensePresenter extends AbstractBasePresenter<UploadLicenseContract.IView>
		implements UploadLicenseContract.IPresenter<UploadLicenseContract.IView> {
	@Override
	public void uploadImageFile(File file) {
		PostRequest postRequest = OkGo.getInstance().post(DnsFactory.getInstance().getDns().getUploadUrl());
		postRequest.tag(this);
		postRequest.params("files", file);
//		postRequest.execute(new FileCallback() {
//					@Override
//					public void onSuccess(List<UploadInfo> uploadInfos, Call call, Response response) {
////						if(mView==null){
////							return;
////						}
//						mView.loadingDialogDismiss();
////						Log.e("onSuccess", JSON.toJSONString(uploadInfos));
//						if(uploadInfos.size() <poiList.size()){
//							mView.showMsg("上传失败");
//							return;
//						}
//						for (int i=0;i<uploadInfos.size();i++){
//							OssRemoteFile entity=new OssRemoteFile();
//							entity.setUrl(uploadInfos.get(i).getUrl());
//							entity.setBucketName(uploadInfos.get(i).getOosBucket());
//							entity.setObjectKey(uploadInfos.get(i).getObjectKey());
//							list.get(poiList.get(i)).setMaterialValue(entity);
////							list.get(poiList.get(i)).setLocalImg(false);
//						}
//
//						mView.uploadImageSuccess(list);
//					}
//
//					@Override
//					public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
//						//这里回调上传进度(该回调在主线程,可以直接更新ui)
//						Logger.d("upProgress:"+progress);
//					}
//					@Override
//					public void onError(Call call, Response response, Exception e) {
//						super.onError(call, response, e);
//						Logger.d("onError:"+e.getMessage());
//						mView.showMsg(TextUtils.isEmpty(e.getMessage())?"":e.getMessage());
//						mView.loadingDialogDismiss();
//					}
//
//					@Override
//					public void onBefore(BaseRequest request) {
//						super.onBefore(request);
//						mView.showLoadingDialog("加载中");
//					}
//				});
	}
}
