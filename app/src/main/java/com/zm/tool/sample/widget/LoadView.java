package com.zm.tool.sample.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zm.tool.sample.R;

/**
 * Created by shake on 2017/9/1.
 *
 * 加载布局
 */
public class LoadView extends FrameLayout {

    public LoadView(@NonNull Context context) {
        super(context);
        initView();
    }

    public LoadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == View.VISIBLE) {
            if (animationDrawable != null && !animationDrawable.isRunning()) {
                animationDrawable.start();
            }
        } else {
            if (animationDrawable != null && animationDrawable.isRunning()) {
                animationDrawable.stop();
            }
        }
    }

    AnimationDrawable animationDrawable = null;
    private ImageView ivLoad;
    private TextView tvLoad;

    private void initView() {
        setBackgroundColor(Color.TRANSPARENT);
        setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        LinearLayout llContent = new LinearLayout(getContext());
        llContent.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        llContent.setGravity(Gravity.CENTER);
        this.addView(llContent);

        ivLoad = new ImageView(getContext());
        ivLoad.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        animationDrawable = loadAnimation(ivLoad);

        tvLoad = new TextView(getContext());
        tvLoad.setTextColor(Color.WHITE);
        tvLoad.setTextSize(Color.parseColor("#9E9E9E"));
        tvLoad.setText(R.string.list_null);
        tvLoad.setVisibility(View.GONE);
        tvLoad.setGravity(Gravity.CENTER);
        int margin = (int)getResources().getDimension(R.dimen.load_margin);
        tvLoad.setPadding(margin,0,margin,0);
        tvLoad.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        tvLoad.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tvLoad.setTextColor(Color.WHITE);
        llContent.addView(ivLoad);
        llContent.addView(tvLoad);

    }
    public AnimationDrawable loadAnimation(ImageView view){
        return new AnimationDrawable();
    }
}
