package itpu.diyoramirzaeva.service.impl;

import itpu.diyoramirzaeva.dao.api.Dao;
import itpu.diyoramirzaeva.dao.factory.DaoFactory;
import itpu.diyoramirzaeva.dao.searchCriteria.SearchCriteria;
import itpu.diyoramirzaeva.entity.AirConditioner;
import itpu.diyoramirzaeva.service.api.ApplianceService;

import java.util.List;
import java.util.Optional;

public class AirConditionerServiceImpl implements ApplianceService<AirConditioner> {
    private final Dao<AirConditioner> dao = DaoFactory.getAirConditionerDao();

    @Override
    public List<AirConditioner> getAll() {
        return dao.findAll();
    }

    @Override
    public Optional<AirConditioner> getById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<AirConditioner> find(SearchCriteria<AirConditioner> criteria) {
        return dao.find(criteria);
    }
}
