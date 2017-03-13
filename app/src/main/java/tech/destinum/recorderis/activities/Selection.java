package tech.destinum.recorderis.activities;

import android.os.Bundle;

import tech.destinum.recorderis.R;

public class Selection extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        super.onCreateDrawer();
    }
}
