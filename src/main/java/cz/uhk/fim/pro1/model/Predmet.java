package cz.uhk.fim.pro1.model;

import java.util.HashMap;

public class Predmet {
//    public static enum Indexy {zkratka,katedra,nazev,kreditu,rok,statut,doporucenyRocnik,doporucenySemestr,vyznamPredmetu,vyukaLS,vyukaZS,rozsah,typZk;}
//    public static String [] definedFields = {"zkratka","katedra", "nazev","kreditu","rok","statut","doporucenyRocnik","doporucenySemestr","vyznamPredmetu","vyukaLS","vyukaZS","rozsah","typZk"};

    String nazev;
    String zkratka;

    String rozsah;
    Semestr semestr;
    int hodinPrednasek;
    int hodinCviceni;

    int kreditu;
    String zakonceni;
    String katedra;
    int doporucenyRocnik;

//    public Predmet(String [] buffer)
//    {
////        "PRO1";"KIKM";"Programování I";"5";"2024";"A";"1";"LS";"T";"A";"N";"2+2+0";"Zk+"
//        this.zkratka = buffer[Indexy.zkratka.ordinal()];
//        this.katedra = buffer[Indexy.katedra.ordinal()];
//        this.nazev = buffer[2];
//        this.kreditu = Integer.parseInt(buffer[3]);
//        this.doporucenyRocnik = Integer.parseInt(buffer[6]);
//        this.semestr = Semestr.valueOf(buffer[7]);
//        this.rozsah = buffer[11];
//        this.zakonceni = buffer[12];
//        String [] hodiny = this.rozsah.split("\\+");
//        this.hodinPrednasek = Integer.parseInt(hodiny[0]);
//        this.hodinCviceni = Integer.parseInt(hodiny[1]);
//    }

    public Predmet(HashMap<String, String> mapa)
    {
        this.zkratka = mapa.get("zkratka");
        this.katedra = mapa.get("katedra");
        this.nazev = mapa.get("nazev");
        this.kreditu = Integer.parseInt(mapa.get("kreditu"));
        this.doporucenyRocnik = Integer.parseInt(mapa.get("doporucenyRocnik"));
        this.semestr = Semestr.valueOf(mapa.get("doporucenySemestr"));
        this.rozsah = mapa.get("rozsah");
        this.zakonceni = mapa.get("typZk");
        String [] hodiny = this.rozsah.split("\\+");
        this.hodinPrednasek = Integer.parseInt(hodiny[0]);
        this.hodinCviceni = Integer.parseInt(hodiny[1]);
    }


    public String getZkratka() {
        return zkratka;
    }

    @Override
    public String toString() {
        return zkratka + " (" + rozsah + ") " + zakonceni + " " + kreditu + "kr.";
    }

    public String info() {
        return zkratka;
    }
}
