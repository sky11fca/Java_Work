package labWork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Player implements Runnable
{
    private String name;
    private List<Tile> hand;
    private Bag bag;
    private Board board;
    private Dictionary dictionary;
    private Random random;

    public Player(Bag bag, String name, Board board, Dictionary dictionary) {
        this.bag = bag;
        this.name = name;
        this.board = board;
        this.dictionary = dictionary;
        this.hand = new ArrayList<>();
        this.random = new Random();
    }

    @Override
    public void run() {
        System.out.println(name + " joined the party!");

        List<Tile> initialTiles = bag.extractTiles(7);
        if(initialTiles.isEmpty()) {
            System.out.println(name + " cannot draw initial tiles. Bag is empty.");
            return;
        }

        hand.addAll(initialTiles);

        //main loop

        while(!bag.isEmpty() || !hand.isEmpty()) {
            String word = fromWord();
            if( word != null) {
                int wordPoints = calculatePoints(word);
                board.submitWord(name, word, wordPoints);
                if(!bag.isEmpty())
                {
                    List<Tile> newTiles = bag.extractTiles(word.length());
                    hand.addAll(newTiles);
                    System.out.println(name+" drew "+ newTiles.size()+" new tiles. Bag has "+ bag.remainingTiles() + " tiles left.");
                }
            }else{
                if(!bag.isEmpty())
                {
                    System.out.println(name+ " couldn't form a word, discarding tiles and drawing new ones");
                    int discardCount = hand.size();
                    hand.clear();
                    List<Tile> newTiles = bag.extractTiles(discardCount);
                    hand.addAll(newTiles);
                }
                else{
                    System.out.println(name+ " cannot draw initial tiles. Bag is empty.");
                    break;
                }
            }

            try{
                Thread.sleep(100+random.nextInt(400));
            }catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println(name + " Finished playing!");
    }

    private String fromWord() {
        if(hand.isEmpty()) {
            return null;
        }

        StringBuilder availableLetterns = new StringBuilder();
        for(Tile tile : hand)
        {
            availableLetterns.append(tile.getLetter());
        }

        String letters = availableLetterns.toString();

        for(int attempt = 0; attempt < 10; attempt++) {
            char[] shuffled = letters.toCharArray();
            shuffleArray(shuffled);

            for(int length = Math.min(shuffled.length, 7); length >= 2; length--) {
                String possibleWord = new String(shuffled, 0, length);

                if(dictionary.isValidWord(possibleWord)) {
                    removeTilesFromWord(possibleWord);
                    return possibleWord;
                }
            }
        }

        return null;
    }

    private void shuffleArray(char[] array)
    {
        for(int i= array.length-1; i>0; i--)
        {
            int index = random.nextInt(i)+1;
            char temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private void removeTilesFromWord(String word) {
        for(char c : word.toCharArray())
        {
            for (Iterator<Tile> it = hand.iterator(); it.hasNext();)
            {
                Tile tile = it.next();
                if(Character.toUpperCase(tile.getLetter()) == Character.toUpperCase(c))
                {
                    it.remove();
                    break;
                }
            }
        }
    }

    private int calculatePoints(String word) {
        int points = 0;
        for(char c : word.toCharArray())
        {
            for(Tile tile : hand)
            {
                if(Character.toUpperCase(tile.getLetter()) == Character.toUpperCase(c))
                {
                    points += tile.getPoints();
                    break;
                }
            }
        }
        return points;
    }

    public String getName() {
        return name;
    }
}
