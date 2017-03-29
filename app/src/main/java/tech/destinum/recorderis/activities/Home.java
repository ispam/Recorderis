package tech.destinum.recorderis.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.HomeAdapter;
import tech.destinum.recorderis.pojo.Dates;
import tech.destinum.recorderis.pojo.User;

public class Home extends BaseActivity {

    private HomeAdapter mHomeAdapter;
    private RecyclerView mRecyclerView;
    private DBHelper mDBHelper;
    private Context mContext;
    private Dates mDates;

    private static int REQUEST_CODE_OK = 12302;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();

        mDBHelper = new DBHelper(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);
        mHomeAdapter = new HomeAdapter(getApplicationContext(), mDBHelper.getUser());

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mHomeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }


}