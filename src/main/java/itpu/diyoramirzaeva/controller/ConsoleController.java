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
import itpu.diyoramirzaeva.entity.Household;
import itpu.diyoramirzaeva.entity.Iron;
import itpu.diyoramirzaeva.service.api.ApplianceService;
import itpu.diyoramirzaeva.service.factory.ServiceFactory;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Command-based controller that accepts requests from the view layer.
 */
public class ConsoleController implements Controller {
    private final ApplianceService<AirConditioner> acService;
    private final ApplianceService<Fridge> fridgeService;
    private final ApplianceService<Iron> ironService;

    public ConsoleController() {
        this(ServiceFactory.airConditionerService(),
                ServiceFactory.fridgeService(),
                ServiceFactory.ironService());
    }

    public ConsoleController(ApplianceService<AirConditioner> acService,
            ApplianceService<Fridge> fridgeService,
            ApplianceService<Iron> ironService) {
        this.acService = acService;
        this.fridgeService = fridgeService;
        this.ironService = ironService;
    }

    @Override
    public Response execute(Request request) {
        String command = request.command();
        if (command.isEmpty() || "help".equals(command)) {
            return help();
        }
        return switch (command) {
            case "list" -> list(request);
            case "search" -> search(request);
            case "exit", "quit", "out" -> ResponseImpl.exit("Bye! See you soon.");
            default ->
                ResponseImpl.error("Unknown command: '" + request.raw() + "'. Type 'help' for the command list.");
        };
    }

    private Response help() {
        String msg = """
                Household appliances warehouse CLI
                Commands:
                  help                               Show this help message
                  list [all|air|fridge|iron]         Show inventory (default = all)
                  search <air|fridge|iron> [params]  Search by parameters. Examples:
                      search air heating=true noise=38.5
                      search fridge hasFreezer=true energyClass=A++
                      search iron capacity=0.35
                  exit                               Quit the application
                """;
        return ResponseImpl.ok(msg);
    }

    private Response list(Request request) {
        if (request.arguments().isEmpty() || "all".equals(request.arguments().get(0))) {
            return ResponseImpl.ok("All items:", listAll());
        }
        Category category = resolveCategory(request.arguments().get(0));
        if (category == null) {
            return ResponseImpl.error("Unknown category: " + request.arguments().get(0));
        }
        return ResponseImpl.ok(category.name() + ":", listByCategory(category));
    }

    private Response search(Request request) {
        if (request.arguments().isEmpty()) {
            return ResponseImpl.error("Specify a category to search: air, fridge, or iron.");
        }
        Category category = resolveCategory(request.arguments().get(0));
        if (category == null) {
            return ResponseImpl.error("Unknown category: " + request.arguments().get(0));
        }
        return switch (category) {
            case AIR -> searchAir(request.parameters());
            case FRIDGE -> searchFridge(request.parameters());
            case IRON -> searchIron(request.parameters());
        };
    }

    private List<Household> listAll() {
        List<Household> all = new java.util.ArrayList<>();
        all.addAll(acService.getAll());
        all.addAll(fridgeService.getAll());
        all.addAll(ironService.getAll());
        return all;
    }

    private List<? extends Household> listByCategory(Category category) {
        return switch (category) {
            case AIR -> acService.getAll();
            case FRIDGE -> fridgeService.getAll();
            case IRON -> ironService.getAll();
        };
    }

    private Response searchAir(Map<String, String> params) {
        SearchCriteria<AirConditioner> criteria = new AirConditionerCriteria();
        if (params.containsKey("heating")) {
            Boolean heating = parseBoolean(params.get("heating"));
            if (heating == null)
                return ResponseImpl.error("Invalid heating value: " + params.get("heating"));
            criteria.add(new HeatingParam(heating));
        }
        if (params.containsKey("noise") || params.containsKey("noiselevel")) {
            String value = params.getOrDefault("noise", params.get("noiselevel"));
            Double noise = parseDouble(value);
            if (noise == null)
                return ResponseImpl.error("Invalid noise value: " + value);
            criteria.add(new NoiseLevelParam(noise));
        }
        List<AirConditioner> result = acService.find(criteria);
        return ResponseImpl.ok("Search result (air conditioners):", result);
    }

    private Response searchFridge(Map<String, String> params) {
        SearchCriteria<Fridge> criteria = new FridgeCriteria();
        if (params.containsKey("hasfreezer") || params.containsKey("freezer")) {
            String value = params.getOrDefault("hasfreezer", params.get("freezer"));
            Boolean hasFreezer = parseBoolean(value);
            if (hasFreezer == null)
                return ResponseImpl.error("Invalid hasFreezer value: " + value);
            criteria.add(new HasFreezerParam(hasFreezer));
        }
        if (params.containsKey("energyclass") || params.containsKey("class")) {
            String value = params.getOrDefault("energyclass", params.get("class"));
            if (value != null && !value.isBlank()) {
                criteria.add(new EnergyClassParam(value.trim()));
            }
        }
        List<Fridge> result = fridgeService.find(criteria);
        return ResponseImpl.ok("Search result (fridges):", result);
    }

    private Response searchIron(Map<String, String> params) {
        SearchCriteria<Iron> criteria = new IronCriteria();
        if (params.containsKey("capacity") || params.containsKey("watertankcapacity")) {
            String value = params.getOrDefault("capacity", params.get("watertankcapacity"));
            Double capacity = parseDouble(value);
            if (capacity == null)
                return ResponseImpl.error("Invalid capacity value: " + value);
            criteria.add(new WaterTankCapacityParam(capacity));
        }
        List<Iron> result = ironService.find(criteria);
        return ResponseImpl.ok("Search result (irons):", result);
    }

    private Boolean parseBoolean(String value) {
        if (value == null)
            return null;
        String v = value.trim().toLowerCase(Locale.ROOT);
        if (v.isEmpty())
            return null;
        if (v.equals("true") || v.equals("t") || v.equals("yes") || v.equals("y") || v.equals("1"))
            return true;
        if (v.equals("false") || v.equals("f") || v.equals("no") || v.equals("n") || v.equals("0"))
            return false;
        return null;
    }

    private Double parseDouble(String value) {
        if (value == null || value.trim().isEmpty())
            return null;
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private Category resolveCategory(String token) {
        return switch (token.toLowerCase(Locale.ROOT)) {
            case "1", "air", "ac", "airconditioner", "air_conditioner", "air-conditioner" -> Category.AIR;
            case "2", "fridge", "refrigerator" -> Category.FRIDGE;
            case "3", "iron" -> Category.IRON;
            default -> null;
        };
    }

    private enum Category {
        AIR, FRIDGE, IRON
    }
}
