package itpu.diyoramirzaeva.entity;

public class Fridge extends Household {
    private boolean hasFreezer;
    private String energyClass;

    public Fridge(int id, String brand, String model, double price, int quantity, int powerWatts, boolean hasFreezer, String energyClass) {
        super(id, brand, model, price, quantity, powerWatts);
        this.hasFreezer = hasFreezer;
        this.energyClass = energyClass;
    }

    public boolean isHasFreezer() {
        return hasFreezer;
    }

    public void setHasFreezer(boolean hasFreezer) {
        this.hasFreezer = hasFreezer;
    }

    public String getEnergyClass() {
        return energyClass;
    }

    public void setEnergyClass(String energyClass) {
        this.energyClass = energyClass;
    }

    @Override
    public String toString() {
        return String.format("""
                Fridge:
                %s
                Has Freezer: %s
                Energy class: %s
                
                ===========================
                """,
                commonFields(), hasFreezer, energyClass);
    }
}
