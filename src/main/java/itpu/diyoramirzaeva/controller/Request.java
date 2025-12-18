package itpu.diyoramirzaeva.controller;

import java.util.List;
import java.util.Map;

/**
 * Immutable representation of a user request that is passed from the view layer to the controller layer.
 */
public interface Request {
    /**
     * Command name in lower-case form, e.g. "list" or "search".
     */
    String command();

    /**
     * Ordered arguments that do not have explicit keys (e.g. category name).
     */
    List<String> arguments();

    /**
     * Named parameters parsed from key=value pairs.
     */
    Map<String, String> parameters();

    /**
     * Raw user input line for logging/debugging.
     */
    String raw();
}
