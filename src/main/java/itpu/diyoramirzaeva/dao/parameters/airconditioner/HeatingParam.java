package itpu.diyoramirzaeva.dao.parameters.airconditioner;

import itpu.diyoramirzaeva.dao.parameters.Parameter;
import itpu.diyoramirzaeva.entity.AirConditioner;

import java.util.Objects;

public record HeatingParam(boolean heating) implements Parameter<AirConditioner> {
    @Override
    public boolean test(AirConditioner airConditioner) {
        return Objects.nonNull(airConditioner) && heating == airConditioner.isHeating();
    }
}
