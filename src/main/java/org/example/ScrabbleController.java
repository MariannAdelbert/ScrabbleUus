package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;
import java.util.HashMap;

import org.example.ScrabbleGameService;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")  // Kõik päringud, mis algavad /api-ga, teenindatakse selle kontrolleriga
public class ScrabbleController {

    private final ScrabbleGameService scrabbleGameService;

    // Konstruktor, et injectida ScrabbleGameService teenus
    public ScrabbleController(ScrabbleGameService scrabbleGameService) {
        this.scrabbleGameService = scrabbleGameService;
    }

    @GetMapping("/welcome")  // Kui tehakse GET päring /api/welcome, siis kutsutakse seda meetodit
    public String welcome() {
        return "Welcome to Scrabble API!";  // Lihtne tervitussõnum
    }

    @GetMapping("/check")
    public Map<String, Object> checkWord(@RequestParam String word) {
        Map<String, Object> response = new HashMap<>();
        int value = scrabbleGameService.calculateWordValue(word);
        boolean isValid = scrabbleGameService.isValidWord(word);
        response.put("value", value);
        response.put("isValid", isValid);
        return response;
    }

    @PostMapping("/start-game")
    public String startGame() {
        scrabbleGameService.startGame();
        return "Game started!";
    }

    @PostMapping("/play-word")  // Kui tehakse POST päring /api/play-word, mängime sõna
    public String playWord(@RequestBody String word) {
        scrabbleGameService.playWord(word);
        return "Word played: " + word;
    }

    @GetMapping("/game-status")  // Kui tehakse GET päring /api/game-status, siis tagastame mängu oleku
    public String getGameStatus() {
        return scrabbleGameService.getGameStatus();
    }

    @PostMapping("/end-game")  // Kui tehakse POST päring /api/end-game, lõpetame mängu
    public String endGame() {
        scrabbleGameService.endGame();
        return "Game ended!";
    }
}
