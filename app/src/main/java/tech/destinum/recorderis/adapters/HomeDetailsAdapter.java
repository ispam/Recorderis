package tech.destinum.recorderis.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.pojo.Date;

public class HomeDetailsAdapter extends RecyclerView.Adapter<HomeDetailsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Date> mDates;

    public HomeDetailsAdapter(Context context, ArrayList<Date> dates) {
        mContext = context;
        mDates = dates;
    }

    @Override
    public HomeDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_home, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeDetailsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDates != null ? mDates.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mName, mDaysLeft, mDays, mDate;

        public ViewHolder(View view) {
            super(view);

            mName = (TextView) view.findViewById(R.id.format_home_name);
            mDaysLeft = (TextView) view.findViewById(R.id.format_home_days_left);
            mDays = (TextView) view.findViewById(R.id.format_home_days);
            mDate = (TextView) view.findViewById(R.id.format_home_date);
        }
    }
}
