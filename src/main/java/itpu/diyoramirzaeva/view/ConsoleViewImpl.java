package itpu.diyoramirzaeva.view;

import itpu.diyoramirzaeva.controller.Controller;
import itpu.diyoramirzaeva.controller.Request;
import itpu.diyoramirzaeva.controller.RequestFactory;
import itpu.diyoramirzaeva.controller.Response;

import java.util.Scanner;

/**
 * Console-based implementation of the view layer.
 */
public class ConsoleViewImpl implements View {
    private final Controller controller;
    private final UserInputValidator validator;

    public ConsoleViewImpl() {
        this(null);
    }

    public ConsoleViewImpl(Controller controller) {
        this(controller, new UserInputValidator());
    }

    public ConsoleViewImpl(Controller controller, UserInputValidator validator) {
        this.controller = controller;
        this.validator = validator;
    }

    @Override
    public void start() {
        Controller activeController = controller == null
                ? itpu.diyoramirzaeva.controller.ControllerFactory.getInstance()
                : controller;
        try (Scanner scanner = new Scanner(System.in)) {
            // auto-display help on startup
            Response response = activeController.execute(RequestFactory.fromUserInput("help"));
            System.out.println(response.responseString());
            do {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
                    break;
                }
                String line = scanner.nextLine();
                UserInputValidator.ValidationResult result = validator.validate(line);
                if (!result.success()) {
                    System.out.println(result.message());
                    continue;
                }
                Request request = RequestFactory.fromUserInput(line);
                response = activeController.execute(request);
                System.out.println(response.responseString());
                if (response.getData() != null) {
                    printData(response.getData());
                }
            } while (!response.isOut());
        }
    }

    @Override
    public void crash() {
        final String msg = "Sorry, something went wrong...";
        System.out.println(msg);
    }

    private void printData(Object data) {
        if (data instanceof java.util.List<?> list) {
            if (list.isEmpty()) {
                System.out.println("  (nothing found)");
                return;
            }
            for (Object item : list) {
                if (item instanceof itpu.diyoramirzaeva.entity.Household h) {
                    System.out.println(formatHousehold(h));
                } else {
                    System.out.println(item);
                }
            }
        } else {
            System.out.println(data);
        }
    }

    private String formatHousehold(itpu.diyoramirzaeva.entity.Household h) {
        // Example: "Laptop: Huawei MateBook | Price: 45000 | Weight: 1.8"
        // We need to check specific types to show relevant fields, or just improve
        // generic toString
        // For now, let's make a generic improved format
        return String.format("  - %s | Price: %.2f", h.toString(), h.getPrice());

    }
}
