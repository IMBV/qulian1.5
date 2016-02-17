package com.quliantrip.qulian.ui.activity.findActivity;

import android.app.Activity;
import android.os.Bundle;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.finderAdapter.ImageScaleAdapter;
import com.quliantrip.qulian.lib.photoview.HackyViewPager;

import java.util.ArrayList;

public class ImageScaleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scale);
        HackyViewPager viewPager = (HackyViewPager) findViewById(R.id.viewPager);
        int item = getIntent().getIntExtra("position", 0);
        //获取要记性适配的数据的聚合
        ArrayList<String> urlList = getIntent().getStringArrayListExtra("screenList");
        ImageScaleAdapter scaleAdapter = new ImageScaleAdapter(urlList);
        scaleAdapter.setActivity(this);
        viewPager.setAdapter(scaleAdapter);
        viewPager.setCurrentItem(item);
    }
}
