package org.example;  // Kasutage oma projekti õiget paketti nime

public class GameStatus {

    private int points;
    private String currentWord;

    public GameStatus(int points, String currentWord) {
        this.points = points;
        this.currentWord = currentWord;
    }

    public int getPoints() {
        return points;
    }

    public String getCurrentWord() {
        return currentWord;
    }
}
