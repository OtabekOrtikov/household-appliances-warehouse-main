package itpu.diyoramirzaeva.config;

import itpu.diyoramirzaeva.controller.ConsoleController;
import itpu.diyoramirzaeva.controller.Controller;
import itpu.diyoramirzaeva.controller.ControllerFactory;
import itpu.diyoramirzaeva.dao.factory.DaoFactory;
import itpu.diyoramirzaeva.dao.impl.AirConditionerCsvDao;
import itpu.diyoramirzaeva.dao.impl.FridgeCsvDao;
import itpu.diyoramirzaeva.dao.impl.IronCsvDao;
import itpu.diyoramirzaeva.service.factory.ServiceFactory;
import itpu.diyoramirzaeva.service.impl.AirConditionerServiceImpl;
import itpu.diyoramirzaeva.service.impl.FridgeServiceImpl;
import itpu.diyoramirzaeva.service.impl.IronServiceImpl;
import itpu.diyoramirzaeva.view.BatchViewImpl;
import itpu.diyoramirzaeva.view.ConsoleViewImpl;
import itpu.diyoramirzaeva.view.View;
import itpu.diyoramirzaeva.view.ViewFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads a properties file from the classpath and initializes the application's factories.
 */
public class PropertiesConfigImpl implements Config {
    private final String propertiesName;

    public PropertiesConfigImpl(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    @Override
    public void init() {
        try (InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(propertiesName + ".properties")) {
            if (is == null) {
                throw new RuntimeException("Properties file not found: " + propertiesName + ".properties");
            }
            Properties properties = new Properties();
            properties.load(is);
            initDao(properties);
            initService(properties);
            initController(properties);
            initView(properties);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    private void initView(Properties properties) {
        String viewMode = properties.getProperty("view", "console");
        View view = switch (viewMode) {
            case "console" -> new ConsoleViewImpl();
            case "batch" -> {
                String file = properties.getProperty("batch.file", "batch-commands.txt");
                yield new BatchViewImpl(file);
            }
            default -> throw new IllegalStateException("Unexpected view mode: " + viewMode);
        };
        ViewFactory.init(view);
    }

    private void initController(Properties properties) {
        String controllerMode = properties.getProperty("controller", "console");
        Controller controller = switch (controllerMode) {
            case "console", "simple" -> new ConsoleController();
            default -> throw new IllegalStateException("Unexpected controller mode: " + controllerMode);
        };
        ControllerFactory.init(controller);
    }

    private void initService(Properties properties) {
        String serviceMode = properties.getProperty("service", "simple");
        switch (serviceMode) {
            case "simple" -> ServiceFactory.init(
                    new AirConditionerServiceImpl(DaoFactory.getAirConditionerDao()),
                    new FridgeServiceImpl(DaoFactory.getFridgeDao()),
                    new IronServiceImpl(DaoFactory.getIronDao())
            );
            default -> throw new IllegalStateException("Unexpected service mode: " + serviceMode);
        }
    }

    private void initDao(Properties properties) {
        String daoMode = properties.getProperty("dao", "csv");
        String sourceMode = properties.getProperty("source", "csv");
        if (!"csv".equals(sourceMode)) {
            throw new IllegalStateException("Unexpected source mode: " + sourceMode);
        }
        if (!"csv".equals(daoMode)) {
            throw new IllegalStateException("Unexpected dao mode: " + daoMode);
        }
        String airName = properties.getProperty("source.air", "air_conditioners") + ".csv";
        String fridgeName = properties.getProperty("source.fridge", "fridges") + ".csv";
        String ironName = properties.getProperty("source.iron", "irons") + ".csv";
        DaoFactory.init(
                new AirConditionerCsvDao(airName),
                new FridgeCsvDao(fridgeName),
                new IronCsvDao(ironName)
        );
    }
}
