package labWork;

public class Main
{
    public static void main(String[] args)
    {
        Airliner a1 = new Airliner("A1", "model1", 300);
        Airliner a2 = new Airliner("A2", "model2", 300);
        Airliner a3 = new Airliner("A3", "model3", 300);

        Freighter f1 = new Freighter("F1", "model1", 300.0);
        Freighter f2 = new Freighter("F2", "model2", 300.0);
        Freighter f3 = new Freighter("F3", "model3", 300.0);

        Drone d1 = new Drone("D1", "model1", 50, 100);
        Drone d2 = new Drone("D2", "model2", 50, 100);
        Drone d3 = new Drone("D3", "model3", 50, 100);

        Aircraft[] aircrafts = {a1, a2, a3, f1, f2, f3, d1, d2, d3};

        for(Aircraft a : aircrafts)
        {
            System.out.println(a);
        }

        System.out.println();

        CargoCapable[] cc = {f1, f2, f3};

        for(CargoCapable c : cc)
        {
            System.out.println(c);
        }
    }
}
