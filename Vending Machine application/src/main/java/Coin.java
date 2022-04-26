import java.util.Objects;

public class Coin {
    private double value;

    public Coin () {}

    public Coin (double value) {
        this.value=value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "$" + value;
    }

    @Override
    public boolean equals(Object o)
    {   if (o instanceof Coin)
           return (((Coin) o).getValue()==this.value);
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
