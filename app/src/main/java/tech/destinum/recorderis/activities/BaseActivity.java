package tech.destinum.recorderis.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import tech.destinum.recorderis.R;

public class BaseActivity extends AppCompatActivity {

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //Set up Navigation Drawer
    private void setupNavDrawer() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mNavigationView= (NavigationView) findViewById(R.id.navigation_view);

        setSupportActionBar(mToolbar);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mToggle);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
                mDrawerLayout.closeDrawers();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (item.getItemId()){
                            case R.id.nav_home:
                                Intent intent0 = new Intent(BaseActivity.this, Home.class);
                                startActivity(intent0.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                mNavigationView.setCheckedItem(R.id.nav_home);

                                break;

                            case R.id.nav_settings:
                                Intent intent1 = new Intent(BaseActivity.this, Settings.class);
                                startActivity(intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                mNavigationView.setCheckedItem(R.id.nav_settings);

                                break;

                            case R.id.nav_selection:
                                Intent intent2 = new Intent(BaseActivity.this, Selection.class);
                                startActivity(intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                mNavigationView.setCheckedItem(R.id.nav_selection);

                                break;

                            case R.id.nav_privacy_policy:
                                Intent intent3 = new Intent(BaseActivity.this, PrivacyPolicy.class);
                                startActivity(intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                mNavigationView.setCheckedItem(R.id.nav_privacy_policy);

                                break;

                            default:
                                Intent intent4 = new Intent(BaseActivity.this, Home.class);
                                startActivity(intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                mNavigationView.setCheckedItem(R.id.nav_home);

                                break;
                        }
                    }
                }, 225);
                return true;
            }
        });
    }
}
