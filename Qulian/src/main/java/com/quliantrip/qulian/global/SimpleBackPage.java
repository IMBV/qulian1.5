package com.quliantrip.qulian.global;


import com.quliantrip.qulian.R;
import com.quliantrip.qulian.ui.fragment.homeFragment.CityChooseFragment;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.PayCheckstandFragment;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.SubmitOrderGoodFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.IntegralFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.LoginFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.MyCommenInfoFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.discount.MyDiscountFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.MySettingFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.register.RegisterFragment;

public enum SimpleBackPage {
    MY_LOGIN(1, R.string.actionbar_title_active, LoginFragment.class),
    MY_SETTING(2, R.string.actionbar_title_setting, MySettingFragment.class),

    CITY_CHOOSE(3, R.string.actionbar_city_choose, CityChooseFragment.class),
    MY_COMMON_INFO(4, R.string.actionbar_commom_info, MyCommenInfoFragment.class),
    MY_REGISTER(5, R.string.actionbar_me_register, RegisterFragment.class),
    MY_INTEGRAL(6, R.string.actionbar_me_integral, IntegralFragment.class),

    GOOD_ORDER(7,R.string.actionbar_good_order,SubmitOrderGoodFragment.class),
    PAY_METHOD(8,R.string.actionbar_pay_method,PayCheckstandFragment.class),
    MY_DISCOUNT(9,R.string.actionbar_me_discount,MyDiscountFragment.class);

    private int title;
    private Class<?> clz;
    private int value;

    private SimpleBackPage(int value, int title, Class<?> clz) {
        this.value = value;
        this.title = title;
        this.clz = clz;
    }


    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static SimpleBackPage getPageByValue(int val) {
        for (SimpleBackPage p : values()) {
            if (p.getValue() == val)
                return p;
        }
        return null;
    }
}
