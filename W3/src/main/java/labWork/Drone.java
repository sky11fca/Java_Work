package labWork;

public class Drone extends Aircraft
{
    public int speed;
    public int height;

    public Drone(String name, String model, int speed, int height) {
        super(name, model);
        this.speed = speed;
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "speed=" + speed +
                ", height=" + height +
                '}' + super.toString();
    }
}
