package tech.destinum.recorderis.activities;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.Calendar;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.adapters.FormAdapter;
import tech.destinum.recorderis.pojo.Document;

import static tech.destinum.recorderis.adapters.FormAdapter.FORM_PREFERENCES;

public class Form extends AppCompatActivity {

    private DBHelper mDBHelper;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private ArrayList<Document> mArrayList;
    private FormAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mDBHelper = new DBHelper(this);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.hint);
        dialog.setCanceledOnTouchOutside(true);
        //for dismissing anywhere you touch
        View masterView = dialog.findViewById(R.id.hint);
        masterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_form);

        mArrayList = new ArrayList<>();
        mArrayList.add(new Document(getString(R.string.doc_soat), getString(R.string.symbol_soat), 0));
        mArrayList.add(new Document(getString(R.string.doc_rtm), getString(R.string.symbol_rtm), 1));
        mArrayList.add(new Document(getString(R.string.doc_str), getString(R.string.symbol_str), 2));
        mArrayList.add(new Document(getString(R.string.doc_tao), getString(R.string.symbol_tao), 3));
        mArrayList.add(new Document(getString(R.string.doc_ext), getString(R.string.symbol_ext), 4));

        mAdapter = new FormAdapter(mContext, mArrayList);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
//        mRecyclerView.addItemDecoration(itemDecoration);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.confirmation:

                SharedPreferences mSP = getSharedPreferences(FORM_PREFERENCES, Context.MODE_PRIVATE);
                long user_id = mDBHelper.getLastUser();

                String soat = mSP.getString("soat", "");
                if (soat != null && soat != ""){
                    mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_soat), soat, getApplicationContext().getString(R.string.symbol_soat), user_id);
                    scheduleNotification(soat, 0);
                }

                String rtm = mSP.getString("rtm", "");
                if (rtm != null && rtm != ""){
                    mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_rtm), rtm, getApplicationContext().getString(R.string.symbol_rtm), user_id);
                    scheduleNotification(rtm, 1);
                }

                String str = mSP.getString("str", "");
                if (str != null && str != ""){
                    mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_str), str, getApplicationContext().getString(R.string.symbol_str), user_id);
                    scheduleNotification(str, 2);
                }

                String to = mSP.getString("to", "");
                if (to != null && to != ""){
                    mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_tao), to, getApplicationContext().getString(R.string.symbol_tao), user_id);
                    scheduleNotification(to, 3);
                }

                String ext = mSP.getString("ext", "");
                if (ext != null && ext != ""){
                    mDBHelper.createNewDate(getApplicationContext().getString(R.string.doc_ext), ext, getApplicationContext().getString(R.string.symbol_ext), user_id);
                    scheduleNotification(ext, 4);
                }

                mSP.edit().clear().commit();

                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

                break;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void scheduleNotification(String text, int notificationId) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 5);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
    }
}

