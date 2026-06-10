package cz.uhk.fim.pro1.model;

public class Osoba {
    protected String jmeno;
    protected String prijmeni;
    protected String email;
    protected String username;
    protected String id;

    public Osoba(String jmeno, String prijmeni) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
    }

    public Osoba(String jmeno, String prijmeni, String email) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
    }

    public Osoba(String jmeno, String prijmeni, String email, String username) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return jmeno + " "  +  prijmeni;
    }
}
