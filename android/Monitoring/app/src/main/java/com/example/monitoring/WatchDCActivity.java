package com.example.monitoring;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoring.model.Group;

import java.util.ArrayList;
import java.util.List;

public class WatchDCActivity extends AppCompatActivity {
    List<Group> watchrootgrouplist;
    RecyclerView recyclerView;
    LinearLayoutManager watchLayoutManager;
    Context context = this;
    TextView emptylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_dc);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        watchrootgrouplist = new ArrayList<Group>();
        watchrootgrouplist = Group.getAllVisibleRoot();
        if(watchrootgrouplist != null) {
            emptylist.setText("");
            recyclerView = (RecyclerView) findViewById(R.id.watch_group_recycler_view);

            GroupAdapter watchRootGroupAdapter = new GroupAdapter(watchrootgrouplist, context);
            recyclerView.setAdapter(watchRootGroupAdapter);

            watchLayoutManager = new LinearLayoutManager(context);
            watchLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            watchLayoutManager.scrollToPosition(0);

            recyclerView.setLayoutManager(watchLayoutManager);
            recyclerView.setHasFixedSize(true);
        }else{
            emptylist.setText("Empty List");
        }
    }
}


