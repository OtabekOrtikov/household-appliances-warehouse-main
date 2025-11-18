package itpu.diyoramirzaeva.dao.parameters;

import itpu.diyoramirzaeva.entity.Household;

public interface Parameter<E extends Household> {
    boolean test(E household);

    static <E extends Household> Parameter<E> any() {
        return h -> true;
    }

    static <E extends Household> Parameter<E> none() {
        return h -> false;
    }
}
