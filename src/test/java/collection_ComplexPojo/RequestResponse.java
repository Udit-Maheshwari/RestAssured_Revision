package collection_ComplexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResponse extends RequestBase {

    RequestResponse() {

    }

    RequestResponse(String method, List<Header> header, Body body, String description, URL URL) {
        super(method, header, body, description);
        this.URL = URL;
    }

    private URL URL;

    public URL getURL() {
        return URL;
    }

    public void setURL(URL URL) {
        this.URL = URL;
    }
}
