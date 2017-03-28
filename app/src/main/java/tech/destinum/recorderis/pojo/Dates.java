package tech.destinum.recorderis.pojo;

import android.os.Parcel;
import android.os.Parcelable;


public class Dates implements Parcelable{
    private int id;
    private String date;

    public Dates(int id, String date) {
        this.id = id;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.date);
    }

    protected Dates(Parcel in) {
        this.id = in.readInt();
        this.date = in.readString();
    }

    public static final Creator<Dates> CREATOR = new Creator<Dates>() {
        @Override
        public Dates createFromParcel(Parcel source) {
            return new Dates(source);
        }

        @Override
        public Dates[] newArray(int size) {
            return new Dates[size];
        }
    };
}
