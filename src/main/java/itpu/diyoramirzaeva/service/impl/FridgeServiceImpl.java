package itpu.diyoramirzaeva.service.impl;

import itpu.diyoramirzaeva.dao.api.Dao;
import itpu.diyoramirzaeva.dao.factory.DaoFactory;
import itpu.diyoramirzaeva.dao.searchCriteria.SearchCriteria;
import itpu.diyoramirzaeva.entity.Fridge;
import itpu.diyoramirzaeva.service.api.ApplianceService;

import java.util.List;
import java.util.Optional;

public class FridgeServiceImpl implements ApplianceService<Fridge> {
    private final Dao<Fridge> dao = DaoFactory.getFridgeDao();

    @Override
    public List<Fridge> getAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Fridge> getById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Fridge> find(SearchCriteria<Fridge> criteria) {
        return dao.find(criteria);
    }
}
