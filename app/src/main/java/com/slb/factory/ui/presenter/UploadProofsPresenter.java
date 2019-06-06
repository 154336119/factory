package com.slb.factory.ui.presenter;

import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.ui.contract.UploadlProofsContract;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.frame.utils.rx.RxUtil;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gifford on 2018/6/6.
 */

public class UploadProofsPresenter extends AbstractBasePresenter<UploadlProofsContract.IView>
		implements UploadlProofsContract.IPresenter<UploadlProofsContract.IView>{
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
	public void uploadlProofs(String orderId, String businessLicense) {
		Logger.d(businessLicense);
		RetrofitSerciveFactory.provideComService().uploadProofs(orderId,businessLicense)
				.lift(new BindPrssenterOpterator<HttpMjResult<Object>>(this))
				.compose(RxUtil.<HttpMjResult<Object>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun<Object>())
				.subscribe(new BaseSubscriber<Object>(this.mView) {
					@Override
					public void onNext(Object entity) {
						super.onNext(entity);
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
//	public void uploadImageFile(String imgPath, final int requestCode) {
//		PostRequest postRequest = OkGo.getInstance().post(DnsFactory.getInstance().getDns().getUploadUrl());
//		postRequest.tag(this);
//
//		File file = new File(imgPath);
//		postRequest.params("bucketName", OssConfig.USER)
//				.params("files", file)
//				.execute(new UploadCallback() {
//					@Override
//					public void onSuccess(List<UploadInfo> uploadInfos, Call call, Response response) {
//						mView.loadingDialogDismiss();
//						if(uploadInfos.size() == 0){
//							mView.showMsg("上传失败");
//							return;
//						}
//						OssRemoteFile entity=new OssRemoteFile();
//						entity.setUrl(uploadInfos.get(0).getUrl());
//						entity.setBucketName(uploadInfos.get(0).getOosBucket());
//						entity.setObjectKey(uploadInfos.get(0).getObjectKey());
//						mView.uploadImageSuccess(entity,requestCode);
//					}
//
//					@Override
//					public void onError(Call call, Response response, Exception e) {
//						super.onError(call, response, e);
//						Logger.d("onError:" + e.getMessage());
//						mView.showMsg(e.getMessage());
//						mView.loadingDialogDismiss();
//					}
//
//					@Override
//					public void onBefore(BaseRequest request) {
//						super.onBefore(request);
//						mView.showLoadingDialog("正在上传图片");
//					}
//				});
//	}
//
//
//
//	/**
//	 * 保存草稿箱
//	 * @param entity
//	 *
//	 */
//	public void submitDraft(final InvestorIncreaseEntity entity){
//		String invenstemType=entity.getInvenstemType();
//		List<InvestorProofEntity> tempList=new ArrayList<>();
//		tempList.addAll(entity.getSecStepProofs());
//		tempList.addAll(entity.getThdStepProofs());
//		String userAuthorMaterialJsonStr= JSON.toJSONString(tempList);
//		RetrofitSerciveFactory.provideInvestorManagerService().editInvestorInfoDraft(entity.getDraftsNo(), Base.getUserEntity().getUserId(),invenstemType,userAuthorMaterialJsonStr)
//				.lift(new BindPrssenterOpterator<HttpResult<Object, Object>>(this))
//				.compose(RxUtil.<HttpResult<Object, Object>>applySchedulersForRetrofit())
//				.map(new HttpEntityFun<Object, Object>())
//                .subscribe(new Subscriber<Object>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.e("ppp","onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.loadingDialogDismiss();
//                        if(!TextUtils.isEmpty(e.getMessage())){
//                            mView.showMsg(e.getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void onNext(Object object) {
////                        if(object!=null){
//							mView.sumitDrafgSuccess( entity);
////							showDialog(view.getActivity());
//
////						}else{
////
////                        }
//                    }
//                });
////				.subscribe(new BaseSubscriber<Object>(this.mView){
////					@Override
////					public void onNext(Object object) {
////						super.onNext(object);
////						if(object!=null){
////							mView.sumitDrafgSuccess( entity);
//////							showDialog(view.getActivity());
////
////						}
////					}
////				});
//
//	}
}
