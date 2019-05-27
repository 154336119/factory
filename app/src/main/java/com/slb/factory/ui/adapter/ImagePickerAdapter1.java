package com.slb.factory.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.slb.factory.BizsConstant;
import com.slb.factory.R;
import com.slb.factory.http.bean.OssRemoteFile;
import com.slb.frame.utils.ImageLoadUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：ikkong （ikkong@163.com），修改 jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：微信图片选择的Adapter, 感谢 ikkong 的提交
 * ================================================
 */
public class ImagePickerAdapter1 extends RecyclerView.Adapter<ImagePickerAdapter1.SelectedPicViewHolder> {
    private int maxImgCount = 9;
    private Context mContext;
    private List<File> mData ;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;
    private List<File> oldData;
    private boolean isAdded;   //是否额外添加了最后一个图片
    public void setMixCount(int num){
        maxImgCount = num;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public void setImages(List<File> data) {
        mData = new ArrayList<>(data);
        this.oldData = data;
        if (getItemCount() < maxImgCount) {
            mData.add(new File(""));
            isAdded = true;
        } else {
            isAdded = false;
        }
        notifyDataSetChanged();
    }

    public void setImages(List<File> data, boolean isAdd) {
        mData = new ArrayList<>(data);
        this.oldData = data;
        if (getItemCount() < maxImgCount) {
            mData.add(new File(""));
            isAdded = true;
        } else {
            isAdded = false;
        }
        isAdded=isAdd;
        notifyDataSetChanged();
    }

    public void setImagesForSee(List<File> data) {
        this.oldData = data;
        mData = new ArrayList<>(data);
        isAdded=false;
        notifyDataSetChanged();
    }

    public List<File> getImages() {
        //由于图片未选满时，最后一张显示添加图片，因此这个方法返回真正的已选图片
        if (isAdded) return new ArrayList<>(mData.subList(0, mData.size() - 1));
        else return mData;
    }

    public ImagePickerAdapter1(Context mContext, List<File> data, boolean isAdd) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        setImages(data,isAdd);
    }

    public ImagePickerAdapter1(Context mContext, List<File> data) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        setImages(data);
    }

    public ImagePickerAdapter1(boolean justsee, Context mContext, List<File> data) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        setImagesForSee(data);
    }

    @Override
    public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedPicViewHolder(mInflater.inflate(R.layout.adapter_img_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SelectedPicViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_img;
        private ImageView iv_delete;
        private int clickPosition;

        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            iv_delete = itemView.findViewById(R.id.iv_delete);
        }

        public void bind(int position) {
            //设置条目的点击事件
            itemView.setOnClickListener(this);
            iv_delete.setOnClickListener(this);
            //根据条目位置设置图片
            File item = mData.get(position);
            if (isAdded && position == getItemCount() - 1) {
                iv_img.setImageResource(R.drawable.slt_image_add);
                iv_delete.setVisibility(View.GONE);
                clickPosition = BizsConstant.IMAGE_ITEM_ADD;
            } else {
                iv_delete.setVisibility(View.VISIBLE);
                ImageLoadUtil.loadFileImage(mContext,item.getPath(),iv_img);
                clickPosition = position;
            }
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.iv_delete){
//                notifyItemRemoved(clickPosition);
                mData.remove(clickPosition);
                oldData.remove(clickPosition);
                notifyDataSetChanged();
                return;
            }
            notifyDataSetChanged();

            if (listener != null) listener.onItemClick(v, clickPosition);
        }
    }
}