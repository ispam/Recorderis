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
    //Navigation Drawer
    public DrawerLayout mDrawerLayout;


    //App's Toolbar
    protected Toolbar mActionBarToolbar;

    protected void onCreateDrawer() {
        //Instantiate Navigation Drawer
        setupNavDrawer();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    //Override this method in subclasses if you want a different toolbar for the activity
    protected Toolbar getActionBarToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }


    //Set up Navigation Drawer
    private void setupNavDrawer() {

        //Instantiate Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Instatiate Navigation Drawer List
        mNavigationView= (NavigationView) findViewById(R.id.navigation_view);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
