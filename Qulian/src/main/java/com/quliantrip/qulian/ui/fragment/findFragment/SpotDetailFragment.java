package com.quliantrip.qulian.ui.fragment.findFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.finderAdapter.SpotDetailVoiceAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.find.SpotDetailBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.service.Iservice;
import com.quliantrip.qulian.ui.activity.findActivity.SpotDetailActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.MyListView;
import com.quliantrip.qulian.view.RollViewPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 景点详情页
 */
public class SpotDetailFragment extends BasePageCheckFragment {
    private View view;
    private AlertDialog dialog;
    private SpotDetailVoiceAdapter spotDetailVoiceAdapter;

    //轮播图与下面的小点
    private RollViewPage rollViewPage;
    @Bind(R.id.top_news_viewpager)
    LinearLayout top_news_viewpager;//轮播的viewpage
    @Bind(R.id.dots_ll)
    LinearLayout dots_ll;//下面的小点
    //添加图片和小点的集合
    private List<String> imageList = new ArrayList<String>();
    private List<View> dotList = new ArrayList<View>();

    //景点语音的列表
    @Bind(R.id.spot_introduct_voice)
    MyListView listView;

    //音乐有关的数据
    private Iservice iservice;//就是我们的中间人对象的实现
    private static SeekBar seekBar;

    //定义一个handler
    public static Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //(1)获取我们封装的数据
            Bundle data = msg.getData();
            int duration = data.getInt("duration");
            int currentPosition = data.getInt("currentPosition");

            //(2)设置seekbar的最大进度
            seekBar.setMax(duration);
            seekBar.setProgress(currentPosition); //设置当前进度
        }

        ;
    };

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_find_spot_method_detail, null);
        seekBar = (SeekBar) view.findViewById(R.id.seekbar_progress);
        ButterKnife.bind(this, view);
        listView.setFocusable(false);
        //添加音频播放时的滑动监听
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //当拖动停止的时候调用
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //调用播放的指定位置的方法
                if (iservice == null)
                    iservice = ((SpotDetailActivity) mContext).getIservice();
                iservice.callSetSeekPosition(seekBar.getProgress());
            }

            //刚开始拖动调用
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //当进度发生改变的时候调用
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

            }
        });
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        return new QuestBean(map, new SpotDetailBean().setTag(getClass().getName()), HttpConstants.VOICE_SQUARE_DETAIL);
    }

    @Override
    public void onEventMainThread(final BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            SpotDetailBean spotDetailBean = (SpotDetailBean) bean;
            if (spotDetailBean.getCode() == 200) {
                SpotDetailBean.DataEntity dataEntity = spotDetailBean.getData();
                ((SpotDetailActivity) mContext).showOrHideBack(false);
                //添加基本信息
                //轮播图
                initRollView(dataEntity.getAttraction().getImgs());

                //添加附近优惠的条目


                //设置该景点的有的可以播放语音内容
                if (spotDetailVoiceAdapter == null) {
                    spotDetailVoiceAdapter = new SpotDetailVoiceAdapter((ArrayList<SpotDetailBean.DataEntity.VoicInfoEntity>) dataEntity.getVoicInfo());
                    listView.setAdapter(spotDetailVoiceAdapter);
                } else {
                    spotDetailVoiceAdapter.updataList((ArrayList<SpotDetailBean.DataEntity.VoicInfoEntity>) dataEntity.getVoicInfo());
                }

            } else {
                ToastUtil.showToast(mContext, spotDetailBean.getMsg());
                ((SpotDetailActivity) mContext).showOrHideBack(true);
            }
        }
    }

    //点击显示播放的语音的内容的文字部分
    @OnClick(R.id.iv_spot_detail_voice_text_des)
    void showSpotDetailText() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        View view = View.inflate(mContext, R.layout.layout_find_voice_play_text_content, null);
        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
    }

    //初始化轮播图
    private void initRollView(String s) {
        imageList.clear();
        dotList.clear();
        String[] imges = s.split(",");
        for (String img : imges) {
            imageList.add(img);
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


    //点击播放音乐的操作
    @OnClick(R.id.iv_spot_play_detail_anniu)
    void playDetail() {
        if (iservice == null)
            iservice = ((SpotDetailActivity) mContext).getIservice();
        iservice.callPlayMusic();
    }
}
