package org.example;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {

    private static final DatabaseHelper dbHelper = new DatabaseHelper(); // DatabaseHelper kasutamine

    // Tähemärkide väärtused
    private static final Map<Character, Integer> letterValues = new HashMap<>();

    static {
        // Tähemärkide väärtused
        letterValues.put('A', 1);
        letterValues.put('E', 1);
        letterValues.put('I', 1);
        letterValues.put('O', 1);
        letterValues.put('N', 1);
        letterValues.put('R', 1);
        letterValues.put('T', 1);
        letterValues.put('L', 1);
        letterValues.put('S', 1);
        letterValues.put('U', 1);
        letterValues.put('D', 2);
        letterValues.put('G', 2);
        letterValues.put('B', 3);
        letterValues.put('C', 3);
        letterValues.put('M', 3);
        letterValues.put('P', 3);
        letterValues.put('F', 4);
        letterValues.put('H', 4);
        letterValues.put('V', 4);
        letterValues.put('W', 4);
        letterValues.put('Y', 4);
        letterValues.put('K', 5);
        letterValues.put('J', 8);
        letterValues.put('X', 8);
        letterValues.put('Q', 10);
        letterValues.put('Z', 10);

        // Laadige sõnad andmebaasi
        DatabaseHelper.loadWordsFromFile();
    }

    // Funktsioon, mis arvutab sõna väärtuse
    public static int calculateWordValue(String word) {
        word = word.toUpperCase();  // Muudame kõik tähed suurteks
        int totalValue = 0;
        StringBuilder detailedValue = new StringBuilder();  // String, kuhu koguda tähemärkide väärtused

        // Läbime iga tähe ja arvutame vastava väärtuse
        for (char letter : word.toCharArray()) {
            int letterValue = letterValues.getOrDefault(letter, 0);  // Kui tähe väärtust ei leita, siis on see 0
            totalValue += letterValue;

            // Lisame tähe väärtuse detailide stringile
            if (detailedValue.length() > 0) {
                detailedValue.append(" + ");
            }
            detailedValue.append(letterValue);  // Lisame tähe väärtuse
        }

        // Kuvame kõik tähe väärtused koos tulemusega logis
        System.out.println(word + " = " + detailedValue.toString() + " = " + totalValue);

        return totalValue;
    }

    // Kontrollime, kas sõna on kehtiv
    public static boolean isValidWord(String word) {
        return dbHelper.isValidWord(word); // Kasutame DatabaseHelper klassi meetodit
    }

//    public static void main(String[] args) {
//        System.out.println("Scrabble rakendus käivitatud!");
//        // Testige, et sõnad on õigesti laaditud ja salvestatud andmebaasi
//        String word = "almond";
//        System.out.println("Kas sõna '" + word + "' on kehtiv? " + isValidWord(word));
//
//        // Arvutame sõna väärtuse
//        System.out.println("Sõna '" + word + "' väärtus on: " + calculateWordValue(word));
//    }
}
