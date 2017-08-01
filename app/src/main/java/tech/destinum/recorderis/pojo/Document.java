package tech.destinum.recorderis.pojo;

public class Document {
    private int mName;
    private int id;

    public int getName() {
        return mName;
    }

    public void setName(int name) {
        mName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Document(int name, int id) {
        mName = name;
        id = id;
    }
}
