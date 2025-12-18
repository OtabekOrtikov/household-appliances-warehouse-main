package itpu.diyoramirzaeva.service.impl;

import itpu.diyoramirzaeva.dao.api.Dao;
import itpu.diyoramirzaeva.dao.factory.DaoFactory;
import itpu.diyoramirzaeva.dao.searchCriteria.SearchCriteria;
import itpu.diyoramirzaeva.entity.Iron;
import itpu.diyoramirzaeva.service.api.ApplianceService;

import java.util.List;
import java.util.Optional;

public class IronServiceImpl implements ApplianceService<Iron> {
    private final Dao<Iron> dao;

    public IronServiceImpl() {
        this(DaoFactory.getIronDao());
    }

    public IronServiceImpl(Dao<Iron> dao) {
        this.dao = dao;
    }

    @Override
    public List<Iron> getAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Iron> getById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Iron> find(SearchCriteria<Iron> criteria) {
        return dao.find(criteria);
    }
}
