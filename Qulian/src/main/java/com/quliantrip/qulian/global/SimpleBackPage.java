package com.quliantrip.qulian.global;


import com.quliantrip.qulian.R;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.ConfirmOrderFragment;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.PayCheckstandFragment;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.PlayMethodConfirmOrderFragment;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.SubmitOrderGoodFragment;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.SubmitOrderPlayMethodFragment;
import com.quliantrip.qulian.ui.fragment.homeFragment.CityChooseFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.AboutMeFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.ChangePasswordFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.IntegralFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.LoginFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.MySettingFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.discount.MyDiscountFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.linkman.AddLinkManFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.linkman.MyLinkmanFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.register.RegisterFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.searchBackPassword.SearchBackPasswordFragment;

public enum SimpleBackPage {
    MY_LOGIN(1, R.string.actionbar_title_active, LoginFragment.class),
    MY_SETTING(2, R.string.actionbar_title_setting, MySettingFragment.class),
    CITY_CHOOSE(3, R.string.actionbar_city_choose, CityChooseFragment.class),
    MY_COMMON_INFO(4, R.string.actionbar_commom_info, MyLinkmanFragment.class),
    MY_REGISTER(5, R.string.actionbar_me_register, RegisterFragment.class),
    MY_INTEGRAL(6, R.string.actionbar_me_integral, IntegralFragment.class),
    GOOD_ORDER(7, R.string.actionbar_good_order, SubmitOrderGoodFragment.class),
    PAY_METHOD(8, R.string.actionbar_pay_method, PayCheckstandFragment.class),
    MY_DISCOUNT(9, R.string.actionbar_me_discount, MyDiscountFragment.class),
    MY_CHANGE_PASSWORD(11, R.string.actionbar_me_change_password, ChangePasswordFragment.class),
    MY_ADD_LINKMAN(12, R.string.actionbar_me_add_link_man, AddLinkManFragment.class),
    ORDER_CONFIRM(13, R.string.actionbar_order_confirm, ConfirmOrderFragment.class),
    OABOUT_ME(14, R.string.actionbar_my_about, AboutMeFragment.class),
    MY_SEARCH_BACK_PASSWORD(15, R.string.actionbar_me_searck_back_password, SearchBackPasswordFragment.class),
    PLAY_METHOD_ORDER(16, R.string.actionbar_good_order, SubmitOrderPlayMethodFragment.class),
    PLAY_METHOD_ORDER_CONFIRM(17, R.string.actionbar_order_confirm, PlayMethodConfirmOrderFragment.class);

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
