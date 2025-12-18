package itpu.diyoramirzaeva;

import itpu.diyoramirzaeva.config.Config;
import itpu.diyoramirzaeva.config.PropertiesConfigImpl;
import itpu.diyoramirzaeva.view.ConsoleViewImpl;
import itpu.diyoramirzaeva.view.View;
import itpu.diyoramirzaeva.view.ViewFactory;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("gui")) {
            launchGUI();
        } else {
            launchConsole();
        }
    }

    private static void launchConsole() {
        View view = new ConsoleViewImpl();
        try {
            Config config = new PropertiesConfigImpl("app");
            config.init();
            ViewFactory.init(view);
            view.start();
        } catch (RuntimeException e) {
            view.crash();
            e.printStackTrace(System.err);
        }
    }

    private static void launchGUI() {
        System.out.println("GUI is not implemented yet. Please run with 'console' argument.");
    }
}
