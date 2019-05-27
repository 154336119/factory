package com.slb.factory;

/**
 * 描述：业务常量
 * Created by Lee
 * on 2016/9/20.
 */
public class BizsConstant {

    /************************************* 页面跳转CODE ************************************************/
    public static final int REQUEST_CODE_ELGIBLELEGALPERSON=1000;
    public static final int REQUEST_CODE_FINANCIAL_IMGPICK=1001;
    public static final int REQUEST_CODE_NET_IMGPICK=1002;
    public static final int REQUEST_CODE_PROOF_IMGPICK=1003;
    public static final int REQUEST_CODE_FINANCIAL_IMGPREVIEW=1004;
    public static final int REQUEST_CODE_NET_IMGPREVIEW=1005;
    public static final int REQUEST_CODE_PROOF_IMGPREVIEW=1006;
    public static final int REQUEST_CODE_ELGIBLELEGALORG=1007;
    public static final int REQUEST_CODE_PROOF_IMG_PREVIEW=1008;
    public static final int REQUEST_CODE_PROOF_IMG_PICK=1009;
    public static final int REQUEST_CODE_PRACTITIONER=1010;
    public static final int REQUEST_CODE_PRACTITIONER_1=1011;
    public static final int REQUEST_CODE_PRACTITIONER_2=1012;
    public static final int REQUEST_CODE_ELGIBLEORDINARY=1013;
    public static final int REQUEST_PERMISSION_CAMERA = 10014;
    public static final int REQUEST_CODE_FACESS= 10015;
    public static final int RESULT_CODE_FACESS_ERROR= 10016;
    public static final int REQUEST_CODE_FACESS_ERROR=10017;

    public static final int REQUEST_CODE_OTHER_IMGPICK=1025;
    public static final int REQUEST_CODE_OTHER_IMGPREVIEW=1026;
    public static final int REQUEST_CODE_ROOF_EMPLOYMENT_IMGPICK=1015;
    public static final int REQUEST_CODE_ROOF_EMPLOYMENT_IMGPREVIEW=1016;
    public static final int REQUEST_CODE_CONTRACT_SCANNING_IMGPICK=1021;
    public static final int REQUEST_CODE_CONTRACT_SCANNING_IMGPREVIEW=1022;
    public static final int REQUEST_CODE_SOCIAL_SECURITY_PROOF_IMGPICK=1023;
    public static final int REQUEST_CODE_SOCIAL_SECURITY_PROOF_IMGPREVIEW=1024;
    public final static int BUNDLER_PICK_INVESTOR_DETAIL_AUTH_PREVIEW=1017;
    public final static int BUNDLER_PICK_INVESTOR_DETAIL_AUTH_PICK=1018;
    public final static int BUNDLER_PICK_INVESTOR_DETAIL_EIGIBLE_PREVIEW=1019;
    public final static int BUNDLER_PICK_INVESTOR_DETAIL_EIGIBLE_PICK=1020;


    /*************************************** 固定值 **************************************************/

    public static final String FIXED_SYSCODE="TTDJJ";
    public static final String HOME_SELECTED_FRAGMENT="home_selected_fragment";
    //图片选择
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    /**类型*/
    public static final String TYPE="type";
    public static final String PARA_RXBUX_TAG="rxbug_tag";
    public static final String PARAM_AGEMCY_VOUCHER= "AgencyVoucher";
    public static final String PARAM_MATERIAL_ENTITY= "MaterialEntity";
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_IMAGE_URL="image_url";
    public static final String PARAM_SIGN = "param_sign";
    public static final String PARAM_ORDER_ID= "Order_id";
    public static final String PARAM_ORDER_INFO_ENTITY= "OrderInfoEntity";
    public static final String PARAM_RISK_ENTITY="risk_entity";
    public static final String PARAM_SIGNFILE="sign_file";
    public static final String PARAM_FROM_CODE = "from_code";
    public static final String PARAM_SUPPLEMENT_TYPE= "supplementType";

    //三证
    public static final String UPLOAD_MATERIAL_THREE = "MATERIAL_CATEGORY_THREE_ORG";
    //三证合一
    public static final String UPLOAD_MATERIAL_ONE = "MATERIAL_CATEGORY_THREE_IN_ONE_ORG";






    /***************************************  参数传递key **************************************************/
    public static final String BUNDLE_INVESTOR_ALL="bundle_investor_all";
    public static final String BUNDLE_INVESTOR_LAGGER_CATEGORY="bundle_investor_lagger_caregory";
    public static final String BUNDLE_INVESTOR_SMALL_CATEGORY="bundle_investor_small_caregory";
    public static final String BUNDLE_INVESTOR_ENTITY="bundle_investor_entity";
    public static final String BUNDLE_INVESTOR_INCREASE_ENTITY="bundle_investor_increase_entity";
    public static final String BUNDLE_INVESTOR_MODIFY="bundle_investor_modify";
    public static final String BUNDLE_INVESTOR_INFO_URL="bundle_investor_info_url";
    public static final String BUNDLE_INVESTOR_INFO_DETAIL="bundle_investor_info_detail";
    public static final String BUNDLE_INVESTOR_INFO_TYPE="bundle_investor_info_type";
    public static final String BUNDLE_INVESTOR_INFO_MODULE="bundle_investor_info_module";
    public static final String BUNDLE_INVESTOR_LIST="bundle_investor_list";
    public static final String BUNDLE_INVESTOR_ORG_PROOF1="bundle_investor_org_proof1";
    public static final String BUNDLE_INVESTOR_PROOF_TYPE="bundle_investor_proof_type";
    public static final String BUNDLE_INVESTOR_CREATE="bundle_investor_create";
    public static final String BUNDLE_OPERATOR_TYPE="bundle_operator_type";
    public static final String BUNDLE_PRODUCT="bundle_product";
    public static final String BUNDLE_PRODUCT_CHOISE="bundle_product_choise";
    public static final String BUNDLE_PRODUCT_APPOINTMENT="bundle_product_appointment";
    public static final String BUNDLE_PRODUCT_ID="bundle_product_id";
    public static final String BUNDLE_PRODUCT_NAME="bundle_product_name";
    public static final String BUNDLE_PROOF_NAME="bundle_proof_name";
    public static final String BUNDLE_PROOF_TYPE="bundle_proof_type";
    public static final String BUNDLE_PROOF="bundle_proof";

    public static final String BUNDLE_ORDER_STATUS="bundle_order_status";
    public static final String BUNDLE_ORDER_ID="bundle_order_id";
    public static final String BUNDLE_CALLING_ID="bundle_calling_id";
    public static final String BUNDLE_ORDER_STATE="bundle_order_state";

    public static final String BUNDLE_VIDEOENTITY="bundle_videoentity";
    public static final String BUNDLE_VIDEOURL="bundle_videourl";
    public static final String BUNDLE_ORDERENTITY="bundle_orderentity";
    public static final String BUNDLE_ORDER_VIDEO_URL="bundle_order_video_url";
    public static final String BUNDLE_CALLING_ACCOUNT="bundle_calling_account";
    public static final String BUNDLE_CHARTID="bundle_chat_id";

    public static final String BUNDLE_BEVISIT_INFO="bundle_bevisit_entity";

    public static final String BUNDLE_BUY_PRODUCT="bundle_buyproduct";
    public static final String BUNDLE_AGENCY_NAME="bundle_agency_name";
    public static final String BUNDLE_BEVISIT_TYPE="bundle_bevisit_type";
    public static final String BUNDLE_MODIFY_PWD_MANAGER="bundle_modify_pwd_manager";
    public static final String BUNDLE_INVENSTEM_USER_ID="InvenstemUserId";

	public static final String BUNDLE_CONTRACT_ENTITY = "bundle_contract_entity";
    public static final String BUNDLE_SHARE_TYPE = "bundle_share_type";
    public static final String BUNDLE_ISBACKMAIN="bundle_isbackmain";
    public static final String BUNDLE_TIPS="bundle_tips";
    public static final String BUNDLE_FILE_NOT_EXIT="bundle_file_not_exit";
    public static final String PARAM_ARGS= "agrs";

    public final static String MONEY_DATA="money_data";

    public final static String BUNDLE_MSG_BUSINESSTYPE="bundle_msg_businesstype";
    public final static String BUNDLE_MSG_BUSINESSTYPE_NAME="bundle_msg_businesstype_name";

    public final static String BUNDLE_PHONE="bundle_phone_number";
    public final static String BUNDLE_VERIFYCODE="bundle_verifycode";
    public final static String BUNDLE_INVESTOR_RECOMMEND="bundle_Recommend";

    public final static String BUNDLE_SET_GESTURE ="bundle_set_gesture";
    public final static String BUNDLE_SET_GESTURE_LOGIN ="bundle_set_gesture_login";
    public final static String BUNDLE_MODIFY_GESTURE="bundle_modify_gesture";
    public final static String BUNDLE_CLOSE_GESTURE="bundle_close_gesture";
    public final static String BUNDLE_OPERATION_URL="bundle_operationmanual_url";
    public final static String BUNDLE_OPERATION_TITLE="bundle_operationmanual_title";
	public final static String GET_VIDEO_ENTITY="bundle_video_entity";
    public final static String BUNDLE_INVESTORBASE_BACK = "bundle_investorbaseinfo_back";
    public static final String BUNDLE_INVESTOR_STATUS="bundle_investor_status";
    public final static String BUNDLE_TOMAIN="bundle_tomain";
    public final static String BUNDLE_LOGIN = "bundle_login";
    public final static String BUNDLE_RELATIONSTATE = "bundle_RelationState";
    public final static String BUNDLE_ORG_TYPE="bundle_org_type";
    public final static String BUNDLE_SALESTYPE = "bundle_org_salestype";
    public final static String BUNDLE_AUTOMATIC_LOGIN = "bundle_automatic_login";

    public final static String BUNDLE_VIDEO_REMOTE_REMOTEPATH="bundle_video_remote_remotePath";

    public final static String BUNDLE_REMOTE_HEAD="bundle_reomte_head";
    public final static String BUNDLE_INVITEENTITY="bundle_InviteEntity";
    public final static String BUNDLE_GUIDANCE="bundle_guidance";
    public final static String BUNDLE_AGAIN_UPLOAD="bundle_again_upload";
}
