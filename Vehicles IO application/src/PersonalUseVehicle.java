public abstract class PersonalUseVehicle extends Vehicle {

    private int topSpeed;
    private String shape;


    public PersonalUseVehicle (String brand, String model, int price, int topSpeed, String shape)
    { super(brand,model,price);
      this.topSpeed=topSpeed;
      this.shape=shape;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public String getShape() {
        return shape;
    }

    @Override
    public String toString() {
        return super.toString()+"  Top Speed: "+topSpeed+"  Shape: "+shape+"\n";
    }
}
