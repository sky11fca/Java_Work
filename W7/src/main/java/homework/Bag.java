package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Bag
{
    private List<Tile> tiles;
    private final Random random = new Random();

    public Bag() {
        tiles = new ArrayList<>();
        initializeBag();
        shuffleBag();
    }

    private void initializeBag() {
        for(char c = 'A'; c <= 'Z'; c++) {
            for(int index = 0; index < 10; index++) {
                int point = random.nextInt(10)+1;
                tiles.add(new Tile(c, point));
            }
        }
    }

    private void shuffleBag() {
        Collections.shuffle(tiles);
    }

    public synchronized List<Tile> extractTiles(int count) {
        if(tiles.isEmpty()) {
            return new ArrayList<>();
        }

        int tilesToExtract = Math.min(count, tiles.size());
        List<Tile> extractedTiles = new ArrayList<>();

        for(int i = 0; i < tilesToExtract; i++) {
            extractedTiles.add(tiles.remove(0));
        }

        return extractedTiles;
    }

    public synchronized boolean isEmpty() {
        return tiles.isEmpty();
    }

    public synchronized int remainingTiles(){
        return tiles.size();
    }
}
