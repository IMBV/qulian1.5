package com.quliantrip.qulian.ui.activity.choiceActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.GoodDetailBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.mode.homeMode.HomeSlideImageMode;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.RollViewPage;
import com.quliantrip.qulian.view.dialog.CollectDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 单品详情页
 */
public class GoodDetailActivity extends SwipeBackActivity {
    private Context mContext;
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
    TextView saveNumber;

    //添加图片和小点的集合
    private List<String> imageList = new ArrayList<String>();
    private List<View> dotList = new ArrayList<View>();

    //mode兑现的显示
    private HomeSlideImageMode homeSlideImageMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mContext = this;
        initData();
    }

    private void initData() {
        isCollect = getIntent().getBooleanExtra("isCollect", false);
        if (isCollect) {
            collectImg.setImageResource(R.mipmap.icon_x_yishoucang);
            collectText.setText("已收藏");
        } else {
            collectImg.setImageResource(R.mipmap.icon_x_shoucang);
            collectText.setText("收藏");
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", getIntent().getStringExtra("goodId"));
        new PacketStringReQuest(HttpConstants.GOOD_DETAIL, new GoodDetailBean().setTag(getClass().getName()), map);
    }

    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            GoodDetailBean goodDetailBean = (GoodDetailBean) bean;
            GoodDetailBean.DataEntity detail = goodDetailBean.getData();
            initRollView(detail.getOnline().getImg());
            name.setText(detail.getOnline().getName());
            saveNumber.setText("已售"+12);
        }
    }

    //点击购买
    @OnClick(R.id.bt_detail_order_buy)
    void intoOrder() {
        if (QulianApplication.getInstance().isLogin()) {
            UIHelper.showGoodOrder(mContext, null);
            overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        } else {
            UIHelper.showMyLogin(this, 41);
            overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
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
                map.put("House[products_id]", getIntent().getStringExtra("goodId"));
                map.put("House[key]", QulianApplication.getInstance().getLoginUser().getAuth_key());
                map.put("House[key]","1");
                new PacketStringReQuest(HttpConstants.GOOD_COLLECT, new GoodDetailBean().setTag(getClass().getName()), map);

            } else {
                collectImg.setImageResource(R.mipmap.icon_x_shoucang);
                collectText.setText("收藏");
                Map<String, String> map = new HashMap<String, String>();
                map.put("House[products_id]", getIntent().getStringExtra("goodId"));
//                map.put("House[key]", QulianApplication.getInstance().getLoginUser().getAuth_key());
//                map.put("House[key]", "1");
                new PacketStringReQuest(HttpConstants.GOOD_CANCEL_COLLECT, new GoodDetailBean().setTag(getClass().getName()), map);
            }
            isCollect = !isCollect;
        } else {
            UIHelper.showMyLogin(this, 41);
            overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }


    //初始化轮播图
    private void initRollView(String img) {
        imageList.clear();
        dotList.clear();
        imageList.add(img);
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
