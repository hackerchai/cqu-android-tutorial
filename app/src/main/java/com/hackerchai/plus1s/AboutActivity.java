package com.hackerchai.plus1s;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    protected static final String ACTIVITY_TAG="plus1s_AboutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp_seconds = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                int seconds = sp_seconds.getInt("seconds",0);
                seconds += 1;
                String original_fab_text = getResources().getString(R.string.main_activity_fab_button_snackbar_text);

                String fab_text = String.format(original_fab_text, seconds);
                Log.d(ACTIVITY_TAG,fab_text);

                Snackbar.make(view, fab_text, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                SharedPreferences.Editor editor = sp_seconds.edit();
                editor.putInt("seconds",seconds);
                editor.apply();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final TextView tv = (TextView)findViewById(R.id.editText);


        Button button_about = findViewById(R.id.button3);
        button_about.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if(tv.getText().toString().equals("苟"))
                                                {
                                                    Snackbar.make(v,R.string.about_activity_snackbar_text_success, Snackbar.LENGTH_LONG)
                                                            .setAction("Action", null).show();
                                                }
                                                else
                                                {
                                                    Snackbar.make(v,R.string.about_activity_snackbar_text_failure, Snackbar.LENGTH_LONG)
                                                            .setAction("Action", null).show();
                                                }
                                            }
                                        }

        );
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
