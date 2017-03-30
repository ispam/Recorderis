package tech.destinum.recorderis.activities;

import android.content.Context;
import android.os.Bundle;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;

public class Home extends BaseActivity {

    private DBHelper mDBHelper;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();

        mDBHelper = new DBHelper(this);



    }


}