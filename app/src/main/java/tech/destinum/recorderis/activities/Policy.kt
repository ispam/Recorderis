package tech.destinum.recorderis.activities

import android.os.Bundle
import android.webkit.WebView
import android.widget.RelativeLayout

import tech.destinum.recorderis.R

class Policy : BaseActivity() {

    private var mRelativeLayout: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_policy)
        super.onCreateDrawer()

        mRelativeLayout = findViewById(R.id.policy)

        val view = WebView(this)

        view.isVerticalScrollBarEnabled = true

        mRelativeLayout!!.addView(view)

        view.loadData(getString(R.string.privacy_policy_text), "text/html; charset=utf-8", "utf-8")


    }
}
