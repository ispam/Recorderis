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
//        TODO formula to calculate the days left
        holder.mDaysLeft.setText("125");
        holder.mDays.setText(R.string.days);
        holder.mDate.setText(date.getDate());
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
