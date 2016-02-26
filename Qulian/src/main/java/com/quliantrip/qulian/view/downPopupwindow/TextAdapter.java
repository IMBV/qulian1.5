package com.quliantrip.qulian.view.downPopupwindow;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.util.CommonHelp;

public class TextAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> mListData;
    private String[] mArrayData;
    private int selectedPos = -1;
    private String selectedText = "";
    private int normalDrawbleId;
    private float textSize;
    private OnClickListener onClickListener;
    private OnItemClickListener mOnItemClickListener;

    public TextAdapter(Context context, List<String> listData, int nId) {
        super(context, R.string.no_data, listData);
        mContext = context;
        mListData = listData;
        normalDrawbleId = nId;
        init();
    }

    private void init() {
        onClickListener = new OnClickListener() {

            @Override
            public void onClick(View view) {
                selectedPos = (Integer) view.getTag();
                setSelectedPosition(selectedPos);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, selectedPos);
                }
            }
        };
    }

    public TextAdapter(Context context, String[] arrayData, int nId) {
        super(context, R.string.no_data, arrayData);
        mContext = context;
        mArrayData = arrayData;
        normalDrawbleId = nId;
        init();
    }

    public void setSelectedPosition(int pos) {
        if (mListData != null && pos < mListData.size()) {
            selectedPos = pos;
            selectedText = mListData.get(pos);
            notifyDataSetChanged();
        } else if (mArrayData != null && pos < mArrayData.length) {
            selectedPos = pos;
            selectedText = mArrayData[pos];
            notifyDataSetChanged();
        }
    }

    public void setSelectedPositionNoNotify(int pos) {
        selectedPos = pos;
        if (mListData != null && pos < mListData.size()) {
            selectedText = mListData.get(pos);
        } else if (mArrayData != null && pos < mArrayData.length) {
            selectedText = mArrayData[pos];
        }
    }

    public int getSelectedPosition() {
        if (mArrayData != null && selectedPos < mArrayData.length) {
            return selectedPos;
        }
        if (mListData != null && selectedPos < mListData.size()) {
            return selectedPos;
        }
        return -1;
    }

    public void setTextSize(float tSize) {
        textSize = tSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view;
        if (convertView == null) {
            view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.choose_item, parent, false);
        } else {
            view = (TextView) convertView;
        }
        view.setTag(position);
        String mString = "";
        if (mListData != null) {
            if (position < mListData.size()) {
                mString = mListData.get(position);
            }
        } else if (mArrayData != null) {
            if (position < mArrayData.length) {
                mString = mArrayData[position];
            }
        }
        view.setText(mString);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        if (selectedText != null && selectedText.equals(mString)) {
            view.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
            view.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
        } else {
            view.setBackgroundColor(CommonHelp.getColor(R.color.colorPrimary));
            view.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
        }

        view.setPadding(20, 0, 0, 0);
        view.setOnClickListener(onClickListener);
        return view;
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    //设置传递过来数据
    public void setSelectedText(String selectedText){
        this.selectedText = selectedText;
        notifyDataSetChanged();
    }

}
