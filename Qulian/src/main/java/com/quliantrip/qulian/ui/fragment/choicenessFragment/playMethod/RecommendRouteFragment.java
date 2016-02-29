package com.quliantrip.qulian.ui.fragment.choicenessFragment.playMethod;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.playMethod.PlayMethodListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.playMethod.MorePlaysBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.choiceActivity.PlayMethodDetailActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.downPopupwindow.ExpandTabView;
import com.quliantrip.qulian.view.downPopupwindow.LeftFilterView;
import com.quliantrip.qulian.view.downPopupwindow.MiddleFilterView;
import com.quliantrip.qulian.view.downPopupwindow.RightFilterView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 玩法展示列表
 */
public class RecommendRouteFragment extends BasePageCheckFragment {
    //筛选条件
    private String city = CommonHelp.getString(R.string.change_city_tacit_id);//筛选城市的id
    private String theme;//特设主题
    private String bespeak;//预约时间
    private String crowd;//使用人群
    private String page = "1";

    @Bind(R.id.expandtab_view)
    ExpandTabView expandTabView;
    @Bind(R.id.rl_pager_empty)
    RelativeLayout empty;

    //下拉刷新
    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    protected ListView listView;
    //添加显示列表对象
    private PlayMethodListAdapter playMethodListAdapter;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_choiceness_play_menthod, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        city = CommonHelp.getStringSp(mContext, "globalCityId", CommonHelp.getString(R.string.change_city_tacit_id));
        Map<String, String> map = new HashMap<String, String>();
        map.put("city", city);
        return new QuestBean(map, new PlayMethodBean().setTag(getClass().getName()), HttpConstants.PLAY_METHOD_LIST);
    }

    public void requestDataForAll() {
        Map<String, String> map = new HashMap<String, String>();
        city = CommonHelp.getStringSp(mContext, "globalCityId", CommonHelp.getString(R.string.change_city_tacit_id));
        if (city != null)
            map.put("city", city);
        if (bespeak != null)
            map.put("bespeak", bespeak);
        if (theme != null)
            map.put("theme", theme);
        if (crowd != null)
            map.put("crowd", crowd);
        new PacketStringReQuest(HttpConstants.PLAY_METHOD_LIST, new PlayMethodBean().setTag(getClass().getName()), map);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            PlayMethodBean playMethodBean = (PlayMethodBean) bean;
            if (viewLeft == null)
                initPlayMethodConditiom(playMethodBean.getData().getScreen());
            initListView(playMethodBean.getData().getPlay());
            if (refreshViewList != null)
                refreshViewList.onRefreshComplete();
        }

        //下拉加载的操作
        if (bean != null && (this.getClass().getName() + "moreData").equals(bean.getTag())) {
            MorePlaysBean morePlaysBean = (MorePlaysBean) bean;
            if (morePlaysBean.getCode() == 200) {
                playMethodListAdapter.addDataListView((ArrayList<PlayBean>) morePlaysBean.getData());
                if (refreshViewList != null)
                    refreshViewList.onRefreshComplete();
                //改变下一页的数据
                page = (Integer.valueOf(page) + 1) + "";
                if (morePlaysBean.getData().size() < 10) {
                    refreshViewList.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                } else {
                    refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
                }
            } else {
                ToastUtil.showToast(mContext, morePlaysBean.getMsg());
            }
        }
    }

    //筛选投的设置
    private LeftFilterView viewLeft;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private MiddleFilterView viewMiddle;
    private RightFilterView viewRight;
    private HashMap<String, String> themeMap = new HashMap<>();
    private HashMap<String, String> timeMap = new HashMap<>();
    private HashMap<String, String> pNumberMap = new HashMap<>();

    List<String> themeString = new ArrayList<String>();
    List<String> timeString = new ArrayList<String>();
    List<String> pNumberString = new ArrayList<String>();

    //初始化筛选条件
    private void initPlayMethodConditiom(List<PlayMethodBean.DataEntity.ScreenEntity> siftList) {
        ArrayList<String> mTextArray = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            PlayMethodBean.DataEntity.ScreenEntity screenEntity = siftList.get(i);
            mTextArray.add(screenEntity.getName());
        }

        PlayMethodBean.DataEntity.ScreenEntity screenEntityOne = siftList.get(0);
        for (PlayMethodBean.DataEntity.ScreenEntity.ChildEntity childEntity : screenEntityOne.getChild()) {
            themeMap.put(childEntity.getName(), childEntity.getId());
            themeString.add(childEntity.getName());
        }

        PlayMethodBean.DataEntity.ScreenEntity screenEntityTwo = siftList.get(1);
        for (PlayMethodBean.DataEntity.ScreenEntity.ChildEntity childEntity : screenEntityTwo.getChild()) {
            timeMap.put(childEntity.getName(), childEntity.getId());
            timeString.add(childEntity.getName());
        }

        PlayMethodBean.DataEntity.ScreenEntity screenEntityThree = siftList.get(2);
        for (PlayMethodBean.DataEntity.ScreenEntity.ChildEntity childEntity : screenEntityThree.getChild()) {
            pNumberMap.put(childEntity.getName(), childEntity.getId());
            pNumberString.add(childEntity.getName());
        }

        String[] themeArray = themeString.toArray(new String[themeString.size()]);
        String[] timeArray = timeString.toArray(new String[timeString.size()]);
        String[] pNumberArray = pNumberString.toArray(new String[pNumberString.size()]);

        viewLeft = new LeftFilterView(mContext, themeArray);
        viewMiddle = new MiddleFilterView(mContext, timeArray);
        viewRight = new RightFilterView(mContext, pNumberArray);

        mViewArray.add(viewLeft);
        mViewArray.add(viewMiddle);
        mViewArray.add(viewRight);
        expandTabView.setValue(mTextArray, mViewArray);
        initListener();
    }

    private void initListener() {

        viewLeft.setOnSelectListener(new LeftFilterView.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewLeft, showText);
            }
        });

        viewMiddle.setOnSelectListener(new MiddleFilterView.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewMiddle, showText);
            }
        });

        viewRight.setOnSelectListener(new RightFilterView.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewRight, showText);
            }
        });
    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    private void onRefresh(View view, String showText) {
        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }

        switch (position) {
            case 0:
                String themeid = themeMap.get(showText);
                RecommendRouteFragment.this.theme = themeid;
                RecommendRouteFragment.this.requestDataForAll();
                break;
            case 1:
                String timeid = timeMap.get(showText);
                RecommendRouteFragment.this.bespeak = timeid;
                RecommendRouteFragment.this.requestDataForAll();
                break;
            case 2:
                String pNumberid = pNumberMap.get(showText);
                RecommendRouteFragment.this.crowd = pNumberid;
                RecommendRouteFragment.this.requestDataForAll();
                break;
        }
    }

    public void hidePopupWindow() {
        if (expandTabView != null) {
            expandTabView.onPressBack();
        }
    }

    private void initListView(final List<PlayBean> listPlayMethod) {
        if (listPlayMethod.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            refreshViewList.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            refreshViewList.setVisibility(View.VISIBLE);
            if (listPlayMethod.size() < 10) {
                refreshViewList.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            } else {
                refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
            }
            listView = refreshViewList.getRefreshableView();
            listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。
            if (playMethodListAdapter == null) {
                playMethodListAdapter = new PlayMethodListAdapter((ArrayList<PlayBean>) listPlayMethod, mContext);
                listView.setAdapter(playMethodListAdapter);
            } else {
                playMethodListAdapter.updataListView((ArrayList<PlayBean>) listPlayMethod);
            }

            //条目点击事件
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    PlayBean bean = listPlayMethod.get(position - 1);
                    Intent intent = new Intent(mContext, PlayMethodDetailActivity.class);
                    intent.putExtra("playMethodId", bean.getId());
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                }
            });

            // 下拉刷新或下拉加载的操作
            refreshViewList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                @Override
                public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                    if (refreshViewList.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                        page = "1";
                        requestDataForAll();
                    } else {
                        Map<String, String> map = new HashMap<String, String>();
                        city = CommonHelp.getStringSp(mContext, "globalCityId", CommonHelp.getString(R.string.change_city_tacit_id));
                        if (city != null)
                            map.put("city", city);
                        if (bespeak != null)
                            map.put("bespeak", bespeak);
                        if (theme != null)
                            map.put("theme", theme);
                        if (crowd != null)
                            map.put("crowd", crowd);
                        map.put("page", page);
                        new PacketStringReQuest(HttpConstants.PLAY_METHOD_LIST, new MorePlaysBean().setTag(RecommendRouteFragment.class.getName() + "moreData"), map);
                    }
                }
            });
        }
    }

    //进行玩法详情页面的筛选条件的数据选着
    public void playMethodSift() {
        expandTabView.setTitle("今日可用", 1);
        viewLeft.checkItem("浪漫樱花");
    }
}

