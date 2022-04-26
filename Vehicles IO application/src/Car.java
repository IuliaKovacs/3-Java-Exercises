public class Car extends PersonalUseVehicle{

    private String transmission;

    public Car (String brand, String model, int price, int topSpeed, String shape,String transmission)
    { super(brand,model,price,topSpeed,shape);
      this.transmission=transmission;
    }

}
