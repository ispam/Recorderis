package tech.destinum.recorderis.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import tech.destinum.recorderis.App

import java.util.ArrayList

import tech.destinum.recorderis.DB.DBHelper
import tech.destinum.recorderis.R
import tech.destinum.recorderis.adapters.FormAdapter
import tech.destinum.recorderis.Data.Entities.Document

import javax.inject.Inject

class Form : AppCompatActivity() {

    private lateinit var mDBHelper: DBHelper
    private val mContext: Context? = null
    private var mRecyclerView: RecyclerView? = null
    private var mArrayList: ArrayList<Document>? = null

//    @Inject
//    lateinit var dateDAO: DateDAO

    companion object {
        val TAG = Form::class.simpleName
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        App.graph.inject(this)

        mDBHelper = DBHelper(this)

        initDialog()
        setUpRecyclerView()

        //        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //        setSupportActionBar(toolbar);

        //        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        //        mRecyclerView.addItemDecoration(itemDecoration);

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

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
//                val wakefulReceiver = WakefulReceiver()
//                val mSP = getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE)
//                val user_id = mDBHelper.lastUser
//
//                val soat = mSP.getString("soat", "")
//                if (soat != null && soat !== "") {
//                    mDBHelper.createNewDate(applicationContext.getString(R.string.doc_soat), soat, applicationContext.getString(R.string.symbol_soat), user_id)
//                    wakefulReceiver.setAlarm(applicationContext, soat, 1)
//                }
//
//                val rtm = mSP.getString("rtm", "")
//                if (rtm != null && rtm !== "") {
//                    mDBHelper.createNewDate(applicationContext.getString(R.string.doc_rtm), rtm, applicationContext.getString(R.string.symbol_rtm), user_id)
//                    wakefulReceiver.setAlarm(applicationContext, rtm, 2)
//                }
//
//                val str = mSP.getString("str", "")
//                if (str != null && str !== "") {
//                    mDBHelper.createNewDate(applicationContext.getString(R.string.doc_str), str, applicationContext.getString(R.string.symbol_str), user_id)
//                    wakefulReceiver.setAlarm(applicationContext, str, 3)
//                }
//
//                val to = mSP.getString("to", "")
//                if (to != null && to !== "") {
//                    mDBHelper.createNewDate(applicationContext.getString(R.string.doc_tao), to, applicationContext.getString(R.string.symbol_tao), user_id)
//                    wakefulReceiver.setAlarm(applicationContext, to, 4)
//                }
//
//                val ext = mSP.getString("ext", "")
//                if (ext != null && ext !== "") {
//                    mDBHelper.createNewDate(applicationContext.getString(R.string.doc_ext), ext, applicationContext.getString(R.string.symbol_ext), user_id)
//                    wakefulReceiver.setAlarm(applicationContext, ext, 5)
//                }
//
//                mSP.edit().clear().commit()
//
//                val intent = Intent(applicationContext, Home::class.java)
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                startActivity(intent)
//                finish()
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

