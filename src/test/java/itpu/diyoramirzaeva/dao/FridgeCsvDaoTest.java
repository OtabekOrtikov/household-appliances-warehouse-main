package itpu.diyoramirzaeva.dao;

import itpu.diyoramirzaeva.dao.impl.FridgeCsvDao;
import itpu.diyoramirzaeva.entity.Fridge;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FridgeCsvDaoTest {
    @Test
    void findAllAndById() {
        FridgeCsvDao dao = new FridgeCsvDao();
        assertFalse(dao.findAll().isEmpty());
        Optional<Fridge> frOpt = dao.findById(1);
        assertTrue(frOpt.isPresent());
        Fridge fr = frOpt.get();
        assertEquals("Samsung", fr.getBrand());
        assertEquals("CoolPro", fr.getModel());
        assertTrue(fr.isHasFreezer());
        assertEquals("A++", fr.getEnergyClass());
    }
}
