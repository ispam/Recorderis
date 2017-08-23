package tech.destinum.recorderis.pojo;

public class Document {
    private String mName, symbol;
    private int id;

    public Document(String name, String symbol, int id) {
        mName = name;
        this.symbol = symbol;
        this.id = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
