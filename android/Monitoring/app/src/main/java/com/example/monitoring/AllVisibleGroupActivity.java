package com.example.monitoring;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoring.model.Group;
import com.example.monitoring.model.GroupAuthentication;

import java.util.ArrayList;
import java.util.List;

public class AllVisibleGroupActivity extends AppCompatActivity {
    Group groupToAuthorize;
    TextView annotationForGroup, annotationForPassword, visibleGroupListText;
    EditText groupPasswordInput;

    GroupAuthentication rootgroupAuthentication;
    List<Group> visiblegrouplist;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_visible_group);

        Intent fromrootgroup = getIntent();
        groupToAuthorize = (Group) fromrootgroup.getSerializableExtra("GroupforAuthorization");

        annotationForGroup = (TextView)findViewById(R.id.root_group_annotation);
        annotationForPassword = (TextView)findViewById(R.id.incorrect_password_annotation);
        visibleGroupListText = (TextView)findViewById(R.id.visible_group_list_text);
        groupPasswordInput = (EditText)findViewById(R.id.root_group_password);

    }


    public void AuthorizeRootGroup(View view){

        String pass = groupPasswordInput.getText().toString();
        rootgroupAuthentication = Group.authorize(groupToAuthorize, pass);
        if(rootgroupAuthentication != null){

            annotationForGroup.setText("Authorization complete!");
            groupPasswordInput.setText("");
            annotationForPassword.setText("");

            visiblegrouplist = new ArrayList<Group>();

            visiblegrouplist = Group.getAllVisible(rootgroupAuthentication);
            if(visiblegrouplist.isEmpty() == false) {
                visibleGroupListText.setText("Visible Group List");
                recyclerView = (RecyclerView) findViewById(R.id.visible_group_recycler_view);

                VisibleGroupAdapter groupAdapter = new VisibleGroupAdapter(visiblegrouplist, AllVisibleGroupActivity.this);
                recyclerView.setAdapter(groupAdapter);

                layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                layoutManager.scrollToPosition(0);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
            }else{
                visibleGroupListText.setText("List is empty");
            }

        }else{
            annotationForPassword.setText("Password is incorrect");
        }



    }


}
