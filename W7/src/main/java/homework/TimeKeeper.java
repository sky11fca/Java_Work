package homework;

public class TimeKeeper implements Runnable
{
    private final GameController controller;
    private final int timeLimit;
    private boolean running;

    public TimeKeeper(GameController controller, int timeLimit) {
        this.controller = controller;
        this.timeLimit = timeLimit;
        this.running = true;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        int elapsedTime = 0;
        int lastReportedSecond = -1;

        while(running && !controller.isGameOver())
        {
            long currentTime = System.currentTimeMillis();
            elapsedTime = (int) ((currentTime - startTime) / 1000);

            if(elapsedTime%5 == 0 && elapsedTime != lastReportedSecond) {
                System.out.println("\n Time elapsed: " + elapsedTime + " seconds");
                lastReportedSecond = elapsedTime;
            }
            if(elapsedTime >= timeLimit) {
                System.out.println("\n TICK TOCK!!! TIME LIMIT REACHED: "+ timeLimit);
                controller.endGame();
                break;
            }

            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                running = false;
            }
        }

        if(elapsedTime < timeLimit) {
            System.out.println("\nGame completed in " + elapsedTime + " seconds");
        }
    }

    public void stop()
    {
        running = false;
    }

}
