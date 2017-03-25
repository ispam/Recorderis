package tech.destinum.recorderis.pojo;

import java.io.Serializable;

public class Dates implements Serializable{
    private int id;
    private String date;

    public Dates(int id, String date) {
        this.id = id;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Dates [date= " + date + ", id= " + id+"]";
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
}
