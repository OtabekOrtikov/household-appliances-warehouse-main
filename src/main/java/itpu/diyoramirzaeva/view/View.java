package itpu.diyoramirzaeva.view;

/**
 * Public API of the view layer.
 */
public interface View {
    /**
     * Starts the user interaction loop.
     */
    void start();

    /**
     * Shows a readable message to the user when an unexpected error occurs.
     */
    void crash();
}
