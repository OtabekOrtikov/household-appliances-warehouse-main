package itpu.diyoramirzaeva.controller;

/**
 * Factory for creating {@link Request} instances from user input.
 */
public final class RequestFactory {
    private RequestFactory() {}

    public static Request fromUserInput(String line) {
        return RequestImpl.fromLine(line);
    }
}
