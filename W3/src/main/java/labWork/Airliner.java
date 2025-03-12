package labWork;

public class Airliner extends Aircraft implements PassengerCapable
{
    private int seats;

    public Airliner(String name, String model, int seats) {
        super(name, model);
        this.seats = seats;
    }

    @Override
    public int getSeats() {
        return seats;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Airliner{" +
                "seats=" + seats +
                '}' + super.toString();
    }
}
