package collection_ComplexPojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RequestRequest extends RequestBase{

    RequestRequest() {

    }

    RequestRequest(String method, List<Header> header, Body body, String description, String URL) {
        super(method, header, body, description);
        this.URL = URL;
    }

    private String URL;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
