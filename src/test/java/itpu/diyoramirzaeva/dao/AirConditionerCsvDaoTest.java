package itpu.diyoramirzaeva.dao;

import itpu.diyoramirzaeva.dao.impl.AirConditionerCsvDao;
import itpu.diyoramirzaeva.entity.AirConditioner;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AirConditionerCsvDaoTest {
    @Test
    void findAllAndById() {
        AirConditionerCsvDao dao = new AirConditionerCsvDao();
        assertFalse(dao.findAll().isEmpty());
        Optional<AirConditioner> acOpt = dao.findById(1);
        assertTrue(acOpt.isPresent());
        AirConditioner ac = acOpt.get();
        assertEquals("LG", ac.getBrand());
        assertEquals("ArtCool", ac.getModel());
        assertTrue(ac.isHeating());
        assertEquals(38.5, ac.getNoiseLevel());
    }
}
