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
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.HomeDetailsAdapter;
import tech.destinum.recorderis.adapters.HomeSymbolsAdapter;
import tech.destinum.recorderis.utils.LinearLayoutPagerManager;
import tech.destinum.recorderis.utils.RecyclerItemClickListener;

import static tech.destinum.recorderis.adapters.FormAdapter.FORM_PREFERENCES;

public class Home extends BaseActivity {

    private DBHelper mDBHelper;
    private Context mContext;
    private RecyclerView mRecyclerViewDetails, mRecyclerViewSymbols;
    private HomeDetailsAdapter mDetailsAdapter;
    private HomeSymbolsAdapter mSymbolsAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private LinearLayoutPagerManager mLinearLayoutPagerManager;

    private int mPostion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();

        mDBHelper = new DBHelper(getApplicationContext());
        mRecyclerViewDetails = (RecyclerView) findViewById(R.id.recycler_view_details);
        mRecyclerViewSymbols = (RecyclerView) findViewById(R.id.recycler_view_symbols);

        mSymbolsAdapter = new HomeSymbolsAdapter(mContext, mDBHelper.getAllDates(), new HomeSymbolsAdapter.clickCallback() {
            @Override
            public void onItemClick(int position) {
                Log.d("Home", String.valueOf(position));

                mPostion = position;
            }
        });

        mRecyclerViewSymbols.setAdapter(mSymbolsAdapter);
        mLinearLayoutPagerManager = new LinearLayoutPagerManager(mContext, LinearLayoutManager.HORIZONTAL, false, 3);
        mRecyclerViewSymbols.setLayoutManager(mLinearLayoutPagerManager);
//        mRecyclerViewSymbols.getLayoutManager().scrollToPosition(Integer.MAX_VALUE / 2);

        mDetailsAdapter = new HomeDetailsAdapter(mContext, mDBHelper.getAllDates());

        mRecyclerViewDetails.setAdapter(mDetailsAdapter);
        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewDetails.setLayoutManager(mLinearLayoutManager);
//        mRecyclerViewDetails.getLayoutManager().scrollToPosition(Integer.MAX_VALUE / 2);


        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerViewDetails);
        PagerSnapHelper snapHelper2 = new PagerSnapHelper();
        snapHelper2.attachToRecyclerView(mRecyclerViewSymbols);

        mRecyclerViewSymbols.setOnFlingListener(snapHelper);
        mRecyclerViewDetails.setOnFlingListener(snapHelper);

        mRecyclerViewSymbols.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mRecyclerViewDetails.smoothScrollToPosition(position);
            }
        }));

        mRecyclerViewDetails.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mRecyclerViewSymbols.smoothScrollToPosition(position);
            }
        }));
//        final RecyclerView.OnScrollListener[] scrollListeners = new RecyclerView.OnScrollListener[2];
//        scrollListeners[0] = new RecyclerView.OnScrollListener( )
//        {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
//            {
//                super.onScrolled(recyclerView, dx, dy);
//                mRecyclerViewDetails.removeOnScrollListener(scrollListeners[1]);
////                mRecyclerViewDetails.smoothScrollToPosition(mPostion);
//                        mRecyclerViewDetails.scrollBy(dx, dy);
//                mRecyclerViewDetails.addOnScrollListener(scrollListeners[1]);
//            }
//        };
//        scrollListeners[1] = new RecyclerView.OnScrollListener( )
//        {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
//            {
//                super.onScrolled(recyclerView, dx, dy);
//                mRecyclerViewSymbols.removeOnScrollListener(scrollListeners[0]);
////                mRecyclerViewSymbols.smoothScrollToPosition(mPostion);
//                mLinearLayoutManager.scrollToPositionWithOffset(1, 0);
////                        mRecyclerViewSymbols.smoothScrollBy(dx, dy);
//                mRecyclerViewSymbols.addOnScrollListener(scrollListeners[0]);
//            }
//        };
//        mRecyclerViewSymbols.addOnScrollListener(scrollListeners[0]);
//        mRecyclerViewDetails.addOnScrollListener(scrollListeners[1]);

    }
}