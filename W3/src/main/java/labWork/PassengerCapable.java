package labWork;

public interface PassengerCapable
{
    int getSeats();

    void setSeats(int seats);

    default boolean isPassengerCapable(){
        return true;
    }


}
