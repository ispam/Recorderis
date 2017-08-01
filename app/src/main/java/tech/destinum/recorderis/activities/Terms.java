package tech.destinum.recorderis.activities;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import tech.destinum.recorderis.R;

public class Terms extends BaseActivity {

    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        super.onCreateDrawer();


        mRelativeLayout = (RelativeLayout) findViewById(R.id.terms);

        final WebView view = new WebView(this);

        view.setVerticalScrollBarEnabled(true);

        mRelativeLayout.addView(view);

        view.loadData(getString(R.string.terms_and_conditions), "text/html; charset=utf-8", "utf-8");


    }

}
