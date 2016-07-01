package com.fisher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fisher.R;
import com.fisher.adapter.RecyclerAdapter;
import com.fisher.po.RouteInfo;
import com.fisher.po.TrackData;
import com.fisher.subscribers.ProgressSubscriber;
import com.fisher.subscribers.SubscriberOnNextListener;
import com.fisher.wx.Article;
import com.fisher.wx.WxHttpMethods;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener, RecyclerAdapter.OnItemLongClickListener {
    private static final String TAG = "MainActivity";

    @BindView(R.id.rootLayout)
    CoordinatorLayout rootLayout;

    @BindView(R.id.multiple_actions)
    FloatingActionsMenu actionsMenu;

    @BindView(R.id.action_fillIn)
    FloatingActionButton actionA;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerAdapter adapter;
    private List<TrackData> trackDataList = new ArrayList<TrackData>();

    private SubscriberOnNextListener getDataOnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        initViews();

        test();

    }


    private void test(){
        getDataOnNext = new SubscriberOnNextListener<List<Article>>() {
            @Override
            public void onNext(List<Article> articles) {
                Log.d(TAG,articles.size()+"");
                setTitle(articles.get(0).getTitle());
                Toast.makeText(MainActivity.this,"complete:"+articles.get(0).getTitle(),Toast.LENGTH_SHORT);
            }
        };
        WxHttpMethods.getInstance().getData(new ProgressSubscriber(getDataOnNext, MainActivity.this), 1);
    }


    private void initViews() {


        //设置并列2行的layoutManager
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        //        设置单列的线性布局的layoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //设置adapter
        adapter = new RecyclerAdapter(trackDataList);
        adapter.setmOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(adapter);


        actionA.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Toast.makeText(MainActivity.this, "手动输入", Toast.LENGTH_SHORT).show();
                                       }
                                   }

        );
    }

    private void initData() {
        List<RouteInfo> routeInfoList = null;
        RouteInfo info = null;
        TrackData data = null;
        for (int i = 0; i < 10; i++) {
            routeInfoList = new ArrayList<RouteInfo>();
            data = new TrackData();
            for (int j = 0; j < 5; j++) {
                info = new RouteInfo("南阳-" + j, "2015-01-01 10:22", "发往南阳光武站1" + j);
                routeInfoList.add(info);
            }
            data.setNu("3944490863");
            data.setCom("zhaijisong");
            data.setData(routeInfoList);
            trackDataList.add(data);
        }
    }

    @Override
    public void onItemClick(View view, Object obj) {
        Intent intent = new Intent(MainActivity.this, TrackActitity.class);
        if (obj != null) {
            TrackData data = (TrackData) ((Object[]) obj)[1];
            intent.putExtra("data", data);
        }
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, Object obj) {
        {
            Object[] data = (Object[]) obj;
            int index = Integer.parseInt(data[0].toString());
            trackDataList.remove(index);
            adapter.notifyDataSetChanged();

            Snackbar.make(rootLayout, index + "已删除！", Snackbar.LENGTH_SHORT).setAction("取消删除操作？", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "取消删除操作，数据已恢复", Toast.LENGTH_SHORT).show();
                }
            }).show();
        }
    }

}
