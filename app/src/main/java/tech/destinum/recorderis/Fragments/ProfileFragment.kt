package tech.destinum.recorderis.Fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

import tech.destinum.recorderis.R
import tech.destinum.recorderis.utils.DateWatcher

class ProfileFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdd: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        mAdd = view.findViewById(R.id.profile_button)
        mRecyclerView = view.findViewById(R.id.recycler_view_profile)

        mRecyclerView.layoutManager = LinearLayoutManager(ProfileFragment@this.requireContext(), LinearLayoutManager.VERTICAL, false)

        mAdd.setOnClickListener { v ->
            val inflater = v.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.dialog_creation, null, true)
            val title = view.findViewById(R.id.dialog_tv_title) as TextView
            val msg = view.findViewById(R.id.dialog_tv_msg) as TextView
            val et = view.findViewById(R.id.dialog_edt_date) as EditText
            val spinner = view.findViewById(R.id.spinner) as Spinner

//            spinner.onItemSelectedListener = this@ProfileFragment.requireContext()

            title.setText(R.string.dialog_new_doc)
            msg.setText(R.string.dialog_choose)

            val mDateWatcher = DateWatcher(et)
            et.addTextChangedListener(mDateWatcher)
        }
        return view
    }


}
