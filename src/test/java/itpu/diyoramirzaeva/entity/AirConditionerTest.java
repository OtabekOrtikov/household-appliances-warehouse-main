package itpu.diyoramirzaeva.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirConditionerTest {
    @Test
    void equalityAndFields() {
        AirConditioner a1 = new AirConditioner(1, "LG", "ArtCool", 799.99, 5, 2200, true, 38.5);
        AirConditioner a2 = new AirConditioner(1, "LG", "ArtCool", 799.99, 5, 2200, true, 38.5);
        AirConditioner a3 = new AirConditioner(2, "LG", "ArtCool", 799.99, 5, 2200, true, 38.5);

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
        assertNotEquals(a1, a3);
        assertTrue(a1.toString().contains("Air conditioner"));
        assertTrue(a1.isHeating());
        assertEquals(38.5, a1.getNoiseLevel());
    }
}
