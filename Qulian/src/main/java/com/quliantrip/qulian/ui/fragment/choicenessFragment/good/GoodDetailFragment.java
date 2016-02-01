package com.quliantrip.qulian.ui.fragment.choicenessFragment.good;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.good.GoodDetailBranchCheckAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.good.GoodDetailBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.mode.homeMode.HomeSlideImageMode;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailActivity;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailIntroduceActivity;
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
 * 订单详情页
 */
public class GoodDetailFragment extends BasePageCheckFragment {
    private View view;
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

    @Bind(R.id.tv_good_detail_name)
    TextView name;
    @Bind(R.id.tv_good_save_number)
    TextView saveNumber;//已售的数量

    @Bind(R.id.tv_good_detail_taocan_number)
    TextView taocanNmber;//可选套餐的数量
    //    @Bind(R.id.tv_good_detail_can_check_time)
//    TextView checkTime;//可选时间
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
        Map<String, String> map = new HashMap<String, String>();
        System.out.println(((Activity) mContext).getIntent().getStringExtra("goodId") + "数据的id");
        map.put("id", ((Activity) mContext).getIntent().getStringExtra("goodId"));
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
                name.setText(detail.getOnline().getName());
                saveNumber.setText("已售" + 12);
                taocanNmber.setText(detail.getNum() + "种");
//                checkTime.setText(Html.fromHtml(detail.getOnline().getPurnotes()));
                goumaInfo.setText(Html.fromHtml(detail.getOnline().getPricedesc()));
                medianCheck.setAdapter(new GoodDetailBranchCheckAdapter((ArrayList<GoodDetailBean.DataEntity.BranchEntity>) detail.getBranch()));
            } else {
                ((GoodDetailActivity) mContext).showOrHideBack(true);
                ToastUtil.showToast(mContext, goodDetailBean.getMsg());
            }
        }
        if (bean.getTag().equals("")) {
            //这里可以进行请求错误时的取消下载的界面
        }
    }

    //点击购买
    @OnClick(R.id.bt_detail_order_buy)
    void intoOrder() {
        if (QulianApplication.getInstance().isLogin()) {
            Bundle bundle = new Bundle();
            bundle.putString("goodId", ((Activity) mContext).getIntent().getStringExtra("goodId"));
            UIHelper.showGoodOrder(mContext, bundle);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        } else {
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //点击收藏
    @OnClick(R.id.ll_good_collect)
    void collectGood() {
        if (QulianApplication.getInstance().isLogin()) {
            if (!isCollect) {
                collectImg.setImageResource(R.mipmap.icon_x_yishoucang);
                collectText.setText("已收藏");
                CollectDialog collectDialog = new CollectDialog(mContext, "已收藏");
                collectDialog.setCanceledOnTouchOutside(true);
                collectDialog.show();
                Map<String, String> map = new HashMap<String, String>();
                map.put("House[products_id]", ((Activity) mContext).getIntent().getStringExtra("goodId"));
                map.put("House[key]", QulianApplication.getInstance().getLoginUser().getAuth_key());
                map.put("House[key]", "1");
                new PacketStringReQuest(HttpConstants.GOOD_COLLECT, new GoodDetailBean().setTag(getClass().getName()), map);
            } else {
                collectImg.setImageResource(R.mipmap.icon_x_shoucang);
                collectText.setText("收藏");
                Map<String, String> map = new HashMap<String, String>();
                map.put("House[products_id]", ((Activity) mContext).getIntent().getStringExtra("goodId"));
//                map.put("House[key]", QulianApplication.getInstance().getLoginUser().getAuth_key());
//                map.put("House[key]", "1");
                new PacketStringReQuest(HttpConstants.GOOD_CANCEL_COLLECT, new GoodDetailBean().setTag(getClass().getName()), map);
            }
            isCollect = !isCollect;
        } else {
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
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

    @OnClick(R.id.iv_detail_back)
    void finishActivity() {
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    //点击进入详情介绍界面
    @OnClick(R.id.rl_good_detail_introduct)
    void intoIntroduce() {
        Intent intent = new Intent(mContext, GoodDetailIntroduceActivity.class);
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }
}
