package com.fisher.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.fisher.R;
import com.fisher.adapter.TimeLineAdapter;
import com.fisher.po.TrackData;
import com.fisher.utils.HttpMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class TrackActitity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    //    @BindView(R.id.recyclerView)
    //    RecyclerView recyclerView;

    @BindView(R.id.listview)
    ListView listView;

    private TimeLineAdapter timelineAdapter;
    private TrackData data;

    private Subscriber subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitleEnabled(false);

        loadData();
    }

    private void loadData() {
        subscriber = new Subscriber<TrackData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(TrackActitity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(TrackData trackData) {
                timelineAdapter = new TimeLineAdapter(TrackActitity.this, trackData.getData());
                listView.setAdapter(timelineAdapter);

            }

        };

        data = (TrackData) getIntent().getSerializableExtra("data");
        HttpMethods.getInstance().getTrackData(subscriber, data.getCom(), data.getNu());
    }
}
