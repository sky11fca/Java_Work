package homework;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dictionary
{
    private Set<String> words;

    public Dictionary() {
        words = new HashSet<>();
        loadDefaultDictionary();
    }
    public Dictionary(String filePath)
    {
        words = new HashSet<>();
        loadFromFile(filePath);
    }

    private void loadDefaultDictionary()
    {
        System.out.println("Loading default dictionary...");

        String[] commonWords = {
                "ACE", "BAT", "CAT", "DOG", "EAT", "FAT", "GOT", "HAT",
                "ICE", "JAM", "KIT", "LIP", "MAP", "NAP", "OAK", "PEN",
                "QUIT", "RAT", "SIT", "TOP", "USE", "VAN", "WEB", "YES", "ZIP",
                "APPLE", "BRAVE", "CLOTH", "DRIVE", "EXCEL", "FRAME", "GRAPE",
                "HAPPY", "INPUT", "JAUNT", "KNIFE", "LOOSE", "MOUSE", "NOBLE",
                "ORANGE", "PURPLE", "QUEEN", "RIVER", "STONE", "TABLE", "UNDER",
                "VIOLET", "WINTER", "XYLOPHONE", "YELLOW", "ZEBRA", "ABOVE",
                "BELOW", "CHILD", "DANCE", "EARTH", "FIRST", "GREAT", "HOUSE"
        };

        for (String word : commonWords)
        {
            words.add(word.toUpperCase());
        }

        try(InputStream is = Dictionary.class.getResourceAsStream("/wordlist.txt"); BufferedReader reader = new BufferedReader(new InputStreamReader(is)))
        {
            String line;

            while((line = reader.readLine()) != null)
            {
                if(line.matches("[A-Za-z]+"))
                {
                    words.add(line.trim().toUpperCase());
                }
            }
            System.out.println("Dictionary loaded");
        } catch(Exception e)
        {
            System.out.println("Error loading default dictionary: " + e.getMessage());
        }

        System.out.println("Dictionary loaded with " + words.size() + " words");
    }

    private void loadFromFile(String filePath)
    {
        System.out.println("Loading dictionary from file: " + filePath);

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;

            int count = 0;

            while((line = reader.readLine()) != null)
            {
                if(line.matches("[A-Za-z]+"))
                {
                    words.add(line.trim().toUpperCase());
                    count++;
                }
            }

            System.out.println("Succesfully loaded" + count + " words");
        }catch(IOException e)
        {
            System.out.println("Error loading default dictionary: " + e.getMessage());
            System.out.println("Loading the default Dictionary instead");
            loadDefaultDictionary();
        }
    }


    public boolean isValidWord(String word)
    {
        return words.contains(word.toUpperCase());
    }

    public int size()
    {
        return words.size();
    }

}
