package com.fisher.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

public class ConfigureActivity extends AppCompatActivity {
    private CoordinatorLayout rootLayout;
    private FloatingActionsMenu actionsMenu;
    private FloatingActionButton actionA;

    private RecyclerView recyclerView;
    private List<RouteInfo> routeInfoList = new ArrayList<RouteInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

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
        final RecyclerAdapter adapter = new RecyclerAdapter(routeInfoList);
        adapter.setOnItemLongClickListener(new RecyclerAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, String data) {
                //                Toast.makeText(ConfigureActivity.this, data, Toast.LENGTH_SHORT).show();

                //                int index = Integer.parseInt(data);
                //                adapter.notifyDataSetChanged();

                Snackbar.make(rootLayout, data + "已删除！", Snackbar.LENGTH_SHORT).setAction("取消删除操作？", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
            }
        });
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
        RouteInfo info = null;
        for (int i = 0; i < 10; i++) {
            info = new RouteInfo("南阳-" + i, "2015-01-01 10:22", "发往南阳光武站1" + i);
            routeInfoList.add(info);
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
