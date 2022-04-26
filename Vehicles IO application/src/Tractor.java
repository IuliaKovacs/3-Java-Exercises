public class Tractor extends Vehicle{

    private int maxPulledWeight;

    public Tractor (String brand, String model, int price, int maxPulledWeight)
    { super(brand,model,price);
      this.maxPulledWeight=maxPulledWeight;
    }

    @Override
    public String toString() {
        return super.toString()+"  Max Pulled Weight: "+maxPulledWeight+"\n";
    }
}
