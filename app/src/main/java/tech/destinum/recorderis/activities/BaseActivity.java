package tech.destinum.recorderis.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import tech.destinum.recorderis.R;

public class BaseActivity extends ActionBarActivity {

    public NavigationView mNavigationView;
    protected ActionBarDrawerToggle mToggle;
    protected Toolbar mToolbar;
    public DrawerLayout mDrawerLayout;

    protected void onCreateDrawer() {
        //Instantiate Navigation Drawer
        setupNavDrawer();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Set up Navigation Drawer
    private void setupNavDrawer() {

        //Instantiate Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        //Instatiate Navigation Drawer List
        mNavigationView= (NavigationView) findViewById(R.id.navigation_view);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent0 = new Intent(BaseActivity.this, Home.class);
                        startActivity(intent0.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                        break;
                    case R.id.nav_settings:
                        Intent intent1 = new Intent(BaseActivity.this, Settings.class);
                        startActivity(intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                        break;
                    case R.id.nav_selection:
                        Intent intent2 = new Intent(BaseActivity.this, Selection.class);
                        startActivity(intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                        break;
                    case R.id.nav_privacy_policy:
                        Intent intent3 = new Intent(BaseActivity.this, PrivacyPolicy.class);
                        startActivity(intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                        break;

                    default:
                        Intent intent4 = new Intent(BaseActivity.this, Home.class);
                        startActivity(intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                        break;
                }
                return true;
            }
        });

    }

}
