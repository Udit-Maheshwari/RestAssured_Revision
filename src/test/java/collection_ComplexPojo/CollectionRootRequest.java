package collection_ComplexPojo;

public class CollectionRootRequest extends CollectionRootBase {

    CollectionRootRequest() {

    }

    CollectionRootRequest(CollectionRequest collection) {
        this.collection = collection;
    }

    private CollectionRequest collection;

    public CollectionRequest getCollection() {
        return collection;
    }

    public void setCollection(CollectionRequest collection) {
        this.collection = collection;
    }
}
