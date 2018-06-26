package tech.destinum.recorderis.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.activities.Form;
import tech.destinum.recorderis.Data.Entities.Category;

public class SelectionAdapter extends RecyclerView.Adapter<SelectionAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Category> mCategories;

    public SelectionAdapter(Context mContext, ArrayList<Category> mCategories) {
        this.mContext = mContext;
        this.mCategories = mCategories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_selection, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Category category = mCategories.get(position);
        holder.mTitle.setText(category.getName());
        holder.mDescription.setText(category.getDescription());
        holder.mImage.setImageResource(category.getImage());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Form.class);
                view.getContext().startActivity(i);
            }
        });
        holder.mCardView.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return mCategories != null ? mCategories.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle, mDescription;
        public ImageView mImage;
        public CardView mCardView;

        public ViewHolder(View view) {
            super(view);

            mTitle = (TextView) view.findViewById(R.id.tv_title);
            mDescription = (TextView) view.findViewById(R.id.tv_description);
            mImage = (ImageView) view.findViewById(R.id.image_category);
            mCardView = (CardView) view.findViewById(R.id.card_view);

        }
    }
}
