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

import com.example.monitoring.model.DataLogger;
import com.example.monitoring.model.Group;
import com.example.monitoring.model.GroupAuthentication;

import java.util.ArrayList;
import java.util.List;

public class GroupDataCollectorsActivity extends AppCompatActivity {
    Group chosenVisibleGroup;
    TextView annotationForDataLoggers, annotationForPassword;
    EditText visibleGroupPasswordInput;
    GroupAuthentication visiblegroupauthentication;

    List<DataLogger> visibledataloggerlist;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_data_collectors);

        Intent itemIntent = getIntent();
        chosenVisibleGroup = (Group) itemIntent.getSerializableExtra("VisibleGroupForDC");

        annotationForDataLoggers = (TextView)findViewById(R.id.visible_group_annotation);
        annotationForPassword = (TextView)findViewById(R.id.incorrect_visiblegroup_password_annotation);
        visibleGroupPasswordInput = (EditText)findViewById(R.id.visible_group_password);

    }

    public void showDataLoggers(View view){
        String pass = visibleGroupPasswordInput.getText().toString();
        visiblegroupauthentication = Group.authorize(chosenVisibleGroup, pass);
        if(visiblegroupauthentication != null){
            annotationForDataLoggers.setText("Authorization complete!");
            annotationForPassword.setText("");
            visibleGroupPasswordInput.setText("");

            visibledataloggerlist = new ArrayList<DataLogger>();

            visibledataloggerlist = DataLogger.getAllFromGroup(visiblegroupauthentication);
            if(visibledataloggerlist.isEmpty() == false) {
                recyclerView = (RecyclerView) findViewById(R.id.data_loggers_recycler_view);

                DataloggersAdapter dataloggerAdapter = new DataloggersAdapter(visibledataloggerlist,
                        GroupDataCollectorsActivity.this,
                        visiblegroupauthentication);
                recyclerView.setAdapter(dataloggerAdapter);

                layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                layoutManager.scrollToPosition(0);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
            }else{
                annotationForDataLoggers.setText("This list is empty!");
            }

        }else{
            annotationForPassword.setText("Password incorrect");
        }
    }

}
