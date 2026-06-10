package cz.uhk.fim.pro1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AkademickyRok {
    int rok;
    LocalDate zacatek;
    LocalDate zacatekLS;
    LocalDate konec;

    Rozvrh hlavniRozvrh;

    List<RozvrhovaAkce> rozvrhoveAkce;
    HashMap<String, Rozvrh>[] rozvrhy;


    public AkademickyRok(int rok, Rozvrh hlavniRozvrh) {
        this.rok = rok;
        this.hlavniRozvrh = hlavniRozvrh;

        zacatek = LocalDate.of(rok, 9, 1);
        zacatekLS = LocalDate.of(rok + 1, 2, 9);
        konec = LocalDate.of(rok + 1, 8, 31);

        this.rozvrhoveAkce = new ArrayList<>();

        this.rozvrhy = new HashMap[2];
        this.rozvrhy[0] = new HashMap<>();
        this.rozvrhy[1] = new HashMap<>();
    }


    public Rozvrh getHlavniRozvrh() {
        return this.hlavniRozvrh;
    }

    public Rozvrh rozvrhOsoby(Semestr semestr, String id) {
        return rozvrhy[semestr.ordinal()].get(id);
    }

    public Rozvrh rozvrhMistnosti(Semestr semestr, String zkratka) {
        return rozvrhy[semestr.ordinal()].get(zkratka);
    }

    public Rozvrh rozvrhPredmetu(Semestr semestr, String zkratka) {
        return rozvrhy[semestr.ordinal()].get(zkratka);
    }

    void pridejAkci(RozvrhovaAkce akce) {

        //Ucitel ucitel = akce.getUcitel();
        Ucebna ucebna = akce.getUcebna();
        Predmet predmet = akce.getPredmet();
        Semestr semestr = akce.getSemestr();

        rozvrhy[semestr.ordinal()].putIfAbsent(predmet.zkratka, new Rozvrh());
        //rozvrhy[semestr.ordinal()].putIfAbsent(ucitel.getId(), new Rozvrh());
        rozvrhy[semestr.ordinal()].putIfAbsent(ucebna.getZkratka(), new Rozvrh());

        Rozvrh rop = rozvrhPredmetu(semestr, predmet.zkratka);
        //Rozvrh rou = rozvrhOsoby(semestr, ucitel.getId());
        Rozvrh rom = rozvrhMistnosti(semestr, ucebna.getZkratka());

        int irop = rop.casNaSlot(akce.casOd);
        //int irou = rou.casNaSlot(akce.casOd);
        int irom = rom.casNaSlot(akce.casOd);


        //if (irop >= 0 && irou >= 0 && irom >= 0)
        if (irop >= 0 && irom >= 0) {

            rozvrhoveAkce.add(akce);

            rop.vlozAkci(akce);
            //rou.vlozAkci(akce); //rou odstranen jen by jsme ho vyuzili na dodaní jmena ucitele.
            rom.vlozAkci(akce);

            if (hlavniRozvrh != null) {
                hlavniRozvrh.vlozAkci(akce);
            }
        }
    }

    @Override
    public String toString() {
        return rok + ", RA: " + rozvrhoveAkce;
    }
}