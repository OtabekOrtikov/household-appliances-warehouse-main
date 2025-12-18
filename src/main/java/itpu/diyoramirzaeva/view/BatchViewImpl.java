package itpu.diyoramirzaeva.view;

import itpu.diyoramirzaeva.controller.Controller;
import itpu.diyoramirzaeva.controller.Request;
import itpu.diyoramirzaeva.controller.RequestFactory;
import itpu.diyoramirzaeva.controller.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Batch mode implementation: reads commands from a file and executes them sequentially.
 */
public class BatchViewImpl implements View {
    private final Controller controller;
    private final String commandsSource;
    private final UserInputValidator validator;

    public BatchViewImpl(String commandsSource) {
        this(itpu.diyoramirzaeva.controller.ControllerFactory.getInstance(), commandsSource, new UserInputValidator());
    }

    public BatchViewImpl(Controller controller, String commandsSource, UserInputValidator validator) {
        this.controller = controller;
        this.commandsSource = commandsSource;
        this.validator = validator;
    }

    @Override
    public void start() {
        List<String> commands = loadCommands(commandsSource);
        for (String rawLine : commands) {
            String line = rawLine == null ? "" : rawLine.trim();
            if (line.isEmpty()) {
                continue;
            }
            UserInputValidator.ValidationResult result = validator.validate(line);
            if (!result.success()) {
                System.out.println("Skipping line: " + line);
                System.out.println("  Reason: " + result.message());
                continue;
            }
            System.out.println("> " + line);
            Request request = RequestFactory.fromUserInput(line);
            Response response = controller.execute(request);
            System.out.println(response.responseString());
            if (response.isOut()) {
                break;
            }
        }
    }

    @Override
    public void crash() {
        System.out.println("Sorry, something went wrong during batch execution...");
    }

    private List<String> loadCommands(String source) {
        List<String> lines = tryLoadFromClasspath(source);
        if (!lines.isEmpty()) {
            return lines;
        }
        try {
            return Files.readAllLines(Path.of(source), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read batch commands from " + source, e);
        }
    }

    private List<String> tryLoadFromClasspath(String source) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(source)) {
            if (is == null) return List.of();
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
            return lines;
        } catch (IOException e) {
            return List.of();
        }
    }
}
