package tech.destinum.recorderis.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class Profile extends BaseActivity implements AdapterView.OnItemSelectedListener {

    public TextView mName;
    public Context mContext;
    public DBHelper mDBHelper;
    public ProfileAdapter mProfileAdapter;
    public RecyclerView mRecyclerView;
    public ScrollView mScrollView;
    public ImageView mAdd;

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
            public void onClick(View v) {
                AlertDialog.Builder add = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_creation, null, true);
                TextView title = (TextView) view.findViewById(R.id.dialog_tv_title);
                TextView msg = (TextView) view.findViewById(R.id.dialog_tv_msg);
                Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
                spinner.setOnItemSelectedListener(Profile.this);

                title.setText(R.string.dialog_new_doc);
                msg.setText(R.string.dialog_choose);

                String[] symbolsArray = {getString(R.string.symbol_soat), getString(R.string.symbol_rtm), getString(R.string.symbol_src), getString(R.string.symbol_str)
                        , getString(R.string.symbol_tao), getString(R.string.symbol_ext)};
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(v.getContext(), R.layout.spinner_item, symbolsArray);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);

                add.setNegativeButton(R.string.cancel, null)
                    .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            dialog.dismiss();
                        }
                    }).setView(view).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
