package model;

import enums.EModelAutomobila;
import enums.EProizvodjacAutomobila;
import enums.EVrstaTaksiVozila;

public class Automobil {

    private int brojTaksiVozila;
    private EModelAutomobila model;
    private EProizvodjacAutomobila proizvodjac;
    private String godinaProizvodnje;
    private String registracija;
    private EVrstaTaksiVozila vrstaTaksiVozila;
    private boolean obrisan;

    public Automobil() {
        this.brojTaksiVozila = 0;
        this.model = null;
        this.proizvodjac = null;
        this.godinaProizvodnje = "";
        this.registracija = "";
        this.vrstaTaksiVozila = null;
        this.obrisan = false;
    }

    public Automobil(int brojTaksiVozila, EModelAutomobila model, EProizvodjacAutomobila proizvodjac, String godinaProizvodnje,
                     String registracija, EVrstaTaksiVozila vrstaTaksiVozila, boolean obrisan) {
        super();
        this.brojTaksiVozila = brojTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godinaProizvodnje = godinaProizvodnje;
        this.registracija = registracija;
        this.vrstaTaksiVozila = vrstaTaksiVozila;
        this.obrisan = obrisan;
    }

    public int getBrojTaksiVozila() {
        return brojTaksiVozila;
    }
    public void setBrojTaksiVozila(int brojTaksiVozila) {
        this.brojTaksiVozila = brojTaksiVozila;
    }

    public EModelAutomobila getModel() {
        return model;
    }
    public void setModel(EModelAutomobila model) {
        this.model = model;
    }

    public EProizvodjacAutomobila getProizvodjac() {
        return proizvodjac;
    }
    public void setProizvodjac(EProizvodjacAutomobila proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getGodinaProizvodnje() {
        return godinaProizvodnje;
    }
    public void setGodinaProizvodnje(String godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public String getRegistracija() {
        return registracija;
    }
    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public EVrstaTaksiVozila getVrstaTaksiVozila() {
        return vrstaTaksiVozila;
    }
    public void setVrstaTaksiVozila(EVrstaTaksiVozila vrstaTaksiVozila) {
        this.vrstaTaksiVozila = vrstaTaksiVozila;
    }

    public boolean isObrisan() {
        return obrisan;
    }
    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }

    @Override
    public String toString() {
        return "Autmobil = " +
                        "Broj taksi vozila: " + brojTaksiVozila +
                        ", Model: " + model +
                        ", Proizvodjac: " + proizvodjac +
                        ", Godina proizvodnje: " + godinaProizvodnje +
                        ", Registracija: " + registracija +
                        ", Vrsta taksi vozila: " + vrstaTaksiVozila +
                        ", Obrisan: " + obrisan;
    }
}