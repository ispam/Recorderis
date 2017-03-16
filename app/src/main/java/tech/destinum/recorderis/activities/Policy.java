package tech.destinum.recorderis.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

import tech.destinum.recorderis.R;

public class Policy extends BaseActivity {

    private TextView mPrivacyPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        super.onCreateDrawer();

        mPrivacyPolicy = (TextView) findViewById(R.id.privacy_policy_text);

        mPrivacyPolicy.setText(Html.fromHtml(getString(R.string.privacy_policy_text)));

    }
}
