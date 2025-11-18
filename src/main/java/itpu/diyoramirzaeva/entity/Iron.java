package itpu.diyoramirzaeva.entity;

public class Iron extends Household {
    private double waterTankCapacity;

    public Iron(int id, String brand, String model, double price, int quantity, int powerWatts, double waterTankCapacity) {
        super(id, brand, model, price, quantity, powerWatts);
        this.waterTankCapacity = waterTankCapacity;
    }

    public double getWaterTankCapacity() {
        return waterTankCapacity;
    }

    public void setWaterTankCapacity(double waterTankCapacity) {
        this.waterTankCapacity = waterTankCapacity;
    }

    @Override
    public String toString() {
        return String.format("""
                Iron:
                %s
                Water tank capacity: %s
                
                ===========================
                """,
                commonFields(), waterTankCapacity);
    }
}
