package com.slb.factory.weight;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.slb.factory.R;

import static com.slb.factory.Base.getContext;

@SuppressLint("ValidFragment")
public class ShareDialog extends BottomSheetDialogFragment {
    private BottomSheetBehavior mBehavior;
    public void setOnButtonClick(OnButtonClick onButtonClick){
        mOnButtonClick = onButtonClick;
    }
    public OnButtonClick mOnButtonClick;
    public interface OnButtonClick{
        void onBtnVxFriend();
        void onBtnVxCircle();
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_share, null);
        view.findViewById(R.id.TvWxFriends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("分享给朋友");
                mOnButtonClick.onBtnVxFriend();
            }
        });
        view.findViewById(R.id.TvWxCircle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("分享到朋友圈");
                mOnButtonClick.onBtnVxCircle();
            }
        });
        view.findViewById(R.id.TvCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("取消");
                dialog.cancel();
            }
        });
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
