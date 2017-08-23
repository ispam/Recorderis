package tech.destinum.recorderis.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.SelectionAdapter;
import tech.destinum.recorderis.pojo.Category;

public class Selection extends BaseActivity {

    private SelectionAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private ArrayList<Category> mCategoryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        super.onCreateDrawer();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_selection);

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_CALENDAR}, MY_CAL_WRITE_REQUEST);
//        }else if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
//
//        }

            int[] covers = new int[]{
                R.drawable.moto640blanco,
                R.drawable.parti640blanco,
                R.drawable.publi640blanco};

        mCategoryArrayList = new ArrayList<>();
        mCategoryArrayList.add(new Category(getString(R.string.pri_serv), getString(R.string.exp_pri_serv), covers[1]));
        mCategoryArrayList.add(new Category(getString(R.string.pub_serv), getString(R.string.exp_pub_serv), covers[2]));
        mCategoryArrayList.add(new Category(getString(R.string.motos), getString(R.string.exp_motos), covers[0]));
        mAdapter = new SelectionAdapter(mContext, mCategoryArrayList);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
//        mRecyclerView.addItemDecoration(itemDecoration);

    }

}
