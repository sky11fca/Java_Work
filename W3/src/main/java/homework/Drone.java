package homework;

/**
 * Drone object derived from Aircraft and implements some bullshit will be removed at laboratory
 */
public class Drone extends Aircraft
{
    /**
     * Speed of the Drone in km/h
     */
    private int speed;
    /**
     * Flight height in m
     */
    private int height;

    /**
     * The drone's battery capacity in Kw/h
     */

    private int batteryCapacity;

    /**
     * Drone constructor, please ignore the mDReference variable
     * @param name String: Aircraft name
     * @param model String: Aircraft model
     * @param tailNumber int: Aircraft tail number
     * @param speed int: Drone speed
     * @param height int: Drone flight height
     * @param batteryCapacity int: Drone's battery capacity
     */
    public Drone(String name, String model, int tailNumber, int speed, int height, int batteryCapacity) {
        super(name, model, tailNumber);
        this.speed = speed;
        this.height = height;
        this.batteryCapacity = batteryCapacity;
    }

    /**
     * Getter for the maximum drone speed
     * @return int: Drone's speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Setter for the maximum drone speed
     * @param speed int: Drone's speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Getter for the maximum height a drone can reach
     * @return int: Drone's maximum height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter for the maximum height a drone can reach
     * @param height int: Drone's maximum height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter for the Drone's battery capacity
     * @return int: Battery capacity
     */
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    /**
     * Setter for the Drone's battery capacity
     * @param batteryCapacity int: Battery capacity
     */
    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    /**
     * Overriden toString Method
     * @return String: All details of the Drone Object
     */
    @Override
    public String toString() {
        return "Drone{" +
                "speed=" + speed +
                ", height=" + height +
                ", batteryCapacity=" + batteryCapacity +
                '}' + super.toString();
    }
}
