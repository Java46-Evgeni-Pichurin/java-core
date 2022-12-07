package telran.net.common;

import java.io.*;

public class Response implements Serializable {

    @Serial
	private static final long serialVersionUID = 1L;
    public ResponseCode code;
    public Serializable responseData;

    public Response(ResponseCode code, Serializable responseData) {
        this.code = code;
        this.responseData = responseData;
    }
}