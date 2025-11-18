package itpu.diyoramirzaeva.entity;

public class AirConditioner extends Household {
    private boolean heating;
    private double noiseLevel;

    public AirConditioner(int id, String brand, String model, double price, int quantity, int powerWatts, boolean heating, double noiseLevel) {
        super(id, brand, model, price, quantity, powerWatts);
        this.heating = heating;
        this.noiseLevel = noiseLevel;
    }

    public boolean isHeating() {
        return heating;
    }

    public double getNoiseLevel() {
        return noiseLevel;
    }

    public void setHeating(boolean heating) {
        this.heating = heating;
    }

    public void setNoiseLevel(double noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    @Override
    public String toString() {
        return String.format("""
                Air conditioner:
                %s
                Heating: %s
                NoiseLevel: %s
                
                ===========================
                """,
                commonFields(), heating, noiseLevel);
    }
}
