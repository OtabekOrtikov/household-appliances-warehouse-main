package itpu.diyoramirzaeva.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleViewTest {

    @Test
    void emptyInputIsInvalid() {
        UserInputValidator validator = new UserInputValidator();
        UserInputValidator.ValidationResult result = validator.validate("   ");
        assertFalse(result.success());
        assertTrue(result.message().toLowerCase().contains("empty"));
    }

    @Test
    void searchWithoutCategoryIsInvalid() {
        UserInputValidator validator = new UserInputValidator();
        UserInputValidator.ValidationResult result = validator.validate("search   ");
        assertFalse(result.success());
        assertTrue(result.message().toLowerCase().contains("category"));
    }

    @Test
    void validSearchPasses() {
        UserInputValidator validator = new UserInputValidator();
        UserInputValidator.ValidationResult result = validator.validate("search iron capacity=0.5");
        assertTrue(result.success());
    }
}
