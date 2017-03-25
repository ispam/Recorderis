package tech.destinum.recorderis.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import tech.destinum.recorderis.DB.DBHelper;
import tech.destinum.recorderis.R;
import tech.destinum.recorderis.pojo.Dates;
import tech.destinum.recorderis.pojo.User;

public class Home extends BaseActivity {

    private TextView mName, mDays, mDate;
    private DBHelper mDBHelper;
    private Context mContext;
    private Dates mDates;

    private static int REQUEST_CODE_OK = 12302;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();

        mDBHelper = new DBHelper(this);

        mName = (TextView) findViewById(R.id.name);
        mDays = (TextView) findViewById(R.id.days);
        mDate = (TextView) findViewById(R.id.date);

        for (int i = 0; i < 5; i++){
            switch (i){
                case 0:
                    mName.setText(mDates);
                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
            }
        }

    }


}