package tech.destinum.recorderis.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.pojo.Date;

public class HomeSymbolsAdapter extends RecyclerView.Adapter<HomeSymbolsAdapter.ViewHolder> {

    private Context mContext;
    private DBHelper mDBHelper;
    private ArrayList<Date> mDatesList;
    private final static int ITEMS_PER_PAGE = 3;

    public HomeSymbolsAdapter(Context context, ArrayList<Date> datesList) {
        mContext = context;
        mDatesList = datesList;
    }

    @Override
    public HomeSymbolsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int itemWidth = parent.getWidth() / ITEMS_PER_PAGE;

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.format_home_symbols, parent, false);

        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        layoutParams.width = itemWidth;
        itemView.setLayoutParams(layoutParams);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeSymbolsAdapter.ViewHolder holder, int position) {

        Date date = mDatesList.get(position);
        holder.mSymbol.setText(date.getSymbol());

    }

    @Override
    public int getItemCount() {
        return mDatesList != null ? mDatesList.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mSymbol;

        public ViewHolder(View view) {
            super(view);

            mSymbol = (TextView) view.findViewById(R.id.symbols_tv);
        }
    }
}
