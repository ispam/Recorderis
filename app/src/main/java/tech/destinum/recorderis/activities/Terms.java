package tech.destinum.recorderis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;

import tech.destinum.recorderis.R;

public class Terms extends BaseActivity {

    private TextView mTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        super.onCreateDrawer();

        mTerms = (TextView) findViewById(R.id.terms_and_conditions_text);

        mTerms.setText(Html.fromHtml(getString(R.string.terms_and_conditions)));

    }

}
