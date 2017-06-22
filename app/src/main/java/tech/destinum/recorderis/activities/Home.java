package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.HomeDetailsAdapter;
import tech.destinum.recorderis.pojo.Date;

import static tech.destinum.recorderis.adapters.FormAdapter.FORM_PREFERENCES;

public class Home extends BaseActivity {

    private DBHelper mDBHelper;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private HomeDetailsAdapter mAdapter;
    private ArrayList<Date> mDatesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();

        SharedPreferences mSP = getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE);

        mDBHelper = new DBHelper(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);

        mDatesList = new ArrayList<>();
        long user_id = mDBHelper.getLastUser();

        String soat = mSP.getString("soat", "");
        if (soat != null){
            mDatesList.add(new Date(user_id, getString(R.string.doc_soat), "soat", soat, 0));
        }

        String rtm = mSP.getString("rtm", "");
        if (rtm != null){
            mDatesList.add(new Date(user_id, getString(R.string.doc_rtm), "rtm", rtm, 1));
        }

        String src = mSP.getString("src", "");
        if (src != null){
            mDatesList.add(new Date(user_id, getString(R.string.doc_src), "src", src, 2));
        }

        String str = mSP.getString("str", "");
        if (str != null){
            mDatesList.add(new Date(user_id, getString(R.string.doc_str), "str", str, 3));
        }

        String to = mSP.getString("to", "");
        if (to != null){
            mDatesList.add(new Date(user_id, getString(R.string.doc_tao), "to", to, 4));
        }

        String ext = mSP.getString("ext", "");
        if (ext != null){
            mDatesList.add(new Date(user_id, getString(R.string.doc_ext), "ext", ext, 5));
        }

        mAdapter = new HomeDetailsAdapter(mContext, mDatesList);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));



    }
}