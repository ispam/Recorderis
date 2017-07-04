package tech.destinum.recorderis.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.HomeDetailsAdapter;
import tech.destinum.recorderis.adapters.HomeSymbolsAdapter;
import tech.destinum.recorderis.utils.LinearLayoutPagerManager;
import tech.destinum.recorderis.utils.OnSwipeTouchListener;


public class Home extends BaseActivity {

    private DBHelper mDBHelper;
    private Context mContext;
    private RecyclerView mRecyclerViewDetails, mRecyclerViewSymbols;
    private HomeDetailsAdapter mDetailsAdapter;
    private HomeSymbolsAdapter mSymbolsAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private LinearLayoutPagerManager mLinearLayoutPagerManager;
    private OnSwipeTouchListener onSwipeTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();

        mDBHelper = new DBHelper(getApplicationContext());
        mRecyclerViewDetails = (RecyclerView) findViewById(R.id.recycler_view_details);

//        mRecyclerViewSymbols = (RecyclerView) findViewById(R.id.recycler_view_symbols);

//        mSymbolsAdapter = new HomeSymbolsAdapter(mContext, mDBHelper.getAllDates(), new HomeSymbolsAdapter.clickCallback() {
//            @Override
//            public void onItemClick(int position) {
//                Log.d("Symbols", String.valueOf(position));
//
//            }
//        });

//        mRecyclerViewSymbols.setAdapter(mSymbolsAdapter);
//        mLinearLayoutPagerManager = new LinearLayoutPagerManager(mContext, LinearLayoutManager.HORIZONTAL, false, 3);
//        mRecyclerViewSymbols.setLayoutManager(mLinearLayoutPagerManager);
//        mRecyclerViewSymbols.getLayoutManager().scrollToPosition(Integer.MAX_VALUE / 2);


        mDetailsAdapter = new HomeDetailsAdapter(mContext, mDBHelper.getAllDates(), new HomeDetailsAdapter.clickCallback() {
            @Override
            public void onItemClick(int position) {
                Log.d("Details", String.valueOf(position));
            }
        });

        mRecyclerViewDetails.setAdapter(mDetailsAdapter);
        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewDetails.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewDetails.getLayoutManager().scrollToPosition((Integer.MAX_VALUE / 2)+1);


        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerViewDetails);

//        mRecyclerViewSymbols.setOnFlingListener(snapHelper2);
//        mRecyclerViewDetails.setOnFlingListener(snapHelper);
//
//        mRecyclerViewSymbols.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                mRecyclerViewDetails.smoothScrollToPosition(position);
//
//                int itemToScroll = mRecyclerViewSymbols.getChildPosition(view);
//                int centerOfScreen = mRecyclerViewSymbols.getWidth() / 2 - view.getWidth() / 2;
//                Log.d("width", String.valueOf(view.getWidth()/2));
//                mLinearLayoutPagerManager.scrollToPositionWithOffset(itemToScroll, centerOfScreen);
//
//            }
//        }));


//        onSwipeTouchListener = new OnSwipeTouchListener(Home.this) {
//
//            public void onSwipeRight() {
//                Toast.makeText(Home.this, "right", Toast.LENGTH_SHORT).show();
//
//            }
//
//            public void onSwipeLeft() {
//                Toast.makeText(Home.this, "left", Toast.LENGTH_SHORT).show();
//
//            }
//        };
//
//
//
//        mRecyclerViewSymbols.setOnTouchListener(onSwipeTouchListener);
//        mRecyclerViewDetails.setOnTouchListener(onSwipeTouchListener);

    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev){
//        onSwipeTouchListener.getGestureDetector().onTouchEvent(ev);
//        return super.dispatchTouchEvent(ev);
//    }

}