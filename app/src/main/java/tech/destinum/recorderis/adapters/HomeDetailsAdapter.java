package tech.destinum.recorderis.adapters;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.pojo.Date;

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
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            calendar.setTime(d);
            int day  = calendar.get(Calendar.DAY_OF_MONTH);
            holder.mDate.setText(String.valueOf(day)+"/"+new SimpleDateFormat("MMM").format(calendar.getTime())
                    +"/"+calendar.get(Calendar.YEAR));

            ContentResolver contentResolver = holder.mView.getContext().getContentResolver();
            ContentValues contentValues = new ContentValues();

            contentValues.put(CalendarContract.Events.TITLE, holder.mView.getResources().getString(R.string.app_name));
            contentValues.put(CalendarContract.Events.DESCRIPTION, "");
            contentValues.put(CalendarContract.Events.DTSTART, calendar.getTimeInMillis());
//            contentValues.put(CalendarContract.Events.DTEND, calendar.getTimeInMillis()+ 60*60*7200);
            contentValues.put(CalendarContract.Events.ALL_DAY, true);
            contentValues.put(CalendarContract.Events.CALENDAR_ID, date.getId());
            contentValues.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());

            Uri uri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, contentValues);

            long eventID = Long.parseLong(uri.getLastPathSegment());

            ContentValues reminders = new ContentValues();
            reminders.put(CalendarContract.Reminders.EVENT_ID, eventID);
            reminders.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
            reminders.put(CalendarContract.Reminders.MINUTES, 60);

            Uri uri2 = contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, reminders);

        } catch (ParseException e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return mDates != null ? mDates.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mName, mDaysLeft, mDays, mDate;
        public ProgressBar mProgressBar;
        public View mView;

        public ViewHolder(View view) {
            super(view);

            this.mView = view;

            mName = (TextView) view.findViewById(R.id.format_home_name);
            mDaysLeft = (TextView) view.findViewById(R.id.format_home_days_left);
            mDays = (TextView) view.findViewById(R.id.format_home_days);
            mDate = (TextView) view.findViewById(R.id.format_home_date);
            mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickCallback.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
