package homework;

import java.util.*;

public class Player implements Runnable
{
    private String name;
    private List<Tile> hand;
    private Bag bag;
    private Dictionary dictionary;
    private GameController controller;
    private Random random;

    public Player(Bag bag, String name, GameController controller, Dictionary dictionary) {
        this.bag = bag;
        this.name = name;
        this.dictionary = dictionary;
        this.controller = controller;
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

        while((!bag.isEmpty() || !hand.isEmpty()) && !controller.isGameOver()) {
            try{
                controller.waitForTurn(this);

                if(controller.isGameOver()) {
                    break;
                }

                System.out.println("\nPlayer: " + name  + "'s turn");

                String word = formWord();

                if(word != null)
                {
                    int wordPoints = calculatePoints(word);

                    controller.submitWord(name, word, wordPoints);
                    if(!bag.isEmpty())
                    {
                        List<Tile> newTiles = bag.extractTiles(word.length());
                        hand.addAll(newTiles);
                        System.out.println(name + " drew " + newTiles.size() + " tiles. Bag has " + bag.remainingTiles() + " tiles left.");

                    }
                }else{
                    if(!bag.isEmpty())
                    {
                        System.out.println(name+ " Couldn't form a word, discarding...");
                        int discardCount = hand.size();
                        hand.clear();
                        List<Tile> newTiles = bag.extractTiles(discardCount);
                        hand.addAll(newTiles);
                    } else{
                        System.out.println(name+ " Couldn't form a word, empty bag");
                    }
                }

                try{
                    Thread.sleep(100+random.nextInt(300));
                }catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    return;
                }

                controller.nextTurn();
            }
            catch(InterruptedException e)
            {
                System.out.println(name + " interrupted");
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println(name + " Finished playing!");
    }

    private String formWord() {
        if(hand.isEmpty()) {
            return null;
        }

        StringBuilder availableLetters = new StringBuilder();
        for(Tile tile : hand)
        {
            availableLetters.append(tile.getLetter());
        }

        String letters = availableLetters.toString();

        for(int attempt = 0; attempt < 10; attempt++) {
            char[] shuffled = letters.toCharArray();
            shuffleArray(shuffled);

            for(int length = Math.min(shuffled.length, 7); length >= 2; length--) {
                String possibleWord = new String(shuffled, 0, length);

                if(dictionary.isValidWord(possibleWord)) {
                    List<Tile> usedTiles = new ArrayList<>();
                    for(char c : possibleWord.toCharArray()) {
                        for(Iterator<Tile> iterator = hand.iterator(); iterator.hasNext(); ) {
                            Tile tile = iterator.next();
                            if(Character.toUpperCase(tile.getLetter()) == Character.toUpperCase(c)) {
                                usedTiles.add(tile);
                                iterator.remove();
                                break;
                            }
                        }
                    }
                    int points = usedTiles.stream().mapToInt(Tile::getPoints).sum();

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
            int index = random.nextInt(i+1);
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

        List<Tile> handCopy = new ArrayList<>(hand);

        for(char c : word.toCharArray())
        {
            for(Iterator<Tile> it = handCopy.iterator(); it.hasNext(); )
            {
                Tile tile = it.next();
                if(Character.toUpperCase(tile.getLetter()) == Character.toUpperCase(c))
                {
                    points += tile.getPoints();
                    it.remove();
                    break;
                }
            }
        }
        removeTilesFromWord(word);

        return points;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
