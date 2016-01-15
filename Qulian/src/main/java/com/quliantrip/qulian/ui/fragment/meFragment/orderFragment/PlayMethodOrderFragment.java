package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.CityListBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.SlipRihtLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Qulian5 on 2016/1/5.
 */
public class PlayMethodOrderFragment extends Fragment {
    private Context mContext;
    private View view;

    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    protected ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_play_method_order, null);
        ButterKnife.bind(this, view);
        initRefreshListView();
        return view;
    }


    private void initRefreshListView() {
        // 设置PullToRefu的mode
        refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
        listView = refreshViewList.getRefreshableView();
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。
        final ArrayList<String> list = new ArrayList<String>();
        int i;
        for(i = 0;i <= 30;i++)
            list.add("asdf" + i);
        final Test test = new Test(list);
        listView.setAdapter(test);

        // 进行数据时的适配和是上啦还是下拉的操作
        refreshViewList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                // 根据不同的mode进行操作,mode中有要进行操作的类型的数据
                if (refreshViewList.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                    // 这里的请求数据是在子线程中，
                    CommonHelp.runOnUIThread(new Runnable() {

                        @Override
                        public void run() {
                            test.addItem("shang");
                            test.notifyDataSetChanged();
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                } else {
                    CommonHelp.runOnUIThread(new Runnable() {

                        @Override
                        public void run() {
                            test.addItem("xia");
                            test.notifyDataSetChanged();
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                }
            }
        });
    }
}

class Test extends BasicAdapter<String> {


    public Test(ArrayList<String> list) {
        super(list);
    }
    public void addItem(String s){
        list.add(s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_city_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        String name = list.get(position);
        holder.city.setText(name);
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_city)
        TextView city;

        public Holder(View convertView) {
            super();
            ButterKnife.bind(this, convertView);
        }

        public static Holder getHolder(View convertView) {
            Holder holder = (Holder) convertView.getTag();
            if (holder == null) {
                holder = new Holder(convertView);
                convertView.setTag(holder);
            }
            return holder;
        }

    }
}
