package tech.destinum.recorderis.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.Data.Entities.Date;
import tech.destinum.recorderis.utils.OnSwipeTouchListener;

public class HomeSymbolsAdapter extends RecyclerView.Adapter<HomeSymbolsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Date> mDatesList;
    private clickCallback mClickCallback;
    private OnSwipeTouchListener onSwipeTouchListener;

    public HomeSymbolsAdapter(Context context, ArrayList<Date> datesList, clickCallback clickCallback) {
        mContext = context;
        mDatesList = datesList;
        mClickCallback = clickCallback;
    }

    public interface clickCallback{
        void onItemClick(int position);
    }

    @Override
    public HomeSymbolsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_home_symbols, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeSymbolsAdapter.ViewHolder holder, int position) {

        int positionInList = position % mDatesList.size();
        Date date = mDatesList.get(positionInList);
        holder.mSymbol.setText(date.getSymbol());

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mSymbol;

        public ViewHolder(View view) {
            super(view);

            mSymbol = (TextView) view.findViewById(R.id.symbols_tv);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickCallback.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
