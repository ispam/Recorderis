package tech.destinum.recorderis.adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.pojo.Document;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Document> mDocuments;
    private final static String PREFS_FORM_ADAPTER = "FormAdapterPREFS";
    private static List<String> mEditTextValues = new ArrayList<>();
    private String[] texts = new String[];

    public FormAdapter(Context mContext, ArrayList<Document> mDocuments) {
        this.mContext = mContext;
        this.mDocuments = mDocuments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_form, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Document document = mDocuments.get(position);
        holder.mTitle.setText(document.getName());
        holder.mTitleExpanded.setText(document.getName());
        holder.mEditText.setTag(R.id.date_et, position);
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mEditTextValues.add(holder.mEditText.getText().toString());
                Log.d("FormAdapter", holder.mEditText.getText().toString());
                Log.d("id", String.valueOf(document.getId()));

                for(int i = 0; i < mEditTextValues.size(); i++) {
                    System.out.println(mEditTextValues.get(i)); //prints element i
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDocuments != null ? mDocuments.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTitle, mTitleExpanded;
        public Button mButton;
        public EditText mEditText;
        private int originalHeight = 0;
        private boolean isViewExpanded = false;
        private ConstraintLayout mConstraintLayout;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            mConstraintLayout = (ConstraintLayout) view.findViewById(R.id.expanded);
            mTitle = (TextView) view.findViewById(R.id.name_title_tv);
            mTitleExpanded = (TextView) view.findViewById(R.id.name_title_tv_expanded);
            mButton = (Button) view.findViewById(R.id.add_button);
            mEditText = (EditText) view.findViewById(R.id.date_et);

            if (isViewExpanded == false) {
                // Set Views to View.GONE and .setEnabled(false)
                mConstraintLayout.setVisibility(View.GONE);
                mConstraintLayout.setEnabled(false);

            }

            mEditText.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                public void afterTextChanged(Editable editable) {}
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    if(mEditText.getTag()!=null){
//                        for (int j = 0; j < mEditTextValues.size(); j++){
//                            mEditTextValues.add(mEditTextValues.set((int)mEditText.getTag(), charSequence.toString()));
//                            Log.d("FormAdapter", charSequence.toString());
//                        }
//                    }

                    int position = (int) mEditText.getTag(R.id.date_et);


                }
            });
        }



        @Override
        public void onClick(final View v) {
            // If the originalHeight is 0 then find the height of the View being used 
            // This would be the height of the ConstraintLayout
            if (originalHeight == 0) {
                originalHeight = v.getHeight();
            }

            // Declare a ValueAnimator object
            ValueAnimator valueAnimator;
            if (!isViewExpanded) {
                mTitle.setVisibility(View.GONE);
                mTitle.setEnabled(false);
                mConstraintLayout.setVisibility(View.VISIBLE);
                mConstraintLayout.setEnabled(true);
                isViewExpanded = true;
                valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + (int) (originalHeight)); // These values in this method can be changed to expand however much you like
            } else {
                isViewExpanded = false;
                valueAnimator = ValueAnimator.ofInt(originalHeight + (int) (originalHeight), originalHeight);

                Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

                a.setDuration(200);
                // Set a listener to the animation and configure onAnimationEnd
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mTitle.setVisibility(View.VISIBLE);
                        mTitle.setEnabled(true);
                        mConstraintLayout.setVisibility(View.GONE);
                        mConstraintLayout.setEnabled(false);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                // Set the animation on the custom view
                mConstraintLayout.startAnimation(a);
            }
            valueAnimator.setDuration(200);
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Integer value = (Integer) animation.getAnimatedValue();
                    v.getLayoutParams().height = value.intValue();
                    v.requestLayout();
                }
            });


            valueAnimator.start();

        }
    }
}