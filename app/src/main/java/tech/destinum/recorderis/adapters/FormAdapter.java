package tech.destinum.recorderis.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
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
import tech.destinum.recorderis.pojo.Category;
import tech.destinum.recorderis.pojo.Document;
import tech.destinum.recorderis.pojo.User;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Document> mDocuments;

    public FormAdapter(Context mContext, ArrayList<Document> mDocuments) {
        this.mContext = mContext;
        this.mDocuments = mDocuments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_form, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Document document = mDocuments.get(position);
        holder.mTitle.setText(document.getName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Form.class);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDocuments != null ? mDocuments.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public ImageView mImage;
        public CardView mCardView;

        public ViewHolder(View view) {
            super(view);

            mTitle = (TextView) view.findViewById(R.id.name_title_tv);
            mImage = (ImageView) view.findViewById(R.id.play_button_downside_iv);
            mCardView = (CardView) view.findViewById(R.id.constrain_layout_form);

        }
    }
}