package com.quliantrip.qulian.mode.homeMode.FunctionModeFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.home.HomeShowBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页功能菜单的数据适配
 */
public class FunctionModeFragmentSecond extends Fragment {
    protected Context mContext;
    private ArrayList<HomeShowBean.DataEntity.MenuEntity> fourList = new ArrayList<>();

    @Bind(R.id.ll_home_function_play_one)
    LinearLayout one;
    @Bind(R.id.ll_home_function_play_two)
    LinearLayout two;
    @Bind(R.id.ll_home_function_play_three)
    LinearLayout three;
    @Bind(R.id.ll_home_function_play_four)
    LinearLayout four;

    @Bind(R.id.lv_home_function_play_one_img)
    ImageView oneImg;
    @Bind(R.id.lv_home_function_play_two_img)
    ImageView twoImg;
    @Bind(R.id.lv_home_function_play_three_img)
    ImageView threeImg;
    @Bind(R.id.lv_home_function_play_four_img)
    ImageView fourImg;

    @Bind(R.id.tv_home_function_play_one_name)
    TextView oneName;
    @Bind(R.id.tv_home_function_play_two_name)
    TextView twoName;
    @Bind(R.id.tv_home_function_play_three_name)
    TextView threeName;
    @Bind(R.id.tv_home_function_play_four_name)
    TextView fourName;

    public FunctionModeFragmentSecond(ArrayList<HomeShowBean.DataEntity.MenuEntity> fourList) {
        this.fourList.clear();
        this.fourList.addAll(fourList);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (Context) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_function_mode_fragment_second, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    //进行数据初始化
    private void initData() {
        if(fourList.size() == 4){
            ImageLoader.getInstance().displayImage(fourList.get(0).getImage(),oneImg, ImageLoaderOptions.pager_options);
            ImageLoader.getInstance().displayImage(fourList.get(1).getImage(),twoImg, ImageLoaderOptions.pager_options);
            ImageLoader.getInstance().displayImage(fourList.get(2).getImage(),threeImg, ImageLoaderOptions.pager_options);
            ImageLoader.getInstance().displayImage(fourList.get(3).getImage(),fourImg, ImageLoaderOptions.pager_options);
            oneName.setText(fourList.get(0).getName());
            oneName.setText(fourList.get(1).getName());
            oneName.setText(fourList.get(2).getName());
            oneName.setText(fourList.get(3).getName());
        }else if(fourList.size() == 3){
            ImageLoader.getInstance().displayImage(fourList.get(0).getImage(),oneImg, ImageLoaderOptions.pager_options);
            ImageLoader.getInstance().displayImage(fourList.get(1).getImage(),twoImg, ImageLoaderOptions.pager_options);
            ImageLoader.getInstance().displayImage(fourList.get(2).getImage(),threeImg, ImageLoaderOptions.pager_options);
            oneName.setText(fourList.get(0).getName());
            oneName.setText(fourList.get(1).getName());
            oneName.setText(fourList.get(2).getName());
            four.setVisibility(View.INVISIBLE);
        }else if (fourList.size() == 2){
            ImageLoader.getInstance().displayImage(fourList.get(0).getImage(),oneImg, ImageLoaderOptions.pager_options);
            ImageLoader.getInstance().displayImage(fourList.get(1).getImage(),twoImg, ImageLoaderOptions.pager_options);
            oneName.setText(fourList.get(0).getName());
            oneName.setText(fourList.get(1).getName());
            three.setVisibility(View.INVISIBLE);
            four.setVisibility(View.INVISIBLE);
        }else if(fourList.size() == 1){
            ImageLoader.getInstance().displayImage(fourList.get(0).getImage(),oneImg, ImageLoaderOptions.pager_options);
            oneName.setText(fourList.get(0).getName());
            two.setVisibility(View.INVISIBLE);
            three.setVisibility(View.INVISIBLE);
            four.setVisibility(View.INVISIBLE);
        }
    }
}
