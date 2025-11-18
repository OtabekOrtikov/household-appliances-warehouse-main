package itpu.diyoramirzaeva.search;

import itpu.diyoramirzaeva.dao.parameters.airconditioner.HeatingParam;
import itpu.diyoramirzaeva.dao.searchCriteria.AirConditionerCriteria;
import itpu.diyoramirzaeva.entity.AirConditioner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchCriteriaTests {
    @Test
    void abstractCriteriaTestsParamAggregation() {
        var ac = new AirConditioner(1, "LG", "ArtCool", 799.99, 5, 2200, true, 38.5);
        var criteria = new AirConditionerCriteria().add(new HeatingParam(true));
        assertTrue(criteria.test(ac));
        var criteria2 = new AirConditionerCriteria().add(new HeatingParam(false));
        assertFalse(criteria2.test(ac));
    }
}
