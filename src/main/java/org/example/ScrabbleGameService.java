package org.example;

import org.springframework.stereotype.Service;

@Service
public class ScrabbleGameService {

    private String gameStatus = "Game not started";

    // Arvutab sõna väärtuse
    public int calculateWordValue(String word) {
        return Scrabble.calculateWordValue(word);  // Kasutame Scrabble klassi meetodit
    }

    // Kontrollib, kas sõna on kehtiv
    public boolean isValidWord(String word) {
        return Scrabble.isValidWord(word);  // Kasutame Scrabble klassi meetodit
    }

    public void startGame() {
        gameStatus = "Game started!";
    }

    public void playWord(String word) {
        // Lisada sõna mängimise loogika
        gameStatus = "Played word: " + word;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void endGame() {
        gameStatus = "Game ended!";
    }
}
