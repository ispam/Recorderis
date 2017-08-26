package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.HomeDetailsAdapter;
import tech.destinum.recorderis.pojo.Document;
import tech.destinum.recorderis.utils.DateWatcher;


public class Home extends BaseActivity {

    private DBHelper mDBHelper;
    private Context mContext;
    private RecyclerView mRecyclerViewDetails;
    private HomeDetailsAdapter mDetailsAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private TextView mTextView;
    public ConstraintLayout mLayout;
    public ImageView mAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();

        mDBHelper = new DBHelper(this);
        mRecyclerViewDetails = (RecyclerView) findViewById(R.id.recycler_view_details);
        mLayout = (ConstraintLayout) findViewById(R.id.cl_empty_redirection);
        mAdd = (ImageView) findViewById(R.id.home_button);

        mDetailsAdapter = new HomeDetailsAdapter(mContext, mDBHelper.getAllDates(), new HomeDetailsAdapter.clickCallback() {
            @Override
            public void onItemClick(int position) {
                Log.d("Details", String.valueOf(position));
            }
        });

        mRecyclerViewDetails.setAdapter(mDetailsAdapter);
        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewDetails.setLayoutManager(mLinearLayoutManager);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerViewDetails);

        if (mDBHelper.getAllDates().size() > 1) {
            View parent = findViewById(R.id.rl_position);

            Snackbar mSnackbar = Snackbar.make(parent, R.string.snack_hint, Snackbar.LENGTH_LONG);
        // get snackbar view
            View mView = mSnackbar.getView();
        // get textview inside snackbar view
            mTextView = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            else
                mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            mSnackbar.show();
        }

        if (mDBHelper.getAllDates().size() == 0){
            mLayout.setVisibility(View.VISIBLE);
            mRecyclerViewDetails.setVisibility(View.GONE);

            mAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    Intent intent = new Intent(Home.this, Selection.class);
                    startActivity(intent);

                }
            });

        } else {
            mLayout.setVisibility(View.GONE);
            mRecyclerViewDetails.setVisibility(View.VISIBLE);
        }
    }
}