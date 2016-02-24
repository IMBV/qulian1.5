package com.quliantrip.qulian.ui.activity.meActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.good.GoodDetailBranchCheckAdapter;
import com.quliantrip.qulian.adapter.myAdapter.RegisterStyleAdapter;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.CollectResultBean;
import com.quliantrip.qulian.domain.choice.good.GoodDetailBean;
import com.quliantrip.qulian.domain.me.GoodCollectListBean;
import com.quliantrip.qulian.domain.me.PlayCollectListBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailActivity;
import com.quliantrip.qulian.ui.fragment.meFragment.collectFragment.GoodCollectFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.collectFragment.PlayMethodCollectFragment;
import com.quliantrip.qulian.util.ToastUtil;
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
 * 我的收藏
 */
public class MyCollectActivity extends SwipeBackActivity {
    private Context mContext;
    //当前的tab
    private int currentTab;
    //状态栏
    private int playTabEditState = 0;
    private int goodTabEditState = 0;

    private View view;
    @Bind(R.id.view_pager_register)
    ViewPager viewPager;
    @Bind(R.id.tv_register_style_phone)
    TextView phoneRegister;
    @Bind(R.id.tv_register_style_email)
    TextView emailRegister;
    @Bind(R.id.v_register_phone_indicate_line)
    View phone_line;
    @Bind(R.id.v_register_email_indicate_line)
    View email_line;

    //编辑的提示文字
    @Bind(R.id.tv_mycolloct_edit)
    TextView editText;
    private RegisterStyleAdapter adapter;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    private PlayMethodCollectFragment playMethodCollectFragment;
    private GoodCollectFragment goodCollectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollect);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mContext = this;
        playMethodCollectFragment = new PlayMethodCollectFragment();
        goodCollectFragment = new GoodCollectFragment();
        initListener();
        initData();
    }

    private void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                lightAndScaleTitle();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    protected void initData() {
        fragments.add(playMethodCollectFragment);
        fragments.add(goodCollectFragment);
        adapter = new RegisterStyleAdapter(((FragmentActivity) mContext).getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        lightAndScaleTitle();
    }

    private void lightAndScaleTitle() {
        currentTab = viewPager.getCurrentItem();
        phoneRegister.setTextColor(getResources().getColor(currentTab == 0 ? R.color.app_main_collor : R.color.app_main_title_text));
        emailRegister.setTextColor(getResources().getColor(currentTab == 1 ? R.color.app_main_collor : R.color.app_main_title_text));
        phone_line.setVisibility(currentTab == 0 ? View.VISIBLE : View.GONE);
        email_line.setVisibility(currentTab == 1 ? View.VISIBLE : View.GONE);
        setEditText();
    }

    @OnClick(R.id.rl_register_style_phone)
    void showPhoneRegister() {
        viewPager.setCurrentItem(0);
        currentTab = viewPager.getCurrentItem();
    }

    @OnClick(R.id.rl_register_style_email)
    void showEmailRegister() {
        viewPager.setCurrentItem(1);
        currentTab = viewPager.getCurrentItem();
    }

    @OnClick(R.id.iv_icon_back)
    void finishActivity() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    private boolean isPlayMethodEdit = true;
    private boolean isHotGoodEdit = true;

    //编辑的操作
    @OnClick(R.id.tv_mycolloct_edit)
    void editListColloct() {
        System.out.println(currentTab +goodTabEditState);
        if (currentTab == 0) {
            if (playMethodDeletelist.size() >= 1)
                isPlayMethodEdit = !isPlayMethodEdit;
            playMethodCollectFragment.setEdit(isPlayMethodEdit);
            if (playTabEditState == 0) {
                editText.setText("取消");
                isPlayMethodEdit = false;
                playTabEditState = 1;
            } else if (playTabEditState == 1) {
                editText.setText("编辑");
                isPlayMethodEdit = true;
                playTabEditState = 0;
            } else {
                //删除玩法集合
                String deleteList = "";
                for (int i = 0; i < playMethodDeletelist.size(); i++) {
                    if (playMethodDeletelist.size() - 1 == i) {
                        deleteList = deleteList + playMethodDeletelist.get(i).getHouseid();
                    } else {
                        deleteList = deleteList + playMethodDeletelist.get(i).getHouseid() + ",";
                    }
                }
                Map<String, String> map = new HashMap<>();
                map.put("id", deleteList);
                new PacketStringReQuest(HttpConstants.GOOD_CANCEL_COLLECT, new CollectResultBean().setTag(getClass().getName() + "playmethodListDelete"), map);
            }
        } else {
            if (hotGoodDeletelist.size() >= 1)
                isHotGoodEdit = !isHotGoodEdit;
            goodCollectFragment.setEdit(isHotGoodEdit);
            if (goodTabEditState == 0) {
                editText.setText("取消");
                isHotGoodEdit = false;
                goodTabEditState = 1;
            } else if (goodTabEditState == 1) {
                editText.setText("编辑");
                isHotGoodEdit = true;
                goodTabEditState = 0;
            } else {
                //这里进行删除商品的操作
                String deleteList = "";
                for (int i = 0; i < hotGoodDeletelist.size(); i++) {
                    if (hotGoodDeletelist.size() - 1 == i) {
                        deleteList = deleteList + hotGoodDeletelist.get(i).getHouseid();
                    } else {
                        deleteList = deleteList + hotGoodDeletelist.get(i).getHouseid() + ",";
                    }
                }
                System.out.println("del"+deleteList);
                Map<String, String> map = new HashMap<>();
                map.put("id", deleteList);
                new PacketStringReQuest(HttpConstants.GOOD_CANCEL_COLLECT, new CollectResultBean().setTag(getClass().getName() + "goodListDelete"), map);
            }
        }
    }

    public void onEventMainThread(BaseJson bean) {
        if (bean != null && (this.getClass().getName() + "playmethodListDelete").equals(bean.getTag())) {
            CollectResultBean collectResultBean = (CollectResultBean) bean;
            if (collectResultBean.getCode() == 200) {
                playMethodCollectFragment.removeAllDelect((ArrayList<PlayCollectListBean.DataEntity>) playMethodDeletelist);
                playMethodDeletelist.clear();
                editText.setText("取消");
                isPlayMethodEdit = false;
                playTabEditState = 1;
            } else {
                ToastUtil.showToast(mContext, collectResultBean.getMsg());
            }
        }

        if (bean != null && (this.getClass().getName() + "goodListDelete").equals(bean.getTag())) {
            CollectResultBean collectResultBean = (CollectResultBean) bean;
            if (collectResultBean.getCode() == 200) {
                goodCollectFragment.removeAllDeleteList(hotGoodDeletelist);
                hotGoodDeletelist.clear();
                editText.setText("取消");
                isHotGoodEdit = false;
                goodTabEditState = 1;
            } else {
                ToastUtil.showToast(mContext, collectResultBean.getMsg());
            }
        }
    }

    //编辑的提示的文字的处理
    public void setEditText() {
        String s = "编辑";
        switch (currentTab) {
            case 0:
                switch (playTabEditState) {
                    case 0:
                        s = "编辑";
                        break;
                    case 1:
                        s = "取消";
                        break;
                    case 2:
                        s = "删除";
                        break;
                }
                break;
            case 1:
                switch (goodTabEditState) {
                    case 0:
                        s = "编辑";
                        break;
                    case 1:
                        s = "取消";
                        break;
                    case 2:
                        s = "删除";
                        break;
                }
                break;
            default:
                break;
        }
        editText.setText(s);
    }

    List<PlayCollectListBean.DataEntity> playMethodDeletelist = new ArrayList<PlayCollectListBean.DataEntity>();
    List<GoodCollectListBean.DataEntity.HouseEntity> hotGoodDeletelist = new ArrayList<GoodCollectListBean.DataEntity.HouseEntity>();

    //添加删除的操作
    public void addOrDelectCollect(boolean b, Object s) {
        if (currentTab == 0) {
            if (b) {
                //删除
                playMethodDeletelist.remove(s);
            } else {
                //添加
                playMethodDeletelist.add((PlayCollectListBean.DataEntity) s);
            }
            if (playMethodDeletelist.size() == 0) {
                editText.setText("取消");
                playTabEditState = 1;
            } else {
                editText.setText("删除");
                playTabEditState = 3;
            }
        } else if (currentTab == 1) {
            if (b) {
                //删除
                hotGoodDeletelist.remove(s);
            } else {
                //添加
                hotGoodDeletelist.add((GoodCollectListBean.DataEntity.HouseEntity) s);
            }
            if (hotGoodDeletelist.size() == 0) {
                editText.setText("取消");
                goodTabEditState = 1;
            } else {
                editText.setText("删除");
                goodTabEditState = 3;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}