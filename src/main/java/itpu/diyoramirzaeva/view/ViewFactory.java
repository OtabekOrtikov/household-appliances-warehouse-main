package itpu.diyoramirzaeva.view;

/**
 * Singleton holder for the configured view implementation.
 */
public final class ViewFactory {
    private static View view;

    private ViewFactory() {}

    public static void init(View instance) {
        view = instance;
    }

    public static View getInstance() {
        if (view == null) {
            throw new IllegalStateException("View is not initialized");
        }
        return view;
    }
}
