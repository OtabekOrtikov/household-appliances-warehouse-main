package itpu.diyoramirzaeva.controller;

/**
 * Basic response implementation with helpers for success, error, and exit
 * responses.
 */
class ResponseImpl implements Response {
    private final String message;
    private final boolean out;
    private final boolean error;
    private final Object data;

    ResponseImpl(String message, boolean out, boolean error, Object data) {
        this.message = message;
        this.out = out;
        this.error = error;
        this.data = data;
    }

    static Response ok(String message) {
        return new ResponseImpl(message, false, false, null);
    }

    static Response ok(String message, Object data) {
        return new ResponseImpl(message, false, false, data);
    }

    static Response error(String message) {
        return new ResponseImpl(message, false, true, null);
    }

    static Response exit(String message) {
        return new ResponseImpl(message, true, false, null);
    }

    @Override
    public String responseString() {
        return message;
    }

    @Override
    public boolean isOut() {
        return out;
    }

    @Override
    public boolean isError() {
        return error;
    }

    @Override
    public Object getData() {
        return data;
    }
}
