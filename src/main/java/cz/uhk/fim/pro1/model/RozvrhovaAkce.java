package cz.uhk.fim.pro1.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RozvrhovaAkce {
    public enum TypAkce {prednaska,cviceni;}
    TypAkce typAkce;
    Semestr semestr;
    public LocalTime casOd;
    public LocalTime casDo;
    Den den;

    public Predmet predmet;
    Ucebna ucebna;
    int kapacita;
    Ucitel ucitel;
    List<Student>  studenti;

    //vyucovaci block a jeho konstrukce
    public RozvrhovaAkce(Semestr s, TypAkce typAkce, Den den, LocalTime casOd, LocalTime casDo, Predmet predmet, Ucebna ucebna, Ucitel ucitel) {
        assert casOd.isBefore(casDo);

        this.semestr = s;
        this.typAkce = typAkce;
        this.den = den;
        this.casOd = casOd;
        this.casDo = casDo;
        this.predmet = predmet;
        this.ucebna = ucebna;
        this.ucitel = ucitel;
        this.studenti = new ArrayList<>();
    }

    public static RozvrhovaAkce vytvorRA(Semestr semestr, TypAkce typAkce, Den den, LocalTime casOd, LocalTime casDo, Predmet predmet, Ucebna ucebna, Ucitel ucitel){
        return new RozvrhovaAkce(semestr, typAkce, den, casOd, casDo, predmet, ucebna, ucitel);
    }

    public Ucitel getUcitel() {
        return ucitel;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public Ucebna getUcebna() {
        return ucebna;
    }

    public Semestr getSemestr() {
        return semestr;
    }

    public Den getDen() {
        return den;
    }

    //neimplementovano kolik studentu na akci prihlaseno
    public int prihlaseno() {
        return studenti.size();
    }

    private boolean lzeZapsat(Student student) {
        return !studenti.contains(student) && studenti.size() < this.kapacita;
    }

    public boolean pridatStudenta(Student student) {
        if (lzeZapsat(student))
            return studenti.add(student);
        return false;
    }


    @Override
    public String toString() {
        return predmet.zkratka + ", " + typAkce + ", " + ucebna.zkratka + ", " + ucitel + ", " + casDo + ", " + casOd;
    }

}
