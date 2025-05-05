package homework;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int GAME_TIME_LIMIT_SECONDS = 10;

    public static void main(String[] args)
    {
        String dictionaryFile = null;
        int timeLimitSeconds = GAME_TIME_LIMIT_SECONDS;

        for(int i = 0; i < args.length; i++)
        {
            if(args[i].equals("-dict") && i+1 < args.length)
            {
                dictionaryFile = args[i+1];
                i++;
            }
            else if(args[i].equals("-time") && i+1 < args.length)
            {
                try{
                    timeLimitSeconds = Integer.parseInt(args[i+1]);
                    i++;
                }
                catch(NumberFormatException e)
                {
                    System.out.println("\nTime limit must be an integer, using the default value");
                }
            }
        }


        Dictionary dictionary = (dictionaryFile != null) ? new Dictionary(dictionaryFile) : new Dictionary();
        Bag bag = new Bag();
        GameController controller = new GameController(timeLimitSeconds);

        List<Player> players = new ArrayList<>();

        players.add(new Player(bag,"Alice",controller, dictionary));
        players.add(new Player(bag,"Bob", controller, dictionary));
        players.add(new Player(bag,"Charlie", controller, dictionary));
        controller.setPlayers(players);

        TimeKeeper timeKeeper = new TimeKeeper(controller, timeLimitSeconds);

        Thread timeKeeperThread = new Thread(timeKeeper);
        timeKeeperThread.setDaemon(true);
        timeKeeperThread.start();

        System.out.println("Starting game with time limit of " + timeLimitSeconds + " seconds");
        System.out.println("Dictionary loaded with " + dictionary.size() + " words");
        System.out.println("Bag initialized with " + bag.remainingTiles() + " tiles\n");

        Thread controllerThread = new Thread(controller);

        controllerThread.start();

        try{
            controllerThread.join();
        }catch(InterruptedException e){
            System.out.println("Game thread interrupted");
        }

        System.out.println("\n================ Game Over ================");
        controller.displayFinalResults();

    }
}
