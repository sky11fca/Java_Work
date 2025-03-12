package labWork;

public class Freighter extends Aircraft implements CargoCapable
{
    private double capacity;

    public Freighter(String name, String model, double capacity) {
        super(name, model);
        this.capacity = capacity;
    }

    @Override
    public double getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Freighter{" +
                "capacity=" + capacity +
                '}'+super.toString();
    }
}
