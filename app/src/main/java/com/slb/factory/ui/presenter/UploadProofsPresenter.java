package com.slb.factory.ui.presenter;

import com.slb.factory.ui.contract.UploadlProofsContract;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
/**
 * Created by Gifford on 2018/6/6.
 */

public class UploadProofsPresenter extends AbstractBasePresenter<UploadlProofsContract.IView>
		implements UploadlProofsContract.IPresenter<UploadlProofsContract.IView>{

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
