package itpu.diyoramirzaeva.dao.api;

import itpu.diyoramirzaeva.dao.searchCriteria.SearchCriteria;
import itpu.diyoramirzaeva.entity.Household;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Dao<T extends Household> {
    List<T> findAll();
    Optional<T> findById(int id);

    default List<T> find(SearchCriteria<T> criteria) {
        return findAll().stream()
                .filter(criteria::test)
                .collect(Collectors.toList());
    }
}
