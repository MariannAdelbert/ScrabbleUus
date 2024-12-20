package org.example;

import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordLoader {

    private static final String FILE_PATH = "src/main/resources/dictionary.txt"; // Faili tee, kust sõnad laadida

    // Laadige sõnad andmebaasi süsteemi käivitamisel
    public static void loadWordsFromFile() {
        try (Connection conn = DatabaseHelper.getConnection();  // Kasutame DatabaseHelper.getConnection()
             BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String word;
            while ((word = br.readLine()) != null) {
                word = word.trim().toLowerCase();  // Eemaldame tühikud ja muudame sõna väikeseks
                insertWord(conn, word);  // Sisestame iga sõna andmebaasi
                System.out.println("Lisatud sõna: " + word); // Logige iga sõna lisamine
            }
            System.out.println("Sõnad on edukalt laaditud andmebaasi.");
        } catch (IOException | SQLException e) {
            e.printStackTrace(); // Kui esineb viga, siis trüki välja
        }
    }

    // Sisesta üks sõna andmebaasi
    private static void insertWord(Connection conn, String word) {
        String sql = "INSERT INTO words (word) VALUES (?) ON CONFLICT DO NOTHING";  // Sõna sisestamine, kui seda veel ei eksisteeri

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, word); // Seame sõna väärtuse
            stmt.executeUpdate(); // Käivitame päringu
        } catch (SQLException e) {
            e.printStackTrace(); // Kui esineb viga, siis trüki välja
        }
    }
}
