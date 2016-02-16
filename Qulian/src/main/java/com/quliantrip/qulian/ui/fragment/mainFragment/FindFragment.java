package com.quliantrip.qulian.ui.fragment.mainFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.HomeBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.fragment.findFragment.FindContentFragment;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.UIHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发现界面
 */
public class FindFragment extends BasePageCheckFragment {
    private View view;
    private AlertDialog dialog;
    @Bind(R.id.find_change_city)
    TextView homeTitle;
    @Bind(R.id.city_img)
    ImageView cityImg;
    @Bind(R.id.tv_find_weather_city)
    TextView weatherCity;
    @Bind(R.id.tv_find_weather_data)
    TextView weatherData;

    //当前的年月
    private String dayString;
    private String monthString;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_main_find, null);
        ButterKnife.bind(this, view);
        ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fl_find_content_container, new FindContentFragment()).commit();
        homeTitle.setText(CommonHelp.getStringSp(mContext, "globalCityName", "北京"));
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Time time = new Time();
        time.setToNow();
        monthString = (time.month+1) + "";
        dayString = time.monthDay + "";
        Map<String, String> map = new HashMap<String, String>();
        map.put("ctl", "index");
        map.put("act", "app");
        map.put("r_type", "1");
        return new QuestBean(map, new HomeBean().setTag(getClass().getName()), HttpConstants.HOST_ADDR_ROOT_NET);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            ImageLoader.getInstance().displayImage(CommonHelp.getStringSp(mContext, "cityImg", ""), cityImg, ImageLoaderOptions.pager_options);
            weatherCity.setText(CommonHelp.getStringSp(mContext, "globalCityName", "北京"));
            weatherData.setText(monthString+"/"+dayString);
        }
    }

    //点击切换城市
    @OnClick(R.id.find_change_city)
    void chooseSity() {
        Bundle bundle = new Bundle();
        String s = homeTitle.getText().toString().trim();
        bundle.putString("cityName", s);
        UIHelper.showCityChoose(this, 31, bundle);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 31) {
            CommonHelp.saveStringSp(mContext, "globalCityId", data.getStringExtra("cityId"));
            CommonHelp.saveStringSp(mContext, "globalCityName", data.getStringExtra("cityName"));
            CommonHelp.saveStringSp(mContext, "cityImg", data.getStringExtra("cityImg"));
            ImageLoader.getInstance().displayImage(data.getStringExtra("cityImg"), cityImg, ImageLoaderOptions.pager_options);
            weatherCity.setText(CommonHelp.getStringSp(mContext, "globalCityName", "北京"));
            homeTitle.setText(data.getStringExtra("cityName"));
            //这里是进行添加数据
//            Map<String, String> map = new HashMap<String, String>();
//            new PacketStringReQuest(HttpConstants.HOME_MAIN, new HomeShowBean().setTag(HomeFragment.this.getClass().getName()), map, null);
        }
    }

    //这里是进行数据展示的界面
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            String cityNameString = CommonHelp.getStringSp(mContext, "globalCityName", "北京");
            homeTitle.setText(cityNameString);
        } else {
            String cityNameString = CommonHelp.getStringSp(mContext, "globalCityName", "北京");
            homeTitle.setText(cityNameString);
            weatherCity.setText(CommonHelp.getStringSp(mContext, "globalCityName", "北京"));
            ImageLoader.getInstance().displayImage(CommonHelp.getStringSp(mContext, "cityImg", ""), cityImg, ImageLoaderOptions.pager_options);
        }
    }

    //点击进入群
    @OnClick(R.id.bt_into_flock)
    void intoQun() {
        //建立弹出对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        View view = View.inflate(mContext, R.layout.layout_find_into_qun_grounp, null);
        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
    }

//这里是播放视频的操作
//    private PopupWindow popupWindow;
//    private View view;
//    private VideoView videoView;
//
//    @Bind(R.id.btn_play)
//    ImageView btn_play;
//    @Bind(R.id.tv_current_progress)
//    TextView tv_current_progress;
//    @Bind(R.id.seekbar_video)
//    SeekBar seekbar_video;
//    @Bind(R.id.tv_duration)
//    TextView tv_duration;
//    @Bind(R.id.btn_screen)
//    ImageView btn_screen;
//
//    private final int MSG_UPDATE_SYSTEM_TIME = 0;//更新系统时间
//    private final int MSG_UPDATE_PLAY_PROGRESS = 1;//更新播放进度
//    private final int MSG_HIDE_CONTROL = 2;//隐藏控制面板
//    private Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case MSG_UPDATE_PLAY_PROGRESS:
//                    updatePlayProgress();
//                    break;
//            }
//        }
//
//        ;
//    };
//    private void initVidio() {
//        videoView = (VideoView) view.findViewById(R.id.video_view);
//        videoView.setVideoURI(Uri.parse("http://192.168.0.19:8080/shipin.mp4"));
//        videoView.start();
//
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                videoView.start();
//
//                seekbar_video.setMax(videoView.getDuration());
//                btn_play.setBackgroundResource(R.drawable.selector_btn_pause);
//                tv_duration.setText(StringUtil.formatVideoDuration(videoView.getDuration()));
//
//                updatePlayProgress();
//            }
//        });
//
//        seekbar_video.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                handler.sendEmptyMessageDelayed(MSG_HIDE_CONTROL, 5000);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                handler.removeMessages(MSG_HIDE_CONTROL);
//            }
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress,
//                                          boolean fromUser) {
//                if (fromUser) {
//                    videoView.seekTo(progress);
//                    seekbar_video.setProgress(progress);
//                    tv_current_progress.setText(StringUtil.formatVideoDuration(progress));
//                }
//            }
//        });
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                btn_play.setBackgroundResource(R.drawable.selector_btn_play);
//            }
//        });
//
//        btn_play.setBackgroundResource(R.drawable.selector_btn_pause);
//        seekbar_video.setProgress(videoView.getCurrentPosition());
//        btn_play.setBackgroundResource(R.drawable.selector_btn_pause);
//    }
//
//    /**
//     * 更新播放进度
//     */
//    private void updatePlayProgress() {
//        seekbar_video.setProgress(videoView.getCurrentPosition());
//        tv_current_progress.setText(StringUtil.formatVideoDuration(videoView.getCurrentPosition()));
//        handler.sendEmptyMessageDelayed(MSG_UPDATE_PLAY_PROGRESS, 500);
//    }
//
//
//
//
//    private void updatePlayBtnBg() {
//        btn_play.setBackgroundResource(videoView.isPlaying() ? R.drawable.selector_btn_pause : R.drawable.selector_btn_play);
//    }
//
//    @OnClick(R.id.btn_play)
//    void startButton() {
//        if (videoView.isPlaying()) {
//            videoView.pause();
//        } else {
//            videoView.start();
//        }
//        updatePlayBtnBg();
//    }
//
//    @OnClick(R.id.btn_screen)
//    void fullScreen() {
//
//        videoView.switchScreen();
//        btn_screen.setBackgroundResource(videoView.isFullScreen() ?
//                R.drawable.selector_btn_defaultscreen : R.drawable.selector_btn_fullscreen);
//        ToastUtil.showToast(mContext, "sf");
//    }
}
