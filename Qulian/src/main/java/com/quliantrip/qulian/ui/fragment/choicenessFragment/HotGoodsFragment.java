package com.quliantrip.qulian.ui.fragment.choicenessFragment;

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
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.HotGoodListAdapter;
import com.quliantrip.qulian.adapter.popAdapter.HotGoodChildAdapter;
import com.quliantrip.qulian.adapter.popAdapter.HotGoodGroupAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.TuanBean;
import com.quliantrip.qulian.domain.choice.HotGoodBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 热门商品的信息
 */
public class HotGoodsFragment extends BasePageCheckFragment {

    @Bind(R.id.ll_route_sift)
    LinearLayout siftCondition;
    @Bind(R.id.tv_recommend_route_text)
    TextView siftText;
    @Bind(R.id.iv_recommend_route_img)
    ImageView siftImg;
    @Bind(R.id.v_consume_list_bottom_line)
    View bottomLine;
    @Bind(R.id.asklfaklhflkafh)
    HorizontalScrollView scrollView;
    @Bind(R.id.sakjdfhlkashflkashf)
    LinearLayout linearLayout;
    @Bind(R.id.overlay)
    ImageView bg;

    //下拉刷新
    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    protected ListView listView;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_choiceness_hot_good, null);
        ButterKnife.bind(this, view);
        return view;
    }

    private QuestBean questBean;

    @Override
    protected QuestBean requestData() {
        if (questBean == null) {
            Map<String, String> map = new HashMap<String, String>();
//            map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
            return new QuestBean(map, new HotGoodBean().setTag(getClass().getName()), HttpConstants.HOT_GOOD_LIST);
        } else {
            return questBean;
        }
    }


    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            //想mode添加数据
            HotGoodBean hotGoodBean = (HotGoodBean) bean;

            for (int i = 0; i < hotGoodBean.getData().getCate().size(); i++) {
                initRadioButton(hotGoodBean.getData().getCate().get(i), i);
            }
            //这里是进行平滑的移动
            linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    Button button = (Button) linearLayout.getChildAt(currentCheckedCate).findViewById(R.id.rb_hot_good_classfy_item);
                    button.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
                    scrollView.scrollBy(linearLayout.getChildAt(currentCheckedCate).getLeft(), 0);
                }
            });
            screenArray = hotGoodBean.getData().getScreen();
            initRefreshListView(hotGoodBean.getData().getOnline());
        }
    }

    //分类的筛选
    private int currentCheckedCate = 2;
    //map 前一个分类的id， 后表一个表示第几个加入
    private ArrayList<HashMap<Integer, String>> addButtonList = new ArrayList<>();

    //添加选着条件按钮
    private void initRadioButton(final HotGoodBean.DataEntity.HotGoodCate cate, final int i) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(i, cate.getId());
        addButtonList.add(map);
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
                ToastUtil.showToast(mContext, cate.getName() + cate.getId());
                currentCheckedCate = i;
            }
        });
        linearLayout.addView(view);
    }

    @OnClick(R.id.ll_route_sift)
    void showSiftRoute() {
        showSiftCondition();
        bg.setVisibility(View.VISIBLE);
    }

    List<HotGoodBean.DataEntity.ScreenEntity> screenArray;
    private PopupWindow siftConditionPop;
    ListView groupListView = null;
    ListView childListView = null;
    HotGoodGroupAdapter groupAdapter = null;
    HotGoodChildAdapter childAdapter = null;
    private PopupWindow siftPopupWindow;

    //显示筛选条件
    public void showSiftCondition() {
        View popView = null;
        if (siftConditionPop == null) {
            popView = View.inflate(mContext, R.layout.popupwindow_sift_double_list, null);
            groupListView = (ListView) popView.findViewById(R.id.lv_group);
            childListView = (ListView) popView.findViewById(R.id.lv_child);
            groupAdapter = new HotGoodGroupAdapter(mContext, screenArray);
            groupListView.setAdapter(groupAdapter);
            childAdapter = new HotGoodChildAdapter(mContext);
            childListView.setAdapter(childAdapter);
            childAdapter.setChildData(screenArray.get(0).getChild());
            childAdapter.notifyDataSetChanged();
            groupAdapter.notifyDataSetChanged();
        }

        groupListView.setOnItemClickListener(new MyItemClick());
        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                HotGoodBean.DataEntity.ScreenEntity.ChildEntity bean = (HotGoodBean.DataEntity.ScreenEntity.ChildEntity) parent.getAdapter().getItem(position);
                ToastUtil.showToast(mContext, bean.getName() + bean.getId());
                //请求数据后发送给主线程中
            }
        });

        siftPopupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, CommonHelp.dip2px(mContext, 285));
        siftPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        siftPopupWindow.setOutsideTouchable(true);
//        siftPopupWindow.setFocusable(true);
//        siftPopupWindow.setou
        //显示的筛选popupwinsow的坐标位置
        int[] location = new int[2];
        bottomLine.getLocationInWindow(location);
        int x = location[0];
        int y = location[1];
        siftPopupWindow.showAtLocation(bottomLine, Gravity.LEFT | Gravity.TOP, x, y + 1);
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
    }

    //加载显示的列表
    private HotGoodListAdapter hotGoodListAdapter;

    private void initRefreshListView(final List<HotGoodBean.DataEntity.OnlineEntity> listGood) {
        refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
        listView = refreshViewList.getRefreshableView();
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。
        final ArrayList<String> list = new ArrayList<String>();
        hotGoodListAdapter = new HotGoodListAdapter((ArrayList<HotGoodBean.DataEntity.OnlineEntity>) listGood);
        listView.setAdapter(hotGoodListAdapter);
        View view = View.inflate(mContext, R.layout.layout_hot_good_best_to_me, null);
        view.findViewById(R.id.ll_hot_good_best_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodDetailActivity.class);
                mContext.startActivity(intent);
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
            }
        });

        listView.addHeaderView(view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HotGoodBean.DataEntity.OnlineEntity bean = listGood.get(position - 2);
                Intent intent = new Intent(mContext, GoodDetailActivity.class);
                intent.putExtra("goodId", bean.getId());
                intent.putExtra("isCollect", bean.isIs_house());
                mContext.startActivity(intent);
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
            }
        });
        // 进行数据时的适配和是上啦还是下拉的操作
        refreshViewList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                // 根据不同的mode进行操作,mode中有要进行操作的类型的数据
                if (refreshViewList.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                    // 这里的请求数据是在子线程中，
                    CommonHelp.runOnUIThread(new Runnable() {

                        @Override
                        public void run() {

                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                } else {
                    CommonHelp.runOnUIThread(new Runnable() {

                        @Override
                        public void run() {
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                }
            }
        });
    }

    @OnClick(R.id.overlay)
    void hideBg() {
        bg.setVisibility(View.GONE);
    }
}