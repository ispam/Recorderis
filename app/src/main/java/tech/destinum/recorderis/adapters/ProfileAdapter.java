package tech.destinum.recorderis.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.pojo.Date;
import tech.destinum.recorderis.utils.DateWatcher;

public class ProfileAdapter extends RecyclerView.Adapter <ProfileAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Date> mDates;
    private DBHelper mDBHelper;
    private DateWatcher mDateWatcher;

    public ProfileAdapter(Context mContext, ArrayList<Date> mDates){
        this.mContext = mContext;
        this.mDates = mDates;
    }

    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_profile, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        mDBHelper = new DBHelper(mContext);

        Date date = mDates.get(position);
        holder.mSymbol.setText(date.getSymbol());
        holder.mDate.setText(date.getDate());
        holder.mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final EditText input = new EditText(v.getContext());
                final String date = mDates.get(position).getDate();
                input.setHint(date);
//                mDateWatcher = new DateWatcher(input);
//                input.addTextChangedListener(mDateWatcher);

                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());

                LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView =inflater.inflate(R.layout.dialog_profile, null, true);
                final EditText edt = (EditText) dialogView.findViewById(R.id.dialog_edt_date);
                dialog.setTitle("Edicion")
                        .setMessage(Html.fromHtml("Ingrese <b>nueva</b> Fecha"))
                        .setNegativeButton("No", null)
                        .setView(dialogView)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (input.getText().toString().equals("") || input.getText().toString().length() <= 0){
                                    Toast.makeText(v.getContext(), R.string.need_date, Toast.LENGTH_SHORT).show();
                                } else {
                                    String newDate = input.getText().toString();
                                    mDBHelper.updateDate(mDates.get(position).getName(), newDate, mDates.get(position).getSymbol(), mDates.get(position).getUser_id());
                                    Log.d("Dialog", newDate);
                                }

                            }
                        }).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return mDates != null ? mDates.size(): 0;
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        public TextView mSymbol, mDate;
        public ImageView mEdit;

        public ViewHolder(View v) {
            super(v);


            mSymbol = (TextView) v.findViewById(R.id.format_profile_symbol);
            mDate = (TextView) v.findViewById(R.id.format_profile_date);
            mEdit = (ImageView) v.findViewById(R.id.format_profile_edit);
        }
    }
}
