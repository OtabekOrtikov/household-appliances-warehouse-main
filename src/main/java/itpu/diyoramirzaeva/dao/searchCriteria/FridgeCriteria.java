package itpu.diyoramirzaeva.dao.searchCriteria;

import itpu.diyoramirzaeva.entity.Fridge;

public class FridgeCriteria extends AbstractCriteria<Fridge> {
    @Override
    public Class<Fridge> getApplianceType() {
        return Fridge.class;
    }
}