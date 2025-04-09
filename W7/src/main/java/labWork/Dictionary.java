package labWork;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dictionary
{
    private Set<String> words;

    public Dictionary() {
        words = new HashSet<>(Arrays.asList( "ACE", "BAT", "CAT", "DOG", "EAT", "FAT", "GOT", "HAT",
                "ICE", "JAM", "KIT", "LIP", "MAP", "NAP", "OAK", "PEN",
                "QUIT", "RAT", "SIT", "TOP", "USE", "VAN", "WEB", "YES", "ZIP",
                "APPLE", "BRAVE", "CLOTH", "DRIVE", "EXCEL", "FRAME", "GRAPE",
                "HAPPY", "INPUT", "JAUNT", "KNIFE", "LOOSE", "MOUSE", "NOBLE"
        ));
    }

    public boolean isValidWord(String word)
    {
        return words.contains(word.toUpperCase());
    }

}
