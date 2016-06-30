package com.fisher.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.fisher.R;
import com.fisher.adapter.TimeLineAdapter;
import com.fisher.po.TrackData;

public class TrackActitity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView recyclerView;

    private TrackData data;

    private ListView listView;
    private TimeLineAdapter timelineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        initData();
        initView();

    }

    private void initData() {
        data = (TrackData) getIntent().getSerializableExtra("data");
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setSupportActionBar(toolbar);
        //显示左上角的返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //不使用左下角的大标题
        //通常有Tablayout的话就不用大标题了
        collapsingToolbarLayout.setTitleEnabled(false);

        listView = (ListView) this.findViewById(R.id.listview);
        listView.setDividerHeight(0);
        timelineAdapter = new TimeLineAdapter(this, data.getData());
        listView.setAdapter(timelineAdapter);
    }
}
