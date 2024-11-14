package collection_ComplexPojo;

import java.util.List;

public class ItemRequest extends ItemBase {

    ItemRequest() {

    }

    ItemRequest(String name, List<RequestRootRequest> item) {
        super(name);
        this.item = item;
    }

    List<RequestRootRequest> item;

    public List<RequestRootRequest> getItem() {
        return item;
    }

    public void setItem(List<RequestRootRequest> item) {
        this.item = item;
    }

}
