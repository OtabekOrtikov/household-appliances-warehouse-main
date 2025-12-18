package itpu.diyoramirzaeva.dao.impl;

import itpu.diyoramirzaeva.dao.api.Dao;
import itpu.diyoramirzaeva.dao.reader.AbstractCSVReader;
import itpu.diyoramirzaeva.entity.Iron;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class IronCsvDao extends AbstractCSVReader<Iron> implements Dao<Iron> {
    private static final String DEFAULT_RESOURCE = "irons.csv";
    private final String resource;

    public IronCsvDao() {
        this(DEFAULT_RESOURCE);
    }

    public IronCsvDao(String resource) {
        this.resource = Objects.requireNonNull(resource, "resource");
    }

    @Override
    protected Iron parse(String[] f) {
        int id = Integer.parseInt(f[0]);
        String brand = f[1];
        String model = f[2];
        double price = Double.parseDouble(f[3]);
        int qty = Integer.parseInt(f[4]);
        int power = Integer.parseInt(f[5]);
        double waterTankCapacity = Double.parseDouble(f[6]);
        return new Iron(id, brand, model, price, qty, power, waterTankCapacity);
    }

    @Override
    public List<Iron> findAll() {
        return readFromResource(resource);
    }

    @Override
    public Optional<Iron> findById(int id) {
        return findAll().stream().filter(a -> a.getId() == id).findFirst();
    }
}
