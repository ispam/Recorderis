package tech.destinum.recorderis.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import java.util.ArrayList

import tech.destinum.recorderis.R
import tech.destinum.recorderis.adapters.SelectionAdapter
import tech.destinum.recorderis.Data.Entities.Category

class Selection : AppCompatActivity() {

    private val mRecyclerView by lazy { findViewById(R.id.recycler_view_selection) as RecyclerView }
    private val mContext: Context? = null
    private var mCategoryArrayList: ArrayList<Category>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        //        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED
        //                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
        //
        //            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_CALENDAR}, MY_CAL_WRITE_REQUEST);
        //        }else if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED
        //                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
        //
        //        }

        val covers = intArrayOf(R.drawable.moto640blanco, R.drawable.parti640blanco, R.drawable.publi640blanco)

        mCategoryArrayList = ArrayList()
        mCategoryArrayList!!.add(Category(1, getString(R.string.pri_serv), getString(R.string.exp_pri_serv), covers[1]))
        mCategoryArrayList!!.add(Category(2, getString(R.string.pub_serv), getString(R.string.exp_pub_serv), covers[2]))
        mCategoryArrayList!!.add(Category(3, getString(R.string.motos), getString(R.string.exp_motos), covers[0]))

        mRecyclerView.itemAnimator = DefaultItemAnimator()
        mRecyclerView.setHasFixedSize(true)

        mRecyclerView.adapter = SelectionAdapter(mCategoryArrayList)
        mRecyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)

        //        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        //        mRecyclerView.addItemDecoration(itemDecoration);

    }

}
