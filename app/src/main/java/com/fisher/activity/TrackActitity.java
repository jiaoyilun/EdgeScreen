package com.fisher.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.fisher.R;
import com.fisher.adapter.TimeLineAdapter;
import com.fisher.http.HttpMethods;
import com.fisher.po.TrackData;
import com.fisher.subscribers.ProgressSubscriber;
import com.fisher.subscribers.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackActitity extends AppCompatActivity {
    private static final String TAG = "TrackActitity";

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

    private SubscriberOnNextListener getTrackOnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitleEnabled(false);


        getTrackOnNext = new SubscriberOnNextListener<TrackData>() {
            @Override
            public void onNext(TrackData trackData) {
                timelineAdapter = new TimeLineAdapter(TrackActitity.this, trackData.getData());
                listView.setAdapter(timelineAdapter);
            }

        };

        //        loadData();
        getData();
    }

  /*  private void loadData() {
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

    }*/

    private void getData() {
        data = (TrackData) getIntent().getSerializableExtra("data");
        HttpMethods.getInstance().getTrackData(new ProgressSubscriber(getTrackOnNext, TrackActitity.this), data.getId(), data.getOrder());

    }

}
