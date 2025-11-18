package itpu.diyoramirzaeva.dao.factory;

import itpu.diyoramirzaeva.dao.api.Dao;
import itpu.diyoramirzaeva.dao.impl.AirConditionerCsvDao;
import itpu.diyoramirzaeva.dao.impl.FridgeCsvDao;
import itpu.diyoramirzaeva.dao.impl.IronCsvDao;
import itpu.diyoramirzaeva.entity.AirConditioner;
import itpu.diyoramirzaeva.entity.Fridge;
import itpu.diyoramirzaeva.entity.Iron;

public final class DaoFactory {
    private static final Dao<AirConditioner> AIR_CONDITIONER_DAO = new AirConditionerCsvDao();
    private static final Dao<Fridge> FRIDGE_DAO = new FridgeCsvDao();
    private static final Dao<Iron> IRON_DAO = new IronCsvDao();

    private DaoFactory() {}

    public static Dao<AirConditioner> getAirConditionerDao() {
        return AIR_CONDITIONER_DAO;
    }

    public static Dao<Fridge> getFridgeDao() {
        return FRIDGE_DAO;
    }

    public static Dao<Iron> getIronDao() {
        return IRON_DAO;
    }
}
