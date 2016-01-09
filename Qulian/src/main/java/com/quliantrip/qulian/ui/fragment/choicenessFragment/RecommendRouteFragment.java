package com.quliantrip.qulian.ui.fragment.choicenessFragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.popAdapter.SingleListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.SingleListBean;
import com.quliantrip.qulian.domain.TuanBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.MyListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yuly on 2015/12/7.
 * www.quliantrip.com
 */
public class RecommendRouteFragment extends BasePageCheckFragment {
    //筛选条的字体和箭头
    @Bind(R.id.tv_paly_method_theme_text)
    TextView siftThemeText;
    @Bind(R.id.iv_paly_method_theme_img)
    ImageView siftThemeImg;
    @Bind(R.id.tv_paly_method_pretime_text)
    TextView siftTimeText;
    @Bind(R.id.iv_paly_method_pre_time_img)
    ImageView siftTimeImg;
    @Bind(R.id.tv_paly_method_person_number_text)
    TextView siftPnumText;
    @Bind(R.id.iv_paly_method_person_number_img)
    ImageView siftPnumImg;

    @Bind(R.id.v_consume_list_bottom_line)
    View bottomLine;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_choiceness_play_menthod, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ctl", "tuan");
        map.put("r_type", "1");
        return new QuestBean(map, new TuanBean().setTag(getClass().getName()), HttpConstants.HOST_ADDR_ROOT_NET);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {

    }

    private boolean isShowTheme = true;
    private boolean isPreTime = true;
    private boolean isPersonNumber = true;
    //点击选择主题
    @OnClick(R.id.ll_paly_method_specialty_theme)
    void showSpacialtyTheme() {
        List<SingleListBean> list = new ArrayList<SingleListBean>();
        list.add(new SingleListBean("1", "主题1"));
        list.add(new SingleListBean("2", "主题2"));
        list.add(new SingleListBean("3", "主题3"));
        list.add(new SingleListBean("4", "主题4"));
        list.add(new SingleListBean("5", "主题5"));
        list.add(new SingleListBean("1", "主题6"));
        list.add(new SingleListBean("2", "主题7"));
        list.add(new SingleListBean("3", "主题8"));
        list.add(new SingleListBean("4", "主题9"));
        list.add(new SingleListBean("5", "主题10"));
        hideOtherPopupWindow(1);
        if (isShowTheme)
            showSingleListPopuWindow(list,1);
        else
            hideSelfPopupwindow();
        setSelectCollor(siftThemeText,siftThemeImg,isShowTheme);
        isShowTheme = !isShowTheme;
    }


    //点击选择预约时间
    @OnClick(R.id.ll_paly_method_pre_time)
    void showPreviewTime() {
        List<SingleListBean> list = new ArrayList<SingleListBean>();
        list.add(new SingleListBean("1", "预约时间1"));
        list.add(new SingleListBean("2", "预约时间2"));
        list.add(new SingleListBean("3", "预约时间3"));
        list.add(new SingleListBean("4", "预约时间4"));
        list.add(new SingleListBean("5", "预约时间5"));
        list.add(new SingleListBean("1", "预约时间6"));
        list.add(new SingleListBean("2", "预约时间7"));
        list.add(new SingleListBean("3", "预约时间8"));
        list.add(new SingleListBean("4", "预约时间9"));
        list.add(new SingleListBean("5", "预约时间10"));
        hideOtherPopupWindow(2);
        if (isPreTime)
            showSingleListPopuWindow(list,2);
        else
            hideSelfPopupwindow();
        setSelectCollor(siftTimeText,siftTimeImg,isPreTime);
        isPreTime = !isPreTime;
    }
    //点击选着适合人数
    @OnClick(R.id.tv_paly_method_person_number_text)
    void showPersonNumber() {
        List<SingleListBean> list = new ArrayList<SingleListBean>();
        list.add(new SingleListBean("1", "适合人数1"));
        list.add(new SingleListBean("2", "适合人数2"));
        list.add(new SingleListBean("3", "适合人数3"));
        list.add(new SingleListBean("4", "适合人数4"));
        list.add(new SingleListBean("5", "适合人数5"));
        list.add(new SingleListBean("1", "适合人数6"));
        list.add(new SingleListBean("2", "适合人数7"));
        list.add(new SingleListBean("3", "适合人数8"));
        list.add(new SingleListBean("4", "适合人数9"));
        list.add(new SingleListBean("5", "适合人数10"));
        hideOtherPopupWindow(3);
        if (isPersonNumber)
            showSingleListPopuWindow(list,3);
        else
            hideSelfPopupwindow();
        setSelectCollor(siftPnumText,siftPnumImg,isPersonNumber);
        isPersonNumber = !isPersonNumber;
    }

    //设置选着的颜色
    public void setSelectCollor(TextView textView,ImageView imageView,boolean b){
        if (b){
            textView.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
            imageView.setImageResource(R.mipmap.nav_shaixuan_press);
        }else{
            textView.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
            imageView.setImageResource(R.mipmap.nav_shaixuan_normal);
        }
    }

    public void hideSelfPopupwindow(){
        if (singlePopupWindow != null) {
            singlePopupWindow.dismiss();
            singlePopupWindow = null;
        }
    }

    //隐藏others其他的popupwindow
    public void hideOtherPopupWindow(int id){
        switch (id){
            case 1:
                isPreTime = true;
                isPersonNumber = true;
                setSelectCollor(siftTimeText,siftTimeImg,!isPreTime);
                setSelectCollor(siftPnumText,siftPnumImg,!isPersonNumber);
                break;

            case 2:
                isShowTheme = true;
                isPersonNumber = true;
                setSelectCollor(siftThemeText,siftThemeImg,!isShowTheme);
                setSelectCollor(siftPnumText,siftPnumImg,!isPersonNumber);
                break;
            case 3:
                isShowTheme = true;
                isPreTime = true;
                setSelectCollor(siftThemeText,siftThemeImg,!isShowTheme);
                setSelectCollor(siftTimeText,siftTimeImg,!isPreTime);
                break;
            default:
                break;
        }
        if (singlePopupWindow != null) {
            singlePopupWindow.dismiss();
            singlePopupWindow = null;
        }

    }
    //隐藏所有的pouwindow
    public void hidePopupWindow() {
        //在onsrcll中的方法在oncreate会调用,所以判断是否为空
        if (singlePopupWindow != null) {
            singlePopupWindow.dismiss();
            singlePopupWindow = null;
        }
        if(siftPnumText!=null){
            hideAllInfo();
        }
    }
    private void hideAllInfo() {
        isShowTheme = true;
        isPreTime = true;
        isPersonNumber = true;
        setSelectCollor(siftThemeText,siftThemeImg,!isShowTheme);
        setSelectCollor(siftTimeText,siftTimeImg,!isPreTime);
        setSelectCollor(siftPnumText,siftPnumImg,!isPersonNumber);
    }

    //显示弹框
    private PopupWindow singlePopupWindow;
    MyListView singleListView = null;
    SingleListAdapter singleListAdapter = null;

    public void showSingleListPopuWindow(List<SingleListBean> singleList, final int type) {

        View popView = null;
        if (singlePopupWindow == null) {
            popView = View.inflate(mContext, R.layout.popupwindow_sift_single_list, null);

            singleListView = (MyListView) popView.findViewById(R.id.mlv_single_list);
            singleListAdapter = new SingleListAdapter(mContext, singleList);
            singleListView.setAdapter(singleListAdapter);
        }

        singleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //获取数据适配的bean对象
                SingleListBean bean = (SingleListBean) parent.getAdapter().getItem(position);
                //这里是内部类在显示的时候要注意发送时的要使用的是推荐路线的fragment中进行接收
                switch (type){
                    case 1:
                        ToastUtil.showToast(mContext,bean.getId()+bean.getDes()+"");
                        break;
                    case 2:
                        ToastUtil.showToast(mContext,bean.getId()+bean.getDes()+"");
                        break;
                    case 3:
                        ToastUtil.showToast(mContext,bean.getId()+bean.getDes()+"");
                        break;
                }
            }
        });
        singlePopupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, CommonHelp.dip2px(mContext,285));
        singlePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //popupwindow显示的坐标的位置
        int[] location = new int[2];
        bottomLine.getLocationInWindow(location);
        int x = location[0];
        int y = location[1];
        singlePopupWindow.showAtLocation(bottomLine, Gravity.LEFT | Gravity.TOP, x, y + 1);
    }
}

