package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.SharedPreferences;
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

import static tech.destinum.recorderis.adapters.FormAdapter.FORM_PREFERENCES;

public class Home extends BaseActivity {

    private DBHelper mDBHelper;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private HomeDetailsAdapter mAdapter;
    private ArrayList<Date> mDatesList;

    public static Boolean noInfoYet = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (noInfoYet.equals(true)){
            setContentView(R.layout.activity_home_help);
        } else {
            setContentView(R.layout.activity_home);

            SharedPreferences mSP = getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE);
            mDBHelper = new DBHelper(this);
            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);
            mDatesList = new ArrayList<>();

            String soat = mSP.getString("soat", "");
            String rtm = mSP.getString("rtm", "");
            String src = mSP.getString("src", "");
            String str = mSP.getString("str", "");
            String to = mSP.getString("to", "");
            String ext = mSP.getString("ext", "");

            long user_id = mDBHelper.getLastUser();

            mDatesList.add(new Date(user_id, getString(R.string.doc_soat), "soat", soat, 0));
            mDatesList.add(new Date(user_id, getString(R.string.doc_rtm), "rtm", rtm, 1));
            mDatesList.add(new Date(user_id, getString(R.string.doc_src), "src", src, 2));
            mDatesList.add(new Date(user_id, getString(R.string.doc_str), "str", str, 3));
            mDatesList.add(new Date(user_id, getString(R.string.doc_tao), "to", to, 4));
            mDatesList.add(new Date(user_id, getString(R.string.doc_ext), "ext", ext, 5));

            mAdapter = new HomeDetailsAdapter(mContext, mDatesList);

            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        }
        super.onCreateDrawer();



    }
}