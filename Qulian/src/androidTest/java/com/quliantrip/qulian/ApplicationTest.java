package com.quliantrip.qulian;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.quliantrip.qulian.util.TDevice;

public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test() {
        System.out.print(TDevice.getVersionName());
    }
}