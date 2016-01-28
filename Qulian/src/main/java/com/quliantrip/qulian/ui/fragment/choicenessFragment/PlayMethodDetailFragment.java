package com.quliantrip.qulian.ui.fragment.choicenessFragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.playMethod.PlayMethodDetailGoodlistAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodDetailBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.mode.homeMode.HomeSlideImageMode;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailActivity;
import com.quliantrip.qulian.ui.activity.choiceActivity.PlayMethodDetailActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.MyListView;
import com.quliantrip.qulian.view.RollViewPage;
import com.quliantrip.qulian.view.dialog.CollectDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 玩法详情页
 */
public class PlayMethodDetailFragment extends BasePageCheckFragment {
    private View view;
    //滑动的scrollView
    @Bind(R.id.sv_author_play_method_des)
    ScrollView scrollView;
    //作者简介
    @Bind(R.id.tv_play_method_author_introduce)
    TextView authorIntroduce;
    private int minHeight, maxHeight;
    //下拉的箭头
    @Bind(R.id.tv_play_method_author_introduce_more)
    ImageView moreArrow;
    //单品集合的列表
    @Bind(R.id.mlv_good_container)
    MyListView goodsListView;
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
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_play_method_detail, null);
        ButterKnife.bind(this, view);
        initSlideImage();
        //获取viewtreeobject的观察者进行数据的设置
        authorIntroduce.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                maxHeight = authorIntroduce.getHeight();
                authorIntroduce.setMaxLines(2);
                authorIntroduce.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                authorIntroduce.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        authorIntroduce.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        minHeight = authorIntroduce.getHeight();
                        authorIntroduce.setMaxLines(Integer.MAX_VALUE);
                        //进行重新的绘制
                        authorIntroduce.getLayoutParams().height = minHeight;
                        authorIntroduce.requestLayout();
                    }
                });
            }
        });
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        return new QuestBean(map, new PlayMethodDetailBean().setTag(getClass().getName()), HttpConstants.PLAY_METHOD_DETRAIL);
    }

    @Override
    public void onEventMainThread(final BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            PlayMethodDetailBean playMethodDetailBean = (PlayMethodDetailBean) bean;
            PlayMethodDetailBean.DataEntity dataEntity = playMethodDetailBean.getData();
            if (playMethodDetailBean.getCode() == 200) {
                ((PlayMethodDetailActivity) mContext).showOrHideBack(false);
                final ArrayList<PlayMethodDetailBean.DataEntity.PackageEntity> listData = (ArrayList<PlayMethodDetailBean.DataEntity.PackageEntity>) dataEntity.getPackageX();
                goodsListView.setAdapter(new PlayMethodDetailGoodlistAdapter(listData));
                goodsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        PlayMethodDetailBean.DataEntity.PackageEntity bean = listData.get(position);
                        Intent intent = new Intent(mContext, GoodDetailActivity.class);
                        intent.putExtra("goodId", bean.getPlayitemsid());
                        intent.putExtra("isCollect", false);
                        mContext.startActivity(intent);
                        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                    }
                });

            } else {
                ToastUtil.showToast(mContext, playMethodDetailBean.getMsg());
                ((PlayMethodDetailActivity) mContext).showOrHideBack(true);
            }
        }
    }

    //作者介绍更多
    private boolean isShow = false;

    @OnClick(R.id.rl_play_method_author_introduce_more)
    void moreAuthoeIntroduce() {
        //进行缩放的显示
        ValueAnimator animator;
        if (isShow)
            animator = ValueAnimator.ofInt(maxHeight, minHeight);
        else
            animator = ValueAnimator.ofInt(minHeight, maxHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int currentValue = (Integer) animator.getAnimatedValue();
                authorIntroduce.getLayoutParams().height = currentValue;
                authorIntroduce.requestLayout();
                if (isShow) {
//                   这里是实现的向上移动的显示的内容
//                    scrollView.scrollBy(0, maxHeight-minHeight);
                }
            }
        });
        animator.setDuration(100);
        animator.start();
        //给箭头添加动画
        ViewPropertyAnimator.animate(moreArrow).rotationBy(180).setDuration(0).start();
        isShow = !isShow;
    }

    //点击购买
    @OnClick(R.id.bt_detail_order_buy)
    void intoOrder() {
        UIHelper.showPlayMethodOrder(mContext, null);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    //点击收藏
    @OnClick(R.id.ll_good_collect)
    void collectGood() {
        if (!isCollect) {
            collectImg.setImageResource(R.mipmap.icon_x_yishoucang);
            collectText.setText("已收藏");
            CollectDialog collectDialog = new CollectDialog(mContext, "已收藏");
            collectDialog.setCanceledOnTouchOutside(true);
            collectDialog.show();
        } else {
            collectImg.setImageResource(R.mipmap.icon_x_shoucang);
            collectText.setText("收藏");
        }
        isCollect = !isCollect;
    }

    //初始化广告的条目
    private void initSlideImage() {
        initRollView();
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
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }
}
