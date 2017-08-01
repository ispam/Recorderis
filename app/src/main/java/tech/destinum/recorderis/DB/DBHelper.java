package tech.destinum.recorderis.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//import tech.destinum.recorderis.pojo.Date;
import tech.destinum.recorderis.pojo.Date;
import tech.destinum.recorderis.pojo.User;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "recorderis";
    private final static int DB_VERSION = 3;

    public final static String TABLE_USERS = "users";
    public final static String USERS_COLUMN_ID = "_id";
    public final static String USERS_COLUMN_NAME = "name";
    public final static String USERS_COLUMN_EMAIL = "email";
    public final static String USERS_COLUMN_PREMIUM = "premium";

    public final static String TABLE_DATES = "dates";
    public final static String DATES_COLUMN_ID = "_id";
    public final static String DATES_COLUMN_NAME = "name";
    public final static String DATES_COLUMN_DATE = "date";
    public final static String DATES_COLUMN_SYMBOL = "symbol";
    public final static String DATES_COLUMN_USER_ID = "user_id";

    public final static String TABLE_EVENTS = "events";
    public final static String EVENTS_COLUMN_ID = "_id";
    public final static String EVENTS_COLUMN_TITLE = "title";
    public final static String EVENTS_COLUMN_DESCRIPTION = "desc";
    public final static String EVENTS_COLUMN_TIMEZONE = "timezone";
    public final static String EVENTS_COLUMN_HOURSTART = "hourstart";
    public final static String EVENTS_COLUMN_HOUREND = "hourdend";
    public final static String EVENTS_COLUMN_DATE_ID = "date_id";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String users = String.format("CREATE TABLE " + TABLE_USERS + "("
                        + USERS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + USERS_COLUMN_NAME + " TEXT, "
                        + USERS_COLUMN_EMAIL + " TEXT)", TABLE_USERS, USERS_COLUMN_ID, USERS_COLUMN_NAME, USERS_COLUMN_EMAIL);

        String dates = String.format("CREATE TABLE " + TABLE_DATES + "("
                        + DATES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + DATES_COLUMN_NAME + " TEXT, "
                        + DATES_COLUMN_DATE + " DATE, "
                        + DATES_COLUMN_USER_ID + " LONG, "
                        + DATES_COLUMN_SYMBOL + " TEXT)", TABLE_DATES, DATES_COLUMN_ID, DATES_COLUMN_NAME, DATES_COLUMN_DATE,
                DATES_COLUMN_SYMBOL,  DATES_COLUMN_USER_ID);

        String events = String.format("CREATE TABLE " + TABLE_EVENTS + "("
                        + EVENTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + EVENTS_COLUMN_TITLE + " TEXT, "
                        + EVENTS_COLUMN_DESCRIPTION + " TEXT, "
                        + EVENTS_COLUMN_TIMEZONE + " TEXT, "
                        + EVENTS_COLUMN_HOURSTART + " INTEGER, "
                        + EVENTS_COLUMN_HOUREND + " INTEGER, "
                        + EVENTS_COLUMN_DATE_ID + " LONG)", EVENTS_COLUMN_ID, EVENTS_COLUMN_TITLE, EVENTS_COLUMN_DESCRIPTION, EVENTS_COLUMN_TIMEZONE, EVENTS_COLUMN_HOURSTART,
                EVENTS_COLUMN_HOUREND, EVENTS_COLUMN_DATE_ID);

        try {
            // Create Database
            db.execSQL(users);
            db.execSQL(dates);
            db.execSQL(events);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
        // If you need to add a column
//        if (newVersion > oldVersion) {
//            db.execSQL("ALTER TABLE foo ADD COLUMN new_column INTEGER DEFAULT 0");
//        }
    }

    public void createNewUser(String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERS_COLUMN_NAME, name);
        values.put(USERS_COLUMN_EMAIL, email);
        db.insertWithOnConflict(TABLE_USERS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void updateUser(long id, String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERS_COLUMN_ID, id);
        values.put(USERS_COLUMN_NAME, name);
        values.put(USERS_COLUMN_EMAIL, email);
        db.update(TABLE_USERS, values, USERS_COLUMN_ID + " = ?", new String[]{id + ""});
        db.close();

    }

    public void deleteUser(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, USERS_COLUMN_ID + " = ?", new String[]{id + ""});
        db.close();
    }

    public long getLastUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id FROM users", null);
        cursor.moveToLast();
        long id = cursor.getLong(cursor.getColumnIndex("_id"));
        return id;
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS , null);
        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            list.add(new User(name, email, id));
        }
        cursor.close();
        db.close();
        return list;
    }

    public void createNewDate(String name, String date, String symbol, long user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATES_COLUMN_NAME, name);
        values.put(DATES_COLUMN_DATE, date);
        values.put(DATES_COLUMN_SYMBOL, symbol);
        values.put(DATES_COLUMN_USER_ID, user_id);
        db.insertWithOnConflict(TABLE_DATES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void updateDate(String name, String date, String symbol, long user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put(DATES_COLUMN_NAME, name);
        values.put(DATES_COLUMN_DATE, date);
        values.put(DATES_COLUMN_SYMBOL, symbol);
        values.put(DATES_COLUMN_USER_ID, user_id);
        db.close();
    }

    public void deleteDate(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATES, DATES_COLUMN_ID + " = ?", new String[]{id+""});
        db.close();
    }

    public ArrayList<Date> getAllDates(){
        ArrayList<Date> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM dates", null);
        while (cursor.moveToNext()){
            long user_id = cursor.getLong(cursor.getColumnIndex("user_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String symbol = cursor.getString(cursor.getColumnIndex("symbol"));
            long id = cursor.getLong(cursor.getColumnIndex("_id"));
            list.add(new Date(user_id, name, symbol, date, id));
        }
        cursor.close();
        db.close();
        return list;
    }

    public void createNewEvent(String title, String desc, String timezone, int hourStart, int hourEnd, long date_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENTS_COLUMN_TITLE, title);
        values.put(EVENTS_COLUMN_DESCRIPTION, desc);
        values.put(EVENTS_COLUMN_TIMEZONE, timezone);
        values.put(EVENTS_COLUMN_HOURSTART, hourStart);
        values.put(EVENTS_COLUMN_HOUREND, hourEnd);
        values.put(EVENTS_COLUMN_DATE_ID, date_id);
        db.insertWithOnConflict(TABLE_EVENTS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

}
