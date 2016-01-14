package com.quliantrip.qulian.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Qulian5 on 2016/1/14.
 */
public class SlipRihtLayout extends FrameLayout {
    public SlipRihtLayout(Context context) {
        this(context, null);
    }

    public SlipRihtLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlipRihtLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private View mBackView;
    private View mFrontView;

    private int mWidth;
    private int mHeight;
    private int mBackWidth;

    // 进行初始话数据和属性
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mBackView = getChildAt(0);
        mFrontView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mBackWidth = mBackView.getMeasuredWidth();
    }

    // 对左边的布局进行从重新的绘制
    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mBackView.layout(-mBackWidth, 0, 0, mHeight);
    }

    // 非平滑关闭或打开的操作
    public void layoutContent(boolean isOpen) {
        Rect frontRect = computeFrontRect(isOpen);
        Rect backRect = computeBackRectFromFront(frontRect);
        mFrontView.layout(frontRect.left, frontRect.top, frontRect.right,frontRect.bottom);
        mBackView.layout(backRect.left, backRect.top, backRect.right,backRect.bottom);
    }

    private Rect computeBackRectFromFront(Rect frontRect) {
        return new Rect(frontRect.left-mBackWidth, frontRect.top, frontRect.left , frontRect.bottom);
    }

    private Rect computeFrontRect(boolean isOpen) {
        int left = 0;
        if (isOpen) {
            left = mBackWidth;
        }
        return new Rect(left, 0, left + mWidth, mHeight);
    }
}
