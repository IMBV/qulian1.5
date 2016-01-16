package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.quliantrip.qulian.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Qulian5 on 2016/1/5.
 */
public class GoodOrderFragment extends Fragment {
    private Context mContext;
    private View view;
    @Bind(R.id.ll_line)
    LinearLayout line;
    //所有
   @Bind(R.id.rb_me_order_good_all)
   RadioButton all;
   //未付款
   @Bind(R.id.rb_me_order_good_to_pay)
   RadioButton toPay;
   //待确认
   @Bind(R.id.rb_me_order_good_to_confirmed)
   RadioButton toConfirmed;
   //已确认
   @Bind(R.id.rb_me_order_good_confirmed)
   RadioButton confirmed;
   //已使用
   @Bind(R.id.rb_me_order_good_user)
   RadioButton user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_order_good, null);
        ButterKnife.bind(this, view);
        line.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        setOrderNumberCollor(all,10);
        setOrderNumberCollor(toPay,5);
        return view;
    }
    private static int chineseNums(String str) {
        byte b[] = str.getBytes();
        int byteLength = b.length;
        int strLength = str.length();
        return (byteLength - strLength) / 2;
    }

    //设置选择框内的数量的显示为黄色
    public void setOrderNumberCollor(RadioButton rb,int number){
        String s = rb.getText().toString();
        int oldLenght = chineseNums(s);
        String num = "("+number+")";
        int endNumber = num.getBytes().length;
        SpannableString styledText = new SpannableString(s+num);
        styledText.setSpan(new TextAppearanceSpan(mContext, R.style.text_end_yellow), oldLenght, oldLenght+endNumber, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        rb.setText(styledText, TextView.BufferType.SPANNABLE);
    }
}
