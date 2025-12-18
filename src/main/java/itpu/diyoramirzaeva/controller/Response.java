package itpu.diyoramirzaeva.controller;

/**
 * Result of executing a {@link Request}. The view layer prints the response string
 * and decides whether to continue the interaction loop.
 */
public interface Response {
    /**
     * Message that should be shown to the user.
     */
    String responseString();

    /**
     * Indicates if the application should terminate after this response.
     */
    boolean isOut();

    /**
     * Signals that the response describes an error.
     */
    boolean isError();
}
