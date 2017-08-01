package tech.destinum.recorderis.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import tech.destinum.recorderis.R;

public class Policy extends BaseActivity {

    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        super.onCreateDrawer();

        mRelativeLayout = (RelativeLayout) findViewById(R.id.policy);

        final WebView view = new WebView(this);

        view.setVerticalScrollBarEnabled(true);

        mRelativeLayout.addView(view);

        view.loadData(getString(R.string.privacy_policy_text), "text/html; charset=utf-8", "utf-8");


    }
}
