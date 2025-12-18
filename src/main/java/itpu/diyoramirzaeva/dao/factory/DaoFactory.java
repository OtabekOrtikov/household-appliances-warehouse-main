package itpu.diyoramirzaeva.dao.factory;

import itpu.diyoramirzaeva.dao.api.Dao;
import itpu.diyoramirzaeva.dao.impl.AirConditionerCsvDao;
import itpu.diyoramirzaeva.dao.impl.FridgeCsvDao;
import itpu.diyoramirzaeva.dao.impl.IronCsvDao;
import itpu.diyoramirzaeva.entity.AirConditioner;
import itpu.diyoramirzaeva.entity.Fridge;
import itpu.diyoramirzaeva.entity.Iron;

import java.util.Objects;

public final class DaoFactory {
    private static Dao<AirConditioner> airConditionerDao;
    private static Dao<Fridge> fridgeDao;
    private static Dao<Iron> ironDao;

    static {
        resetToDefaults();
    }

    private DaoFactory() {}

    public static void init(Dao<AirConditioner> airDao,
                            Dao<Fridge> frDao,
                            Dao<Iron> irDao) {
        airConditionerDao = Objects.requireNonNull(airDao, "airDao");
        fridgeDao = Objects.requireNonNull(frDao, "fridgeDao");
        ironDao = Objects.requireNonNull(irDao, "ironDao");
    }

    public static void resetToDefaults() {
        init(new AirConditionerCsvDao(), new FridgeCsvDao(), new IronCsvDao());
    }

    public static Dao<AirConditioner> getAirConditionerDao() {
        return airConditionerDao;
    }

    public static Dao<Fridge> getFridgeDao() {
        return fridgeDao;
    }

    public static Dao<Iron> getIronDao() {
        return ironDao;
    }
}
