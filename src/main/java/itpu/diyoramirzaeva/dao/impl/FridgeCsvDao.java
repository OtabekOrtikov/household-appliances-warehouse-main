package itpu.diyoramirzaeva.dao.impl;

import itpu.diyoramirzaeva.dao.api.Dao;
import itpu.diyoramirzaeva.dao.reader.AbstractCSVReader;
import itpu.diyoramirzaeva.entity.Fridge;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FridgeCsvDao extends AbstractCSVReader<Fridge> implements Dao<Fridge> {
    private static final String DEFAULT_RESOURCE = "fridges.csv";
    private final String resource;

    public FridgeCsvDao() {
        this(DEFAULT_RESOURCE);
    }

    public FridgeCsvDao(String resource) {
        this.resource = Objects.requireNonNull(resource, "resource");
    }

    @Override
    protected Fridge parse(String[] f) {
        int id = Integer.parseInt(f[0]);
        String brand = f[1];
        String model = f[2];
        double price = Double.parseDouble(f[3]);
        int qty = Integer.parseInt(f[4]);
        int power = Integer.parseInt(f[5]);
        boolean hasFreezer = Boolean.parseBoolean(f[6]);
        String energyClass = f[7];
        return new Fridge(id, brand, model, price, qty, power, hasFreezer, energyClass);
    }

    @Override
    public List<Fridge> findAll() {
        return readFromResource(resource);
    }

    @Override
    public Optional<Fridge> findById(int id) {
        return findAll().stream().filter(a -> a.getId() == id).findFirst();
    }
}
