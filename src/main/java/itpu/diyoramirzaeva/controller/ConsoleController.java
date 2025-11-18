package itpu.diyoramirzaeva.controller;

import itpu.diyoramirzaeva.dao.parameters.airconditioner.HeatingParam;
import itpu.diyoramirzaeva.dao.parameters.airconditioner.NoiseLevelParam;
import itpu.diyoramirzaeva.dao.parameters.fridge.EnergyClassParam;
import itpu.diyoramirzaeva.dao.parameters.fridge.HasFreezerParam;
import itpu.diyoramirzaeva.dao.parameters.iron.WaterTankCapacityParam;
import itpu.diyoramirzaeva.dao.searchCriteria.AirConditionerCriteria;
import itpu.diyoramirzaeva.dao.searchCriteria.FridgeCriteria;
import itpu.diyoramirzaeva.dao.searchCriteria.IronCriteria;
import itpu.diyoramirzaeva.dao.searchCriteria.SearchCriteria;
import itpu.diyoramirzaeva.entity.AirConditioner;
import itpu.diyoramirzaeva.entity.Fridge;
import itpu.diyoramirzaeva.entity.Iron;
import itpu.diyoramirzaeva.service.api.ApplianceService;
import itpu.diyoramirzaeva.service.factory.ServiceFactory;
import itpu.diyoramirzaeva.view.ConsoleView;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleController {
    private final ConsoleView view;
    private final Scanner scanner;

    private final ApplianceService<AirConditioner> acService;
    private final ApplianceService<Fridge> fridgeService;
    private final ApplianceService<Iron> ironService;

    public ConsoleController() {
        this(new ConsoleView(), new Scanner(System.in),
                ServiceFactory.airConditionerService(),
                ServiceFactory.fridgeService(),
                ServiceFactory.ironService());
    }

    public ConsoleController(ConsoleView view,
                             Scanner scanner,
                             ApplianceService<AirConditioner> acService,
                             ApplianceService<Fridge> fridgeService,
                             ApplianceService<Iron> ironService) {
        this.view = view;
        this.scanner = scanner;
        this.acService = acService;
        this.fridgeService = fridgeService;
        this.ironService = ironService;
    }

    public void run() {
        while (true) {
            view.showMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> showAllProducts();
                case "2" -> showByCategory();
                case "3" -> search();
                case "0", "exit", "quit" -> {
                    view.print("Exiting...");
                    return;
                }
                default -> view.printError("Unknown command. Please try again.");
            }
        }
    }

    private void showAllProducts() {
        view.print("=== Air Conditioners ===");
        view.printList(acService.getAll());
        view.print("=== Fridges ===");
        view.printList(fridgeService.getAll());
        view.print("=== Irons ===");
        view.printList(ironService.getAll());
    }

    private void showByCategory() {
        view.showCategoryMenu();
        String c = scanner.nextLine().trim();
        switch (c) {
            case "1" -> view.printList(acService.getAll());
            case "2" -> view.printList(fridgeService.getAll());
            case "3" -> view.printList(ironService.getAll());
            default -> view.printError("Unknown category.");
        }
    }

    private void search() {
        view.showCategoryMenu();
        String c = scanner.nextLine().trim();
        switch (c) {
            case "1" -> searchAirConditioners();
            case "2" -> searchFridges();
            case "3" -> searchIrons();
            default -> view.printError("Unknown category.");
        }
    }

    private void searchAirConditioners() {
        SearchCriteria<AirConditioner> criteria = new AirConditionerCriteria();

    view.print("Search parameters (empty = skip):");
    Boolean heating = askBoolean("Heating (true/false): ");
        if (heating != null) criteria.add(new HeatingParam(heating));

    Double noise = askDouble("Noise level (e.g., 38.5): ");
        if (noise != null) criteria.add(new NoiseLevelParam(noise));

        List<AirConditioner> result = acService.find(criteria);
        view.printList(result);
    }

    private void searchFridges() {
        SearchCriteria<Fridge> criteria = new FridgeCriteria();

    view.print("Search parameters (empty = skip):");
    Boolean hasFreezer = askBoolean("Has freezer (true/false): ");
        if (hasFreezer != null) criteria.add(new HasFreezerParam(hasFreezer));

        String energyClass = askString("Energy class (A+, A++, A+++ ...): ");
        if (energyClass != null) criteria.add(new EnergyClassParam(energyClass));

        List<Fridge> result = fridgeService.find(criteria);
        view.printList(result);
    }

    private void searchIrons() {
        SearchCriteria<Iron> criteria = new IronCriteria();

    view.print("Search parameters (empty = skip):");
    Double capacity = askDouble("Water tank capacity (e.g., 0.35): ");
        if (capacity != null) criteria.add(new WaterTankCapacityParam(capacity));

        List<Iron> result = ironService.find(criteria);
        view.printList(result);
    }

    private String askString(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine().trim();
        if (s.isEmpty()) return null;
        return s;
    }

    private Boolean askBoolean(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        if (s.isEmpty()) return null;
        if (s.equals("true") || s.equals("t") || s.equals("yes") || s.equals("y") || s.equals("1")) return true;
        if (s.equals("false") || s.equals("f") || s.equals("no") || s.equals("n") || s.equals("0")) return false;
        view.printError("Invalid value, skipping parameter.");
        return null;
    }

    private Double askDouble(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine().trim();
        if (s.isEmpty()) return null;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            view.printError("Invalid number, skipping parameter.");
            return null;
        }
    }
}
