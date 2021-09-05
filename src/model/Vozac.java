package model;

import enums.EPol;

import java.util.ArrayList;

public class Vozac extends Korisnik {

    private double plata;
    private String brojClanskeKarte;
    private Automobil automobil;
    private ArrayList<Voznja> voznje;

    public Vozac() {
        this.plata = 0;
        this.brojClanskeKarte = "";
        this.automobil = new Automobil();
        this.voznje = new ArrayList<Voznja>();
    }



    public Vozac(int id, String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa,
                 EPol pol, String brojTelefona, double plata, String brojClanskeKarte,
                 Automobil automobil, boolean obrisan, ArrayList<Voznja> voznje) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan);
        this.plata = plata;
        this.brojClanskeKarte = brojClanskeKarte;
        this.automobil = automobil;
        this.voznje = voznje;
    }

    public double getPlata() {
        return plata;
    }
    public void setPlata(double plata) {
        this.plata = plata;
    }

    public String getBrojClanskeKarte() {
        return brojClanskeKarte;
    }
    public void setBrojClanskeKarte(String brojClanskeKarte) {
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public Automobil getAutomobil() {
        return automobil;
    }
    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    public ArrayList<Voznja> getVoznje() {
        return voznje;
    }
    public void setVoznje(ArrayList<Voznja> voznje) {
        this.voznje = voznje;
    }

    @Override
    public String toString() {
        return "Vozac =" +
                "ID: " + id +
                " Plata: " + plata +
                ", Broj clanske karte: " + brojClanskeKarte +
                ", Automobil: " + automobil +
                ", Voznje: " + voznje +
                ", Korisnicko ime: " + korisnickoIme +
                ", Lozinka: " + lozinka +
                ", Ime: " + ime +
                ", Prezime: " + prezime +
                ", JMBG: " + jmbg +
                ", Adresa: " + adresa +
                ", Pol: " + pol +
                ", Broj telefona: " + brojTelefona +
                ", Obrisan: " + obrisan;
    }
}
