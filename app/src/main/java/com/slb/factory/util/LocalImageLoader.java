package com.slb.factory.util;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.imagepicker.loader.ImageLoader;

/**
 * Created by Administrator on 2017/11/22.
 */

public class LocalImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(path)   //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(com.slb.frame.R.mipmap.default_image)           //设置错误图片
                .placeholder(com.slb.frame.R.mipmap.default_image)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(path)   //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(com.slb.frame.R.mipmap.default_image)           //设置错误图片
                .placeholder(com.slb.frame.R.mipmap.default_image)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }
}
