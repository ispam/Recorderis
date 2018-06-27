package tech.destinum.recorderis.adapters

import android.animation.ValueAnimator
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import java.util.ArrayList

import tech.destinum.recorderis.DB.DBHelper
import tech.destinum.recorderis.R
import tech.destinum.recorderis.Data.Entities.Document
import tech.destinum.recorderis.utils.DateWatcher

class FormAdapter(private val mDocuments: ArrayList<Document>?) : RecyclerView.Adapter<FormAdapter.ViewHolder>() {
    private var mDBHelper: DBHelper? = null

    var zero: Boolean = false
    var one: Boolean = false
    var two: Boolean = false
    var three: Boolean = false
    var four: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.format_form, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val document = mDocuments!![position]
        mDBHelper = DBHelper(holder.mCardView.context)

        holder.mTitle.text = document.name
        holder.mTitleExpanded.text = document.name

        holder.mEditText.setTag(R.id.date_et, position)

        holder.mCancelButton.setOnClickListener { v ->
            holder.isViewExpanded = false

            val valueAnimator = ValueAnimator.ofInt(holder.originalHeight + holder.originalHeight, holder.originalHeight)
            val a = AlphaAnimation(1.00f, 0.00f)

            a.duration = 200

            a.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    holder.mConstraintLayout.visibility = View.GONE
                    holder.mConstraintLayout.isEnabled = false
                    holder.mTitle.visibility = View.VISIBLE
                    holder.mTitle.isEnabled = true
                    holder.mCardView.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    holder.mEditText.setText("")
                    holder.mImageView.visibility = View.VISIBLE
                    holder.mImageViewCheck.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })

            holder.mConstraintLayout.startAnimation(a)

            valueAnimator.duration = 200
            valueAnimator.interpolator = AccelerateDecelerateInterpolator()
            valueAnimator.addUpdateListener { animation ->
                val value = animation.animatedValue as Int
                holder.mCardView.layoutParams.height = value
                holder.mCardView.requestLayout()
            }
            valueAnimator.start()

            val nothing = ""

            val mSP = v.context.getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE)
            val mEditor = mSP.edit()

            when (position) {
                0 -> {
                    zero = false
                    mEditor.putString("soat", nothing)
                    mEditor.commit()
                }
                1 -> {
                    one = false
                    mEditor.putString("rtm", nothing)
                    mEditor.commit()
                }
                2 -> {
                    two = false
                    mEditor.putString("str", nothing)
                    mEditor.commit()
                }
                3 -> {
                    three = false
                    mEditor.putString("to", nothing)
                    mEditor.commit()
                }
                4 -> {
                    four = false
                    mEditor.putString("ext", nothing)
                    mEditor.commit()
                }
            }
            val view = v.rootView
            if (view != null) {
                val inputManager = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        holder.mAddButton.setOnClickListener { v ->
            val data = holder.mEditText.text.toString()

            if (data.length <= 0 || data === "") {
                Toast.makeText(v.context, R.string.need_date, Toast.LENGTH_SHORT).show()
            } else {
                holder.isViewExpanded = false

                if (holder.originalHeight == 0) {
                    holder.originalHeight = holder.mCardView.height
                }

                val valueAnimator = ValueAnimator.ofInt(holder.originalHeight + holder.originalHeight, holder.originalHeight)
                val a = AlphaAnimation(1.00f, 0.00f) // Fade out

                a.duration = 200
                // Set a listener to the animation and configure onAnimationEnd
                a.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {

                    }

                    override fun onAnimationEnd(animation: Animation) {
                        holder.mConstraintLayout.visibility = View.GONE
                        holder.mConstraintLayout.isEnabled = false
                        holder.mCardView.setBackgroundColor(Color.parseColor("#E5FDEB"))
                        holder.mTitle.visibility = View.VISIBLE
                        holder.mTitle.isEnabled = true
                        holder.mImageView.visibility = View.GONE
                        holder.mImageViewCheck.visibility = View.VISIBLE

                    }

                    override fun onAnimationRepeat(animation: Animation) {

                    }
                })

                // Set the animation on the custom view
                holder.mConstraintLayout.startAnimation(a)

                valueAnimator.duration = 200
                valueAnimator.interpolator = AccelerateDecelerateInterpolator()
                valueAnimator.addUpdateListener { animation ->
                    val value = animation.animatedValue as Int
                    holder.mCardView.layoutParams.height = value
                    holder.mCardView.requestLayout()
                }
                valueAnimator.start()

                val mSP = v.context.getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE)
                val mEditor = mSP.edit()

                when (position) {
                    0 -> {

                        zero = true
                        mEditor.putString("soat", data)
                        mEditor.commit()
                    }
                    1 -> {

                        one = true
                        mEditor.putString("rtm", data)
                        mEditor.commit()
                    }

                    2 -> {

                        two = true
                        mEditor.putString("str", data)
                        mEditor.commit()
                    }
                    3 -> {

                        three = true
                        mEditor.putString("to", data)
                        mEditor.commit()
                    }
                    4 -> {

                        four = true
                        mEditor.putString("ext", data)
                        mEditor.commit()
                    }
                }
                val view = v.rootView
                if (view != null) {
                    val inputManager = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                }
            }
        }

        holder.mCardView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                when (position) {
                    0 ->

                        if (zero) {
                            animation1()
                        } else {
                            animation2()
                        }
                    1 ->

                        if (one) {
                            animation1()
                        } else {
                            animation2()
                        }
                    2 ->

                        if (two) {
                            animation1()
                        } else {
                            animation2()
                        }
                    3 ->

                        if (three) {
                            animation1()
                        } else {
                            animation2()
                        }
                    4 ->

                        if (four) {
                            animation1()
                        } else {
                            animation2()
                        }
                }
            }

            private fun animation1() {

                if (holder.originalHeight == 0) {
                    holder.originalHeight = holder.mCardView.height
                }

                val valueAnimator: ValueAnimator

                if (!holder.isViewExpanded) {
                    holder.mTitle.visibility = View.GONE
                    holder.mTitle.isEnabled = false
                    holder.mImageView.visibility = View.GONE
                    holder.mConstraintLayout.visibility = View.VISIBLE
                    holder.mConstraintLayout.isEnabled = true
                    holder.mImageViewCheck.visibility = View.GONE
                    holder.isViewExpanded = true
                    valueAnimator = ValueAnimator.ofInt(holder.originalHeight, holder.originalHeight + holder.originalHeight)

                } else {
                    holder.isViewExpanded = false

                    valueAnimator = ValueAnimator.ofInt(holder.originalHeight + holder.originalHeight, holder.originalHeight)
                    val a = AlphaAnimation(1.00f, 0.00f) // Fade out

                    a.duration = 200
                    // Set a listener to the animation and configure onAnimationEnd
                    a.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {

                        }

                        override fun onAnimationEnd(animation: Animation) {
                            holder.mConstraintLayout.visibility = View.GONE
                            holder.mConstraintLayout.isEnabled = false
                            holder.mCardView.setBackgroundColor(Color.parseColor("#E5FDEB"))
                            holder.mTitle.visibility = View.VISIBLE
                            holder.mTitle.isEnabled = true
                            holder.mImageView.visibility = View.GONE
                            holder.mImageViewCheck.visibility = View.VISIBLE

                        }

                        override fun onAnimationRepeat(animation: Animation) {

                        }
                    })

                    // Set the animation on the custom view
                    holder.mConstraintLayout.startAnimation(a)
                }

                valueAnimator.duration = 200
                valueAnimator.interpolator = AccelerateDecelerateInterpolator()
                valueAnimator.addUpdateListener { animation ->
                    val value = animation.animatedValue as Int
                    holder.mCardView.layoutParams.height = value
                    holder.mCardView.requestLayout()
                }
                valueAnimator.start()
            }

            private fun animation2() {
                if (holder.originalHeight == 0) {
                    holder.originalHeight = holder.mCardView.height
                }

                val valueAnimator: ValueAnimator

                if (!holder.isViewExpanded) {
                    holder.mTitle.visibility = View.GONE
                    holder.mTitle.isEnabled = false
                    holder.mImageView.visibility = View.GONE
                    holder.mConstraintLayout.visibility = View.VISIBLE
                    holder.mConstraintLayout.isEnabled = true
                    holder.mImageViewCheck.visibility = View.GONE
                    holder.isViewExpanded = true
                    valueAnimator = ValueAnimator.ofInt(holder.originalHeight, holder.originalHeight + holder.originalHeight) // These values in this method can be changed to expand however much you like
                } else {
                    holder.isViewExpanded = false
                    valueAnimator = ValueAnimator.ofInt(holder.originalHeight + holder.originalHeight, holder.originalHeight)

                    val a = AlphaAnimation(1.00f, 0.00f) // Fade out

                    a.duration = 200
                    // Set a listener to the animation and configure onAnimationEnd
                    a.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {

                        }

                        override fun onAnimationEnd(animation: Animation) {
                            holder.mTitle.visibility = View.VISIBLE
                            holder.mTitle.isEnabled = true
                            holder.mImageView.visibility = View.VISIBLE
                            holder.mConstraintLayout.visibility = View.GONE
                            holder.mConstraintLayout.isEnabled = false
                        }

                        override fun onAnimationRepeat(animation: Animation) {

                        }
                    })

                    // Set the animation on the custom view
                    holder.mConstraintLayout.startAnimation(a)
                }

                valueAnimator.duration = 200
                valueAnimator.interpolator = AccelerateDecelerateInterpolator()
                valueAnimator.addUpdateListener { animation ->
                    val value = animation.animatedValue as Int
                    holder.mCardView.layoutParams.height = value
                    holder.mCardView.requestLayout()
                }
                valueAnimator.start()
            }
        })
    }

    override fun getItemCount(): Int {
        return mDocuments?.size ?: 0
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var mTitle = view.findViewById(R.id.name_title_tv) as TextView
        var mTitleExpanded = view.findViewById(R.id.name_title_tv_expanded) as TextView
        var mAddButton = view.findViewById(R.id.add_button) as Button
        var mCancelButton = view.findViewById(R.id.button_cancel) as Button
        var mEditText = view.findViewById(R.id.date_et) as EditText
        var mImageView = view.findViewById(R.id.imageView_up) as ImageView
        var mImageViewCheck = view.findViewById(R.id.imageView_black_check) as ImageView
        var mCardView = view.findViewById(R.id.card_view_form) as CardView
        var mConstraintLayout = view.findViewById(R.id.expanded) as ConstraintLayout

        var originalHeight = 0
        var isViewExpanded = false
        private val mDateWatcher = DateWatcher(mEditText)

        init {

            mEditText.addTextChangedListener(mDateWatcher)

            if (!isViewExpanded) {
                // Set Views to View.GONE and .setEnabled(false)
                mConstraintLayout.visibility = View.GONE
                mConstraintLayout.isEnabled = false
            }
        }
    }

    companion object {
        val FORM_PREFERENCES = "FormPreferences"
    }
}