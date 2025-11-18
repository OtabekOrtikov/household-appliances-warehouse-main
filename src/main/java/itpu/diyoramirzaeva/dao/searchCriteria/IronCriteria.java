package itpu.diyoramirzaeva.dao.searchCriteria;

import itpu.diyoramirzaeva.entity.Iron;

public class IronCriteria extends AbstractCriteria<Iron> {
    @Override
    public Class<Iron> getApplianceType() {
        return Iron.class;
    }
}