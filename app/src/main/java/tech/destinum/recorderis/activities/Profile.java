package tech.destinum.recorderis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tech.destinum.recorderis.R;

public class Profile extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        super.onCreateDrawer();
    }
}
