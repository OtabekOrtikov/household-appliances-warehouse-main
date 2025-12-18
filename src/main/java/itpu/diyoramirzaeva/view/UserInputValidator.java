package itpu.diyoramirzaeva.view;

import java.util.Locale;
import java.util.Set;

/**
 * Performs lightweight client-side validation of user input before sending it to the controller layer.
 */
public class UserInputValidator {
    private static final Set<String> SUPPORTED_COMMANDS = Set.of("help", "list", "search", "exit", "quit", "out");

    public ValidationResult validate(String line) {
        if (line == null || line.trim().isEmpty()) {
            return ValidationResult.invalid("Command is empty. Type 'help' for available commands.");
        }
        String trimmed = line.trim();
        String[] tokens = trimmed.split("\\s+");
        String commandToken = tokens[0];
        if (commandToken.contains("=")) {
            return ValidationResult.invalid("Command must not contain '='. Example: search air heating=true");
        }
        String command = commandToken.toLowerCase(Locale.ROOT);
        if (!SUPPORTED_COMMANDS.contains(command)) {
            return ValidationResult.invalid("Unknown command: " + command + ". Use help/list/search/exit.");
        }
        if ("search".equals(command)) {
            if (tokens.length < 2) {
                return ValidationResult.invalid("Search requires a category: search air|fridge|iron [params]");
            }
            String category = tokens[1].toLowerCase(Locale.ROOT);
            if (!isKnownCategory(category)) {
                return ValidationResult.invalid("Unknown category: " + category + ". Use air|fridge|iron.");
            }
        }
        if ("list".equals(command) && tokens.length >= 2) {
            String category = tokens[1].toLowerCase(Locale.ROOT);
            if (!"all".equals(category) && !isKnownCategory(category)) {
                return ValidationResult.invalid("Unknown category: " + category + ". Use all|air|fridge|iron.");
            }
        }
        // validate key=value pairs
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            int eqIdx = token.indexOf('=');
            if (eqIdx == 0 || eqIdx == token.length() - 1) {
                return ValidationResult.invalid("Parameter must be in key=value format. Bad token: " + token);
            }
        }
        return ValidationResult.valid();
    }

    private boolean isKnownCategory(String token) {
        return switch (token) {
            case "air", "ac", "airconditioner", "air_conditioner", "air-conditioner",
                    "fridge", "refrigerator", "iron" -> true;
            default -> false;
        };
    }

    public record ValidationResult(boolean success, String message) {
        public static ValidationResult valid() {
            return new ValidationResult(true, "");
        }

        public static ValidationResult invalid(String message) {
            return new ValidationResult(false, message);
        }
    }
}
