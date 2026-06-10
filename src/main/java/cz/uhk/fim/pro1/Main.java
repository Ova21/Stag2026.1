package cz.uhk.fim.pro1;

import cz.uhk.fim.pro1.model.*;
import cz.uhk.fim.pro1.GUI.RozvrhGUI;


public class Main {

    public static void main(String[] args) {
        System.out.println("Vitejte na FIM!");

        // Inicializace
        Fakulta fakulta = new Fakulta("Fakulta informatiky a managementu", "FIM");
        System.out.println(fakulta);
        // Rozvrch
        Rozvrh automatickyRozvrh = fakulta.getAktualniRozvrh();
        javax.swing.SwingUtilities.invokeLater(() -> new RozvrhGUI(automatickyRozvrh));
    }
}