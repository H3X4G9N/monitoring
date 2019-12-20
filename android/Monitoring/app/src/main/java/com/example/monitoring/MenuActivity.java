package com.example.monitoring;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.monitoring.model.Group;
import com.example.monitoring.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;

public class MenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener, Serializable {
    // Navigation drawer
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    Context context = this;

    // Database
    User user;
    Group rootgroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //connection
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //user
        Intent loginintent = getIntent();
        user = (User)loginintent.getSerializableExtra("User");
        rootgroup = Group.getRootFromUser(user);

        // groups

        // Set Drawer Navigation
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.Open,R.string.Close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("DashboardUser", user);
            DashboardFragment dashboardFragment = new DashboardFragment();
            dashboardFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dashboardFragment).commit();
            mNavigationView.setCheckedItem(R.id.nav_dashboard);
        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_dashboard:
                Bundle dashboardbundle = new Bundle();
                dashboardbundle.putSerializable("DashboardUser", user);
                DashboardFragment dashboardFragment = new DashboardFragment();
                dashboardFragment.setArguments(dashboardbundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dashboardFragment).commit();
                break;
            case R.id.nav_groups:
                GroupFragment groupFragment = new GroupFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, groupFragment).commit();
                break;
            case R.id.nav_data_collectors:
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", user);
                bundle.putSerializable("Usergroup", rootgroup);
                DCFragment dcFragment = new DCFragment();
                dcFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dcFragment).commit();
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
