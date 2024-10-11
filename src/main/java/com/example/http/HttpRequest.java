package com.example.http;

public class HttpRequest extends HttpMessage {

    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    // Add an appropriate access modifier like 'public' or 'protected'
    public HttpRequest() {
    }

    public HttpMethod getMethod() {
        return method;
    }

    void setMethod(String methodName) {
        for (HttpMethod method : HttpMethod.values()) {
            if (methodName.equals(method.name())) {
                this.method = method;
                return;
            }
        }
        throw new HttpParsingException(
            HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED
        );
    }
}
