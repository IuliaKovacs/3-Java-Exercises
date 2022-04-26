public class Product {
    private String brand;
    private String type;
    private double price;
    private String calories;

    public Product() {
    }

    public Product(String brand, String type, double price, String calories) {
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.calories=calories;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return brand + " " + type + " $" + price + " " + calories;
    }

    @Override
    public boolean equals(Object o)
    {   if (o instanceof Product)
        return (((Product) o).getBrand().equals(this.brand) &&((Product) o).getType().equals(this.type));
    else return false;
    }
}
