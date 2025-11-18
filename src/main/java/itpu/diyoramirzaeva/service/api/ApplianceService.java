package itpu.diyoramirzaeva.service.api;

import itpu.diyoramirzaeva.dao.searchCriteria.SearchCriteria;
import itpu.diyoramirzaeva.entity.Household;

import java.util.List;
import java.util.Optional;

public interface ApplianceService<T extends Household> {
    List<T> getAll();
    Optional<T> getById(int id);
    List<T> find(SearchCriteria<T> criteria);
}
