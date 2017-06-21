package tech.destinum.recorderis.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.HomeDetailsAdapter;
import tech.destinum.recorderis.pojo.Date;

public class Home extends BaseActivity {

    private DBHelper mDBHelper;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private HomeDetailsAdapter mAdapter;
    private ArrayList<Date> mDatesList;

    private Boolean noInfoYet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (noInfoYet.equals(true)){
            setContentView(R.layout.activity_home_help);
        } else {
            setContentView(R.layout.activity_home);
        }
        super.onCreateDrawer();

        mDBHelper = new DBHelper(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);



        mDatesList = new ArrayList<>();

        mAdapter = new HomeDetailsAdapter(mContext, mDatesList);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

    }
}