package itpu.diyoramirzaeva.controller;

import itpu.diyoramirzaeva.entity.AirConditioner;
import itpu.diyoramirzaeva.entity.Fridge;
import itpu.diyoramirzaeva.entity.Iron;
import itpu.diyoramirzaeva.service.api.ApplianceService;
import itpu.diyoramirzaeva.service.factory.ServiceFactory;
import itpu.diyoramirzaeva.view.ConsoleView;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleControllerTest {

    @Test
    void menuShowAllThenExit() {
        String input = "1\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(in);
        ConsoleView view = new ConsoleView();
        ApplianceService<AirConditioner> ac = ServiceFactory.airConditionerService();
        ApplianceService<Fridge> fr = ServiceFactory.fridgeService();
        ApplianceService<Iron> ir = ServiceFactory.ironService();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        try {
            System.setOut(new PrintStream(out));
            new ConsoleController(view, scanner, ac, fr, ir).run();
        } finally {
            System.setOut(old);
        }
        String s = out.toString();
        assertTrue(s.contains("Air Conditioners"));
        assertTrue(s.contains("Fridges"));
        assertTrue(s.contains("Irons"));
        assertTrue(s.contains("Exiting"));
    }

    @Test
    void menuSearchAcByHeatingThenExit() {
        // 3 -> search, 1 -> AC category, 'true' heating, '' noise skip, 0 -> exit
        String input = "3\n1\ntrue\n\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(in);
        ConsoleView view = new ConsoleView();
        ApplianceService<AirConditioner> ac = ServiceFactory.airConditionerService();
        ApplianceService<Fridge> fr = ServiceFactory.fridgeService();
        ApplianceService<Iron> ir = ServiceFactory.ironService();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        try {
            System.setOut(new PrintStream(out));
            new ConsoleController(view, scanner, ac, fr, ir).run();
        } finally {
            System.setOut(old);
        }
        String s = out.toString();
        assertTrue(s.contains("Search parameters"));
        assertTrue(s.contains("Air conditioner"));
        assertTrue(s.contains("Exiting"));
    }
}
