package model;

import enums.EPol;

import java.util.ArrayList;

public class Musterija extends Korisnik {

    private ArrayList<Voznja> njegoveVoznje;

    public Musterija() {
        this.njegoveVoznje = new ArrayList<Voznja>();
    }

    public Musterija(int id, String korisnickoIme, String lozinka, String ime, String prezime, String jmbg,
                     String adresa, EPol pol, String brojTelefona, ArrayList<Voznja> voznje, boolean obrisan) {
        super(id, korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan);
        this.njegoveVoznje = voznje;
    }

    public ArrayList<Voznja> getNjegoveVoznje() {
        return njegoveVoznje;
    }
    public void setNjegoveVoznje(ArrayList<Voznja> njegoveVoznje) {
        this.njegoveVoznje = njegoveVoznje;
    }

    @Override
    public String toString() {
        return "Musterija =" +
                "Njegove voznje: " + njegoveVoznje +
                ", ID: " + id +
                ", Korisnicko ime: " + korisnickoIme +
                ", Lozinka: " + lozinka +
                ", Ime: " + ime +
                ", Prezime: " + prezime +
                ", JMBG: " + jmbg +
                ", Adresa: " + adresa +
                ", Pol: " + pol +
                ", Broj telefona: " + brojTelefona
                + ", Obrisan: " + obrisan;
    }
}