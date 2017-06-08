package tech.destinum.recorderis.activities;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.ListAdapter;
import tech.destinum.recorderis.pojo.Category;

public class Selection extends BaseActivity {

    private ListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private ArrayList<Category> mCategoryArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        super.onCreateDrawer();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_selection);

        int[] covers = new int[]{
                R.drawable.moto640blanco,
                R.drawable.parti640blanco,
                R.drawable.publi640blanco};

        mCategoryArrayList = new ArrayList<>();
        mCategoryArrayList.add(new Category("Servicio Particular", "Si usted utiliza un vehículo de placas amarillas y de uso privado", covers[1]));
        mCategoryArrayList.add(new Category("Servicio Público", "Si usted utiliza un vehículo de placas blancas y de uso público", covers[2]));
        mCategoryArrayList.add(new Category("Motos", "Si usted utiliza una moto de 2 o 4 Tiempos", covers[0]));
        mAdapter = new ListAdapter(mContext, mCategoryArrayList);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
//        mRecyclerView.addItemDecoration(itemDecoration);

    }

}
