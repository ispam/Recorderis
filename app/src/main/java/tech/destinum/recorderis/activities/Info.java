package tech.destinum.recorderis.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import tech.destinum.recorderis.R;

public class Info extends BaseActivity {

    private TextView mPrivacyPolicy, mTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        super.onCreateDrawer();

        mPrivacyPolicy = (TextView) findViewById(R.id.policy_tv);;
    }

}