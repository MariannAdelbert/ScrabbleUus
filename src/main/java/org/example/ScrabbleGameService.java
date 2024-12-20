package org.example;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrabbleGameService {

    private String gameStatus = "Game not started";



    public void startGame() {
        gameStatus = "Game started!";
    }

    public void playWord(String word) {
        // Siia lisada sõna mängimise loogika

        //db check if exists
        // calculate and return points
        gameStatus = "Played word: " + word;
    }
    public void addWord(String word) {

    }

    //public List<String> getWords() {
    //    return dbst kõik lisatud sõnad
    //}

    public String getGameStatus() {
        return gameStatus;
    }

    public void endGame() {
        gameStatus = "Game ended!";
    }
}
