package com.dat.android.horizontalcenterlockrecyclerviewexperiment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dat.android.horizontalcenterlockrecyclerviewexperiment.model.DummyGenerator;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.vertical_list)
    protected RecyclerView list;
    private VerticalListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VerticalListAdapter(this, DummyGenerator.getCheeseItems(15));
        list.setAdapter(adapter);
    }
}
