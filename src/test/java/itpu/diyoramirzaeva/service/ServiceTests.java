package itpu.diyoramirzaeva.service;

import itpu.diyoramirzaeva.dao.parameters.airconditioner.HeatingParam;
import itpu.diyoramirzaeva.dao.parameters.airconditioner.NoiseLevelParam;
import itpu.diyoramirzaeva.dao.parameters.fridge.EnergyClassParam;
import itpu.diyoramirzaeva.dao.parameters.fridge.HasFreezerParam;
import itpu.diyoramirzaeva.dao.parameters.iron.WaterTankCapacityParam;
import itpu.diyoramirzaeva.dao.searchCriteria.AirConditionerCriteria;
import itpu.diyoramirzaeva.dao.searchCriteria.FridgeCriteria;
import itpu.diyoramirzaeva.dao.searchCriteria.IronCriteria;
import itpu.diyoramirzaeva.entity.AirConditioner;
import itpu.diyoramirzaeva.entity.Fridge;
import itpu.diyoramirzaeva.entity.Iron;
import itpu.diyoramirzaeva.service.factory.ServiceFactory;
import itpu.diyoramirzaeva.service.api.ApplianceService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTests {

    @Test
    void airConditionerSearchByParams() {
        ApplianceService<AirConditioner> svc = ServiceFactory.airConditionerService();
        var criteria = new AirConditionerCriteria()
                .add(new HeatingParam(true))
                .add(new NoiseLevelParam(38.5));
        List<AirConditioner> list = svc.find(criteria);
        assertEquals(1, list.size());
        assertEquals("LG", list.get(0).getBrand());
    }

    @Test
    void fridgeSearchByParams() {
        ApplianceService<Fridge> svc = ServiceFactory.fridgeService();
        var criteria = new FridgeCriteria()
                .add(new HasFreezerParam(true))
                .add(new EnergyClassParam("A++"));
        List<Fridge> list = svc.find(criteria);
        assertEquals(1, list.size());
        assertEquals("Samsung", list.get(0).getBrand());
    }

    @Test
    void ironSearchByParams() {
        ApplianceService<Iron> svc = ServiceFactory.ironService();
        var criteria = new IronCriteria()
                .add(new WaterTankCapacityParam(0.35));
        List<Iron> list = svc.find(criteria);
        assertEquals(1, list.size());
        assertEquals("Philips", list.get(0).getBrand());
    }
}
