package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.HomeDetailsAdapter;
import tech.destinum.recorderis.adapters.HomeSymbolsAdapter;
import tech.destinum.recorderis.utils.LinearLayoutPagerManager;

import static tech.destinum.recorderis.adapters.FormAdapter.FORM_PREFERENCES;

public class Home extends BaseActivity {

    private DBHelper mDBHelper;
    private Context mContext;
    private RecyclerView mRecyclerViewDetails, mRecyclerViewSymbols;
    private HomeDetailsAdapter mDetailsAdapter;
    private HomeSymbolsAdapter mSymbolsAdapter;
    private RelativeLayout mLayout;
    private ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();

        mDBHelper = new DBHelper(getApplicationContext());
        mRecyclerViewDetails = (RecyclerView) findViewById(R.id.recycler_view_details);
        mRecyclerViewSymbols = (RecyclerView) findViewById(R.id.recycler_view_symbols);

        mLayout = (RelativeLayout) findViewById(R.id.rl_position);

        mSymbolsAdapter = new HomeSymbolsAdapter(mContext, mDBHelper.getAllDates());

        mRecyclerViewSymbols.setAdapter(mSymbolsAdapter);
        mRecyclerViewSymbols.setLayoutManager(new LinearLayoutPagerManager(mContext, LinearLayoutManager.HORIZONTAL, false, 3));

        mDetailsAdapter = new HomeDetailsAdapter(mContext, mDBHelper.getAllDates());

        mRecyclerViewDetails.setAdapter(mDetailsAdapter);
        mRecyclerViewDetails.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));


//        final RecyclerView.OnScrollListener[] scrollListeners = new RecyclerView.OnScrollListener[2];
//        scrollListeners[0] = new RecyclerView.OnScrollListener( )
//        {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
//            {
//                super.onScrolled(recyclerView, dx, dy);
//                mRecyclerViewDetails.removeOnScrollListener(scrollListeners[1]);
//                mRecyclerViewDetails.scrollBy(dx, dy);
//                mRecyclerViewDetails.addOnScrollListener(scrollListeners[1]);
//
//            }
//        };
//        scrollListeners[1] = new RecyclerView.OnScrollListener( )
//        {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
//            {
//                super.onScrolled(recyclerView, dx, dy);
//                mRecyclerViewSymbols.removeOnScrollListener(scrollListeners[0]);
//                mRecyclerViewSymbols.scrollBy(dx, dy);
//                mRecyclerViewSymbols.addOnScrollListener(scrollListeners[0]);
//            }
//        };
//        mRecyclerViewSymbols.addOnScrollListener(scrollListeners[0]);
//        mRecyclerViewDetails.addOnScrollListener(scrollListeners[1]);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerViewDetails);


    }
}