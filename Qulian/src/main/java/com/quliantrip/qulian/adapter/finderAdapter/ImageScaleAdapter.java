package com.quliantrip.qulian.adapter.finderAdapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.lib.photoview.PhotoViewAttacher;

import java.util.ArrayList;
public class ImageScaleAdapter extends BasePageAdapter<String> {
	private Activity mActivity;
	public ImageScaleAdapter(ArrayList<String> list) {
		super(list);
	}
	public void setActivity(Activity activity){
		mActivity = activity;
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		//在这里要适配的viewpage已经进行了设置宽高的指定
		ImageView imageView = new ImageView(QulianApplication.getContext());
		final PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);
		ImageLoader.getInstance().displayImage(list.get(position),
				imageView, ImageLoaderOptions.pager_options, new SimpleImageLoadingListener(){

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						super.onLoadingComplete(imageUri, view, loadedImage);
						attacher.update();
					}
			
		});
		final long[] mHits = new long[2];
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//双击取消
				System.arraycopy(mHits, 1, mHits, 0, mHits.length-1);
	            mHits[mHits.length-1] = SystemClock.uptimeMillis();
	            if (mHits[0] >= (SystemClock.uptimeMillis()-500)) {
	            	if(mActivity!= null)
	            		mActivity.finish();
	            }
			}
		});
		//切记添加到容器中去
		container.addView(imageView);
		return imageView;
	}

}
