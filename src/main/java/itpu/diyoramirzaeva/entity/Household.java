package itpu.diyoramirzaeva.entity;

import java.util.Objects;

public abstract class Household {
    private int id;
    private String brand;
    private String model;
    private double price;
    private int quantity;
    private int powerWatts;

    public Household(int id, String brand, String model, double price, int quantity, int powerWatts) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
        this.powerWatts = powerWatts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPowerWatts() {
        return powerWatts;
    }

    public void setPowerWatts(int powerWatts) {
        this.powerWatts = powerWatts;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Household household = (Household) o;
        return id == household.id && Double.compare(price, household.price) == 0 && quantity == household.quantity && powerWatts == household.powerWatts && Objects.equals(brand, household.brand) && Objects.equals(model, household.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, price, quantity, powerWatts);
    }

    @Override
    public String toString() {
        return "Household{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", powerWatts=" + powerWatts +
                '}';
    }

    protected String commonFields(){
        return String.format("""
                Brand: %s
                Model: %s
                Price: %s
                Quantity: %s
                powerWatts: %s""",
                brand, model, price, quantity, powerWatts);

    }
}
