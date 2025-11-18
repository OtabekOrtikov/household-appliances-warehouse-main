package itpu.diyoramirzaeva.dao.searchCriteria;

import itpu.diyoramirzaeva.entity.AirConditioner;

public class AirConditionerCriteria extends AbstractCriteria<AirConditioner> {
    @Override
    public Class<AirConditioner> getApplianceType() {
        return AirConditioner.class;
    }
}