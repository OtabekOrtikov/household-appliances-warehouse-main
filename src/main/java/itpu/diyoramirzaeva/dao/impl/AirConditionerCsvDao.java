package itpu.diyoramirzaeva.dao.impl;

import itpu.diyoramirzaeva.dao.api.Dao;
import itpu.diyoramirzaeva.dao.reader.AbstractCSVReader;
import itpu.diyoramirzaeva.entity.AirConditioner;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AirConditionerCsvDao extends AbstractCSVReader<AirConditioner> implements Dao<AirConditioner> {
    private static final String DEFAULT_RESOURCE = "air_conditioners.csv";
    private final String resource;

    public AirConditionerCsvDao() {
        this(DEFAULT_RESOURCE);
    }

    public AirConditionerCsvDao(String resource) {
        this.resource = Objects.requireNonNull(resource, "resource");
    }

    @Override
    protected AirConditioner parse(String[] f) {
        int id = Integer.parseInt(f[0]);
        String brand = f[1];
        String model = f[2];
        double price = Double.parseDouble(f[3]);
        int qty = Integer.parseInt(f[4]);
        int power = Integer.parseInt(f[5]);
        boolean heating = Boolean.parseBoolean(f[6]);
        double noise = Double.parseDouble(f[7]);
        return new AirConditioner(id, brand, model, price, qty, power, heating, noise);
    }

    @Override
    public List<AirConditioner> findAll() {
        return readFromResource(resource);
    }

    @Override
    public Optional<AirConditioner> findById(int id) {
        return findAll().stream().filter(a -> a.getId() == id).findFirst();
    }
}
