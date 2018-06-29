package tech.destinum.recorderis.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.destinum.recorderis.App
import tech.destinum.recorderis.Data.Entities.Date

import java.util.ArrayList

import tech.destinum.recorderis.R
import tech.destinum.recorderis.adapters.FormAdapter
import tech.destinum.recorderis.Data.Entities.Document
import tech.destinum.recorderis.Data.ViewModels.DateViewModel
import tech.destinum.recorderis.adapters.FormAdapter.Companion.FORM_PREFERENCES

import javax.inject.Inject
import javax.inject.Named

class Form : AppCompatActivity() {

    private val mContext: Context? = null
    private var mRecyclerView: RecyclerView? = null
    private var mArrayList: ArrayList<Document>? = null
    private val mDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var dateVM: DateViewModel

    companion object {
        val TAG = Form::class.simpleName
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        App.graph.inject(this)

        initDialog()
        setUpRecyclerView()

        //        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //        setSupportActionBar(toolbar);

        //        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        //        mRecyclerView.addItemDecoration(itemDecoration);

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onDestroy() {

        if (mDisposable != null && !mDisposable.isDisposed){
            mDisposable.clear()
        }

        super.onDestroy()
    }

    private fun setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_view_form)

        mArrayList = ArrayList()
        mArrayList!!.add(Document(1, getString(R.string.doc_soat), getString(R.string.symbol_soat)))
        mArrayList!!.add(Document(2, getString(R.string.doc_rtm), getString(R.string.symbol_rtm)))
        mArrayList!!.add(Document(3, getString(R.string.doc_str), getString(R.string.symbol_str)))
        mArrayList!!.add(Document(4, getString(R.string.doc_tao), getString(R.string.symbol_tao)))
        mArrayList!!.add(Document(5, getString(R.string.doc_ext), getString(R.string.symbol_ext)))


        mRecyclerView!!.itemAnimator = DefaultItemAnimator()
        mRecyclerView!!.setHasFixedSize(true)

        mRecyclerView!!.adapter = FormAdapter(mArrayList)
        mRecyclerView!!.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun initDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setContentView(R.layout.hint)
        dialog.setCanceledOnTouchOutside(true)
        //for dismissing anywhere you touch
        val masterView = dialog.findViewById<View>(R.id.hint)
        masterView.setOnClickListener { view -> dialog.dismiss() }
        dialog.show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.form_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.confirmation -> {
                val mSP = getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE)

                val soat = mSP.getString("soat", "")
                if (soat != null && soat != "") {

                    mDisposable.add(dateVM.createDate(Date(this.getString(R.string.doc_soat), this.getString(R.string.symbol_soat),soat))
                            .subscribeOn(Schedulers.io())
                            .subscribe())

                }

                val rtm = mSP.getString("rtm", "")
                if (rtm != null && rtm != "") {
//                    mDBHelper.createNewDate(applicationContext.getString(R.string.doc_rtm), rtm, applicationContext.getString(R.string.symbol_rtm), 1)
                    mDisposable.add(dateVM.createDate(Date(this.getString(R.string.doc_rtm), this.getString(R.string.symbol_rtm),rtm))
                            .subscribeOn(Schedulers.io())
                            .subscribe())
                }

                val str = mSP.getString("str", "")
                if (str != null && str != "") {
                    mDisposable.add(dateVM.createDate(Date(this.getString(R.string.doc_str), this.getString(R.string.symbol_str),str))
                            .subscribeOn(Schedulers.io())
                            .subscribe())
                }

                val to = mSP.getString("to", "")
                if (to != null && to != "") {
//                    wakefulReceiver.setAlarm(applicationContext, to, 4)
                    mDisposable.add(dateVM.createDate(Date(this.getString(R.string.doc_tao), this.getString(R.string.symbol_tao),to))
                            .subscribeOn(Schedulers.io())
                            .subscribe())
                }

                val ext = mSP.getString("ext", "")
                if (ext != null && ext != "") {
//                    wakefulReceiver.setAlarm(applicationContext, ext, 5)
                    mDisposable.add(dateVM.createDate(Date(this.getString(R.string.doc_ext), this.getString(R.string.symbol_ext),ext))
                            .subscribeOn(Schedulers.io())
                            .subscribe())
                }

                mSP.edit().clear().commit()

                val intent = Intent(applicationContext, Home::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

