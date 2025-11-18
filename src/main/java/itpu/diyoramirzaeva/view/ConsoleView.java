package itpu.diyoramirzaeva.view;

import java.util.List;

public class ConsoleView {
    public void showMainMenu() {
        System.out.println("==============================");
        System.out.println("Household Warehouse - Menu");
        System.out.println("1) Show all products");
        System.out.println("2) Show products by category");
        System.out.println("3) Search by parameters");
        System.out.println("0) Exit");
        System.out.println("==============================");
        System.out.print("Choice: ");
    }

    public void showCategoryMenu() {
        System.out.println("Categories:");
        System.out.println("1) Air Conditioners");
        System.out.println("2) Fridges");
        System.out.println("3) Irons");
        System.out.print("Select category: ");
    }

    public void print(String msg) {
        System.out.println(msg);
    }

    public void printError(String msg) {
        System.err.println(msg);
    }

    public void printList(List<?> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("Nothing found.");
            return;
        }
        items.forEach(System.out::println);
    }
}
