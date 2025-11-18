package itpu.diyoramirzaeva.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IronTest {
    @Test
    void equalityAndFields() {
        Iron i1 = new Iron(3, "Philips", "SteamMaster", 129.99, 10, 2400, 0.35);
        Iron i2 = new Iron(3, "Philips", "SteamMaster", 129.99, 10, 2400, 0.35);
        Iron i3 = new Iron(4, "Philips", "SteamMaster", 129.99, 10, 2400, 0.35);

        assertEquals(i1, i2);
        assertEquals(i1.hashCode(), i2.hashCode());
        assertNotEquals(i1, i3);
        assertTrue(i1.toString().contains("Iron"));
        assertEquals(0.35, i1.getWaterTankCapacity());
    }
}
