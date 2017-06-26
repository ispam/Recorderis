package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.HomeDetailsAdapter;
import tech.destinum.recorderis.adapters.HomeSymbolsAdapter;

import static tech.destinum.recorderis.adapters.FormAdapter.FORM_PREFERENCES;

public class Home extends BaseActivity {

    private DBHelper mDBHelper;
    private Context mContext;
    private RecyclerView mRecyclerViewDetails, mRecyclerViewSymbols;
    private HomeDetailsAdapter mDetailsAdapter;
    private HomeSymbolsAdapter mSymbolsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();

        SharedPreferences mSP = getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE);

        mDBHelper = new DBHelper(getApplicationContext());
        mRecyclerViewDetails = (RecyclerView) findViewById(R.id.recycler_view_details);
        mRecyclerViewSymbols = (RecyclerView) findViewById(R.id.recycler_view_symbols);

        mSymbolsAdapter = new HomeSymbolsAdapter(mContext, mDBHelper.getAllDates());

        mRecyclerViewSymbols.setAdapter(mSymbolsAdapter);
        mRecyclerViewSymbols.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

        long user_id = mDBHelper.getLastUser();
        Log.d("User", String.valueOf(user_id));

        String soat = mSP.getString("soat", "");
        if (soat != null && soat != ""){
            mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_soat), soat, "SOAT", user_id);
        }

        String rtm = mSP.getString("rtm", "");
        if (rtm != null && soat != ""){
            mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_rtm), rtm, "RTM", user_id);
        }

        String src = mSP.getString("src", "");
        if (src != null && soat != ""){
            mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_src), src, "SRC", user_id);
        }

        String str = mSP.getString("str", "");
        if (str != null && soat != ""){
            mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_str), str, "STR", user_id);
        }

        String to = mSP.getString("to", "");
        if (to != null && soat != ""){
            mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_tao), to, "TO", user_id);
        }

        String ext = mSP.getString("ext", "");
        if (ext != null && soat != ""){
            mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_ext), ext, "EXT", user_id);
        }

        mDetailsAdapter = new HomeDetailsAdapter(mContext, mDBHelper.getAllDates());

        mRecyclerViewDetails.setAdapter(mDetailsAdapter);
        mRecyclerViewDetails.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));


    }
}