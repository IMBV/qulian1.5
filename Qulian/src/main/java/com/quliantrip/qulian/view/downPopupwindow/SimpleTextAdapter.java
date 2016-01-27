//package com.quliantrip.qulian.view.downPopupwindow;
//
//import android.content.Context;
//import android.util.TypedValue;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import com.quliantrip.qulian.R;
//import com.quliantrip.qulian.adapter.BasicAdapter;
//import com.quliantrip.qulian.util.CommonHelp;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SimpleTextAdapter extends BasicAdapter<PlayMethodBean.DataEntity.ScreenEntity.ChildEntity> {
//    private Context mContext;
//    private ArrayList<PlayMethodBean.DataEntity.ScreenEntity.ChildEntity> mListData;
//
//    public SimpleTextAdapter(ArrayList<PlayMethodBean.DataEntity.ScreenEntity.ChildEntity> list,Context context) {
//        super(list);
//        mContext = context;
//    }
//
//    public void setTextSize(float tSize) {
//        textSize = tSize;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        TextView view;
//        if (convertView == null) {
//            view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.choose_item, parent, false);
//        } else {
//            view = (TextView) convertView;
//        }
//        view.setTag(position);
//        PlayMethodBean.DataEntity.ScreenEntity.ChildEntity mString = null;
//        if (mListData != null) {
//            if (position < mListData.size()) {
//                mString = mListData.get(position);
//            }
//        } else if (mArrayData != null) {
//            if (position < mArrayData.length) {
//                mString = mArrayData[position];
//            }
//        }
//        view.setText(mString);
//        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
//        if (selectedText != null && selectedText.equals(mString)) {
//            view.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
//            view.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
//        } else {
//            view.setBackgroundColor(CommonHelp.getColor(R.color.colorPrimary));
//            view.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
//        }
//
//        view.setPadding(20, 0, 0, 0);
//        view.setOnClickListener(onClickListener);
//        return view;
//    }
//
//    public void setOnItemClickListener(OnItemClickListener l) {
//        mOnItemClickListener = l;
//    }
//
//    public interface OnItemClickListener {
//        public void onItemClick(View view, int position);
//    }
//
//}
