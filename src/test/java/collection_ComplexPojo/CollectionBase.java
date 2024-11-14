package collection_ComplexPojo;

import java.util.List;

public abstract class CollectionBase {

    CollectionBase() {

    }

    CollectionBase(Info info) {
        this.info = info;
    }

   private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
