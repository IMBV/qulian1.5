package com.quliantrip.qulian.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.ValueAnimator;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.mode.homeMode.HomeSlideImageMode;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.RollViewPage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Yuly on 2016/1/8.
 */
public class PlayMethodDetailActivity extends SwipeBackActivity {
    private Context mContext;
    //作者简介
    @Bind(R.id.tv_play_method_author_introduce)
    TextView authorIntroduce;
    private int minHeight,maxHeight;
    //收藏
    @Bind(R.id.iv_good_collect_img)
    ImageView collectImg;
    @Bind(R.id.iv_good_collect_text)
    TextView collectText;
    private boolean isCollect = false;//收藏的状态

    private RollViewPage rollViewPage;
    @Bind(R.id.top_news_viewpager)
    LinearLayout top_news_viewpager;//轮播的viewpage
    @Bind(R.id.dots_ll)
    LinearLayout dots_ll;//下面的小点

    //添加图片和小点的集合
    private List<String> imageList = new ArrayList<String>();
    private List<View> dotList = new ArrayList<View>();

    //mode兑现的显示
    private HomeSlideImageMode homeSlideImageMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_play_method_detail);
        ButterKnife.bind(this);
        mContext = this;
        initSlideImage();
        authorIntroduce.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                maxHeight = authorIntroduce.getHeight();
                authorIntroduce.setMaxLines(5);
                authorIntroduce.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                authorIntroduce.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        authorIntroduce.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        minHeight = authorIntroduce.getHeight();
                        authorIntroduce.setMaxLines(Integer.MAX_VALUE);
                        //杩涜閲嶆柊鐨勭粯鍒�
                        authorIntroduce.getLayoutParams().height = minHeight;
                        authorIntroduce.requestLayout();

                    }
                });
            }
        });
    }
    private void initSlideImage() {
        initRollView();
    }

    private boolean isShow = false;
    //作者介绍更多
    @OnClick(R.id.tv_play_method_author_introduce_more) void moreAuthoeIntroduce(){
        ValueAnimator animator;
        if(isShow)
            animator = ValueAnimator.ofInt(maxHeight,minHeight);
        else
            animator = ValueAnimator.ofInt(minHeight,maxHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int currentValue = (Integer) animator.getAnimatedValue();
                authorIntroduce.getLayoutParams().height = currentValue;
                authorIntroduce.requestLayout();
                if(isShow){
//                    scrollView.scrol？lBy(0, maxHeight-minHeight);
                }
            }
        });
        animator.setDuration(350);
        animator.start();
        //缁欑澶存坊鍔犲姩鐢�
//        ViewPropertyAnimator.animate(iv_des_arrow).rotationBy(180).setDuration(350).start();
        isShow = !isShow;

    }
    //点击购买
    @OnClick(R.id.bt_detail_order_buy)
    void intoOrder() {
        UIHelper.showGoodOrder(mContext, null);
        overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    //点击收藏
    @OnClick(R.id.ll_good_collect)
    void collectGood() {
        if (!isCollect) {
            collectImg.setImageResource(R.mipmap.icon_x_yishoucang);
            collectText.setText("已收藏");
        } else {
            collectImg.setImageResource(R.mipmap.icon_x_shoucang);
            collectText.setText("收藏");
        }
        isCollect = !isCollect;
    }

    //初始化轮播图
    private void initRollView() {
        imageList.clear();
        dotList.clear();
        imageList.add("http://www.quliantrip.com/public/attachment/201511/20/16/564ed3a64400b.png");
        imageList.add("http://www.quliantrip.com/public/attachment/201511/20/16/564ed3762c8e5.png");
        imageList.add("http://www.quliantrip.com/public/attachment/201511/20/16/564ed327a6642.png");
        if (imageList.size() > 0) {
            //初始化小点
            initDoc();
            //添加之定义的viewPage带有滚动效果的
            rollViewPage = new RollViewPage(QulianApplication.getContext(), imageList, dotList);
            rollViewPage.roll();
            rollViewPage.setCurrentItem(imageList.size() * 50);
            rollViewPage.setOnTouchImage(new RollViewPage.OnTouchImage() {

                @Override
                public void touchImage(String url) {
                }
            });
            top_news_viewpager.removeAllViews();
            top_news_viewpager.addView(rollViewPage);
        }
    }

    //初始化小点的个数
    private void initDoc() {
        dots_ll.removeAllViews();
        dotList.clear();
        for (int i = 0; i < imageList.size(); i++) {
            View view = new View(QulianApplication.getContext());
            if (i == 0) {
                view.setBackgroundResource(R.drawable.shape_point_all_white);
            } else {
                view.setBackgroundResource(R.drawable.shape_point_half_white);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    CommonHelp.dip2px(QulianApplication.getContext(), 6),
                    CommonHelp.dip2px(QulianApplication.getContext(), 6));
            params.setMargins(CommonHelp.dip2px(QulianApplication.getContext(), 5), 0, 0, 0);
            dots_ll.addView(view, params);
            dotList.add(view);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (rollViewPage != null) {
            rollViewPage.roll();
        }
    }

    @OnClick(R.id.iv_detail_back)
    void finishActivity() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }
}
