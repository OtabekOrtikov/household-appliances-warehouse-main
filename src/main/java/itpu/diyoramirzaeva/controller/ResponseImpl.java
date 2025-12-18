package itpu.diyoramirzaeva.controller;

/**
 * Basic response implementation with helpers for success, error, and exit responses.
 */
class ResponseImpl implements Response {
    private final String message;
    private final boolean out;
    private final boolean error;

    ResponseImpl(String message, boolean out, boolean error) {
        this.message = message;
        this.out = out;
        this.error = error;
    }

    static Response ok(String message) {
        return new ResponseImpl(message, false, false);
    }

    static Response error(String message) {
        return new ResponseImpl(message, false, true);
    }

    static Response exit(String message) {
        return new ResponseImpl(message, true, false);
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
}
