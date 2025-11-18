package itpu.diyoramirzaeva.view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleViewTest {
    @Test
    void showMainMenuIsEnglish() {
        ConsoleView view = new ConsoleView();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        try {
            System.setOut(new PrintStream(out));
            view.showMainMenu();
        } finally {
            System.setOut(old);
        }
        String s = out.toString();
        assertTrue(s.contains("Household Warehouse - Menu"));
        assertTrue(s.contains("Show all products"));
        assertTrue(s.contains("Exit"));
    }

    @Test
    void showCategoryMenuIsEnglish() {
        ConsoleView view = new ConsoleView();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        try {
            System.setOut(new PrintStream(out));
            view.showCategoryMenu();
        } finally {
            System.setOut(old);
        }
        String s = out.toString();
        assertTrue(s.contains("Categories"));
        assertTrue(s.contains("Air Conditioners"));
        assertTrue(s.contains("Irons"));
    }
}
