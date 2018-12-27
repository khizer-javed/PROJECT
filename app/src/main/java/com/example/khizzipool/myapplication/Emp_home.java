package com.example.khizzipool.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Emp_home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView drawerName;
    DatabaseReference databaseReference;
    String Key,first_name,Last_Name,Name;
    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment_home fragment_home = new Fragment_home();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headView = navigationView.getHeaderView(0);
        drawerName = (TextView) headView.findViewById(R.id.drawerName);

        first_name = getIntent().getStringExtra("Name");
        Last_Name = getIntent().getStringExtra("Last_Name");
        Key = getIntent().getStringExtra("userkey");
        Bundle bundle = new Bundle();
        bundle.putString("userkey",Key);
        fragment_home.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.Employee_frame , fragment_home).commit();

        Name = first_name +" "+ Last_Name;
        drawerName.setText(Name);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sign out?");
            builder.setMessage("Do you really want to leave?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Emp_home.this, User_Login.class);
                    finish();
                    startActivity(i);
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.emp_home, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.fragment_home)
        {
            fragmentManager.beginTransaction().replace(R.id.Employee_frame , fragment_home).commit();
        }

        else if (id == R.id.Events)
        {
            fragmentManager.beginTransaction().replace(R.id.Employee_frame , new Fragments_Events()).commit();
        }

        else if (id == R.id.sign_out)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sign out?");
            builder.setMessage("Do you really want to leave?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Emp_home.this, User_Login.class);
                    finish();
                    startActivity(i);
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
