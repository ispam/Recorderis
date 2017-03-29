package tech.destinum.recorderis.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.pojo.User;

import static tech.destinum.recorderis.adapters.FormAdapter.FORM_PREFERENCES;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<User> mUser;
    private DBHelper mDBHelper;

    public HomeAdapter(Context context, ArrayList<User> user) {
        mContext = context;
        mUser = user;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_home, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mDBHelper = new DBHelper(mContext);
        SharedPreferences mSP = mContext.getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE);
        User user = mUser.get(position);
        holder.mMonth.setText(String.valueOf(user.getId()));
        holder.mDaysLeft.setText(user.getName());
        holder.mDate.setText(user.get);
    }


    @Override
    public int getItemCount() {
        return mUser != null ? mUser.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mDate, mMonth, mDaysLeft, mName;

        public ViewHolder(View view) {
            super(view);

            mDate = (TextView) view.findViewById(R.id.format_home_date_tv);
            mDaysLeft = (TextView) view.findViewById(R.id.format_home_days_left_tv);
            mMonth = (TextView) view.findViewById(R.id.format_home_month_tv);
            mName = (TextView) view.findViewById(R.id.format_home_name_tv);
        }
    }
}
