package cz.uhk.fim.pro1.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Osoba {

    Fakulta.Obor obor;
    int rok;

    List<Predmet> zapsanePredmety;
    Rozvrh rozvrh;

    //vysledky studia

    public Student (String jmeno, String prijmeni) {
        super(jmeno,prijmeni);
        Rozvrh rozvrh = new Rozvrh();
        zapsanePredmety = new ArrayList<>();
        this.rok = 1;
    }

    public Student(String jmeno, String prijmeni, String email, String username, Fakulta.Obor obor, Fakulta.FormaStudia formaStudia) {
        super(jmeno, prijmeni, email);
        this.username = username;
        this.obor = obor;
        Rozvrh rozvrh = new Rozvrh();
        zapsanePredmety = new ArrayList<>();
        this.rok = 1;
    }

    @Override
    public String toString() {
        String s = super.toString();
        s += ", " + username + ", rocnik: " + rok + ", obor: " + obor;
        return s;
    }


}
