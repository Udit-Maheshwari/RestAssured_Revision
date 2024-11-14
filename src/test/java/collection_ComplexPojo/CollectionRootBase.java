package collection_ComplexPojo;

public abstract class CollectionRootBase {

    CollectionRootBase() {

    }

    CollectionRootBase(CollectionBase collection) {
        this.collection = collection;
    }

    private CollectionBase collection;

    public CollectionBase getCollection() {
        return collection;
    }

    public void setCollection(CollectionBase collection) {
        this.collection = collection;
    }
}
