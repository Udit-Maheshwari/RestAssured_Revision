package collection_ComplexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemResponse extends ItemBase {

    ItemResponse() {

    }

    ItemResponse(String name, List<RequestRootResponse> item) {
        super(name);
        this.item = item;
    }

    List<RequestRootResponse> item;

    public List<RequestRootResponse> getItem() {
        return item;
    }

    public void setItem(List<RequestRootResponse> item) {
        this.item = item;
    }

}
