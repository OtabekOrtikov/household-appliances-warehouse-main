package itpu.diyoramirzaeva.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FridgeTest {
    @Test
    void equalityAndFields() {
        Fridge f1 = new Fridge(2, "Samsung", "CoolPro", 899.99, 3, 150, true, "A++");
        Fridge f2 = new Fridge(2, "Samsung", "CoolPro", 899.99, 3, 150, true, "A++");
        Fridge f3 = new Fridge(3, "Samsung", "CoolPro", 899.99, 3, 150, true, "A++");

        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
        assertNotEquals(f1, f3);
        assertTrue(f1.toString().contains("Fridge"));
        assertTrue(f1.isHasFreezer());
        assertEquals("A++", f1.getEnergyClass());
    }
}
