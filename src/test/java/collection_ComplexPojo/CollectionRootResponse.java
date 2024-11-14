package collection_ComplexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRootResponse extends CollectionRootBase {

    CollectionRootResponse() {

    }

    CollectionRootResponse(CollectionResponse collection) {
        this.collection = collection;
    }
    private CollectionResponse collection;

    public CollectionResponse getCollection() {
        return collection;
    }

    public void setCollection(CollectionResponse collection) {
        this.collection = collection;
    }
}
