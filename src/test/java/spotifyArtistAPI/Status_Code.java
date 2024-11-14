package spotifyArtistAPI;

public enum Status_Code {

    CODE_200(200, "");

    public final int code;
    public final String msg;

    Status_Code(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
