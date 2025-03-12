package labWork;

public interface CargoCapable
{
    double getCapacity();
    void setCapacity(double capacity);

    default boolean isPassengerCapable(){
        return true;
    }
}
