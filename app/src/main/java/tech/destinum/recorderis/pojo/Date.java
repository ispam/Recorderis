package tech.destinum.recorderis.pojo;

public class Date {

    private long user_id;
    private String name, symbol, date;
    private int id;

    public Date(long user_id, String name, String symbol, String date, int id) {
        this.user_id = user_id;
        this.name = name;
        this.symbol = symbol;
        this.date = date;
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
