package collection_ComplexPojo;

public class RequestRootRequest extends RequestRootBase {

    RequestRootRequest() {

    }

    RequestRootRequest(String name, RequestRequest request) {
        super(name);
        this.request = request;
    }

    private RequestRequest request;

    public RequestRequest getRequest() {
        return request;
    }

    public void setRequest(RequestRequest request) {
        this.request = request;
    }
}
