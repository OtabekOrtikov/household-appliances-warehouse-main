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
            } while (!response.isOut());
        }
    }

    @Override
    public void crash() {
        final String msg = "Sorry, something went wrong...";
        System.out.println(msg);
    }
}
