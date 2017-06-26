package tech.destinum.recorderis.adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.pojo.Date;
import tech.destinum.recorderis.pojo.Document;
import tech.destinum.recorderis.utils.DateWatcher;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {

    public static final String FORM_PREFERENCES = "FormPreferences";
    private Context mContext;
    private DBHelper mDBHelper;
    private ArrayList<Document> mDocuments;

    public boolean zero, one, two, three, four, five;

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
        mDBHelper = new DBHelper(holder.mCardView.getContext());

        holder.mTitle.setText(document.getName());
        holder.mTitleExpanded.setText(document.getName());

        holder.mEditText.setTag(R.id.date_et, position);

        holder.mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.isViewExpanded = false;

                ValueAnimator valueAnimator;
                valueAnimator = ValueAnimator.ofInt(holder.originalHeight + (int) (holder.originalHeight), holder.originalHeight);
                Animation a = new AlphaAnimation(1.00f, 0.00f);

                a.setDuration(200);

                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        holder.mConstraintLayout.setVisibility(View.GONE);
                        holder.mConstraintLayout.setEnabled(false);
                        holder.mTitle.setVisibility(View.VISIBLE);
                        holder.mTitle.setEnabled(true);
                        holder.mCardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        holder.mEditText.setText("");
                        holder.mImageView.setVisibility(View.VISIBLE);
                        holder.mImageViewCheck.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                holder.mConstraintLayout.startAnimation(a);

                valueAnimator.setDuration(200);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        holder.mCardView.getLayoutParams().height = value.intValue();
                        holder.mCardView.requestLayout();
                    }
                });
                valueAnimator.start();

                String nothing = "";

                SharedPreferences mSP = v.getContext().getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor mEditor = mSP.edit();

                switch (position){
                    case 0:

                        zero = false;
                        Log.d("SOAT", nothing);
                        mEditor.putString("soat", nothing);
                        mEditor.commit();

                        break;
                    case 1:

                        one = false;
                        Log.d("RTM", nothing);
                        mEditor.putString("rtm", nothing);
                        mEditor.commit();

                        break;
                    case 2:

                        two = false;
                        Log.d("SRC", nothing);
                        mEditor.putString("src", nothing);
                        mEditor.commit();

                        break;
                    case 3:

                        three = false;
                        Log.d("STR", nothing);
                        mEditor.putString("str", nothing);
                        mEditor.commit();

                        break;
                    case 4:

                        four = false;
                        Log.d("TO", nothing);
                        mEditor.putString("to", nothing);
                        mEditor.commit();

                        break;
                    case 5:

                        five = false;
                        Log.d("EXT", nothing);
                        mEditor.putString("ext", nothing);
                        mEditor.commit();

                        break;
                }
            }
        });

        holder.mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                String data = holder.mEditText.getText().toString();

                if (data.length() <= 0 || data == "") {
                    Toast.makeText(v.getContext(), R.string.need_date, Toast.LENGTH_SHORT).show();
                } else {
                    holder.isViewExpanded = false;

                    if (holder.originalHeight == 0) {
                        holder.originalHeight = holder.mCardView.getHeight();
                    }

                    ValueAnimator valueAnimator;
                    valueAnimator = ValueAnimator.ofInt(holder.originalHeight + (int) (holder.originalHeight), holder.originalHeight);
                    Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

                    a.setDuration(200);
                    // Set a listener to the animation and configure onAnimationEnd
                    a.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            holder.mConstraintLayout.setVisibility(View.GONE);
                            holder.mConstraintLayout.setEnabled(false);
                            holder.mCardView.setBackgroundColor(Color.parseColor("#E5FDEB"));
                            holder.mTitle.setVisibility(View.VISIBLE);
                            holder.mTitle.setEnabled(true);
                            holder.mImageView.setVisibility(View.GONE);
                            holder.mImageViewCheck.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    // Set the animation on the custom view
                    holder.mConstraintLayout.startAnimation(a);

                    valueAnimator.setDuration(200);
                    valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer value = (Integer) animation.getAnimatedValue();
                            holder.mCardView.getLayoutParams().height = value.intValue();
                            holder.mCardView.requestLayout();
                        }
                    });
                    valueAnimator.start();

                    Log.d("pos", String.valueOf(position));

                    SharedPreferences mSP = v.getContext().getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = mSP.edit();

                    switch (position) {
                        case 0:

                            zero = true;
                            Log.d("SOAT", data);
                            mEditor.putString("soat", data);
                            mEditor.commit();

                            break;
                        case 1:

                            one = true;
                            Log.d("RTM", data);
                            mEditor.putString("rtm", data);
                            mEditor.commit();

                            break;
                        case 2:

                            two = true;
                            Log.d("SRC", data);
                            mEditor.putString("src", data);
                            mEditor.commit();

                            break;
                        case 3:

                            three = true;
                            Log.d("STR", data);
                            mEditor.putString("str", data);
                            mEditor.commit();

                            break;
                        case 4:

                            four = true;
                            Log.d("TO", data);
                            mEditor.putString("to", data);
                            mEditor.commit();

                            break;
                        case 5:

                            five = true;
                            Log.d("EXT", data);
                            mEditor.putString("ext", data);
                            mEditor.commit();

                            break;
                    }
                }
            }
        });

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position){
                    case 0:

                        if (zero == true){
                            animation1();
                        } else {
                            animation2();
                        }

                        break;
                    case 1:

                        if (one == true){
                            animation1();
                        } else {
                            animation2();
                        }

                        break;
                    case 2:

                        if (two == true){
                            animation1();
                        } else {
                            animation2();
                        }

                        break;
                    case 3:

                        if (three == true){
                            animation1();
                        } else {
                            animation2();
                        }

                        break;
                    case 4:

                        if (four == true){
                            animation1();
                        } else {
                            animation2();
                        }

                        break;

                    case 5:

                        if (five == true){
                            animation1();
                        } else {
                            animation2();
                        }

                        break;
                }
            }

            private void animation1() {

                if (holder.originalHeight == 0) {
                    holder.originalHeight = holder.mCardView.getHeight();
                }

                ValueAnimator valueAnimator;

                if (!holder.isViewExpanded) {
                    holder.mTitle.setVisibility(View.GONE);
                    holder.mTitle.setEnabled(false);
                    holder.mImageView.setVisibility(View.GONE);
                    holder.mConstraintLayout.setVisibility(View.VISIBLE);
                    holder.mConstraintLayout.setEnabled(true);
                    holder.mImageViewCheck.setVisibility(View.GONE);
                    holder.isViewExpanded = true;
                    valueAnimator = ValueAnimator.ofInt(holder.originalHeight, holder.originalHeight + (int) (holder.originalHeight));

                } else {
                    holder.isViewExpanded = false;

                    valueAnimator = ValueAnimator.ofInt(holder.originalHeight + (int) (holder.originalHeight), holder.originalHeight);
                    Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

                    a.setDuration(200);
                    // Set a listener to the animation and configure onAnimationEnd
                    a.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            holder.mConstraintLayout.setVisibility(View.GONE);
                            holder.mConstraintLayout.setEnabled(false);
                            holder.mCardView.setBackgroundColor(Color.parseColor("#E5FDEB"));
                            holder.mTitle.setVisibility(View.VISIBLE);
                            holder.mTitle.setEnabled(true);
                            holder.mImageView.setVisibility(View.GONE);
                            holder.mImageViewCheck.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    // Set the animation on the custom view
                    holder.mConstraintLayout.startAnimation(a);
                }

                valueAnimator.setDuration(200);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        holder.mCardView.getLayoutParams().height = value.intValue();
                        holder.mCardView.requestLayout();
                    }
                });
                valueAnimator.start();
            }

            private void animation2(){
                if (holder.originalHeight == 0) {
                    holder.originalHeight = holder.mCardView.getHeight();
                }

                ValueAnimator valueAnimator;

                if (!holder.isViewExpanded) {
                    holder.mTitle.setVisibility(View.GONE);
                    holder.mTitle.setEnabled(false);
                    holder.mImageView.setVisibility(View.GONE);
                    holder.mConstraintLayout.setVisibility(View.VISIBLE);
                    holder.mConstraintLayout.setEnabled(true);
                    holder.mImageViewCheck.setVisibility(View.GONE);
                    holder.isViewExpanded = true;
                    valueAnimator = ValueAnimator.ofInt(holder.originalHeight, holder.originalHeight + (int) (holder.originalHeight)); // These values in this method can be changed to expand however much you like
                } else {
                    holder.isViewExpanded = false;
                    valueAnimator = ValueAnimator.ofInt(holder.originalHeight + (int) (holder.originalHeight), holder.originalHeight);

                    Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

                    a.setDuration(200);
                    // Set a listener to the animation and configure onAnimationEnd
                    a.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            holder.mTitle.setVisibility(View.VISIBLE);
                            holder.mTitle.setEnabled(true);
                            holder.mImageView.setVisibility(View.VISIBLE);
                            holder.mConstraintLayout.setVisibility(View.GONE);
                            holder.mConstraintLayout.setEnabled(false);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    // Set the animation on the custom view
                    holder.mConstraintLayout.startAnimation(a);
                }

                valueAnimator.setDuration(200);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        holder.mCardView.getLayoutParams().height = value.intValue();
                        holder.mCardView.requestLayout();
                    }
                });
                valueAnimator.start();
            }
        });
    }



    @Override
    public int getItemCount() {
        return mDocuments != null ? mDocuments.size(): 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle, mTitleExpanded;
        public Button mAddButton, mCancelButton;
        public EditText mEditText;
        public ImageView mImageView, mImageViewCheck;
        public CardView mCardView;

        public int originalHeight = 0;
        public boolean isViewExpanded = false;
        public ConstraintLayout mConstraintLayout;
        private DateWatcher mDateWatcher;

        public ViewHolder(final View view) {
            super(view);

            mConstraintLayout = (ConstraintLayout) view.findViewById(R.id.expanded);
            mTitle = (TextView) view.findViewById(R.id.name_title_tv);
            mTitleExpanded = (TextView) view.findViewById(R.id.name_title_tv_expanded);
            mAddButton = (Button) view.findViewById(R.id.add_button);
            mCancelButton = (Button) view.findViewById(R.id.button_cancel);
            mEditText = (EditText) view.findViewById(R.id.date_et);
            mImageView = (ImageView) view.findViewById(R.id.imageView_up);
            mImageViewCheck = (ImageView) view.findViewById(R.id.imageView_black_check);
            mCardView = (CardView) view.findViewById(R.id.card_view_form);

            mDateWatcher = new DateWatcher(mEditText);
            mEditText.addTextChangedListener(mDateWatcher);

            if (isViewExpanded == false) {
                // Set Views to View.GONE and .setEnabled(false)
                mConstraintLayout.setVisibility(View.GONE);
                mConstraintLayout.setEnabled(false);
            }
        }
    }
}