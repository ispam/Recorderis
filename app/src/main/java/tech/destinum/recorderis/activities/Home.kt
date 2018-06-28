package tech.destinum.recorderis.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import tech.destinum.recorderis.DB.DBHelper
import tech.destinum.recorderis.R
import tech.destinum.recorderis.adapters.HomeDetailsAdapter


class Home : AppCompatActivity() {

    private var mDBHelper: DBHelper? = null
    private val mContext: Context? = null
    private val mRecyclerViewDetails by lazy { findViewById<RecyclerView>(R.id.recycler_view_details) }
    private val mAdd by lazy { findViewById<ImageView>(R.id.home_button) }
    private val mBotNav by lazy { findViewById(R.id.navigation) as BottomNavigationView }
    private lateinit var mLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mDBHelper = DBHelper(this)

        mLayout = findViewById(R.id.cl_empty_redirection)
//        val mAdd = findViewById(R.id.home_button) as ImageView
        val mMSG = findViewById(R.id.redirection_msg) as TextView

        mMSG.setText(R.string.redirection_msg)
//        for kotlin extensions
//        private val streamList: RecyclerView
//        get() = stream_picker_list <-- ID of RV

        mRecyclerViewDetails.adapter = HomeDetailsAdapter(mDBHelper!!.allDates)
        mRecyclerViewDetails.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mRecyclerViewDetails)

        if (mDBHelper!!.allDates.size > 1) {
            val parent = findViewById<View>(R.id.rl_position)

            val mSnackbar = Snackbar.make(parent, R.string.snack_hint, Snackbar.LENGTH_LONG)
            // get snackbar view
            val mView = mSnackbar.view
            // get textview inside snackbar view
            val mSnackTV = mView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                mSnackTV.textAlignment = View.TEXT_ALIGNMENT_CENTER
            else
                mSnackTV.gravity = Gravity.CENTER_HORIZONTAL
            mSnackbar.show()
        }

        if (mDBHelper!!.allDates.size == 0) {
            mLayout.visibility = View.VISIBLE
            mRecyclerViewDetails.visibility = View.GONE

            mAdd.setOnClickListener { v ->
                val intent = Intent(this@Home, Selection::class.java)
                startActivity(intent)
            }

        } else {
            mLayout.visibility = View.GONE
            mRecyclerViewDetails.visibility = View.VISIBLE
        }


        mBotNav.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId){
                R.id.action_home -> Toast.makeText(this, "Action Home", Toast.LENGTH_SHORT).show()
                R.id.action_profile -> Toast.makeText(this, "Action Profile", Toast.LENGTH_SHORT).show()
                R.id.action_info -> Toast.makeText(this, "Action Info", Toast.LENGTH_SHORT).show()
        };true}

    }
}