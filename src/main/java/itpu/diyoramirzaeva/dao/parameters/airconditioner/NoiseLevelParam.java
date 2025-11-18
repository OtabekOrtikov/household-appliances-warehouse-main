package itpu.diyoramirzaeva.dao.parameters.airconditioner;

import itpu.diyoramirzaeva.dao.parameters.Parameter;
import itpu.diyoramirzaeva.entity.AirConditioner;

import java.security.InvalidParameterException;
import java.util.Objects;

public record NoiseLevelParam(double noiseLevel) implements Parameter<AirConditioner> {
    public NoiseLevelParam {
        if (noiseLevel < 0) {
            throw new InvalidParameterException("noiseLevel can not be less than 0");
        }
    }

    @Override
    public boolean test(AirConditioner airConditioner) {
        return Objects.nonNull(airConditioner) && noiseLevel == airConditioner.getNoiseLevel();
    }
}