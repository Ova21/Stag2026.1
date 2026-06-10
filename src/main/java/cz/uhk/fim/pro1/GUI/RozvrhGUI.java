package cz.uhk.fim.pro1.GUI;

import cz.uhk.fim.pro1.model.*;
import cz.uhk.fim.pro1.fileOperations.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RozvrhGUI extends JFrame {

    private final Rozvrh rozvrh;
    private JPanel mainPanel;

    public RozvrhGUI(Rozvrh rozvrh) {
        super("Rozvrh");
        this.rozvrh = rozvrh;

        initGui();


        String[] columns = new String[17];
        columns[0] = "Den";

//        for (int i = 0; i < 16; i++) {
//            columns[i + 1] = Rozvrh.casy[i].toString();
//        }

        Object[][] data = new Object[7][17];

//        for (int d = 0; d < 7; d++) {
//
//            data[d][0] = Dny.Jmeno(d);
//
//            for (int s = 0; s < 16; s++) {
//
//                RozvrhovaAkce a = rozvrh.data[d][s];
//                System.out.println(a);
//
//                if (a != null && rozvrh.casNaSlot(a.casOd) == s) {
//                    data[d][s + 1] =
//                            a.getPredmet().info() + " " + a.getUcebna().getZkratka();
//                } else {
//                    data[d][s + 1] = "X";
//                }
//            }
//        }

//        for (int d = 0; d < 7; d++) {
//            for (int s = 0; s < 16; s++) {
//                System.out.println("[" + d + "][" + s + "] = " + data[d][s]);
//            }
//        }

        //model tabulky
    DefaultTableModel model = new DefaultTableModel(7, 17) { // 7 řádků, 17 sloupců
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        @Override
        public Object getValueAt(int row, int column) {
            if (column == 0) {
                return Dny.Jmeno(row);
            }

            int slot = column - 1;
            RozvrhovaAkce a = rozvrh.data[row][slot];

            if (a != null) {
                //kontrola jestli a je vyplneno
                // System.out.println("Hurá! Našel jsem akci v GUI na indexu [" + row + "][" + slot + "]: " + a.getPredmet().info());
                return a.getPredmet().info() + " " + a.getUcebna().getZkratka();
            }

            return "";
        }

        //prepisovani tabulky (Zahlaví)
        @Override
        public String getColumnName(int column) {
            if (column == 0) return "Den";
            return Rozvrh.casy[column - 1].toString();
        }
        };

        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JButton tlacitko = new JButton("Stáhnout rozvrh");
        tlacitko.addActionListener(e -> {
            //nová třida DownloadHandler
            DownloadHandler handler = new DownloadHandler();
            handler.exportToTextFile("Rozvrh.txt", this.rozvrh);
            //kontrola funkce
            JOptionPane.showMessageDialog(this, "Soubor byl úspěšně uložen!");
        });
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(tlacitko, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    //zakladni parametry
    private void initGui() {
        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
        //tlacitko na stahovaní rozvrhu.
        setSize(1300, 250);
        setLocationRelativeTo(null);
    }
}