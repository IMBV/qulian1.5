package com.quliantrip.qulian.ui.fragment.choicenessFragment.good;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.good.HotGoodListAdapter;
import com.quliantrip.qulian.adapter.popAdapter.HotGoodChildAdapter;
import com.quliantrip.qulian.adapter.popAdapter.HotGoodGroupAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.good.GoodBean;
import com.quliantrip.qulian.domain.choice.good.GoodDetailBean;
import com.quliantrip.qulian.domain.choice.good.HotGoodBean;
import com.quliantrip.qulian.domain.choice.good.MoreGoodsBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailActivity;
import com.quliantrip.qulian.ui.activity.mainAcivity.MainActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.TDevice;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 热门商品展示显示页
 */
public class HotGoodsFragment extends BasePageCheckFragment {
    private View bestView;
    //请求的参数
    private String city = CommonHelp.getString(R.string.change_city_tacit_id);
    private String cate;
    private String merchant;
    private String bespeak;
    private String theme;
    private String page = "2";
    private QuestBean questBean;//请求的数据对象
    //界面上的布局显示对向
    @Bind(R.id.ll_route_sift)
    LinearLayout siftCondition;
    @Bind(R.id.tv_recommend_route_text)
    TextView siftText;
    @Bind(R.id.iv_recommend_route_img)
    ImageView siftImg;
    @Bind(R.id.v_consume_list_bottom_line)
    View bottomLine;
    @Bind(R.id.hs_home_good_cate)
    HorizontalScrollView scrollView;
    @Bind(R.id.ll_home_good_cate_content)
    LinearLayout linearLayout;
    @Bind(R.id.overlay)
    ImageView bg;
    //下拉刷新
    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    protected ListView listView;
    private HotGoodBean.DataEntity.ProductInfoEntity productInfoEntity;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_choiceness_hot_good, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        if (questBean == null) {
            city = CommonHelp.getStringSp(mContext, "globalCityId", CommonHelp.getString(R.string.change_city_tacit_id));
            Map<String, String> map = new HashMap<String, String>();
            map.put("city", city);
            if (QulianApplication.getInstance().getLoginUser().getAuth_key() != null) {
                map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
            }
            questBean = new QuestBean(map, new HotGoodBean().setTag(getClass().getName()), HttpConstants.HOT_GOOD_LIST);
            return questBean;
        } else {
            return questBean;
        }
    }

    //所有参数进行
    public void requestDataForAll() {
        Map<String, String> map = new HashMap<String, String>();
        city = CommonHelp.getStringSp(mContext, "globalCityId", CommonHelp.getString(R.string.change_city_tacit_id));
        if (city != null)
            map.put("city", city);
        if (cate != null)
            map.put("cate", cate);
        if (merchant != null)
            map.put("merchant", merchant);
        if (bespeak != null)
            map.put("bespeak", bespeak);
        if (theme != null)
            map.put("theme", theme);
        if (QulianApplication.getInstance().getLoginUser().getAuth_key() != null) {
            map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        }
        new PacketStringReQuest(HttpConstants.HOT_GOOD_LIST, new HotGoodBean().setTag(getClass().getName()), map);
    }

    private int oldCateSize = 0;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            //想mode添加数据
            HotGoodBean hotGoodBean = (HotGoodBean) bean;
            if (cateList == null || oldCateSize != hotGoodBean.getData().getCate().size()) {
                cateList = hotGoodBean.getData().getCate();
                oldCateSize = cateList.size();
                if (map.size() < 2) {
                    for (int i = 0; i < hotGoodBean.getData().getCate().size(); i++) {
                        initRadioButton(hotGoodBean.getData().getCate().get(i), i);
                    }
                }
                currentCheckedCate = map.get(classId);
                //这里是进行平滑的移动
                linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        Button button = (Button) linearLayout.getChildAt(currentCheckedCate).findViewById(R.id.rb_hot_good_classfy_item);
                        button.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
                        button.setBackground(CommonHelp.getDrawable(R.drawable.shape_button_corner_press));
                        scrollView.scrollBy(linearLayout.getChildAt(currentCheckedCate).getLeft(), 0);
                    }
                });
                screenArray = hotGoodBean.getData().getScreen();
            }
            productInfoEntity = hotGoodBean.getData().getProductInfo();
            initRefreshListView(hotGoodBean.getData().getOnline());
            if (refreshViewList != null)
                refreshViewList.onRefreshComplete();
        }
        //下拉加载的操作
        if (bean != null && (this.getClass().getName() + "moreData").equals(bean.getTag())) {
            MoreGoodsBean moreGoodsBean = (MoreGoodsBean) bean;
            if (moreGoodsBean.getCode() == 200) {
                hotGoodListAdapter.addCollecListView((ArrayList<GoodBean>) moreGoodsBean.getData());
                if (refreshViewList != null)
                    refreshViewList.onRefreshComplete();
                page = (Integer.valueOf(page) + 1) + "";
                if (moreGoodsBean.getData().size() < 10) {
                    refreshViewList.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                } else {
                    refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
                }
            } else {
                ToastUtil.showToast(mContext, moreGoodsBean.getMsg());
            }
        }
    }

    private List<HotGoodBean.DataEntity.HotGoodCate> cateList;
    //分类的筛选
    private int currentCheckedCate = 0;
    private int oldCheckedCate = -1;
    private String classId = "-1";
    //map 前一个分类的id， 后表一个表示第几个加入
    HashMap<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("-1", 0);
        }
    };

    //添加选着条件按钮
    private void initRadioButton(final HotGoodBean.DataEntity.HotGoodCate cate, final int i) {
        map.put(cate.getId(), i);
        View view = View.inflate(mContext, R.layout.view_hot_good_classfy_item, null);
        final Button button = (Button) view.findViewById(R.id.rb_hot_good_classfy_item);
        button.setText(cate.getName());
        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (currentCheckedCate != i) {
                    button.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
                    button.setBackground(CommonHelp.getDrawable(R.drawable.shape_button_corner_press));
                    Button oldButton = (Button) linearLayout.getChildAt(currentCheckedCate).findViewById(R.id.rb_hot_good_classfy_item);
                    oldButton.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
                    oldButton.setBackground(CommonHelp.getDrawable(R.drawable.shape_button_corner_normal));
                }
                bg.setVisibility(View.GONE);
                oldCheckedCate = currentCheckedCate;
                currentCheckedCate = i;
                HotGoodsFragment.this.cate = cate.getId();
                requestDataForAll();
            }
        });
        linearLayout.addView(view);
    }

    private boolean isShowSift = false;

    @OnClick(R.id.ll_route_sift)
    void showSiftRoute() {
        if (isShowSift) {
            hidePopupWindow();
        } else {
            showSiftCondition();
            bg.setVisibility(View.VISIBLE);
        }
        isShowSift = !isShowSift;
    }

    List<HotGoodBean.DataEntity.ScreenEntity> screenArray;
    private PopupWindow siftConditionPop;
    ListView groupListView = null;
    ListView childListView = null;
    HotGoodGroupAdapter groupAdapter = null;
    HotGoodChildAdapter childAdapter = null;
    private PopupWindow siftPopupWindow;
    private String sreenGigId = "0";

    //显示筛选条件
    public void showSiftCondition() {
        View popView = null;
        if (siftConditionPop == null) {
            popView = View.inflate(mContext, R.layout.popupwindow_sift_double_list, null);
            groupListView = (ListView) popView.findViewById(R.id.lv_group);
            childListView = (ListView) popView.findViewById(R.id.lv_child);
            groupAdapter = new HotGoodGroupAdapter(mContext, screenArray);
            groupListView.setAdapter(groupAdapter);
            if (childAdapter == null) {
                childAdapter = new HotGoodChildAdapter(mContext);
            }
            childListView.setAdapter(childAdapter);
            childAdapter.setChildData(screenArray.get(0).getChild());
            childAdapter.notifyDataSetChanged();
            groupAdapter.notifyDataSetChanged();
        }
        groupListView.setOnItemClickListener(new MyItemClick());
        //筛选二级的分类
        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HotGoodBean.DataEntity.ScreenEntity.ChildEntity bean = (HotGoodBean.DataEntity.ScreenEntity.ChildEntity) parent.getAdapter().getItem(position);
                switch (sreenGigId) {
                    case "0":
                        childAdapter.setMerchantId(bean.getId().equals("") ? "-1" : bean.getId());
                        HotGoodsFragment.this.merchant = bean.getId();
                        HotGoodsFragment.this.requestDataForAll();
                        break;
                    case "1":
                        childAdapter.setBespeakId(bean.getId().equals("") ? "-2" : bean.getId());
                        HotGoodsFragment.this.bespeak = bean.getId();
                        HotGoodsFragment.this.requestDataForAll();
                        break;
                    case "2":
                        childAdapter.setThemeId(bean.getId().equals("") ? "-3" : bean.getId());
                        HotGoodsFragment.this.theme = bean.getId();
                        HotGoodsFragment.this.requestDataForAll();
                        break;
                }
                hidePopupWindow();
            }
        });
        siftPopupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, CommonHelp.dip2px(mContext, 285));
        siftPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        siftPopupWindow.setOutsideTouchable(true);
        siftPopupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        //显示的筛选popupwinsow的坐标位置
        int[] location = new int[2];
        bottomLine.getLocationInWindow(location);
        int x = location[0];
        int y = location[1];
        siftPopupWindow.showAtLocation(bottomLine, Gravity.LEFT | Gravity.TOP, x, y - 1);
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 20:
                    childAdapter.setChildData(screenArray.get(msg.arg1).getChild());
                    childAdapter.notifyDataSetChanged();
                    groupAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    class MyItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            groupAdapter.setSelectedPosition(position);
            if (childAdapter == null) {
                childAdapter = new HotGoodChildAdapter(mContext);
                childListView.setAdapter(childAdapter);
            } else {
                childListView.setAdapter(childAdapter);
            }
            sreenGigId = position + "";
            childAdapter.setBigCate(position);
            Message msg = new Message();
            msg.what = 20;
            msg.arg1 = position;
            handler.sendMessage(msg);
        }
    }

    //隐藏pouwindow
    public void hidePopupWindow() {
        //在onsrcll中的方法在oncreate会调用,所以判断是否为空
        if (siftPopupWindow != null) {
            siftPopupWindow.dismiss();
            siftPopupWindow = null;
            bg.setVisibility(View.GONE);
        }
        if (childAdapter != null)
            childAdapter.setBigCate(0);
    }

    //加载显示的列表
    private HotGoodListAdapter hotGoodListAdapter;

    private void initRefreshListView(final List<GoodBean> listGood) {
        if (listGood.size() < 10) {
            refreshViewList.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        } else {
            refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
        }
        listView = refreshViewList.getRefreshableView();
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。
        if (hotGoodListAdapter == null) {
            hotGoodListAdapter = new HotGoodListAdapter((ArrayList<GoodBean>) listGood);
            listView.setAdapter(hotGoodListAdapter);
        } else {
            hotGoodListAdapter.updateListView((ArrayList<GoodBean>) listGood);
        }
        //最优的一条数据的数据适配
        if (bestView == null) {
            bestView = View.inflate(mContext, R.layout.layout_hot_good_best_to_me, null);
            ImageLoader.getInstance().displayImage(productInfoEntity.getImgs().split(",")[0], (ImageView) bestView.findViewById(R.id.iv_choice_hot_good_best_pic), ImageLoaderOptions.pager_options);
            bestView.findViewById(R.id.ll_hot_good_best_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GoodDetailActivity.class);
                    intent.putExtra("goodId", productInfoEntity.getId());
                    intent.putExtra("isCollect", false);
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                }
            });
            ((TextView) bestView.findViewById(R.id.tv_choice_hot_good_best_name)).setText(productInfoEntity.getName());
            ((TextView) bestView.findViewById(R.id.tv_hot_good_best_oldPrice)).setText("￥" + productInfoEntity.getSale());
            ((TextView) bestView.findViewById(R.id.tv_hot_good_best_newPrice)).setText("￥" + productInfoEntity.getProce());
            ((TextView) bestView.findViewById(R.id.tv_good_best_location_dicount)).setText(productInfoEntity.getChinese_name() + " · " + productInfoEntity.getMeter());
            final View oldLine = ((View) bestView.findViewById(R.id.tv_hot_good_best_oldPrice_line));
            oldLine.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    oldLine.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int oldWidth = ((TextView) bestView.findViewById(R.id.tv_hot_good_best_oldPrice)).getWidth();
                    ViewGroup.LayoutParams params = oldLine.getLayoutParams();
                    params.width = oldWidth + (oldWidth / 10) * 2;
                    oldLine.setLayoutParams(params);
                }
            });
            listView.addHeaderView(bestView);
        } else {
            ImageLoader.getInstance().displayImage(productInfoEntity.getImgs().split(",")[0], (ImageView) bestView.findViewById(R.id.iv_choice_hot_good_best_pic), ImageLoaderOptions.pager_options);
            bestView.findViewById(R.id.ll_hot_good_best_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GoodDetailActivity.class);
                    intent.putExtra("goodId", productInfoEntity.getId());
                    intent.putExtra("isCollect", false);
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                }
            });
            ((TextView) bestView.findViewById(R.id.tv_choice_hot_good_best_name)).setText(productInfoEntity.getName());
            ((TextView) bestView.findViewById(R.id.tv_hot_good_best_oldPrice)).setText("￥" + productInfoEntity.getSale());
            ((TextView) bestView.findViewById(R.id.tv_hot_good_best_newPrice)).setText("￥" + productInfoEntity.getProce());
            ((TextView) bestView.findViewById(R.id.tv_good_best_location_dicount)).setText(productInfoEntity.getChinese_name() + " · " + productInfoEntity.getMeter());
            final View oldLine = ((View) bestView.findViewById(R.id.tv_hot_good_best_oldPrice_line));
            oldLine.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    oldLine.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int oldWidth = ((TextView) bestView.findViewById(R.id.tv_hot_good_best_oldPrice)).getWidth();
                    ViewGroup.LayoutParams params = oldLine.getLayoutParams();
                    params.width = oldWidth + (oldWidth / 10) * 2;
                    oldLine.setLayoutParams(params);
                }
            });
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TDevice.getNetworkType() != 0) {
                    GoodBean bean = listGood.get(position - 2);
                    Intent intent = new Intent(HotGoodsFragment.this.getActivity(), GoodDetailActivity.class);
                    intent.putExtra("goodId", bean.getId());
                    mContext.startActivity(intent);
//                    HotGoodsFragment.this.startActivityForResult(intent, 2205);
                    ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                } else {
                    ToastUtil.showToast(mContext, "请检查网络设置");
                }
            }
        });

        // 进行数据时的适配和是上啦还是下拉的操作
        refreshViewList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                // 根据不同的mode进行操作,mode中有要进行操作的类型的数据
                if (refreshViewList.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                    page = "2";
                    requestDataForAll();
                } else {
                    Map<String, String> map = new HashMap<String, String>();
                    city = CommonHelp.getStringSp(mContext, "globalCityId", CommonHelp.getString(R.string.change_city_tacit_id));
                    if (city != null)
                        map.put("city", city);
                    if (cate != null)
                        map.put("cate", cate);
                    if (merchant != null)
                        map.put("merchant", merchant);
                    if (bespeak != null)
                        map.put("bespeak", bespeak);
                    if (theme != null)
                        map.put("theme", theme);
                    if (QulianApplication.getInstance().getLoginUser().getAuth_key() != null) {
                        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
                    }
                    map.put("page", page);
                    new PacketStringReQuest(HttpConstants.HOT_GOOD_LIST, new MoreGoodsBean().setTag(HotGoodsFragment.class.getName() + "moreData"), map);
                }
            }
        });
    }

    //设置背景取消
    @OnClick(R.id.overlay)
    void hideBg() {
        bg.setVisibility(View.GONE);
    }

    //设置默认选中的设置
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void changeClassify(String id) {
        classId = id;
        Map<String, String> map = new HashMap<String, String>();
        map.put("city", CommonHelp.getStringSp(QulianApplication.getContext(), "globalCityId", CommonHelp.getString(R.string.change_city_tacit_id)));
        map.put("cate", classId);
        if (questBean == null) {
            questBean = new QuestBean(map, new HotGoodBean().setTag(getClass().getName()), HttpConstants.HOT_GOOD_LIST);
        } else {
            new PacketStringReQuest(HttpConstants.HOT_GOOD_LIST, new HotGoodBean().setTag(getClass().getName()), map, null);
            //这里是进行平滑的移动
            linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    Button oldButton = (Button) linearLayout.getChildAt(currentCheckedCate).findViewById(R.id.rb_hot_good_classfy_item);
                    oldButton.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
                    oldButton.setBackground(CommonHelp.getDrawable(R.drawable.shape_button_corner_normal));
                    currentCheckedCate = HotGoodsFragment.this.map.get(classId);
                    Button button = (Button) linearLayout.getChildAt(currentCheckedCate).findViewById(R.id.rb_hot_good_classfy_item);
                    button.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
                    button.setBackground(CommonHelp.getDrawable(R.drawable.shape_button_corner_press));
                    scrollView.scrollTo(linearLayout.getChildAt(currentCheckedCate).getLeft(), 0);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        requestDataForAll();
    }
}