package itpu.diyoramirzaeva.dao.parameters.iron;

import itpu.diyoramirzaeva.dao.parameters.Parameter;
import itpu.diyoramirzaeva.entity.Iron;

import java.security.InvalidParameterException;
import java.util.Objects;

public record WaterTankCapacityParam(double waterTankCapacity) implements Parameter<Iron> {
    public WaterTankCapacityParam {
        if (waterTankCapacity < 0) {
            throw new InvalidParameterException("waterTankCapacity can not be less than 0");
        }
    }

    @Override
    public boolean test(Iron iron) {
        return Objects.nonNull(iron) && waterTankCapacity == iron.getWaterTankCapacity();
    }
}
