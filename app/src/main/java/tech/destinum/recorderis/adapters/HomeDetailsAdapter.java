package tech.destinum.recorderis.adapters;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.provider.CalendarContract;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.pojo.Date;
import tech.destinum.recorderis.utils.FlipAnimator;

public class HomeDetailsAdapter extends RecyclerView.Adapter<HomeDetailsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Date> mDates;
    private clickCallback mClickCallback;

    public HomeDetailsAdapter(Context context, ArrayList<Date> dates, clickCallback clickCallback) {
        mContext = context;
        mDates = dates;
        mClickCallback = clickCallback;
    }
    public interface clickCallback{
        void onItemClick(int position);
    }

    @Override
    public HomeDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_home, parent, false));
    }

    @Override
    public void onBindViewHolder(final HomeDetailsAdapter.ViewHolder holder, int position) {
        Date date = mDates.get(position);
        holder.mName.setText(date.getName());

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            java.util.Date d = sdf.parse(date.getDate());
            Calendar c = Calendar.getInstance();
            final long diff = d.getTime() - c.getTimeInMillis();
            holder.mDaysLeft.setText(String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));
            holder.mProgressBar.setProgress(Math.round(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));

        } catch (ParseException e){
            e.printStackTrace();
        }

        holder.mDays.setText(R.string.days);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            java.util.Date d = sdf.parse(date.getDate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            int day  = calendar.get(Calendar.DAY_OF_MONTH);
            String month = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
            holder.mDate.setText(String.valueOf(day)+"/"+ month.substring(0,1).toUpperCase()+month.substring(1)+"/"+calendar.get(Calendar.YEAR));

        } catch (ParseException e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return mDates != null ? mDates.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mName, mDaysLeft, mDays, mDate;
        public ProgressBar mProgressBar;
        public View mView;
        public ConstraintLayout mFront, mBack;
//        int click = 1;

        public ViewHolder(View view) {
            super(view);

            this.mView = view;

            mName = (TextView) view.findViewById(R.id.format_home_name);
            mDaysLeft = (TextView) view.findViewById(R.id.format_home_days_left);
            mDays = (TextView) view.findViewById(R.id.format_home_days);
            mDate = (TextView) view.findViewById(R.id.format_home_date);
            mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            mFront = (ConstraintLayout) view.findViewById(R.id.cl_front);
            mBack = (ConstraintLayout) view.findViewById(R.id.cl_back);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickCallback.onItemClick(getAdapterPosition());
                }
            });

//            view.setOnClickListener(this);
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
