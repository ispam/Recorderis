package tech.destinum.recorderis.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.destinum.recorderis.App
import tech.destinum.recorderis.BuildConfig
import tech.destinum.recorderis.Data.Entities.Document
import tech.destinum.recorderis.Data.ViewModels.DocumentViewModel
import tech.destinum.recorderis.Fragments.AboutFragment
import tech.destinum.recorderis.Fragments.HomeFragment
import tech.destinum.recorderis.Fragments.ProfileFragment

import tech.destinum.recorderis.R
import javax.inject.Inject

class Home : AppCompatActivity() {

    private val mBotNav by lazy { findViewById(R.id.navigation) as BottomNavigationView }
    private var mSP: SharedPreferences? = null
    private val mDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mDocumentVM: DocumentViewModel

    override fun onDestroy() {

        if (mDisposable != null && !mDisposable.isDisposed){
            mDisposable.clear()
        }
        super.onDestroy()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        App.graph.inject(this)

        checkFirstRun()

        this.supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_layout, HomeFragment())
                .commit()


        mBotNav.setOnNavigationItemSelectedListener{ item ->
            var fragment: Fragment? = null
            when (item.itemId){
                R.id.action_home -> fragment = HomeFragment()
                R.id.action_profile -> fragment = ProfileFragment()
                R.id.action_info -> fragment = AboutFragment()
            }
            this.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit()
            true
        }

    }

    private fun checkFirstRun() {

        val PREF_NAME = "HomeActivityPrefs"
        val PREF_VERSION_CODE_KEY = "1"
        val DOESNT_EXIST = -1

        // Get current version code
        val currentVersionCode = BuildConfig.VERSION_CODE

        // Get saved version code
        mSP = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val savedVersionCode = mSP!!.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST)

        // Check for first run or upgrade
        when {
            currentVersionCode == savedVersionCode -> {
                // This is just a normal run
                val documents = resources.getStringArray(R.array.documents)
                val symbols = resources.getStringArray(R.array.symbols)

                var document = ""
                for (i in 0 until documents.size){
                    document = documents[i]

                }

                var symbol = ""
                for (j in 0 until symbols.size){
                    symbol = symbols[j]
                }
                println("$document, $symbol")
            }
            savedVersionCode == DOESNT_EXIST -> {
                // TODO This is a new install (or the user cleared the shared preferences)



//
//                mDisposable.add(mDocumentVM.createDocument(Document(getString(R.string.doc_soat), getString(R.string.symbol_soat)))
//                        .subscribeOn(Schedulers.io())
//                        .subscribe())
//
//                documentFun(getString(R.string.doc_soat),getString(R.string.symbol_soat))

//                mDBHelper.createNewDocument(getString(R.string.doc_rtm), getString(R.string.symbol_rtm));
//                mDBHelper.createNewDocument(getString(R.string.doc_str), getString(R.string.symbol_str));
//                mDBHelper.createNewDocument(getString(R.string.doc_tao), getString(R.string.symbol_tao));
//                mDBHelper.createNewDocument(getString(R.string.doc_ext), getString(R.string.symbol_ext));


            }
            currentVersionCode > savedVersionCode -> {

                // TODO This is an upgrade
            }
        }

        mSP!!.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply()

    }

    private fun documentFun(name: String, symbol: String) {

        mDisposable.add(mDocumentVM.createDocument(Document(name, symbol)).toObservable<Any>()
                .subscribeOn(Schedulers.io())
                .subscribe())
    }
}