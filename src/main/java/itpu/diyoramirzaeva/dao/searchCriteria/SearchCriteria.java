package itpu.diyoramirzaeva.dao.searchCriteria;

import itpu.diyoramirzaeva.dao.parameters.Parameter;
import itpu.diyoramirzaeva.entity.Household;

public interface SearchCriteria<A extends Household> {
    Class<A> getApplianceType();
    <P extends Parameter<A>> SearchCriteria<A> add(P parameter);
    boolean test(A household);
}