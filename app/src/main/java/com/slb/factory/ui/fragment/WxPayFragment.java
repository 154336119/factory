package com.slb.factory.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.slb.factory.R;
import com.slb.factory.ui.contract.WxPayContract;
import com.slb.factory.ui.presenter.WxPayPresenter;
import com.slb.frame.ui.fragment.BaseMvpFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.leo.permission.PermissionRequest;

/**
 * 刁剑
 * Created on 2017/12/9
 * 注释:
 */
public class WxPayFragment extends BaseMvpFragment<WxPayContract.IView, WxPayContract.IPresenter>
        implements WxPayContract.IView {
    @BindView(R.id.IvPay)
    ImageView IvPay;
    @BindView(R.id.BtnSave)
    Button BtnSave;
    @BindView(R.id.TvContent)
    TextView TvContent;
    @BindView(R.id.BtnGoHome)
    Button BtnGoHome;
    @BindView(R.id.BtnGoUpload)
    Button BtnGoUpload;

    public static WxPayFragment newInstance() {
        WxPayFragment fragment = new WxPayFragment();
        return fragment;
    }

    Unbinder unbinder;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected boolean hasToolbar() {
        return false;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_choise_pay_wx;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public WxPayContract.IPresenter initPresenter() {
        return new WxPayPresenter();
    }

    @PermissionRequest({Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void saveImageToGallery(Bitmap bitmap) {
        // 首先保存图片
        File file = null;
        String fileName = System.currentTimeMillis() + ".jpg";
        File root = new File(Environment.getExternalStorageDirectory(), _mActivity.getPackageName());
        File dir = new File(root, "images");
        if (dir.mkdirs() || dir.isDirectory()) {
            file = new File(dir, fileName);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(_mActivity.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            MediaScannerConnection.scanFile(_mActivity, new String[]{file.getAbsolutePath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                            mediaScanIntent.setData(uri);
                            _mActivity.sendBroadcast(mediaScanIntent);
                        }
                    });
        } else {
            String relationDir = file.getParent();
            File file1 = new File(relationDir);
            _mActivity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.fromFile(file1.getAbsoluteFile())));
        }
        showMsg("保存成功");
    }

    @OnClick({R.id.BtnSave, R.id.BtnGoHome, R.id.BtnGoUpload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BtnSave:
                Bitmap bitmap =((BitmapDrawable)IvPay.getDrawable()).getBitmap();
                saveImageToGallery(bitmap);
                break;
            case R.id.BtnGoHome:
                break;
            case R.id.BtnGoUpload:
                break;
        }
    }
}
