package cz.uhk.fim.pro1.model;

import java.time.Duration;
import java.time.LocalTime;

public class Rozvrh {

    private final int N_DAYS = 7;
    private final int N_SLOTS = 16;
    public static final int DELKA_LEKCE = 50;

    public RozvrhovaAkce[][] data = new RozvrhovaAkce[N_DAYS][N_SLOTS];
    public static LocalTime[] casy;

    public Rozvrh() {
        casy = new LocalTime[N_SLOTS];

        casy[0] = LocalTime.of(7, 25);
        for (int i = 1; i < N_SLOTS; i++) {
            casy[i] = casy[i - 1].plusMinutes(DELKA_LEKCE);
        }
    }

    // OK: stabilní převod času na slot
    public int casNaSlot(LocalTime cas) {
        for (int i = 0; i < N_SLOTS; i++) {
            LocalTime start = casy[i];
            LocalTime end = start.plusMinutes(DELKA_LEKCE);

            if (!cas.isBefore(start) && cas.isBefore(end)) {
                return i;
            }
        }
        return -1;
    }

    // Jak dlouhe jsou sloty
    private int delkaNaSloty(LocalTime od, LocalTime doCas) {
        if (od == null || doCas == null) return 0;

        long min = Duration.between(od, doCas).toMinutes();

        if (min <= 0) return 0;

        return (int) Math.ceil(min / (double) DELKA_LEKCE);
    }

    public boolean lzeZapsat(RozvrhovaAkce akce) {
        if (akce == null || akce.getDen() == null) return false;

        int den = akce.getDen().ordinal();

        if (den < 0 || den >= N_DAYS) return false;

        int start = casNaSlot(akce.casOd);
        int delka = delkaNaSloty(akce.casOd, akce.casDo);

        if (start < 0 || delka <= 0) return false;
        if (start + delka > N_SLOTS) return false;

        for (int i = start; i < start + delka; i++) {
            if (data[den][i] != null) return false;
        }

        return true;
    }

    public void vlozAkci(RozvrhovaAkce akce) {

        if (akce == null || akce.getDen() == null) return;

        int den = akce.getDen().ordinal();

        if (den < 0 || den >= N_DAYS) return;

        int start = casNaSlot(akce.casOd);
        int delka = delkaNaSloty(akce.casOd, akce.casDo);

        //kontrolni vypis
//        System.out.println("OD: " + akce.casOd);
//        System.out.println("START SLOT: " + start);
//        System.out.println("DELKA: " + delka);
//        System.out.println("Akce: " + akce.ucitel);

        if (start < 0 || delka <= 0) return;
        if (start + delka > N_SLOTS) return;

        for (int i = start; i < start + delka; i++) {
            data[den][i] = akce;
        }
    }
}