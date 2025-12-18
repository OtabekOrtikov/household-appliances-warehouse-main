package itpu.diyoramirzaeva.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Simple request implementation that splits user input into a command, positional arguments,
 * and key/value parameters separated by '='.
 */
class RequestImpl implements Request {
    private final String raw;
    private final String command;
    private final List<String> arguments;
    private final Map<String, String> parameters;

    RequestImpl(String raw, String command, List<String> arguments, Map<String, String> parameters) {
        this.raw = raw;
        this.command = command;
        this.arguments = List.copyOf(arguments);
        this.parameters = Map.copyOf(parameters);
    }

    static Request fromLine(String line) {
        if (line == null) {
            return new RequestImpl("", "", List.of(), Map.of());
        }
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return new RequestImpl("", "", List.of(), Map.of());
        }
        String[] tokens = trimmed.split("\\s+");
        String command = tokens[0].toLowerCase(Locale.ROOT);
        List<String> args = new ArrayList<>();
        Map<String, String> params = new LinkedHashMap<>();
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            int eqIdx = token.indexOf('=');
            if (eqIdx > 0 && eqIdx < token.length() - 1) {
                String key = token.substring(0, eqIdx).toLowerCase(Locale.ROOT);
                String value = token.substring(eqIdx + 1);
                params.put(key, value);
            } else {
                args.add(token.toLowerCase(Locale.ROOT));
            }
        }
        return new RequestImpl(trimmed, command, args, params);
    }

    @Override
    public String command() {
        return command;
    }

    @Override
    public List<String> arguments() {
        return arguments;
    }

    @Override
    public Map<String, String> parameters() {
        return parameters;
    }

    @Override
    public String raw() {
        return raw;
    }
}
