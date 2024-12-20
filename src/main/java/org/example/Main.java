package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Laadige sõnad andmebaasi süsteemi käivitamisel
        DatabaseHelper.loadWordsFromFile();  // Laadige sõnad failist andmebaasi

        // Testige, et sõnad on õigesti laaditud ja salvestatud andmebaasi
        String word = "almond";
        System.out.println("Kas sõna '" + word + "' on kehtiv? " + DatabaseHelper.isValidWord(word));

        // Näiteks, kui soovite logida sõnu, mis on andmebaasi lisatud
        System.out.println("Testime sõnade logimist...");
        try {
            // Näiteks sõnad, mille lisamine võiks toimuda
            List<String> words = DatabaseHelper.getAllWords();  // Võtke kõik sõnad andmebaasist
            for (String currentWord : words) {
                System.out.println("Lisatud sõna: " + currentWord);  // Logige iga sõna
            }
        } catch (SQLException e) {
            System.err.println("Viga sõnade laadimisel: " + e.getMessage());
        }
    }
}
