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
//        String timeStemp = getTime("2016-04-01");
//        System.out.println(timeStemp);
//        System.out.println("sadfasdfsadf");
//        System.out.println("sadfasdfsadf");
//        System.out.println("sadfasdfsadf");
//        System.out.println("sadfasdfsadf");
//        System.out.println("sadfasdfsadf");
    }



//    public static String getTime(String user_time) {
//        String re_time = null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date d;
//        try {
//            d = sdf.parse(user_time);
//            long l = d.getTime();
//            String str = String.valueOf(l);
//            re_time = str.substring(0, 10);
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return re_time;
//    }
}