package tech.destinum.recorderis.activities

import android.os.Bundle
import android.webkit.WebView
import android.widget.RelativeLayout

import tech.destinum.recorderis.R

class Terms : BaseActivity() {

    private val mRelativeLayout by lazy { findViewById(R.id.terms) as RelativeLayout }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)
        super.onCreateDrawer()

        val view = WebView(this)

        view.isVerticalScrollBarEnabled = true

        mRelativeLayout.addView(view)

        view.loadData(getString(R.string.terms_and_conditions), "text/html; charset=utf-8", "utf-8")

    }

}
