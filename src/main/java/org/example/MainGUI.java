package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class MainGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Loo raamistiku (JFrame) objekt
        JFrame frame = new JFrame("Scrabble Sõna kontroll");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Loo peamine paigutus (layout) - kasutame vertikaalset paigutust
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        // Loo sisendi väli, kuhu kasutaja sisestab sõna
        JTextField wordField = new JTextField();
        wordField.setMaximumSize(new Dimension(300, 30));
        wordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Loo nupp sõna kontrollimiseks
        JButton checkButton = new JButton("Kontrolli sõna");
        checkButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Loo nupp sõna lisamiseks
        JButton addButton = new JButton("Lisa sõna");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Loo piirkond tulemuste kuvamiseks
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultArea.setPreferredSize(new Dimension(300, 100));

        // Pane kõik komponendid paigutusse
        panel.add(wordField);
        panel.add(checkButton);
        panel.add(addButton);
        panel.add(resultArea);

        // Lisa nuppudele tegevused
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = wordField.getText().trim();
                if (!word.isEmpty()) {
                    // Arvutame sõna väärtuse
                    int wordValue = Scrabble.calculateWordValue(word);
                    // Kontrollime sõna kehtivust
                    boolean isValid = Scrabble.isValidWord(word);
                    resultArea.setText("Sõna väärtus: " + wordValue + "\nKehtiv: " + isValid);
                } else {
                    resultArea.setText("Sisesta sõna!");
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = wordField.getText().trim();
                if (!word.isEmpty()) {
                    try (Connection conn = DatabaseHelper.getConnection()) {
                        // Lisa sõna andmebaasi
                        DatabaseHelper.insertWord(conn, word);
                        resultArea.setText("Sõna '" + word + "' lisatud andmebaasi.");
                    } catch (SQLException ex) {
                        resultArea.setText("Viga sõna lisamisel: " + ex.getMessage());
                    }
                } else {
                    resultArea.setText("Sisesta sõna, et lisada see andmebaasi.");
                }
            }
        });

        // Kuvame akna
        frame.setVisible(true);
    }
}
