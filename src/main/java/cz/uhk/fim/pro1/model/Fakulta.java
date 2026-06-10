package cz.uhk.fim.pro1.model;

import cz.uhk.fim.pro1.fileOperations.CsvOperations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static cz.uhk.fim.pro1.model.RozvrhovaAkce.vytvorRA;

public class Fakulta {

    public static final int AR_0 = 2025;

    public enum Obor {
        AI2, AI3, IM2, IM3, EAM, ISB3;
    }

    public enum FormaStudia {
        PREZ, KOMB;
    }

    String nazev;
    String zkratka;
    int aktualniAR;

    Semestr aktualniSemestr() {
        return (LocalDate.now().isBefore(ar().zacatekLS) ) ? Semestr.ZS : Semestr.LS;
    }

    protected List<AkademickyRok> archiv;

    HashMap<String,Student> studenti;
    HashMap<String,Ucitel> ucitele;
    HashMap<String,Predmet> predmety;
    HashMap<String,Ucebna> ucebny;

    public Fakulta(String nazev, String zkratka) {
        this.nazev = nazev;
        this.zkratka = zkratka;
        studenti = new HashMap<>();
        ucitele = new HashMap<>();
        predmety = new HashMap<>();
        ucebny = new HashMap<>();
        archiv = new ArrayList<>();
        init();
    }

    public void init() {
        load();
        pridejStudenty();
        upravUcitele();
        LocalDate today = LocalDate.now();
        int thisYear = today.getYear();
        for (int y = AR_0; y <= thisYear; y++) {
            archiv.add(new AkademickyRok(y, new Rozvrh()));
        }
        if (today.isBefore(ar(thisYear).zacatek))
            aktualniAR = thisYear-1;
        else
            aktualniAR = thisYear;
        pridejRA();
    }


    public Rozvrh getAktualniRozvrh() {
        return ar().getHlavniRozvrh();
    }

    public AkademickyRok ar(int rok) {
        return archiv.get(rok-AR_0);
    }

    public AkademickyRok ar() {
        return ar(aktualniAR);
    }

    public void pridejStudenty(){
        studenti.put("dolenja1", new Student("Jakub", "Dolének", "jakub.dolenek@uhk.cz", "dolenja1", Obor.AI3, FormaStudia.PREZ));
        studenti.put("mrskoma1", new Student("Marek", "Mrskoč", "marek.mrskoc@uhk.cz", "mrskoma1", Obor.AI3, FormaStudia.PREZ));
    }

    public void upravUcitele() {
        Ucitel bau = ucitele.get("246023");
        if (bau != null) {
            bau.username = "bauerpe1";
            System.out.println(bau);
        }
    }

    public void pridejRA() {
        // Kontrola
        if (predmety.isEmpty() || ucebny.isEmpty() || ucitele.isEmpty()) {
            System.err.println("Warning: Cannot automate pridejRA(). Maps are empty. Check CSV files.");
            return;
        }

        // Nactení dat
        Predmet nahodnyPredmet = predmety.values().iterator().next();
        Ucebna nahodnaUcebna = ucebny.values().iterator().next();
        Ucitel nahodnyUcitel = ucitele.values().iterator().next();

        //1 pridani predmetu
        RozvrhovaAkce akce = vytvorRA(
                aktualniSemestr(),
                RozvrhovaAkce.TypAkce.cviceni,
                Den.Po,
                LocalTime.of(14, 55),
                LocalTime.of(16, 30),
                nahodnyPredmet,
                nahodnaUcebna,
                nahodnyUcitel
        );

        //2 pridani predmetu
        RozvrhovaAkce a = vytvorRA(
                aktualniSemestr(),
                RozvrhovaAkce.TypAkce.cviceni,
                Den.St,
                LocalTime.of(8, 15),
                LocalTime.of(9, 50),
                nahodnyPredmet,
                nahodnaUcebna,
                nahodnyUcitel
        );


        pridejAkci(akce);
        pridejAkci(a);
    }

    public void pridejAkci(RozvrhovaAkce akce, int rok) {
        ar(rok).pridejAkci(akce);
    }

    public void pridejAkci(RozvrhovaAkce akce) {
        pridejAkci(akce, aktualniAR);
    }

    public void load() {
        CsvOperations.loadPredmetyMap("predmety.csv", this.predmety);
        CsvOperations.loadUciteleMap("ucitele.csv", this.ucitele);
        CsvOperations.loadUcebnyMap("mistnosti.csv", this.ucebny);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("Nazev: ").append(nazev).append("\n");
        b.append("Zkratka: ").append(zkratka).append("\n");
        b.append("Studenti: ").append(studenti.values()).append("\n");
        b.append("Ucitele: ").append(ucitele.values()).append("\n");
        b.append("Predmety: ").append(predmety.values()).append("\n");
        b.append("Ucebny: ").append(ucebny.values()).append("\n");
        b.append("Akademicky rok: ").append(aktualniAR).append(" " + aktualniSemestr()).append("\n");
        b.append("Rozvrhove akce: ").append(ar().rozvrhoveAkce).append("\n");
        b.append("Archiv: ").append("\n");
        b.append("RA: ").append(archiv).append("\n");

        return b.toString();
    }
}