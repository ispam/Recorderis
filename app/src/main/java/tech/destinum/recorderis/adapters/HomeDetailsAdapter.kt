package tech.destinum.recorderis.adapters

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

import tech.destinum.recorderis.R
import tech.destinum.recorderis.Data.Entities.Date

class HomeDetailsAdapter(private val mDates: ArrayList<Date>?) : RecyclerView.Adapter<HomeDetailsAdapter.ViewHolder>() {
    interface clickCallback {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDetailsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.format_home, parent, false))
    }

    override fun onBindViewHolder(holder: HomeDetailsAdapter.ViewHolder, position: Int) {

        val date = mDates!![position]
        holder.bindInfo(date)
    }

    override fun getItemCount(): Int {
        return mDates?.size ?: 0
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        private val mName = mView.findViewById(R.id.format_home_name) as TextView
        private var mDaysLeft = mView.findViewById(R.id.format_home_days_left) as TextView
        private var mDays = mView.findViewById(R.id.format_home_days) as TextView
        private var mDate = mView.findViewById(R.id.format_home_date) as TextView
        private var mProgressBar = mView.findViewById(R.id.progressBar) as ProgressBar
        private var mInfoName = mView.findViewById(R.id.tv_info_name) as TextView
        private var mFront = mView.findViewById(R.id.cl_front) as ConstraintLayout
        private var mBack = mView.findViewById(R.id.cl_back) as ConstraintLayout


         fun bindInfo(date: Date){
            mName.text = date.name

            try {
                val sdf = SimpleDateFormat("dd/MM/yy")
                val d = sdf.parse(date.date)
                val c = Calendar.getInstance()
                val diff = d.time - c.timeInMillis
                mDaysLeft.text = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toString()
                mProgressBar.progress = Math.round(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toFloat())


            } catch (e: ParseException) {
                e.printStackTrace()
            }

            mDays.setText(R.string.days)

            try {
                val sdf = SimpleDateFormat("dd/MM/yy")
                val d = sdf.parse(date.date)
                val calendar = Calendar.getInstance()
                calendar.time = d
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val month = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
                mDate.text = day.toString() + "/" + month.substring(0, 1).toUpperCase() + month.substring(1) + "/" + calendar.get(Calendar.YEAR)

            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        //        @Override
        //        public void onClick(View v) {
        //
        //            click++;
        //            if(click % 2 == 0){
        //                FlipAnimator.flipView(mView.getContext(),mBack,mFront,true);
        //            }else {
        //                FlipAnimator.flipView(mView.getContext(),mBack,mFront,false);
        //            }
        //
        //        }
    }
}
