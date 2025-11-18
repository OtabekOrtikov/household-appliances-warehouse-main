package itpu.diyoramirzaeva.service.factory;

import itpu.diyoramirzaeva.entity.AirConditioner;
import itpu.diyoramirzaeva.entity.Fridge;
import itpu.diyoramirzaeva.entity.Iron;
import itpu.diyoramirzaeva.service.api.ApplianceService;
import itpu.diyoramirzaeva.service.impl.AirConditionerServiceImpl;
import itpu.diyoramirzaeva.service.impl.FridgeServiceImpl;
import itpu.diyoramirzaeva.service.impl.IronServiceImpl;

public final class ServiceFactory {
    private static final ApplianceService<AirConditioner> AC_SERVICE = new AirConditionerServiceImpl();
    private static final ApplianceService<Fridge> FRIDGE_SERVICE = new FridgeServiceImpl();
    private static final ApplianceService<Iron> IRON_SERVICE = new IronServiceImpl();

    private ServiceFactory() {}

    public static ApplianceService<AirConditioner> airConditionerService() {
        return AC_SERVICE;
    }

    public static ApplianceService<Fridge> fridgeService() {
        return FRIDGE_SERVICE;
    }

    public static ApplianceService<Iron> ironService() {
        return IRON_SERVICE;
    }
}
