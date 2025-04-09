package homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GameController implements Runnable
{
    private Map<String, Integer> playerScores;
    private Map<String, List<String>> playerWords;
    private List<Player> players;
    private int currentPlayerIndex;
    private volatile boolean gameOver;
    private final int timeLimit;

    public GameController(int timeLimit) {
        playerScores = new ConcurrentHashMap<>();
        playerWords = new ConcurrentHashMap<>();
        currentPlayerIndex = 0;
        gameOver = false;
        this.timeLimit = timeLimit;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public void run() {
        System.out.println("GAME STARTED!");

        List<Thread> playerThreads = new ArrayList<>();

        for(Player player : players) {
            Thread t = new Thread(player);
            playerThreads.add(t);
            t.start();
        }

        for(Thread thread : playerThreads) {
            try{
                thread.join();
            }
            catch(InterruptedException e)
            {
                System.out.println("Game Controller Interrupted");
                endGame();
            }
        }

        endGame();
    }


    public synchronized void submitWord(String playerName, String word, int points)
    {
        playerScores.putIfAbsent(playerName, 0);
        playerScores.put(playerName, playerScores.get(playerName) + points);

        playerWords.putIfAbsent(playerName, new ArrayList<>());
        playerWords.get(playerName).add(word);

        System.out.println("Player " + playerName + " submitted word: " + word + " points: " + points);
    }

    public synchronized Player getCurrentPlayer()
    {
        return players.get(currentPlayerIndex);
    }

    public synchronized void nextTurn()
    {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        notifyAll();
    }

    public synchronized void waitForTurn(Player player) throws InterruptedException
    {
        while(!gameOver && !getCurrentPlayer().equals(player)) {
            wait();
        }
    }

    public synchronized void endGame()
    {
        gameOver= true;
        notifyAll();
    }
    public boolean isGameOver()
    {
        return gameOver;
    }

    public void displayFinalResults()
    {
        System.out.println("Final Results: ");

        List<Map.Entry<String, Integer>> sortedScores = playerScores.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toList());

        for(Map.Entry<String, Integer> score : sortedScores) {
            String player = score.getKey();
            int points = score.getValue();

            System.out.println("Player " + player + ": " + points);
            System.out.println(" Words:" + String.join(" ", playerWords.getOrDefault(player, new ArrayList<>())));
        }

        if(!sortedScores.isEmpty()) {
            String winner = sortedScores.get(0).getKey();
            System.out.println("\nWinner: "+ winner + " With " + sortedScores.get(0).getValue() + " points");

        }
        else {
            System.out.println("No winner");
        }
    }

}
