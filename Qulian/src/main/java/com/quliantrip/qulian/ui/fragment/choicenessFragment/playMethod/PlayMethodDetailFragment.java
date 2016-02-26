package com.quliantrip.qulian.ui.fragment.choicenessFragment.playMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.playMethod.PlayMethodDetailGoodlistAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.CollectResultBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodDetailBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailActivity;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailIntroduceActivity;
import com.quliantrip.qulian.ui.activity.choiceActivity.PlayMethodDetailActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.CircleImageView;
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
    private String playMethodId;
    private String houseId;

    //滑动的scrollView
    @Bind(R.id.sv_author_play_method_des)
    ScrollView scrollView;

    //作者简介
    @Bind(R.id.tv_play_method_author_img)
    CircleImageView authorImg;
    @Bind(R.id.tv_play_method_detail_name)
    TextView authorName;
    @Bind(R.id.tv_play_method_author_introduce)
    TextView authorIntroduce;
    private int minHeight, maxHeight;
    @Bind(R.id.tv_play_method_author_introduce_more)
    ImageView moreArrow;

    //单品集合的列表
    @Bind(R.id.mlv_good_container)
    MyListView goodsListView;

    //玩法详细信息
    @Bind(R.id.tv_total_price)
    TextView totalPrice;
    @Bind(R.id.tv_tuan_deal_oldprice)
    TextView oldTotalPeice;
    @Bind(R.id.v_tuan_deal_oldprice_line)
    View oldTotalPeiceLine;
    @Bind(R.id.tv_play_method_buy_des)
    TextView buyDes;

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

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_play_method_detail, null);
        ButterKnife.bind(this, view);
        //获取viewtreeobject的观察者进行数据的设置

        goodsListView.setFocusable(false);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        playMethodId = ((Activity) mContext).getIntent().getStringExtra("playMethodId");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", playMethodId);
        if (QulianApplication.getInstance().getLoginUser().getAuth_key() != null) {
            map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        }
        return new QuestBean(map, new PlayMethodDetailBean().setTag(getClass().getName()), HttpConstants.PLAY_METHOD_DETRAIL);
    }

    @Override
    public void onEventMainThread(final BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            PlayMethodDetailBean playMethodDetailBean = (PlayMethodDetailBean) bean;
            PlayMethodDetailBean.DataEntity dataEntity = playMethodDetailBean.getData();
            initRollView(dataEntity.getPlay().getImgs());
            if (playMethodDetailBean.getCode() == 200) {
                ((PlayMethodDetailActivity) mContext).showOrHideBack(false);
                final ArrayList<PlayMethodDetailBean.DataEntity.PackageEntity> listData = (ArrayList<PlayMethodDetailBean.DataEntity.PackageEntity>) dataEntity.getPackageX();
                //初始化作者的信息
                PlayMethodDetailBean.DataEntity.PlayEntity playEntity = dataEntity.getPlay();
                ImageLoader.getInstance().displayImage(playEntity.getHead_img(), authorImg, ImageLoaderOptions.pager_options);
                authorName.setText(playEntity.getUsername());
                totalPrice.setText(playEntity.getProce());
                oldTotalPeice.setText("￥" + playEntity.getSale());
                //动态添加数据进行数据适配
                oldTotalPeiceLine.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        oldTotalPeiceLine.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        int oldWidth = oldTotalPeice.getWidth();
                        ViewGroup.LayoutParams params = oldTotalPeiceLine.getLayoutParams();
                        params.width = oldWidth + (oldWidth / 10) * 2;
                        oldTotalPeiceLine.setLayoutParams(params);
                    }
                });

                authorIntroduce.setText(playEntity.getSummary());
                //这里是用于显示多于两行时就初始化显示两行
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

                //填充购买须知数据
                buyDes.setText(playEntity.getBuydesc());
                goodsListView.setAdapter(new PlayMethodDetailGoodlistAdapter(listData));
                goodsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        PlayMethodDetailBean.DataEntity.PackageEntity bean = listData.get(position);
                        Intent intent = new Intent(mContext, GoodDetailActivity.class);
                        intent.putExtra("goodId", bean.getClass_id());
                        intent.putExtra("isCollect", false);
                        mContext.startActivity(intent);
                        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                    }
                });

                //添加登录状态
                if (playEntity.getHouseid() != null)
                    houseId = playEntity.getHouseid();
                isCollect = playEntity.isIs_house();
                if (playEntity.isIs_house()) {
                    collectImg.setImageResource(R.mipmap.icon_x_yishoucang);
                    collectText.setText("已收藏");
                } else {
                    collectImg.setImageResource(R.mipmap.icon_x_shoucang);
                    collectText.setText("收藏");
                }

            } else {
                ToastUtil.showToast(mContext, playMethodDetailBean.getMsg());
                ((PlayMethodDetailActivity) mContext).showOrHideBack(true);
            }
        }

        if (bean != null && (this.getClass().getName() + "denglu").equals(bean.getTag())) {
            PlayMethodDetailBean playMethodDetailBean = (PlayMethodDetailBean) bean;
            PlayMethodDetailBean.DataEntity dataEntity = playMethodDetailBean.getData();
            if (playMethodDetailBean.getCode() == 200) {
                PlayMethodDetailBean.DataEntity.PlayEntity playEntity = dataEntity.getPlay();
                //添加登录状态
                isCollect = playEntity.isIs_house();
                if (playEntity.getHouseid() != null)
                    houseId = playEntity.getHouseid();
                if (playEntity.isIs_house()) {
                    collectImg.setImageResource(R.mipmap.icon_x_yishoucang);
                    collectText.setText("已收藏");
                } else {
                    collectImg.setImageResource(R.mipmap.icon_x_shoucang);
                    collectText.setText("收藏");
                }
            } else {
                ToastUtil.showToast(mContext, playMethodDetailBean.getMsg());
            }
        }

        if (bean != null && (getClass().getName() + "shouchang").equals(bean.getTag())) {
            CollectResultBean collectResultBean = (CollectResultBean) bean;
            if (collectResultBean.getCode() == 200) {
                CollectResultBean.DataEntity dataEntity = collectResultBean.getData();
                houseId = dataEntity.getId();
                //更改收藏状态
                isCollect = !isCollect;
                collectImg.setImageResource(R.mipmap.icon_x_yishoucang);
                collectText.setText("已收藏");
                final CollectDialog collectDialog = new CollectDialog(mContext, "已收藏");
                collectDialog.setCanceledOnTouchOutside(true);
                collectDialog.show();
                new Thread() {
                    public void run() {
                        SystemClock.sleep(1000);
                        collectDialog.cancel();
                    };
                }.start();
            } else {
                ToastUtil.showToast(mContext, collectResultBean.getMsg());
            }
        }
        if (bean != null && (getClass().getName() + "cancel").equals(bean.getTag())) {
            CollectResultBean collectResultBean = (CollectResultBean) bean;
            if (collectResultBean.getCode() == 200) {
                CollectResultBean.DataEntity dataEntity = collectResultBean.getData();
                isCollect = !isCollect;
                collectImg.setImageResource(R.mipmap.icon_x_shoucang);
                collectText.setText("收藏");
            } else {
                ToastUtil.showToast(mContext, collectResultBean.getMsg());
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
//                    scrollView.scro   llBy(0, maxHeight-minHeight);
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
        if (QulianApplication.getInstance().isLogin()) {
            Bundle bundle = new Bundle();
            bundle.putString("playMethodId", playMethodId);
            UIHelper.showPlayMethodOrder(mContext, bundle);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        } else {
            UIHelper.showMyLogin(this, 2010);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //点击收藏
    @OnClick(R.id.ll_good_collect)
    void collectGood() {
        if (QulianApplication.getInstance().isLogin()) {
            if (!isCollect) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("House[products_id]", playMethodId);
                map.put("House[key]", QulianApplication.getInstance().getLoginUser().getAuth_key());
                map.put("House[type]", "1");
                new PacketStringReQuest(HttpConstants.GOOD_COLLECT, new CollectResultBean().setTag(getClass().getName() + "shouchang"), map);
            } else {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", houseId);
                new PacketStringReQuest(HttpConstants.GOOD_CANCEL_COLLECT, new CollectResultBean().setTag(getClass().getName() + "cancel"), map);
            }

        } else {
            UIHelper.showMyLogin(this, 2010);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 2010) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", playMethodId);
            if (QulianApplication.getInstance().getLoginUser().getAuth_key() != null) {
                map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
            }
            new PacketStringReQuest(HttpConstants.PLAY_METHOD_DETRAIL, new PlayMethodDetailBean().setTag(getClass().getName() + "denglu"), map);
        }
    }

    //初始化轮播图
    private void initRollView(String imgsString) {
        imageList.clear();
        dotList.clear();
        if (imgsString != null) {
            String[] imgArray = imgsString.split(",");
            for (int i = 0; i < imgArray.length; i++) {
                imageList.add(imgArray[i]);
            }
        }
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

    //达人经验
    @OnClick(R.id.ll_good_method_exprice_into)
    void intoExprice() {
        Intent intent = new Intent(mContext, GoodDetailIntroduceActivity.class);
        intent.putExtra("goodId", houseId);
        intent.putExtra("field", "experience");
        intent.putExtra("type", "2");
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }
}
