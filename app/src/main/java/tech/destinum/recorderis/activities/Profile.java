package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.ProfileAdapter;

public class Profile extends BaseActivity {

    public TextView mName;
    public Context mContext;
    public DBHelper mDBHelper;
    public ProfileAdapter mProfileAdapter;
    public RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        super.onCreateDrawer();

        mDBHelper = new DBHelper(this);
        mName = (TextView) findViewById(R.id.format_profile_name);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_profile);

        SharedPreferences mSP = getSharedPreferences(BaseActivity.PREFERENCES, Context.MODE_PRIVATE);
        String name = mSP.getString("name", "");
        mName.setText(name);

        mProfileAdapter = new ProfileAdapter(mContext, mDBHelper.getAllDates());
        mRecyclerView.setAdapter(mProfileAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

    }
}
