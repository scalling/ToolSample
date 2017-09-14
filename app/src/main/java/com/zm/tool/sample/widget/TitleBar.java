package com.zm.tool.sample.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zm.tool.sample.R;

/**
 * Created by shake on 2017/6/20.
 * 自定义标题栏
 */

public class TitleBar extends FrameLayout {

    private String mLeftButtonText;//左边文字
    private int mLeftButtonTextColor=Color.WHITE;//左边字体颜色
    private Drawable mLeftButtonImage;//左边图片
    private String mTitleButtonText;//标题
    private int mTitleButtonTextColor=Color.WHITE;//标题颜色
    private String mRightButtonText;//右边文字
    private int mRightButtonTextColor=Color.WHITE;//右边字体颜色
    private Drawable mRightButtonImage;//右边图片
    private int mBgColor=Color.WHITE;//背景颜色
    private Drawable mBgDrawable;//背景图片


    protected TextView tvTitle;//标题控件
    protected TextView tvRests;//右边文字控件
    protected ImageView ivRests;//右边图标控件
    private LinearLayout llBack;//左边点击控件
    private ImageView ivBack;//左边图标控件
    private TextView tvBack;//左边文字
    protected RelativeLayout rlTitle;//背景控件

    public TitleBar(Context context) {
        this(context, null);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context,attrs);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
        initView(context);
    }


    private void init(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Titlebar);
        mLeftButtonText = typedArray.getString(R.styleable.Titlebar_leftButtonText);
        mLeftButtonTextColor = typedArray.getColor(R.styleable.Titlebar_leftButtonTextColor, mLeftButtonTextColor);
        mLeftButtonImage = typedArray.getDrawable(R.styleable.Titlebar_leftButtonImage);

        mTitleButtonText = typedArray.getString(R.styleable.Titlebar_titleText);
        mTitleButtonTextColor = typedArray.getColor(R.styleable.Titlebar_titleColor, mTitleButtonTextColor);
        mRightButtonText = typedArray.getString(R.styleable.Titlebar_rightButtonText);
        mRightButtonTextColor = typedArray.getColor(R.styleable.Titlebar_rightButtonTextColor,mRightButtonTextColor);
        mRightButtonImage = typedArray.getDrawable(R.styleable.Titlebar_rightButtonImage);

        mBgColor = typedArray.getColor(R.styleable.Titlebar_bgColor,mBgColor);
        mBgDrawable = typedArray.getDrawable(R.styleable.Titlebar_bgImage);


        typedArray.recycle();
    }

    private void initView(Context context) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.title_layout, null);
        rlTitle = (RelativeLayout) view.findViewById(R.id.rl_title);
        tvTitle = (TextView)view.findViewById(R.id.tv_title);
        llBack = (LinearLayout)view.findViewById(R.id.ll_back);
        tvRests = (TextView)view.findViewById(R.id.tv_rests);
        ivRests = (ImageView)view.findViewById(R.id.ivRests);
        tvBack = (TextView)view.findViewById(R.id.tv_back);
        ivBack = (ImageView)view.findViewById(R.id.iv_back);
        if(mLeftButtonText != null){
            // 当用户没有设置左侧按钮图片并设置了左侧的按钮文本属性时--添加左侧文本按钮
            tvBack.setText(mLeftButtonText);
            tvBack.setTextColor(mLeftButtonTextColor);
            tvBack.setVisibility(View.VISIBLE);
        }
        if(mLeftButtonImage != null){
            // 添加左侧图片按钮
            ivBack.setImageDrawable(mLeftButtonImage);
        }
//
        if(mTitleButtonText!=null){
            // 添加中间标题
            tvTitle.setText(mTitleButtonText);
            tvTitle.setTextColor(mTitleButtonTextColor);
        }
        if(mRightButtonImage == null & mRightButtonText != null){
            // 当用户没有设置右侧按钮图片并设置了左侧的按钮文本属性时--添加右侧文本按钮
            tvRests.setText(mRightButtonText);
            tvRests.setTextColor(mRightButtonTextColor);
            tvRests.setVisibility(View.VISIBLE);
        }else if(mRightButtonImage != null){
            // 添加右侧图片按钮
            ivRests.setImageDrawable(mRightButtonImage);
            ivRests.setVisibility(View.VISIBLE);
        }
        if(mBgDrawable!=null){
            rlTitle.setBackgroundDrawable(mBgDrawable);
        }else{
            rlTitle.setBackgroundColor(mBgColor);
        }
        addView(view);

    }



    //左边点击事件
    public void onBack(OnClickListener clickListener){
        if(llBack!=null){
            llBack.setOnClickListener(clickListener);
        }
    }
    //右边点击事件
    public void onRests(OnClickListener clickListener){
        if(ivRests!=null){
            ivRests.setOnClickListener(clickListener);
        }
        if(tvRests!=null){
            tvRests.setOnClickListener(clickListener);
        }
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvRests() {
        return tvRests;
    }

    public ImageView getIvRests() {
        return ivRests;
    }

    public LinearLayout getLlBack() {
        return llBack;
    }

    public ImageView getIvBack() {
        return ivBack;
    }

    public TextView getTvBack() {
        return tvBack;
    }

    public RelativeLayout getRlTitle() {
        return rlTitle;
    }
}
