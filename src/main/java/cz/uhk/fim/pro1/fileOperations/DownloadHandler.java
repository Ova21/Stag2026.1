package cz.uhk.fim.pro1.fileOperations;

import cz.uhk.fim.pro1.GUI.*;
import cz.uhk.fim.pro1.model.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DownloadHandler {//neboli vytverec slozek.
    public void exportToTextFile(String filename, Rozvrh rozvrh) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            //zahlavi
            writer.write(String.format("%-12s |", "Den"));
            for (int s = 0; s < 16; s++) {
                writer.write(String.format("%-12s |", Rozvrh.casy[s].toString()));

            }
            writer.newLine();

            //oddeleni zahlavi
            writer.write("-".repeat(12 + (16 * 15)));
            writer.newLine();

            for (int d = 0; d < 7; d++) {
                writer.write(String.format("%-12s |", Dny.Jmeno(d)));

                for (int s = 0; s < 16; s++) {
                    RozvrhovaAkce a = rozvrh.data[d][s];
                    String cellText;

                    //vypis předmětu
                    if (a != null) {
                        cellText = a.getPredmet().info() + " " + a.getUcebna().getZkratka();
                    } else {
                        cellText = "X";
                    }

                    writer.write(String.format("%-12s |", cellText));
                }
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}