package model;

public class TaksiSluzba {

    private int pib;
    private String naziv;
    private String adresa;
    private double cenaStartaVoznje;
    private double cenaPoKilometru;

    public TaksiSluzba() {
        this.pib = 0;
        this.naziv = "";
        this.adresa = "";
        this.cenaStartaVoznje = 0;
        this.cenaPoKilometru = 0;
    }

    public TaksiSluzba(int pib, String naziv, String adresa, double cenaStartaVoznje, double cenaPoKilometru) {
        super();
        this.pib = pib;
        this.naziv = naziv;
        this.adresa = adresa;
        this.cenaStartaVoznje = cenaStartaVoznje;
        this.cenaPoKilometru = cenaPoKilometru;
    }

    public int getPib() {
        return pib;
    }
    public void setPib(int pib) {
        this.pib = pib;
    }

    public String getNaziv() {
        return naziv;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public double getCenaStartaVoznje() {
        return cenaStartaVoznje;
    }
    public void setCenaStartaVoznje(double cenaStartaVoznje) {
        this.cenaStartaVoznje = cenaStartaVoznje;
    }

    public double getCenaPoKilometru() {
        return cenaPoKilometru;
    }
    public void setCenaPoKilometru(double cenaPoKilometru) {
        this.cenaPoKilometru = cenaPoKilometru;
    }


    @Override
    public String toString() {
        return "Taksi sluzba = " +
                "Pib: " + pib +
                ", Naziv: " + naziv +
                ", Adresa: " + adresa +
                ", Cena starata voznje: " + cenaStartaVoznje +
                ", Cena po kilometru: " + cenaPoKilometru;
    }
}
