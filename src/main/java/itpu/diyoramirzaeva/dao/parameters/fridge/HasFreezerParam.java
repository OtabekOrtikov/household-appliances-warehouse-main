package itpu.diyoramirzaeva.dao.parameters.fridge;

import itpu.diyoramirzaeva.dao.parameters.Parameter;
import itpu.diyoramirzaeva.entity.Fridge;

import java.util.Objects;

public record HasFreezerParam(boolean hasFreezer) implements Parameter<Fridge> {
    @Override
    public boolean test(Fridge fridge) {
        return Objects.nonNull(fridge) && hasFreezer == fridge.isHasFreezer();
    }
}
