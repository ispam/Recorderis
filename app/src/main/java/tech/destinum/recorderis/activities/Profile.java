package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.ProfileAdapter;
import tech.destinum.recorderis.pojo.Date;
import tech.destinum.recorderis.pojo.Document;
import tech.destinum.recorderis.utils.DateWatcher;

import static tech.destinum.recorderis.adapters.FormAdapter.FORM_PREFERENCES;

public class Profile extends BaseActivity implements AdapterView.OnItemSelectedListener {

    public TextView mName;
    public Context mContext;
    public DBHelper mDBHelper;
    public ProfileAdapter mProfileAdapter;
    public RecyclerView mRecyclerView;
    public ScrollView mScrollView;
    public ImageView mAdd;
    public String label;
    public int pos;
    public DateWatcher mDateWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        super.onCreateDrawer();

        mDBHelper = new DBHelper(this);
        mName = (TextView) findViewById(R.id.format_profile_name);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_profile);
        mScrollView = (ScrollView) findViewById(R.id.scroll_view);
        mAdd = (ImageView) findViewById(R.id.profile_button);

        SharedPreferences mSP = getSharedPreferences(BaseActivity.PREFERENCES, Context.MODE_PRIVATE);
        String name = mSP.getString("name", "");
        mName.setText(name);
        mScrollView.smoothScrollTo(0,0);

        mProfileAdapter = new ProfileAdapter(mContext, mDBHelper.getAllDates());
        mRecyclerView.setAdapter(mProfileAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder add = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_creation, null, true);
                TextView title = (TextView) view.findViewById(R.id.dialog_tv_title);
                TextView msg = (TextView) view.findViewById(R.id.dialog_tv_msg);
                final EditText et = (EditText) view.findViewById(R.id.dialog_edt_date);
                Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
                spinner.setOnItemSelectedListener(Profile.this);

                title.setText(R.string.dialog_new_doc);
                msg.setText(R.string.dialog_choose);

                mDateWatcher = new DateWatcher(et);
                et.addTextChangedListener(mDateWatcher);

                final List<Document> list = mDBHelper.getAllDocuments();
                String [] symbols = new String[list.size()];

                for (int i = 0; i< list.size(); i++){
                    symbols[i] = list.get(i).getSymbol();
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(v.getContext(), R.layout.spinner_item, symbols);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);

                add.setNegativeButton(R.string.cancel, null)
                    .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            long user_id = mDBHelper.getLastUser();

                            switch (pos){
                                case 0:
                                    mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_soat), et.getText().toString(), getApplicationContext().getString(R.string.symbol_soat), user_id);
                                    refresh();
                                    break;
                                case 1:
                                    mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_rtm), et.getText().toString(), getApplicationContext().getString(R.string.symbol_rtm), user_id);
                                    refresh();
                                    break;
                                case 2:
                                    mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_str), et.getText().toString(), getApplicationContext().getString(R.string.symbol_str), user_id);
                                    refresh();
                                    break;
                                case 3:
                                    mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_tao), et.getText().toString(), getApplicationContext().getString(R.string.symbol_tao), user_id);
                                    refresh();
                                    break;
                                case 4:
                                    mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_ext), et.getText().toString(), getApplicationContext().getString(R.string.symbol_ext), user_id);
                                    refresh();
                                    break;
                            }

                            dialog.dismiss();
                        }
                    }).setView(view).show();
            }

            private void refresh() {
                mProfileAdapter.refreshAdapter(mDBHelper.getAllDates());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        label = parent.getItemAtPosition(position).toString();
        pos = position;

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), getString(R.string.dialog_selection) + label,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
