package tech.destinum.recorderis.activities;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import tech.destinum.recorderis.R;

public class Policy extends BaseActivity {

    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        super.onCreateDrawer();

        mRelativeLayout = findViewById(R.id.policy);

        final WebView view = new WebView(this);

        view.setVerticalScrollBarEnabled(true);

        mRelativeLayout.addView(view);

        view.loadData(getString(R.string.privacy_policy_text), "text/html; charset=utf-8", "utf-8");



    }
}
