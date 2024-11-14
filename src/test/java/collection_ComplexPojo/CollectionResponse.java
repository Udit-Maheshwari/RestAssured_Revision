package collection_ComplexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionResponse extends CollectionBase{

    CollectionResponse() {

    }

    CollectionResponse(Info info, List<ItemResponse> item) {
        super(info);
        this.item = item;
    }

    private List<ItemResponse> item;

    public List<ItemResponse> getItem() {
        return item;
    }

    public void setItem(List<ItemResponse> item) {
        this.item = item;
    }

}
