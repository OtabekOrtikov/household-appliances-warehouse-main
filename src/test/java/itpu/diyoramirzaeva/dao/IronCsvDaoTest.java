package itpu.diyoramirzaeva.dao;

import itpu.diyoramirzaeva.dao.impl.IronCsvDao;
import itpu.diyoramirzaeva.entity.Iron;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class IronCsvDaoTest {
    @Test
    void findAllAndById() {
        IronCsvDao dao = new IronCsvDao();
        assertFalse(dao.findAll().isEmpty());
        Optional<Iron> irOpt = dao.findById(1);
        assertTrue(irOpt.isPresent());
        Iron ir = irOpt.get();
        assertEquals("Philips", ir.getBrand());
        assertEquals("SteamMaster", ir.getModel());
        assertEquals(0.35, ir.getWaterTankCapacity());
    }
}
