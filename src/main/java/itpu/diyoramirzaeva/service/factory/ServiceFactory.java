package itpu.diyoramirzaeva.service.factory;

import itpu.diyoramirzaeva.entity.AirConditioner;
import itpu.diyoramirzaeva.entity.Fridge;
import itpu.diyoramirzaeva.entity.Iron;
import itpu.diyoramirzaeva.service.api.ApplianceService;
import itpu.diyoramirzaeva.service.impl.AirConditionerServiceImpl;
import itpu.diyoramirzaeva.service.impl.FridgeServiceImpl;
import itpu.diyoramirzaeva.service.impl.IronServiceImpl;

import java.util.Objects;

public final class ServiceFactory {
    private static ApplianceService<AirConditioner> acService;
    private static ApplianceService<Fridge> fridgeService;
    private static ApplianceService<Iron> ironService;

    static {
        resetToDefaults();
    }

    private ServiceFactory() {}

    public static void init(ApplianceService<AirConditioner> ac,
                            ApplianceService<Fridge> fridge,
                            ApplianceService<Iron> iron) {
        acService = Objects.requireNonNull(ac, "acService");
        fridgeService = Objects.requireNonNull(fridge, "fridgeService");
        ironService = Objects.requireNonNull(iron, "ironService");
    }

    public static void resetToDefaults() {
        init(new AirConditionerServiceImpl(), new FridgeServiceImpl(), new IronServiceImpl());
    }

    public static ApplianceService<AirConditioner> airConditionerService() {
        return acService;
    }

    public static ApplianceService<Fridge> fridgeService() {
        return fridgeService;
    }

    public static ApplianceService<Iron> ironService() {
        return ironService;
    }
}
