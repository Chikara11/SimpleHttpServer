package com.example.http;

public enum  HttpStatusCode {

    /* ---CLIENT ERROS--- */
    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad Request"),
    CLIENT_ERROR_401_METHOD_NOT_ALLOWED(401, "Method Not Allowed"),
    CLIENT_ERROR_414_URI_TOO_LONG(414, "URI too long "),
    /* ---SERVER ERROS--- */
    SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500, "Internal server error"),
    SERVER_ERROR_501_NOT_IMPLEMENTED(501, "Not implemented")
    ;

    


    public final int STATUS_CODE;
    public final String MESSAGE;

    private HttpStatusCode(int STATUS_CODE,String MESSAGE) {
        this.STATUS_CODE = 0;
        this.MESSAGE = null;
    }

    

}
