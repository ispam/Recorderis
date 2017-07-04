package tech.destinum.recorderis.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onBindViewHolder(HomeDetailsAdapter.ViewHolder holder, int position) {
        int positionInList = position % mDates.size();
        Date date = mDates.get(positionInList);
        holder.mName.setText(date.getName());


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            java.util.Date d = sdf.parse(date.getDate());
            Calendar c = Calendar.getInstance();
            long diff = d.getTime() - c.getTimeInMillis();
            holder.mDaysLeft.setText(String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));

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
//            holder.mDate.setText(String.valueOf(sdf.format(d)));
            holder.mDate.setText(String.valueOf(day)+"/"+new SimpleDateFormat("MMM").format(calendar.getTime())
                    +"/"+calendar.get(Calendar.YEAR));
        } catch (ParseException e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
//        return mDates != null ? mDates.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mName, mDaysLeft, mDays, mDate;

        public ViewHolder(View view) {
            super(view);

            mName = (TextView) view.findViewById(R.id.format_home_name);
            mDaysLeft = (TextView) view.findViewById(R.id.format_home_days_left);
            mDays = (TextView) view.findViewById(R.id.format_home_days);
            mDate = (TextView) view.findViewById(R.id.format_home_date);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickCallback.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
