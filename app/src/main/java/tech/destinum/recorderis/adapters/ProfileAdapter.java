package tech.destinum.recorderis.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private ProfileAdapter mAdapter;

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


        final Date date = mDates.get(position);
        holder.mSymbol.setText(date.getSymbol());
        holder.mDate.setText(date.getDate());
        holder.mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                mDBHelper = new DBHelper(v.getContext());
                final AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());

                LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView =inflater.inflate(R.layout.dialog_profile, null, true);
                final EditText edt = (EditText) dialogView.findViewById(R.id.dialog_edt_date);
                TextView title = (TextView) dialogView.findViewById(R.id.dialog_tv_title) ;
                TextView msg = (TextView) dialogView.findViewById(R.id.dialog_tv_msg) ;

                title.setText(v.getContext().getResources().getString(R.string.dialog_title)+date.getSymbol());
                msg.setText(Html.fromHtml(v.getContext().getResources().getString(R.string.dialog_msg))+date.getDate());
                mDateWatcher = new DateWatcher(edt);
                edt.addTextChangedListener(mDateWatcher);

                dialog.setNegativeButton(R.string.cancel, null)
                        .setNeutralButton(R.string.dialog_delete, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AlertDialog.Builder confirmation = new AlertDialog.Builder(v.getContext());
                                LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.dialog_confirmation, null, true);
                                TextView title = (TextView) view.findViewById(R.id.dialog_tv_title);
                                TextView msg = (TextView) view.findViewById(R.id.dialog_tv_msg);
                                title.setText(v.getContext().getResources().getString(R.string.dialog_confirmation));
                                msg.setText(Html.fromHtml(v.getContext().getResources().getString(R.string.dialog_confirmation_msg))+date.getSymbol() +" : "+ date.getDate());

                                confirmation.setNegativeButton(R.string.dialog_no, null)
                                        .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                mDBHelper.deleteDate(date.getId());
                                                refreshAdapter(mDBHelper.getAllDates());
                                                dialog.dismiss();

                                            }
                                        }).setView(view).show();

                            }
                        })
                        .setPositiveButton(R.string.dialog_change, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (edt.getText().equals("") || edt.getText().length() < 6){
                                    Toast.makeText(v.getContext(), R.string.need_date, Toast.LENGTH_SHORT).show();
                                } else {
                                    mDBHelper.updateDate(date.getName(), edt.getText().toString(), date.getSymbol(), date.getUser_id(), date.getId());
                                    refreshAdapter(mDBHelper.getAllDates());
                                    dialog.dismiss();
                                }


                            }
                        });
                dialog.setView(dialogView).show();

            }
        });

    }

    public synchronized void refreshAdapter(ArrayList<Date> mDatess){
        mDates.clear();
        mDates.addAll(mDatess);
        notifyDataSetChanged();
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
