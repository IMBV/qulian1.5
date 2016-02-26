package com.quliantrip.qulian.ui.fragment.choicenessFragment.good;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.good.GoodDetailBranchCheckAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.CollectResultBean;
import com.quliantrip.qulian.domain.choice.good.GoodDetailBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailActivity;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailIntroduceActivity;
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
 * 订单详情页
 */
public class GoodDetailFragment extends BasePageCheckFragment {
    private View view;
    private String houseId;
    private String goodId;
    //收藏
    @Bind(R.id.iv_good_collect_img)
    ImageView collectImg;
    @Bind(R.id.iv_good_collect_text)
    TextView collectText;
    private boolean isCollect;//收藏的状态

    private RollViewPage rollViewPage;
    @Bind(R.id.top_news_viewpager)
    LinearLayout top_news_viewpager;//轮播的viewpage
    @Bind(R.id.dots_ll)
    LinearLayout dots_ll;//下面的小点

    //单品的详细信息
    @Bind(R.id.tv_good_detail_name)
    TextView name;
    @Bind(R.id.tv_hot_good_currentprice)
    TextView newPrice;
    @Bind(R.id.tv_hot_good_old_price)
    TextView oldPrice;
    @Bind(R.id.v_hot_good_old_price_line)
    View oldPriceLine;

    @Bind(R.id.tv_good_save_number)
    TextView saveNumber;//已售的数量

    @Bind(R.id.tv_good_detail_featured)
    TextView featured;//特色活动
    @Bind(R.id.tv_good_detail_discount)
    TextView discount;

    @Bind(R.id.tv_good_detail_taocan_number)
    TextView taocanNmber;//可选套餐的数量
    @Bind(R.id.tv_good_detail_gouma_info)
    TextView goumaInfo;//够吗须知
    @Bind(R.id.mlv_good_detail_can_check_median)
    MyListView medianCheck;

    //添加图片和小点的集合
    private List<String> imageList = new ArrayList<String>();
    private List<View> dotList = new ArrayList<View>();

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_good_detail, null);
        ButterKnife.bind(this, view);
        isCollect = ((Activity) mContext).getIntent().getBooleanExtra("isCollect", false);
        if (isCollect) {
            collectImg.setImageResource(R.mipmap.icon_x_yishoucang);
            collectText.setText("已收藏");
        } else {
            collectImg.setImageResource(R.mipmap.icon_x_shoucang);
            collectText.setText("收藏");
        }
        medianCheck.setFocusable(false);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        goodId = ((Activity) mContext).getIntent().getStringExtra("goodId");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", ((Activity) mContext).getIntent().getStringExtra("goodId"));
        if (QulianApplication.getInstance().getLoginUser().getAuth_key() != null) {
            map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        }
        return new QuestBean(map, new GoodDetailBean().setTag(getClass().getName()), HttpConstants.GOOD_DETAIL);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            GoodDetailBean goodDetailBean = (GoodDetailBean) bean;
            if (goodDetailBean.getCode() == 200) {
                ((GoodDetailActivity) mContext).showOrHideBack(false);
                GoodDetailBean.DataEntity detail = goodDetailBean.getData();
                initRollView(detail.getOnline().getImgs());
                //初始化单品的信息
                name.setText(detail.getOnline().getName());
                newPrice.setText("￥" + detail.getOnline().getProce());
                oldPrice.setText("￥" + detail.getOnline().getSale());
                //设置线的长度
                oldPriceLine.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        oldPriceLine.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        int oldWidth = oldPrice.getWidth();
                        ViewGroup.LayoutParams params = oldPriceLine.getLayoutParams();
                        params.width = oldWidth + (oldWidth / 10) * 2;
                        oldPriceLine.setLayoutParams(params);
                    }
                });

                saveNumber.setText("已售" + detail.getOnline().getBuynum());
                taocanNmber.setText(detail.getNum() + "种");
                if (detail.getOnline().getPricedesc() != null)
                    goumaInfo.setText(Html.fromHtml(detail.getOnline().getPricedesc()));
                featured.setText(detail.getOnline().getFeatured());
                discount.setText(detail.getOnline().getAgio() + "折");
                medianCheck.setAdapter(new GoodDetailBranchCheckAdapter((ArrayList<GoodDetailBean.DataEntity.BranchEntity>) detail.getBranch()));

                //初始化收藏的界面
                isCollect = detail.getOnline().isIs_house();
                if (detail.getOnline().getHouseid() != null)
                    houseId = detail.getOnline().getHouseid();
                if (detail.getOnline().isIs_house()) {
                    collectImg.setImageResource(R.mipmap.icon_x_yishoucang);
                    collectText.setText("已收藏");
                } else {
                    collectImg.setImageResource(R.mipmap.icon_x_shoucang);
                    collectText.setText("收藏");
                }
            } else {
                ((GoodDetailActivity) mContext).showOrHideBack(true);
                ToastUtil.showToast(mContext, goodDetailBean.getMsg());
            }
        }

        if (bean != null && (this.getClass().getName() + "denglu").equals(bean.getTag())) {
            GoodDetailBean goodDetailBean = (GoodDetailBean) bean;
            if (goodDetailBean.getCode() == 200) {
                ((GoodDetailActivity) mContext).showOrHideBack(false);
                GoodDetailBean.DataEntity detail = goodDetailBean.getData();
                //初始化收藏的界面
                isCollect = detail.getOnline().isIs_house();
                if (detail.getOnline().getHouseid() != null)
                    houseId = detail.getOnline().getHouseid();
                if (detail.getOnline().isIs_house()) {
                    collectImg.setImageResource(R.mipmap.icon_x_yishoucang);
                    collectText.setText("已收藏");
                } else {
                    collectImg.setImageResource(R.mipmap.icon_x_shoucang);
                    collectText.setText("收藏");
                }
            } else {
                ((GoodDetailActivity) mContext).showOrHideBack(true);
                ToastUtil.showToast(mContext, goodDetailBean.getMsg());
            }
        }

        //添加我的收藏
        if (bean != null && (this.getClass().getName() + "shouchang").equals(bean.getTag())) {
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

        //取消我的收藏
        if (bean != null && (this.getClass().getName() + "cancel").equals(bean.getTag())) {
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
//        if (bean.getTag().equals("")) {
//            //这里可以进行请求错误时的取消下载的界面
//        }
    }

    //点击购买
    @OnClick(R.id.bt_detail_order_buy)
    void intoOrder() {
        if (QulianApplication.getInstance().isLogin()) {
            Bundle bundle = new Bundle();
            bundle.putString("goodId", goodId);
            UIHelper.showGoodOrder(mContext, bundle);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        } else {
            UIHelper.showMyLogin(this, 2110);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //点击收藏
    @OnClick(R.id.ll_good_collect)
    void collectGood() {
        if (QulianApplication.getInstance().isLogin()) {
            if (!isCollect) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("House[products_id]", goodId);
                map.put("House[key]", QulianApplication.getInstance().getLoginUser().getAuth_key());
                map.put("House[type]", "0");
                new PacketStringReQuest(HttpConstants.GOOD_COLLECT, new CollectResultBean().setTag(getClass().getName() + "shouchang"), map);
            } else {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", houseId);
                new PacketStringReQuest(HttpConstants.GOOD_CANCEL_COLLECT, new CollectResultBean().setTag(getClass().getName() + "cancel"), map);
            }
        } else {
            UIHelper.showMyLogin(this, 2110);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 2110) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", goodId);
            if (QulianApplication.getInstance().getLoginUser().getAuth_key() != null) {
                map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
            }
            new PacketStringReQuest(HttpConstants.GOOD_DETAIL, new GoodDetailBean().setTag(getClass().getName() + "denglu"), map);
        }
    }

    //初始化轮播图
    private void initRollView(String img) {
        imageList.clear();
        dotList.clear();
        String[] strings = img.split(",");
        for (int i = 0; i < strings.length; i++) {
            imageList.add(strings[i]);
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

    //返回
    @OnClick(R.id.iv_detail_back)
    void finishActivity() {
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    //点击进入详情介绍界面
    @OnClick(R.id.rl_good_detail_introduct)
    void intoIntroduce() {
        Intent intent = new Intent(mContext, GoodDetailIntroduceActivity.class);
        intent.putExtra("goodId", goodId);
        intent.putExtra("field", "desc");
        intent.putExtra("type", "1");
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    //点击进入使用说明界面
    @OnClick(R.id.rl_good_detail_user_explain)
    void intoUserexPlain() {
        Intent intent = new Intent(mContext, GoodDetailIntroduceActivity.class);
        intent.putExtra("goodId", goodId);
        intent.putExtra("field", "pricedesc");
        intent.putExtra("type", "1");
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }
}
