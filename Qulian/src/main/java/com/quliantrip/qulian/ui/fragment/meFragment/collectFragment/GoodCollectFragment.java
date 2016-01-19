package com.quliantrip.qulian.ui.fragment.meFragment.collectFragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.ui.activity.meActivity.MyCollectActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.SlipRihtLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuly on 2016/1/5.
 * 热门商品的收藏
 */
public class GoodCollectFragment extends Fragment {
    private Context mContext;
    private View view;
    private GoodListAdapter test;
    private ArrayList<com.quliantrip.qulian.domain.Test> list;

    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    protected ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_play_method_order, null);
        ButterKnife.bind(this, view);
        initRefreshListView();
        return view;
    }

    public void setEdit(boolean b) {
        test.setEdit(b);
        test.notifyDataSetChanged();
        if (b) {
            //条目单击事件
            listView.setOnItemClickListener(null);
            //条目长按事件
            listView.setOnItemLongClickListener(null);
        } else {
            //条目单击事件
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //这里下拉刷新头是占一条的
                    ToastUtil.showToast(mContext, list.get(position - 1).getName());
                }
            });
            //条目长按事件
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    ToastUtil.showToast(mContext, "长按" + list.get(position - 1));
                    return true;
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (test != null)
            test.notifyDataSetChanged();
    }

    private void initRefreshListView() {
        // 设置PullToRefu的mode
        refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
        listView = refreshViewList.getRefreshableView();
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。
        list = new ArrayList<com.quliantrip.qulian.domain.Test>();
        int i;
        for (i = 0; i <= 30; i++)
            list.add(new com.quliantrip.qulian.domain.Test(false, "hotGood" + i));
        test = new GoodListAdapter(list, mContext);
        listView.setAdapter(test);
        //条目单击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这里下拉刷新头是占一条的
                ToastUtil.showToast(mContext, "商品的单击事件" + list.get(position - 1).getName());
            }
        });
        //条目长按事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showToast(mContext, "商品长按" + list.get(position - 1).getName());
                return true;
            }
        });

        //这里是为了让滑动时显示出来数据
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int currentItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (Math.abs(getScrollY() - currentItem) > 5 && firstVisibleItem < (totalItemCount - visibleItemCount))
                    test.notifyDataSetChanged();
                currentItem = getScrollY();
            }

            public int getScrollY() {
                View c = listView.getChildAt(0);
                if (c == null) {
                    return 0;
                }
                int firstVisiblePosition = listView.getFirstVisiblePosition();
                int top = c.getTop();
                return -top + firstVisiblePosition * c.getHeight();
            }
        });

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
                            test.addItem(new com.quliantrip.qulian.domain.Test(false, "shang"));
                            test.notifyDataSetChanged();
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                } else {
                    CommonHelp.runOnUIThread(new Runnable() {

                        @Override
                        public void run() {
                            test.addItem(new com.quliantrip.qulian.domain.Test(false, "xia"));
                            test.notifyDataSetChanged();
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                }
            }
        });
    }
}

class GoodListAdapter extends BasicAdapter<com.quliantrip.qulian.domain.Test> {
    private MyCollectActivity activity;

    public GoodListAdapter(ArrayList<com.quliantrip.qulian.domain.Test> list, Context context) {
        super(list);
        activity = (MyCollectActivity) context;
    }

    public void addItem(com.quliantrip.qulian.domain.Test s) {
        list.add(s);
    }

    private boolean isEdit = false;

    public void setEdit(boolean b) {
        isEdit = b;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_my_collect_play_method_item, null);
        }
        final Holder holder = Holder.getHolder(convertView);

        final com.quliantrip.qulian.domain.Test name = list.get(position);
        holder.city.setText(name.getName());

        holder.checkDeledct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addOrDelectCollect(name.ischeck(), "saasdf" + name.getName());
                name.setIscheck(!name.ischeck());
                notifyDataSetChanged();

            }
        });

        if (name.ischeck())
            holder.state.setImageResource(R.mipmap.cnb_wode_pre);
        else
            holder.state.setImageResource(R.mipmap.cnb_wode_nor);

        holder.slipRihtLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                holder.slipRihtLayout.layoutContent(isEdit);
            }
        });
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_text)
        TextView city;
        @Bind(R.id.ll_is_checked_delect)
        LinearLayout checkDeledct;
        @Bind(R.id.srl_play_method_collect_item)
        SlipRihtLayout slipRihtLayout;
        @Bind(R.id.iv_collect_Img_state)
        ImageView state;

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
