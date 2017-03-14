package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.result.UserProfile;
import com.squareup.picasso.Picasso;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.utils.CredentialsManager;

public class BaseActivity extends AppCompatActivity {

    public NavigationView mNavigationView;
    protected ActionBarDrawerToggle mToggle;
    protected Toolbar mToolbar;
    public DrawerLayout mDrawerLayout;

    private ImageView mImageProfile;
    private TextView mName, mEmail;
    private Auth0 mAuth0;
    private UserProfile mUserProfile;

    private static final String PREFERENCES = "Preferences";

    protected void onCreateDrawer() {
        //Instantiate Navigation Drawer
        setupNavDrawer();

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
                                Toast.makeText(BaseActivity.this, "Profile Request Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

    }


    private void refreshScreenInformation() {
        if (mUserProfile.getPictureURL() != null) {
            Picasso.with(getApplicationContext()).load(mUserProfile.getPictureURL()).into(mImageProfile);
        } else {
            Picasso.with(getApplicationContext()).load(mUserProfile.getPictureURL()).into(mImageProfile);
        }
        mName.setText(mUserProfile.getName());

        if (mUserProfile.getEmail() == null){
            final EditText email = new EditText(getApplicationContext());
            AlertDialog mDialog = new AlertDialog.Builder(BaseActivity.this).setTitle(R.string.need_email).setView(email)
                    .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String item = String.valueOf(email.getText()).trim();
                            if (item.length() <= 0 || item == ""){
                                Toast.makeText(getApplicationContext(), R.string.need_email, Toast.LENGTH_SHORT).show();
                            } else {
                                SharedPreferences mSharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = mSharedPreferences.edit();
                                editor.putString("email", item);
                                editor.commit();
                                mEmail.setText(mUserProfile.getEmail());
                            }
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "Need to create settings before using this toast", Toast.LENGTH_LONG).show();
                        }
                    }).create();
            mDialog.show();
        } else {
            mEmail.setText(mUserProfile.getEmail());
        }
    }


    //Set up Navigation Drawer
    private void setupNavDrawer() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mNavigationView= (NavigationView) findViewById(R.id.navigation_view);

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
        } else if (this.getClass().equals(Settings.class)) {
            mNavigationView.setCheckedItem(R.id.nav_settings);
        } else if (this.getClass().equals(Selection.class)){
            mNavigationView.setCheckedItem(R.id.nav_selection);
        } else if (this.getClass().equals(PrivacyPolicy.class)){
            mNavigationView.setCheckedItem(R.id.nav_privacy_policy);
        }
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
            CredentialsManager.deleteCredentials(this);
            startActivity(new Intent(this, Login.class));
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
