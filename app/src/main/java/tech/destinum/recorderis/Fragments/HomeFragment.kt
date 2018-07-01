package tech.destinum.recorderis.Fragments

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.destinum.recorderis.App
import tech.destinum.recorderis.Data.ViewModels.DateViewModel

import tech.destinum.recorderis.R
import tech.destinum.recorderis.activities.Selection
import tech.destinum.recorderis.adapters.HomeDetailsAdapter
import javax.inject.Inject


class HomeFragment : Fragment() {

    private lateinit var mRecyclerViewDetails: RecyclerView
    private lateinit var mAdd: ImageView
    private lateinit var mLayout: ConstraintLayout
    private val mDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mDateVM: DateViewModel

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
    }

    override fun onDestroyOptionsMenu() {
        if (mDisposable != null && !mDisposable.isDisposed) {
            mDisposable.clear()
        }
        super.onDestroyOptionsMenu()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        App.graph.inject(this)

        mRecyclerViewDetails = view.findViewById(R.id.recycler_view_details)
        mAdd = view.findViewById(R.id.home_button)
        mLayout = view.findViewById(R.id.cl_empty_redirection)

        val mMSG = view.findViewById(R.id.redirection_msg) as TextView

        mMSG.setText(R.string.redirection_msg)
//        for kotlin extensions
//        private val streamList: RecyclerView
//        get() = stream_picker_list <-- ID of RV

        mRecyclerViewDetails.layoutManager = LinearLayoutManager(HomeFragment@ this.requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mRecyclerViewDetails)

        mDisposable.add(mDateVM.getAllDates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list ->
                            run {
                                mRecyclerViewDetails.adapter = HomeDetailsAdapter(list)

                                when {
                                    list.isNotEmpty() -> {
                                        val parent = view.findViewById<View>(R.id.cl_fragment_home)

                                        val mSnackbar = Snackbar.make(parent, R.string.snack_hint, Snackbar.LENGTH_LONG)
                                        // get snackbar view
                                        val mView = mSnackbar.view
                                        // get textview inside snackbar view
//                                        val params = mView.layoutParams as ConstraintLayout.LayoutParams
//                                        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, 56)
//                                        mSnackbar.apply { view.layoutParams = params }
                                        val mSnackTV = mView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
                                        mSnackTV.textAlignment = View.TEXT_ALIGNMENT_CENTER
                                        mSnackbar.show()
//                                        Snackbar.make(parent, R.string.snack_hint, Snackbar.LENGTH_LONG).apply {view.layoutParams = (view.layoutParams as CoordinatorLayout.LayoutParams).apply {setMargins(leftMargin, topMargin, rightMargin, 56)}}.show()
                                    }
                                    list.isEmpty() -> {
                                        mLayout.visibility = View.VISIBLE
                                        mRecyclerViewDetails.visibility = View.GONE

                                        mAdd.setOnClickListener { v ->
                                            val intent = Intent(v.context, Selection::class.java)
                                            startActivity(intent)
                                        }
                                    }
                                    else -> {
                                        mLayout.visibility = View.GONE
                                        mRecyclerViewDetails.visibility = View.VISIBLE
                                    }
                                }
                            }
                        },
                        { throwable -> Log.e(TAG, "${throwable.message}") },
                        { Log.d(TAG, "completed") }
                ))

        return view
    }

}
