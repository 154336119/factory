package com.slb.factory.ui.presenter;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;
import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.dns.DnsFactory;
import com.slb.factory.ui.contract.UploadLicenseContract;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.retrofit.HttpResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpEntityFun;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.frame.utils.rx.RxUtil;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gifford on 2017/11/29.
 */

public class UploadLicensePresenter extends AbstractBasePresenter<UploadLicenseContract.IView>
		implements UploadLicenseContract.IPresenter<UploadLicenseContract.IView> {
	@Override
	public void uploadQiNiu(File data, String token) {
		mView.showLoadingDialog("正在加载");
		UploadManager uploadManager = new UploadManager();
		// 设置图片名字
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String key = "pic_" + sdf.format(new Date()) + ".jpg";
		uploadManager.put(data, key, token,
				new UpCompletionHandler() {
					@Override
					public void complete(String key, ResponseInfo info, JSONObject res) {
						//res包含hash、key等信息，具体字段取决于上传策略的设置
						mView.loadingDialogDismiss();
						if(info.isOK()) {
							Logger.d("Upload Success");
							mView.uploadQiNiuSuccess("http://img.xikeqiche.com/" + key);
						} else {
							Logger.d("Upload Fail");
							//如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
						}
						Logger.d(key + ",\r\n " + info + ",\r\n " + res);
					}
				}, null);

	}

	@Override
	public void uploadLicense(String token, String businessLicense) {
		RetrofitSerciveFactory.provideComService().uploadLicense(token,businessLicense)
				.lift(new BindPrssenterOpterator<HttpMjResult<Object>>(this))
				.compose(RxUtil.<HttpMjResult< Object>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun< Object>())
				.subscribe(new BaseSubscriber<Object>(this.mView) {
					@Override
					public void onNext(Object entity) {
						super.onNext(entity);
						mView.showMsg("上传成功");
						mView.uploadImageSuccess();
					}
				});
	}

	@Override
	public void getPicToken(String token) {
		RetrofitSerciveFactory.provideComService().getPicToken(token)
				.lift(new BindPrssenterOpterator<HttpMjResult<String>>(this))
				.compose(RxUtil.<HttpMjResult<String>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun<String>())
				.subscribe(new BaseSubscriber<String>(this.mView) {
					@Override
					public void onNext(String entity) {
						super.onNext(entity);
						mView.getPicTokenSuccess(entity);
					}
				});
	}

	//	@Override
//	public void uploadImageFile(File file) {
//		PostRequest postRequest = OkGo.getInstance().post(DnsFactory.getInstance().getDns().getUploadUrl());
//		postRequest.tag(this);
//		postRequest.params("files", file);
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
//	}
}
