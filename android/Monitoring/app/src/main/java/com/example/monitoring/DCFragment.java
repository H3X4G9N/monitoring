package com.example.monitoring;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoring.model.DataLogger;
import com.example.monitoring.model.Group;
import com.example.monitoring.model.GroupAuthentication;
import com.example.monitoring.model.User;

import java.util.ArrayList;
import java.util.List;

public class DCFragment extends Fragment {

    List<DataLogger> userdataloggers;
    RecyclerView dcrecyclerview;
    LinearLayoutManager layoutManager;
    Context context = getActivity();
    TextView emptyListAnnotation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View dataloggersview = inflater.inflate(R.layout.fragment_data_collectors, container, false);

        User currentuser = (User) getArguments().getSerializable("User");
        Group usergroup = (Group) getArguments().getSerializable("Usergroup");
        GroupAuthentication authentication = Group.authorize(usergroup, usergroup.getPassword());
        userdataloggers = new ArrayList<DataLogger>();

        userdataloggers = DataLogger.getAllFromUser(currentuser);
        if(userdataloggers != null) {
            emptyListAnnotation = (TextView)dataloggersview.findViewById(R.id.empty_list_view);
            emptyListAnnotation.setText("");

            dcrecyclerview = (RecyclerView) dataloggersview.findViewById(R.id.user_dataloggersgers_recycler_view);

            DataloggersAdapter userDataLoggerAdapter = new DataloggersAdapter(userdataloggers, getActivity(), authentication);
            dcrecyclerview.setAdapter(userDataLoggerAdapter);

            layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            layoutManager.scrollToPosition(0);

            dcrecyclerview.setLayoutManager(layoutManager);
            dcrecyclerview.setHasFixedSize(true);

            return dataloggersview;
        }else{

            emptyListAnnotation = (TextView)dataloggersview.findViewById(R.id.empty_list_view);
            emptyListAnnotation.setText("List is Empty");
            return dataloggersview;
        }
    }
}
