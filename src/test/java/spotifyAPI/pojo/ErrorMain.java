package spotifyAPI.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMain {

    private ErrorNested error;

    public ErrorNested getError() {
        return error;
    }

    public void setError(ErrorNested error) {
        this.error = error;
    }
}
