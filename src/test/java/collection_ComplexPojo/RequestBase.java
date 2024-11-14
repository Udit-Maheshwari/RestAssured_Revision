package collection_ComplexPojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public abstract class RequestBase {

    RequestBase() {

    }

    RequestBase(String method, List<Header> header, Body body, String description) {
        this.method = method;
        this.header = header;
        this.body = body;
        this.description = description;
    }

    private String method;
    private List<Header> header;
    private Body body;
    private String description;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Header> getHeader() {
        return header;
    }

    public void setHeader(List<Header> header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
