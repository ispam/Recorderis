package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.Toast;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.HomeDetailsAdapter;
import tech.destinum.recorderis.adapters.HomeSymbolsAdapter;
import tech.destinum.recorderis.utils.LinearLayoutPagerManager;
import tech.destinum.recorderis.utils.OnSwipeTouchListener;
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
    private OnSwipeTouchListener onSwipeTouchListener;
    private ConstraintLayout mConstraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();

        mDBHelper = new DBHelper(getApplicationContext());
        mRecyclerViewDetails = (RecyclerView) findViewById(R.id.recycler_view_details);
        mRecyclerViewSymbols = (RecyclerView) findViewById(R.id.recycler_view_symbols);
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.cl_symbols);

        mSymbolsAdapter = new HomeSymbolsAdapter(mContext, mDBHelper.getAllDates(), new HomeSymbolsAdapter.clickCallback() {
            @Override
            public void onItemClick(int position) {
                Log.d("Symbols", String.valueOf(position));

            }
        });

        mRecyclerViewSymbols.setAdapter(mSymbolsAdapter);
        mLinearLayoutPagerManager = new LinearLayoutPagerManager(mContext, LinearLayoutManager.HORIZONTAL, false, 3);
        mRecyclerViewSymbols.setLayoutManager(mLinearLayoutPagerManager);
        mRecyclerViewSymbols.getLayoutManager().scrollToPosition(Integer.MAX_VALUE / 2);


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
        PagerSnapHelper snapHelper2 = new PagerSnapHelper();
        snapHelper2.attachToRecyclerView(mRecyclerViewSymbols);

//        mRecyclerViewSymbols.setOnFlingListener(snapHelper2);
//        mRecyclerViewDetails.setOnFlingListener(snapHelper);

        mRecyclerViewSymbols.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mRecyclerViewDetails.smoothScrollToPosition(position);

                int itemToScroll = mRecyclerViewSymbols.getChildPosition(view);
                int centerOfScreen = mRecyclerViewSymbols.getWidth() / 2 - view.getWidth() / 2;
                Log.d("width", String.valueOf(view.getWidth()/2));
                mLinearLayoutPagerManager.scrollToPositionWithOffset(itemToScroll, centerOfScreen);

            }
        }));


        onSwipeTouchListener = new OnSwipeTouchListener(Home.this) {

            public void onSwipeRight() {
                Toast.makeText(Home.this, "right", Toast.LENGTH_SHORT).show();

                mRecyclerViewDetails.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {

                        int childView = mRecyclerViewSymbols.getChildAdapterPosition(mConstraintLayout);
                        int itemToScroll = mRecyclerViewSymbols.getChildPosition(mConstraintLayout);
                        Log.d("view name", String.valueOf(mConstraintLayout));
                        int centerOfScreen = mRecyclerViewSymbols.getWidth() / 2 - 132;
                        mLinearLayoutPagerManager.scrollToPositionWithOffset(childView, centerOfScreen);
                        Log.d("right", String.valueOf(itemToScroll));
                    }
                });
            }

            public void onSwipeLeft() {
                Toast.makeText(Home.this, "left", Toast.LENGTH_SHORT).show();

                mRecyclerViewDetails.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {

                        int itemToScroll = mRecyclerViewSymbols.getChildPosition(mConstraintLayout);
                        Log.d("view name", String.valueOf(mConstraintLayout));
                        int centerOfScreen = mRecyclerViewSymbols.getWidth() / 2 - 132;
                        mLinearLayoutPagerManager.scrollToPositionWithOffset(itemToScroll, centerOfScreen);
                        Log.d("left", String.valueOf(itemToScroll));
                    }
                });
            }
        };


        mRecyclerViewSymbols.setOnTouchListener(onSwipeTouchListener);
        mRecyclerViewDetails.setOnTouchListener(onSwipeTouchListener);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        onSwipeTouchListener.getGestureDetector().onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

}