package itpu.diyoramirzaeva.dao.reader;

import itpu.diyoramirzaeva.dao.exception.DaoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCSVReader<T> {
    private final String delimiter;
    private final boolean hasHeader;

    protected AbstractCSVReader() {
        this(",", true);
    }

    protected AbstractCSVReader(String delimiter, boolean hasHeader) {
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
    }

    protected abstract T parse(String[] fields);

    protected List<T> readFromResource(String resourcePath) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (is == null) {
            throw new DaoException("Resource not found: " + resourcePath);
        }
        List<T> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            boolean skipHeader = hasHeader;
            while ((line = br.readLine()) != null) {
                if (skipHeader) { // skip the first line as header once
                    skipHeader = false;
                    continue;
                }
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] tokens = line.split(delimiter);
                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim();
                }
                try {
                    result.add(parse(tokens));
                } catch (Exception parseEx) {
                    throw new DaoException("Failed to parse CSV line: '" + line + "' in resource: " + resourcePath, parseEx);
                }
            }
        } catch (IOException e) {
            throw new DaoException("Failed to read resource: " + resourcePath, e);
        }
        return result;
    }
}
