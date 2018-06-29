package tech.destinum.recorderis.Fragments

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import tech.destinum.recorderis.R
import tech.destinum.recorderis.activities.Selection
import tech.destinum.recorderis.adapters.HomeDetailsAdapter


class HomeFragment : Fragment() {

    private val mContext: Context? = null
    private lateinit var mRecyclerViewDetails : RecyclerView
    private lateinit var mAdd : ImageView
    private lateinit var mLayout: ConstraintLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)


        mRecyclerViewDetails = view.findViewById(R.id.recycler_view_details)
        mAdd = view.findViewById(R.id.home_button)
        mLayout = view.findViewById(R.id.cl_empty_redirection)

        val mMSG = view.findViewById(R.id.redirection_msg) as TextView

        mMSG.setText(R.string.redirection_msg)
//        for kotlin extensions
//        private val streamList: RecyclerView
//        get() = stream_picker_list <-- ID of RV

        mRecyclerViewDetails.layoutManager = LinearLayoutManager(HomeFragment@this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        mRecyclerViewDetails.adapter = HomeDetailsAdapter(mDBHelper!!.allDates)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mRecyclerViewDetails)

//        if (mDBHelper!!.allDates.size > 1) {
//            val parent = view.findViewById<View>(R.id.rl_position)
//
//            val mSnackbar = Snackbar.make(parent, R.string.snack_hint, Snackbar.LENGTH_LONG)
//            // get snackbar view
//            val mView = mSnackbar.view
//            // get textview inside snackbar view
//            val mSnackTV = mView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
//                mSnackTV.textAlignment = View.TEXT_ALIGNMENT_CENTER
//            else
//                mSnackTV.gravity = Gravity.CENTER_HORIZONTAL
//            mSnackbar.show()
//        }
//
//        if (mDBHelper!!.allDates.size == 0) {
//            mLayout.visibility = View.VISIBLE
//            mRecyclerViewDetails.visibility = View.GONE
//
//            mAdd.setOnClickListener { v ->
//                val intent = Intent(v.context, Selection::class.java)
//                startActivity(intent)
//            }
//
//        } else {
//            mLayout.visibility = View.GONE
//            mRecyclerViewDetails.visibility = View.VISIBLE
//        }


        return view
    }


}
