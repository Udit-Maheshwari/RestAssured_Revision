package collection_ComplexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRootResponse extends RequestRootBase {

    RequestRootResponse() {

    }

    RequestRootResponse(String name, RequestResponse request) {
        super(name);
        this.request = request;
    }

    private RequestResponse request;

    public RequestResponse getRequest() {
        return request;
    }

    public void setRequest(RequestResponse request) {
        this.request = request;
    }
}
