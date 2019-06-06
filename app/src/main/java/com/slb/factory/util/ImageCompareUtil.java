package com.slb.factory.util;

import com.lzy.imagepicker.bean.ImageItem;
import com.slb.factory.http.bean.old.InvestorProofEntity;
import com.slb.factory.http.bean.old.OssRemoteFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gifford on 2017/12/1.
 */

public class ImageCompareUtil {
	public static void getImages(List<OssRemoteFile> images, ArrayList<ImageItem> list){
		List<OssRemoteFile> delList=new ArrayList<>();
		for (OssRemoteFile item : images){
			boolean isExit=false;
			for(ImageItem item1 : list){
				if(item.getUrl().equals(item1.path)){
					isExit=true;
					break;
				}
			}
			if(!isExit){
				delList.add(item);
			}
		}
		images.removeAll(delList);
	}
	public static void getFileImages(List<File> images, ArrayList<ImageItem> list){
		if(list == null){
			return;
		}
		List<File> delList=new ArrayList<>();
		for (File item : images){
			boolean isExit=false;
			for(ImageItem item1 : list){
				if(item.getPath().equals(item1.path)){
					isExit=true;
					break;
				}
			}
			if(!isExit){
				delList.add(item);
			}
		}
		images.removeAll(delList);
	}

	public static List<String> convert2Str(List<OssRemoteFile> list){
		List<String> tempList=new ArrayList<>();
		for (OssRemoteFile item : list){
			tempList.add(item.getUrl());
		}
		return tempList;
	}

	public static List<String> convert2Str1(List<File> list){
		List<String> tempList=new ArrayList<>();
		for (File item : list){
			tempList.add(item.getPath());
		}
		return tempList;
	}


}
