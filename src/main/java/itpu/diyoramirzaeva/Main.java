package itpu.diyoramirzaeva;

import itpu.diyoramirzaeva.config.Config;
import itpu.diyoramirzaeva.config.PropertiesConfigImpl;
import itpu.diyoramirzaeva.view.ConsoleViewImpl;
import itpu.diyoramirzaeva.view.View;
import itpu.diyoramirzaeva.view.ViewFactory;

public class Main {
    public static void main(String[] args) {
        String propertiesName = args.length == 0 ? "app" : args[0];
        // fallback view to show crash message if config fails
        View view = new ConsoleViewImpl();
        try {
            Config config = new PropertiesConfigImpl(propertiesName);
            config.init();
            view = ViewFactory.getInstance();
            view.start();
        } catch (RuntimeException e) {
            view.crash();
            e.printStackTrace(System.err);
        }
    }
}
