package com.fisher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.fisher.R;
import com.fisher.adapter.RecyclerAdapter;
import com.fisher.po.RouteInfo;
import com.fisher.po.TrackData;
import com.fisher.test.ApiManager;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener, RecyclerAdapter.OnItemLongClickListener {
    private static final String TAG = "MainActivity";


    private CoordinatorLayout rootLayout;
    private FloatingActionsMenu actionsMenu;
    private FloatingActionButton actionA;

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<TrackData> trackDataList = new ArrayList<TrackData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initViews();

    }


    private void initViews() {
        actionsMenu = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
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


        actionA = (FloatingActionButton)

                findViewById(R.id.action_fillIn);

        actionA.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {


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
            data.setNu("201235412" + i);
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

    private void getData() {
        String com = "";
        String nu = "";
        ApiManager.getTranckData(com, nu).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<TrackData>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(TrackData trackData) {
            }
        });
    }
}
