package itpu.diyoramirzaeva.controller;

import itpu.diyoramirzaeva.entity.AirConditioner;
import itpu.diyoramirzaeva.entity.Fridge;
import itpu.diyoramirzaeva.entity.Iron;
import itpu.diyoramirzaeva.service.api.ApplianceService;
import itpu.diyoramirzaeva.service.factory.ServiceFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleControllerTest {

    @Test
    void listAllContainsAllSections() {
        ApplianceService<AirConditioner> ac = ServiceFactory.airConditionerService();
        ApplianceService<Fridge> fr = ServiceFactory.fridgeService();
        ApplianceService<Iron> ir = ServiceFactory.ironService();
        ConsoleController controller = new ConsoleController(ac, fr, ir);

        Response response = controller.execute(RequestFactory.fromUserInput("list all"));
        assertFalse(response.isError());
        assertTrue(response.responseString().toLowerCase().contains("air conditioners"));
        assertTrue(response.responseString().toLowerCase().contains("fridges"));
        assertTrue(response.responseString().toLowerCase().contains("irons"));
    }

    @Test
    void searchAirWithParamsReturnsResultBlock() {
        ApplianceService<AirConditioner> ac = ServiceFactory.airConditionerService();
        ApplianceService<Fridge> fr = ServiceFactory.fridgeService();
        ApplianceService<Iron> ir = ServiceFactory.ironService();
        ConsoleController controller = new ConsoleController(ac, fr, ir);

        Response response = controller.execute(RequestFactory.fromUserInput("search air heating=true noise=40"));
        assertFalse(response.isError());
        assertTrue(response.responseString().toLowerCase().contains("search result"));
    }
}
