package labWork;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        Dictionary dictionary = new Dictionary();
        Bag bag = new Bag();
        Board board = new Board();

        List<Player> players = new ArrayList<>();

        players.add(new Player(bag,"Alice", board, dictionary));
        players.add(new Player(bag,"Bob", board, dictionary));
        players.add(new Player(bag,"Charlie", board, dictionary));

        List<Thread> threads = new ArrayList<>();
        for(Player player : players)
        {
            Thread t = new Thread(player);
            threads.add(t);
            t.start();
        }

        for(Thread thread : threads)
        {
            try{
                thread.join();
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("\n==============GAME OVER==============");
        board.displayFinalResults();
    }
}
