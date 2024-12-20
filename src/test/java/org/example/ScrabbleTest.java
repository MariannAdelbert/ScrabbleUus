package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScrabbleTest {

    @Test
    public void testCalculateWordValue() {
        String word = "tere";
        int expectedValue = 4;  // Oodatud väärtus
        int actualValue = Scrabble.calculateWordValue(word);  // Arvutame väärtuse

        System.out.println("Oodatud väärtus: " + expectedValue);
        System.out.println("Tegelik väärtus: " + actualValue);

        assertEquals(expectedValue, actualValue);  // Kontrollime, kas väärtused ühtivad
    }
}
