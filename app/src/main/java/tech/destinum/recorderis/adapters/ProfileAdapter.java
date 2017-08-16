package tech.destinum.recorderis.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.pojo.Date;

public class ProfileAdapter extends RecyclerView.Adapter <ProfileAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Date> mDates;

    public ProfileAdapter(Context mContext, ArrayList<Date> mDates){
        this.mContext = mContext;
        this.mDates = mDates;
    }

    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_profile, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Date date = mDates.get(position);
        holder.mSymbol.setText(date.getSymbol());
        holder.mDate.setText(date.getDate());
        holder.mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ProfileAdapter", "button pressed");
            }
        });

    }


    @Override
    public int getItemCount() {
        return mDates != null ? mDates.size(): 0;
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        public TextView mSymbol, mDate;
        public ImageButton mEdit;

        public ViewHolder(View v) {
            super(v);

            mSymbol = (TextView) v.findViewById(R.id.format_profile_symbol);
            mDate = (TextView) v.findViewById(R.id.format_profile_date);
            mEdit = (ImageButton) v.findViewById(R.id.format_profile_edit);
        }
    }
}
