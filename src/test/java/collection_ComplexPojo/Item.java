package collection_ComplexPojo;

import java.util.List;

public class Item {

    private String name;
    List<RequestRoot> item;

    public List<RequestRoot> getItem() {
        return item;
    }

    public void setItem(List<RequestRoot> item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
