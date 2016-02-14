package com.quliantrip.qulian;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.quliantrip.qulian.util.TDevice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test() {

        String data = getStrTime("1459440000");
        System.out.println(data);
        System.out.println(data);
        System.out.println(data);
        System.out.println(data);
        System.out.println(data);
        System.out.println(data);
        System.out.println(data);
    }
    // 将时间戳转为字符串
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }
}