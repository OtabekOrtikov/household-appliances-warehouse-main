package itpu.diyoramirzaeva.dao.searchCriteria;


import itpu.diyoramirzaeva.dao.parameters.Parameter;
import itpu.diyoramirzaeva.entity.Household;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractCriteria<A extends Household> implements SearchCriteria<A> {

    protected final Map<Class<?>, Parameter<A>> parameters = new HashMap<>();

    @Override
    public <F extends Parameter<A>> SearchCriteria<A> add(F parameter) {
        parameters.put(parameter.getClass(), parameter);
        return this;
    }

    @Override
    public boolean test(A household) {
        if (Objects.isNull(household)) return false;
        if (parameters.isEmpty()) return true;
        for (Parameter<A> p : parameters.values()) {
            if (!p.test(household)) return false;
        }
        return true;
    }
}