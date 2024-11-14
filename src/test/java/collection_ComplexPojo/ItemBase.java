package collection_ComplexPojo;

import java.util.List;

public abstract class ItemBase {

    ItemBase() {

    }

    ItemBase(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
