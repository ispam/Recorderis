package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.result.UserProfile;
import com.squareup.picasso.Picasso;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.utils.CredentialsManager;

public class BaseActivity extends AppCompatActivity {

    public NavigationView mNavigationView;
    protected ActionBarDrawerToggle mToggle;
    protected Toolbar mToolbar;
    public DrawerLayout mDrawerLayout;
    private Menu mMenu;

    private Boolean gotEmail = false;
    private ImageView mImageProfile;
    private TextView mName, mEmail;
    private Auth0 mAuth0;
    private UserProfile mUserProfile;
    private DBHelper mDBHelper;

    public static final String PREFERENCES = "Preferences";

    protected void onCreateDrawer() {
        //Instantiate Navigation Drawer
        setupNavDrawer();

        Fabric.with(this, new Crashlytics());

        //AuthO
        mAuth0 = new Auth0(getString(R.string.auth0_client_id), getString(R.string.auth0_domain));
        // The process to reclaim an UserProfile is preceded by an Authentication call.
        AuthenticationAPIClient aClient = new AuthenticationAPIClient(mAuth0);
        aClient.tokenInfo(CredentialsManager.getCredentials(this).getIdToken())
                .start(new BaseCallback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onSuccess(final UserProfile payload) {
                        BaseActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                mUserProfile = payload;
                                refreshScreenInformation();
                            }
                        });
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {
                        BaseActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(BaseActivity.this, R.string.profile_request_failed, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

    }


    private void refreshScreenInformation() {
        mDBHelper = new DBHelper(getApplicationContext());

        if (mUserProfile.getPictureURL() != null) {
            Picasso.with(getApplicationContext()).load(mUserProfile.getPictureURL()).into(mImageProfile);
        } else {
            Picasso.with(getApplicationContext()).load(mUserProfile.getPictureURL()).into(mImageProfile);
        }
        SharedPreferences mSharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        mName.setText(mUserProfile.getName());
        mSharedPreferences.edit().putString("name", mUserProfile.getName()).commit();

        if (gotEmail.equals(true)){

            String email = mSharedPreferences.getString("email", "");
            mEmail.setText(email);
            if (mSharedPreferences.getBoolean("first_time", true)){
                mDBHelper.createNewUser(mUserProfile.getName(), email);

                mSharedPreferences.edit().putBoolean("first_time", false).commit();
            }

        } else {
            mEmail.setText(mUserProfile.getEmail());
            if (mSharedPreferences.getBoolean("first_time", true)){
                mDBHelper.createNewUser(mUserProfile.getName(), mUserProfile.getEmail());

                mSharedPreferences.edit().putBoolean("first_time", false).commit();
            }

        }


    }


    //Set up Navigation Drawer
    private void setupNavDrawer() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mNavigationView= (NavigationView) findViewById(R.id.navigation_view);
        mMenu = mNavigationView.getMenu();

        MenuItem about = mMenu.findItem(R.id.about);
        SpannableString s =  new SpannableString(about.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.BlackTextColor), 0, s.length(), 0);
        about.setTitle(s);

        View hView =  mNavigationView.getHeaderView(0);
        mImageProfile = (ImageView) hView.findViewById(R.id.image_profile);
        mName = (TextView) hView.findViewById(R.id.nav_name);
        mEmail = (TextView) hView.findViewById(R.id.nav_email);

        setSupportActionBar(mToolbar);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mToggle);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
                mDrawerLayout.closeDrawers();

                item.setChecked(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (item.getItemId()){

                            case R.id.nav_home:
                                Intent intent = new Intent(BaseActivity.this, Home.class);
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                                break;

                            case R.id.nav_privacy_policy:
                                intent = new Intent(BaseActivity.this, Policy.class);
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                                break;

                            case R.id.nav_terms:
                                intent = new Intent(BaseActivity.this, Terms.class);
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                                break;

                            case R.id.nav_profile:
                                intent = new Intent(BaseActivity.this, Profile.class);
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                                break;

                            default:
                                intent = new Intent(BaseActivity.this, Home.class);
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                                break;
                        }
                    }
                }, 225);
                return true;
            }
        });

        setNavigationViewCheckedItem();
    }

    private void setNavigationViewCheckedItem() {
        if (this.getClass().equals(Home.class)) {
            mNavigationView.setCheckedItem(R.id.nav_home);
        } else if (this.getClass().equals(Policy.class)){
            mNavigationView.setCheckedItem(R.id.nav_privacy_policy);
            setTitle(R.string.privacy_policy_link);
        } else if (this.getClass().equals(Terms.class)){
            mNavigationView.setCheckedItem(R.id.nav_terms);
            setTitle(R.string.terms_link);
        } else if (this.getClass().equals(Profile.class)){
            mNavigationView.setCheckedItem(R.id.nav_profile);
            setTitle(R.string.nav_profile);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (this.getClass().equals(Home.class)) {
            return false;
        } else if (this.getClass().equals(Policy.class)){
            return false;
        } else if (this.getClass().equals(Terms.class)){
            return false;
        } else if (this.getClass().equals(Profile.class)){
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.logout) {
                CredentialsManager.deleteCredentials(this);
                Intent intent = new Intent(this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            }
        return super.onOptionsItemSelected(item);
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
}
