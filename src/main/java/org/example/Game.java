//package org.example;
//
//public class Game {
//
//    private boolean isStarted;
//    private boolean isEnded;
//    private String currentWord;
//
//    // Algatab mängu
//    public void start() {
//        isStarted = true;
//        isEnded = false;
//        System.out.println("Game started!");
//    }
//
//    // Mängib sõna
//    public void playWord(String word) {
//        if (!isStarted) {
//            System.out.println("Game has not started yet.");
//            return;
//        }
//        currentWord = word;
//        System.out.println("Word played: " + word);
//    }
//
//    // Lõpetab mängu
//    public void end() {
//        isEnded = true;
//        System.out.println("Game ended!");
//    }
//
//    // Tagastab mängu staatuse
//    public String getStatus() {
//        if (isEnded) {
//            return "Game has ended.";
//        }
//        if (isStarted) {
//            return "Game is in progress.";
//        }
//        return "Game has not started yet.";
//    }
//}
