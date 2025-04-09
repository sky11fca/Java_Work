package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Board
{
    private Map<String, Integer> playerScores;
    private Map<String, List<String>> playerWords;

    public Board() {
        playerScores = new ConcurrentHashMap<>();
        playerWords = new ConcurrentHashMap<>();
    }

    public synchronized void submitWord(String playerName, String word, int points){

        playerScores.putIfAbsent(playerName, 0);
        playerScores.put(playerName, playerScores.get(playerName) + points);

        playerWords.putIfAbsent(playerName, new ArrayList<>());
        playerWords.get(playerName).add(word);

        System.out.println(playerName + " submitted word " + word + " points " + points);
    }

    public void displayFinalResults()
    {
        System.out.println("Final results:");

        List<Map.Entry<String, Integer>> sortedScores = playerScores.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toList());

        for(Map.Entry<String, Integer> score : sortedScores)
        {
            String playerName = score.getKey();
            int points = score.getValue();
            System.out.println(playerName + " " + points);
            System.out.println(" Words: "+String.join(" ", playerWords.getOrDefault(playerName, new ArrayList<>())));
        }

        if(!sortedScores.isEmpty())
        {
            String winner = sortedScores.get(0).getKey();
            System.out.println("Winner: " + winner + "With " + sortedScores.get(0).getValue()+" Points!");
        }
    }
}
