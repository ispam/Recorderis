package tech.destinum.recorderis.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.activities.Home;
import tech.destinum.recorderis.pojo.Dates;

import static tech.destinum.recorderis.adapters.FormAdapter.FORM_PREFERENCES;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Dates> mDates;

    public HomeAdapter(Context context, ArrayList<Dates> dates) {
        mContext = context;
        mDates = dates;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_home, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SharedPreferences mSP = mContext.getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE);
        Dates dates = mDates.get(position);
        holder.mMonth.setText(dates.getDate());
    }


    @Override
    public int getItemCount() {
        return mDates != null ? mDates.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mDaysLeft, mMonth, mSymbol;

        public ViewHolder(View view) {
            super(view);

            mDaysLeft = (TextView) view.findViewById(R.id.days_left_tv);
            mSymbol = (TextView) view.findViewById(R.id.symbol_tv);
            mMonth = (TextView) view.findViewById(R.id.month_tv);
        }
    }
}
