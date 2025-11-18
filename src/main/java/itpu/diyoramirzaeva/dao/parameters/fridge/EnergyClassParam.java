package itpu.diyoramirzaeva.dao.parameters.fridge;

import itpu.diyoramirzaeva.dao.parameters.Parameter;
import itpu.diyoramirzaeva.entity.Fridge;

import java.security.InvalidParameterException;
import java.util.Objects;

public record EnergyClassParam(String energyClass) implements Parameter<Fridge> {
    public EnergyClassParam{
        if(energyClass == null || energyClass.isBlank()){
            throw new InvalidParameterException("EnergyClass can be empty");
        }
    }

    @Override
    public boolean test(Fridge fridge) {
        return Objects.nonNull(fridge) && energyClass.equals(fridge.getEnergyClass());
    }
}

