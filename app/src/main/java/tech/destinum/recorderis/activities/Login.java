package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.lock.AuthenticationCallback;
import com.auth0.android.lock.Lock;
import com.auth0.android.lock.LockCallback;
import com.auth0.android.lock.utils.LockException;
import com.auth0.android.result.Credentials;
import com.auth0.android.result.UserProfile;

import java.util.HashMap;
import java.util.Map;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.utils.CredentialsManager;

import static tech.destinum.recorderis.adapters.FormAdapter.FORM_PREFERENCES;

public class Login extends AppCompatActivity {

    private Lock lock;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Auth0 auth0 = new Auth0("28BCzzh69tSn8aWrbZvZmJSgExSUrH4W", "ispam.auth0.com");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("scope", "openid offline_access");
        lock = Lock.newBuilder(auth0, callback)
                .withAuthenticationParameters(parameters)
                // Add parameters to the Lock Builder
                .build(this);

        if (CredentialsManager.getCredentials(this).getIdToken() == null) {
            startActivity(lock.newIntent(this));
            return;
        } else {
            goHomeScreen();
        }

        AuthenticationAPIClient aClient = new AuthenticationAPIClient(auth0);
        aClient.tokenInfo(CredentialsManager.getCredentials(this).getIdToken())
                .start(new BaseCallback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onSuccess(final UserProfile payload) {
                        Login.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(Login.this, R.string.automatic_login_success, Toast.LENGTH_SHORT).show();
                                goHomeScreen();
                            }
                        });
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {
                        Login.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(Login.this, R.string.session_expired, Toast.LENGTH_SHORT).show();
                            }
                        });
//                        CredentialsManager.deleteCredentials(getApplicationContext());
//                        startActivity(lock.newIntent(Login.this));
                    }
                });
    }

    private void goHomeScreen() {
        mDBHelper = new DBHelper(this);
        SharedPreferences mSP = getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE);
        if (mSP.getBoolean("first_time", true)) {

            Intent intent = new Intent(getApplicationContext(), Selection.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

            mSP.edit().putBoolean("first_time", false).commit();
        } else {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Your own Activity code
        lock.onDestroy(this);
        lock = null;
    }

    private LockCallback callback = new AuthenticationCallback() {
        @Override
        public void onAuthentication(Credentials credentials) {
            // Login Success response
            Toast.makeText(getApplicationContext(), R.string.login_success, Toast.LENGTH_SHORT).show();
            CredentialsManager.saveCredentials(getApplicationContext(), credentials);
            goHomeScreen();
        }

        @Override
        public void onCanceled() {
            // Login Cancelled response
            Toast.makeText(getApplicationContext(), R.string.login_canceled, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(LockException error){
            // Login Error response
            Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_SHORT).show();
        }
    };

}
