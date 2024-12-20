package org.example;

import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static final String URL = "jdbc:postgresql://localhost:5432/scrabbledb"; // Andmebaasi URL
    private static final String USER = "postgres"; // PostgreSQL kasutajanimi
    private static final String PASSWORD = "V2gaTurvalineParool"; // PostgreSQL parool

    // Ühenduse loomine PostgreSQL andmebaasiga
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Laadige sõnad andmebaasi failist
    public static void loadWordsFromFile() {
        String filePath = "src/main/resources/dictionary.txt"; // Faili tee, kust sõnad laadida

        try (Connection conn = getConnection();
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String word;
            while ((word = br.readLine()) != null) {
                insertWord(conn, word.trim()); // Sisestame iga sõna andmebaasi
                System.out.println("Lisatud sõna: " + word);  // Logige iga sõna
            }
            System.out.println("Sõnad on edukalt laaditud andmebaasi.");
        } catch (IOException | SQLException e) {
            e.printStackTrace(); // Kui esineb viga, siis trüki välja
        }
    }

    // Sisesta üks sõna andmebaasi
    public static void insertWord(Connection conn, String word) {
        String sql = "INSERT INTO words (word) VALUES (?) ON CONFLICT DO NOTHING"; // Sõna sisestamine, kui seda veel ei eksisteeri
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, word); // Seame sõna väärtuse
            stmt.executeUpdate(); // Käivitame päringu
        } catch (SQLException e) {
            e.printStackTrace(); // Kui esineb viga, siis trüki välja
        }
    }

    // Kontrollige, kas sõna on kehtiv (kas sõna on andmebaasis olemas)
    public static boolean isValidWord(String word) {
        try (Connection conn = getConnection()) {
            String sql = "SELECT 1 FROM words WHERE UPPER(word) = ?";  // Kasutame UPPER(), et võrrelda suurtähtede järgi
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, word.toUpperCase()); // Teeme sõna suurte tähtedega, et võrrelda
                ResultSet rs = stmt.executeQuery();
                return rs.next(); // Kui tulemus on olemas, tagastame tõese väärtuse
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Kui esineb viga, siis trüki välja
        }
        return false; // Kui sõna ei leita, tagastame vale
    }


    // Võtke kõik sõnad andmebaasist
    public static List<String> getAllWords() throws SQLException {
        List<String> words = new ArrayList<>();
        String sql = "SELECT word FROM words";  // Valime kõik sõnad

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                words.add(rs.getString("word"));  // Lisame sõna nimekirja
            }
        }
        return words;
    }
}
