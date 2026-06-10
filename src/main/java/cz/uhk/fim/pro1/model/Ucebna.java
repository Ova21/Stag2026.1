package cz.uhk.fim.pro1.model;

import java.util.HashMap;

public class Ucebna {
    String zkratka;
    String budova;
    int podlazi;
    int kapacita;
    public enum TypUcebny {pocitacova, poslucharna, seminarni, ostatni;}
    //rozvrh rom rozvrch místnost pokud je potřeba importovat


    public Ucebna() {
    }

    public String getZkratka() {
        return zkratka;
    }

    public static Ucebna vytvor(String [] buffer) {
        Ucebna ucebna = new Ucebna();
        ucebna.zkratka = buffer[1];
        ucebna.budova = buffer[0];
        ucebna.podlazi = Integer.parseInt(buffer[14]);
        ucebna.kapacita = Integer.parseInt(buffer[6]);

        return ucebna;
    }

    public static Ucebna vytvor(HashMap<String,String> mapa) {
        Ucebna ucebna = new Ucebna();
        ucebna.zkratka = mapa.get("cisloMistnosti");
        ucebna.budova = mapa.get("zkrBudovy");
        ucebna.podlazi = Integer.parseInt(mapa.get("podlazi"));
        ucebna.kapacita = Integer.parseInt(mapa.get("kapacita"));

        return ucebna;
    }

    @Override
    public String toString() {
        return zkratka + "(" + kapacita + ")";
    }
}
