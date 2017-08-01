package tech.destinum.recorderis.pojo;

public class Event {

    public long _id, date_id;
    public String title, description, timeZone;
    public int hourStart, hourdEnd;

    public Event(long _id, long date_id, String title, String description, String timeZone, int hourStart, int hourdEnd) {
        this._id = _id;
        this.date_id = date_id;
        this.title = title;
        this.description = description;
        this.timeZone = timeZone;
        this.hourStart = hourStart;
        this.hourdEnd = hourdEnd;
    }


    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getHourStart() {
        return hourStart;
    }

    public void setHourStart(int hourStart) {
        this.hourStart = hourStart;
    }

    public int getHourdEnd() {
        return hourdEnd;
    }

    public void setHourdEnd(int hourdEnd) {
        this.hourdEnd = hourdEnd;
    }

    public long getDate_id() {
        return date_id;
    }

    public void setDate_id(long date_id) {
        this.date_id = date_id;
    }
}
