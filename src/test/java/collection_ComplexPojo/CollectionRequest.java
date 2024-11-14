package collection_ComplexPojo;

import java.util.List;

public class CollectionRequest extends CollectionBase {

    CollectionRequest() {

    }

    CollectionRequest(Info info, List<ItemRequest> item) {
        super(info);
        this.item = item;
    }

    private List<ItemRequest> item;

    public List<ItemRequest> getItem() {
        return item;
    }

    public void setItem(List<ItemRequest> item) {
        this.item = item;
    }

}
