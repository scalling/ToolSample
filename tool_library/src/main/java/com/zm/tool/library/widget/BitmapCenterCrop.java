package com.zm.tool.library.widget;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * Created by shake on 17-4-12.
 * 图片处理类
 */

public class BitmapCenterCrop extends BitmapImageViewTarget {

    public BitmapCenterCrop(ImageView view) {
        super(view);
    }

    @Override
    public void onResourceReady(Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
        if (resource != null && view.getScaleType() != ImageView.ScaleType.FIT_XY) {
            view.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        super.onResourceReady(resource, transition);
    }

    @Override
    protected void setResource(Bitmap resource) {
        super.setResource(resource);
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        if (errorDrawable != null && view != null && view.getScaleType() != ImageView.ScaleType.CENTER_CROP) {
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        super.onLoadFailed(errorDrawable);
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        if (placeholder != null && placeholder != null && view != null && view.getScaleType() != ImageView.ScaleType.CENTER_CROP) {
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        super.onLoadStarted(placeholder);
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {
        if (placeholder != null && placeholder != null && view != null && view.getScaleType() != ImageView.ScaleType.CENTER_CROP) {
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        super.onLoadCleared(placeholder);
    }

}