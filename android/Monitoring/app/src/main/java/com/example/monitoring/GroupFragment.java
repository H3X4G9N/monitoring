package com.example.monitoring;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoring.model.Group;

import java.util.ArrayList;
import java.util.List;


public class GroupFragment extends Fragment {

    List<Group> rootgrouplist;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Context context = getActivity();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View groupview = inflater.inflate(R.layout.fragment_groups, container, false);

        rootgrouplist = new ArrayList<Group>();

        rootgrouplist = Group.getAllVisibleRoot();

        recyclerView = (RecyclerView) groupview.findViewById(R.id.group_recycler_view);

        GroupAdapter rootGroupAdapter = new GroupAdapter(rootgrouplist, getActivity());
        recyclerView.setAdapter(rootGroupAdapter);

        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        return groupview;
    }



}
