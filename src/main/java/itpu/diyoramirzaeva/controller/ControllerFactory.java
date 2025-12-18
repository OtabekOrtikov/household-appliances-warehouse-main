package itpu.diyoramirzaeva.controller;

/**
 * Singleton holder for the controller implementation.
 */
public final class ControllerFactory {
    private static Controller controller;

    private ControllerFactory() {}

    public static void init(Controller instance) {
        controller = instance;
    }

    public static Controller getInstance() {
        if (controller == null) {
            throw new IllegalStateException("Controller is not initialized");
        }
        return controller;
    }
}
