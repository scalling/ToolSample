package com.zm.tool.library.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.zm.tool.library.listener.OnGlideImageViewListener;
import com.zm.tool.library.listener.OnProgressListener;
import com.zm.tool.library.util.ProgressManager;

import java.lang.ref.WeakReference;

/**
 * Created by shake on 2017/8/29.
 */

public class GlideImageLoader {
    private static final String HTTP = "http";
    private WeakReference<ImageView> mImageView;
    private Object mImageUrlObj;
    private long mTotalBytes = 0;
    private long mLastBytesRead = 0;
    private boolean mLastStatus = false;
    private Handler mMainThreadHandler;

    private OnProgressListener internalProgressListener;
    private OnGlideImageViewListener onGlideImageViewListener;
    private OnProgressListener onProgressListener;

    public static GlideImageLoader create(ImageView imageView) {
        return new GlideImageLoader(imageView);
    }

    private GlideImageLoader(ImageView imageView) {
        mImageView = new WeakReference<>(imageView);
        mMainThreadHandler = new Handler(Looper.getMainLooper());
    }

    public ImageView getImageView() {
        if (mImageView != null) {
            return mImageView.get();
        }
        return null;
    }

    public Context getContext() {
        if (getImageView() != null) {
            return getImageView().getContext();
        }
        return null;
    }
    public String getImageUrl() {
        if (mImageUrlObj == null) return null;
        if (!(mImageUrlObj instanceof String)) return null;
        return (String) mImageUrlObj;
    }

    private RequestOptions getRequestOptions(int defaultImg){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        if(defaultImg>0){
            requestOptions.placeholder(defaultImg);
        }
        requestOptions .dontAnimate();
        return requestOptions;
    }


    private RequestBuilder<Drawable> requestBuilder(Object obj, RequestOptions options) {
        this.mImageUrlObj = obj;
        return Glide.with(getContext()).load(obj).apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                mainThreadCallback(mLastBytesRead, mTotalBytes, true, e);
                ProgressManager.removeProgressListener(internalProgressListener);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                mainThreadCallback(mLastBytesRead, mTotalBytes, true, null);
                ProgressManager.removeProgressListener(internalProgressListener);
                return false;
            }
        });
    }
    private RequestBuilder<Bitmap> requestBuilderBitmap(Object obj, RequestOptions options) {
        this.mImageUrlObj = obj;
        return Glide.with(getContext()).asBitmap().load(obj).apply(options).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                mainThreadCallback(mLastBytesRead, mTotalBytes, true, e);
                ProgressManager.removeProgressListener(internalProgressListener);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                mainThreadCallback(mLastBytesRead, mTotalBytes, true, null);
                ProgressManager.removeProgressListener(internalProgressListener);
                return false;
            }
        });
    }
    //加载普通图片
    public void load(Object uri, RequestOptions options){
        requestBuilder(uri,options).into(getImageView());
    }
    public void load(Object uri,int defaultImg){
        requestBuilder(uri,getRequestOptions(defaultImg)).into(getImageView());
    }

    public void loadThumbnail(Object uri, RequestOptions options,float thumbnail){
        requestBuilder(uri,options).thumbnail(thumbnail).into(getImageView());
    }
    public void loadThumbnail(Object uri,int defaultImg,float thumbnail){
        requestBuilder(uri,getRequestOptions(defaultImg)).thumbnail(thumbnail).into(getImageView());
    }
    //加载处理过的图片
    public void loadBitmap(Object uri,int defaultImg){
        requestBuilderBitmap(uri,getRequestOptions(defaultImg)).into(new BitmapCenterCrop(getImageView()));
    }
    public void loadBitmap(Object uri,RequestOptions options){
        requestBuilderBitmap(uri,options).into(new BitmapCenterCrop(getImageView()));
    }


    private void addProgressListener() {
        if (getImageUrl() == null) return;
        final String url = getImageUrl();
        if (!url.startsWith(HTTP)) return;

        internalProgressListener = new OnProgressListener() {
            @Override
            public void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone, GlideException exception) {
                if (totalBytes == 0) return;
                if (!url.equals(imageUrl)) return;
                if (mLastBytesRead == bytesRead && mLastStatus == isDone) return;

                mLastBytesRead = bytesRead;
                mTotalBytes = totalBytes;
                mLastStatus = isDone;
                mainThreadCallback(bytesRead, totalBytes, isDone, exception);

                if (isDone) {
                    ProgressManager.removeProgressListener(this);
                }
            }
        };
        ProgressManager.addProgressListener(internalProgressListener);
    }

    private void mainThreadCallback(final long bytesRead, final long totalBytes, final boolean isDone, final GlideException exception) {
        mMainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                final int percent = (int) ((bytesRead * 1.0f / totalBytes) * 100.0f);
                if (onProgressListener != null) {
                    onProgressListener.onProgress((String) mImageUrlObj, bytesRead, totalBytes, isDone, exception);
                }

                if (onGlideImageViewListener != null) {
                    onGlideImageViewListener.onProgress(percent, isDone, exception);
                }
            }
        });
    }

    public void setOnGlideImageViewListener(String imageUrl, OnGlideImageViewListener onGlideImageViewListener) {
        this.mImageUrlObj = imageUrl;
        this.onGlideImageViewListener = onGlideImageViewListener;
        addProgressListener();
    }

    public void setOnProgressListener(String imageUrl, OnProgressListener onProgressListener) {
        this.mImageUrlObj = imageUrl;
        this.onProgressListener = onProgressListener;
        addProgressListener();
    }
}
