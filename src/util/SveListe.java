package util;

import enums.*;
import model.*;

import java.io.*;
import java.util.ArrayList;

public class SveListe {

    private ArrayList<Korisnik> korisnici;
    private ArrayList<Vozac> vozaci;
    private ArrayList<Musterija> musterije;
    private ArrayList<Dispecer> dispeceri;
    private ArrayList<Automobil> automobili;
    private ArrayList<TaksiSluzba> taksiSluzbe;
    private ArrayList<Voznja> voznje;

    public SveListe() {
        this.korisnici = new ArrayList<Korisnik>();
        this.vozaci = new ArrayList<Vozac>();
        this.musterije = new ArrayList<Musterija>();
        this.dispeceri = new ArrayList<Dispecer>();
        this.automobili = new ArrayList<Automobil>();
        this.taksiSluzbe = new ArrayList<TaksiSluzba>();
        this.voznje = new ArrayList<Voznja>();
    }

    public ArrayList<Vozac> getVozaci() {
        return vozaci;
    }

    public void setVozaci(ArrayList<Vozac> vozaci) {
        this.vozaci = vozaci;
    }

    public void dodajVozaca(Vozac vozac) {
        this.vozaci.add(vozac);
    }

    public void obrisiVozaca(Vozac vozac) {
        this.vozaci.remove(vozac);
    }

    public Vozac nadjiVozaca(int id) {
        for (Vozac vozac : vozaci) {
            if (vozac.getId() == id) {
                return vozac;
            }
        }
        return null;
    }

    public Vozac izmeniVozaca(Vozac v) {
        for(Vozac vozac: vozaci) {
            if(vozac.getId()==v.getId()) {
                vozac.setIme(v.getIme());
                vozac.setPrezime(v.getPrezime());
            }
            else {
                System.out.println("Ne postoji taj Vozac");
            }
        }
        return v;
    }

    //Geteri, seteri, dodavanje, brisanje

    public ArrayList<Musterija> getMusterije() {
        return musterije;
    }

    public void setMusterije(ArrayList<Musterija> musterije) {
        this.musterije = musterije;
    }

    public void dodajMusteriju(Musterija musterija) {
        this.musterije.add(musterija);
    }

    public void obrisiDispecera(Musterija musterija) {
        this.musterije.remove(musterija);
    }

    public ArrayList<Dispecer> getDispeceri() {
        return dispeceri;
    }

    public void setDispeceri(ArrayList<Dispecer> dispeceri) {
        this.dispeceri = dispeceri;
    }

    public void dodajDispecera(Dispecer dispecer) {
        this.dispeceri.add(dispecer);
    }

    public void obrisiDispecera(Dispecer dispecer) {
        this.dispeceri.remove(dispecer);
    }

    public ArrayList<Automobil> getAutomobili() {
        return automobili;
    }

    public void setAutomobili(ArrayList<Automobil> automobili) {
        this.automobili = automobili;
    }

    public void dodajAutomobil(Automobil automobil) {
        this.automobili.add(automobil);
    }

    public void obrisiAutomobil(Automobil automobil) {
        this.automobili.remove(automobil);
    }

    public ArrayList<TaksiSluzba> getTaksiSluzbe() {
        return taksiSluzbe;
    }

    public void setTaksiSluzbe(ArrayList<TaksiSluzba> taksiSluzbe) {
        this.taksiSluzbe = taksiSluzbe;
    }

    public void dodajTaksiSluzbu(TaksiSluzba taksiSluzba) {
        this.taksiSluzbe.add(taksiSluzba);
    }

    public void obrisiTaksiSluzbu(TaksiSluzba taksiSluzba) {
        this.taksiSluzbe.remove(taksiSluzba);
    }

    public ArrayList<Voznja> getVoznje() {
        return voznje;
    }

    public void setVoznje(ArrayList<Voznja> voznje) {
        this.voznje = voznje;
    }

    public void dodajVoznju(Voznja voznja) {
        this.voznje.add(voznja);
    }

    public void obrisiVoznju(Voznja voznja) {
        this.voznje.remove(voznja);
    }

    public ArrayList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ArrayList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public void ucitajDispecere(String putanja){
        File file = new File(putanja);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linija = null;
            while((linija = reader.readLine()) != null) {
                String[] sp = linija.split("\\|");
                int id = Integer.parseInt(sp[0]);
                String korisnickoIme = sp[1];
                String lozinka = sp[2];
                String ime = sp[3];
                String prezime = sp[4];
                String jmbg = sp[5];
                String adresa = sp[6];
                EPol pol = EPol.values()[Integer.parseInt(sp[7])];
                String brojTelefona = sp[8];
                double plata = Double.parseDouble(sp[9]);
                String brojTelefonskeLinije = sp[10];
                ETelefonskaOdeljenja telefonskaOdeljenja = ETelefonskaOdeljenja.values()[Integer.parseInt(sp[11])];
                Boolean obrisan = Boolean.parseBoolean(sp[12]);

                Dispecer dispecer = new Dispecer(id, korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plata, brojTelefonskeLinije, telefonskaOdeljenja, obrisan);
                dispeceri.add(dispecer);
                korisnici.add(dispecer);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom citanja dispecera iz datoteke");
        }
    }

    public void snimiDispecere(String putanja) {
        File file = new File("src/files/dispeceri.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Dispecer dispecer : dispeceri) {
                String linija_za_upis = dispecer.getId() + "|" + dispecer.getKorisnickoIme() + "|" + dispecer.getLozinka() + "|"
                        + dispecer.getIme() + "|" + dispecer.getPrezime() + "|"
                        + dispecer.getJmbg() + "|" + dispecer.getAdresa() + "|"
                        + dispecer.getPol().ordinal() + "|" + dispecer.getBrojTelefona() + "|"
                        + dispecer.getPlata() + "|" + dispecer.getBrojTelefonskeLinije() + "|"
                        + dispecer.getTelefonskaOdeljenja().ordinal() + "|" + dispecer.isObrisan() + "\n";
                writer.write(linija_za_upis);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Greska prilikom upisa dispecera u fajl.");
        }
    }
/*    public void snimiDispecere(String putanja) {

        try {
            File file = new File("src/files/dispeceri.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String sadrzaj = "";
            for (Dispecer dispecer : dispeceri) {
                sadrzaj += dispecer.getId() + "|" + dispecer.getKorisnickoIme() + "|" + dispecer.getLozinka() + "|"
                        + dispecer.getIme() + "|" + dispecer.getPrezime() + "|"
                        + dispecer.getJmbg() + "|" + dispecer.getAdresa() + "|"
                        + dispecer.getPol().ordinal() + "|" + dispecer.getBrojTelefona() + "|"
                        + dispecer.getPlata() + "|" + dispecer.getBrojTelefonskeLinije() + "|"
                        + dispecer.getTelefonskaOdeljenja().ordinal() + "|" + dispecer.isObrisan() + "\n";
            }

            writer.write(sadrzaj);
            writer.close();
        } catch (Exception e) {
            System.out.println("Greska prilikom upisa dispecera u fajl.");
        }
    }*/

    public void ucitajMusterije(String putanja){
        File file = new File(putanja);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linija = null;
            while((linija = reader.readLine()) != null) {
                String[] sp = linija.split("\\|");
                int id = Integer.parseInt(sp[0]);
                String korisnickoIme = sp[1];
                String lozinka = sp[2];
                String ime = sp[3];
                String prezime = sp[4];
                String jmbg = sp[5];
                String adresa = sp[6];
                EPol pol = EPol.values()[Integer.parseInt(sp[7])];
                String brojTelefona = sp[8];
                Boolean obrisan = Boolean.parseBoolean(sp[9]);
                ArrayList<Voznja> njegoveVoznje = new ArrayList<Voznja>();

                Musterija musterija = new Musterija(id, korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, njegoveVoznje, obrisan);
                musterije.add(musterija);
                korisnici.add(musterija);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom citanja musterija iz datoteke");
        }
    }

    public void snimiMusterije(String putanja) {
        File file = new File(putanja);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Musterija musterija : musterije) {
                String linija_za_upis  = musterija.getId() + "|" + musterija.getKorisnickoIme() + "|" + musterija.getLozinka() + "|"
                        + musterija.getIme() + "|" + musterija.getPrezime() + "|"
                        + musterija.getJmbg() + "|" + musterija.getAdresa() + "|"
                        + musterija.getPol().ordinal() + "|" + musterija.getBrojTelefona() + "|" + musterija.isObrisan() + "\n";
                writer.write(linija_za_upis);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Greska prilikom upisa musterija u fajl.");
        }
    }

    public void ucitajAutomobile(String putanja) {
        File file = new File(putanja);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linija = null;
            while((linija = reader.readLine()) != null) {
                String[] sp = linija.split("\\|");
                int brojTaksiVozila = Integer.parseInt(sp[0]);
                EModelAutomobila model = EModelAutomobila.values()[Integer.parseInt(sp[1])];
                EProizvodjacAutomobila proizvodjac = EProizvodjacAutomobila.values()[Integer.parseInt(sp[2])];
                String godinaProizvodnje = sp[3];
                String registracija = sp[4];
                EVrstaTaksiVozila vrstaTaksiVozila = EVrstaTaksiVozila.values()[Integer.parseInt(sp[5])];
                Boolean obrisan = Boolean.parseBoolean(sp[6]);


                Automobil automobil = new Automobil(brojTaksiVozila, model, proizvodjac, godinaProizvodnje, registracija, vrstaTaksiVozila, obrisan);
                automobili.add(automobil);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom citanja automobila iz datoteke");
        }
    }

    public void snimiAutomobile() {
        File file = new File("src/files/automobili.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Automobil automobil : automobili) {
                String linija_za_upis = automobil.getBrojTaksiVozila() + "|" + automobil.getModel().ordinal() + "|"
                        + automobil.getProizvodjac().ordinal() + "|" + automobil.getGodinaProizvodnje() + "|"
                        + automobil.getRegistracija() + "|" + automobil.getVrstaTaksiVozila().ordinal() + "|" + automobil.isObrisan() + "\n";
                writer.write(linija_za_upis);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Greska prilikom upisa musterija u fajl.");
        }
    }

    public void ucitajVozace(String putanja){
        File file = new File(putanja);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linija = null;
            while((linija = reader.readLine()) != null) {
                String[] sp = linija.split("\\|");
                int id = Integer.parseInt(sp[0]);
                String korisnickoIme = sp[1];
                String lozinka = sp[2];
                String ime = sp[3];
                String prezime = sp[4];
                String jmbg = sp[5];
                String adresa = sp[6];
                EPol pol = EPol.values()[Integer.parseInt(sp[7])];
                String brojTelefona = sp[8];
                Double plata = Double.parseDouble(sp[9]);
                String brojClanskeKarte = sp[10];
                Automobil automobil = NadjiAutomobil(Integer.parseInt(sp[11]));
                Boolean obrisan = Boolean.parseBoolean(sp[12]);
                ArrayList<Voznja> voznje = new ArrayList<Voznja>();

                Vozac vozac = new Vozac(id, korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plata, brojClanskeKarte, automobil, obrisan, voznje);
                vozaci.add(vozac);
                korisnici.add(vozac);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom citanja vozaca iz datoteke");
        }
    }

    public void snimiVozace(String putanja) {
        File file = new File(putanja);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Vozac vozac : vozaci) {
                String linija_za_upis = vozac.getId() + "|" + vozac.getKorisnickoIme() + "|" + vozac.getLozinka() + "|"
                        + vozac.getIme() + "|" + vozac.getPrezime() + "|"
                        + vozac.getJmbg() + "|" + vozac.getAdresa() + "|"
                        + vozac.getPol().ordinal() + "|" + vozac.getBrojTelefona() + "|" + vozac.getPlata() + "|"
                        + vozac.getBrojClanskeKarte() + "|" + String.valueOf(vozac.getAutomobil().getBrojTaksiVozila()) + "|"
                        + vozac.isObrisan() + "\n";
                writer.write(linija_za_upis);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Greska prilikom upisa vozaca u fajl.");
        }
    }

    public void ucitajVoznje(String putanja){

        try {
            File file = new File(putanja);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linija = null;
            while((linija = reader.readLine()) != null) {
                String[] sp = linija.split("\\|");
                if(Integer.parseInt(sp[9]) == 3) {

                    int id = Integer.parseInt(sp[0]);
                    String datum = sp[1];
                    String vremePorudzbine = sp[2];
                    String adresaPolaska = sp[3];
                    String adresaDestinacije = sp[4];
                    Musterija musterija = NadjiMusteriju(Integer.parseInt(sp[5]));
                    Vozac vozac = nadjiVozaca(Integer.parseInt(sp[6]));
                    String brojPredjenihKilometara = sp[7];
                    String trajanjeVoznje = sp[8];
                    EStatusVoznje status = EStatusVoznje.values()[Integer.parseInt(sp[9])];
                    Boolean obrisan = Boolean.parseBoolean(sp[10]);

                    Voznja voznja = new Voznja(id, datum, vremePorudzbine, adresaPolaska, adresaDestinacije, musterija, vozac, brojPredjenihKilometara, trajanjeVoznje, status, obrisan);
                    vozac.getVoznje().add(voznja);
                    musterija.getNjegoveVoznje().add(voznja);

                    voznje.add(voznja);
                }
                else if(Integer.parseInt(sp[9]) == 0) {
                    int id = Integer.parseInt(sp[0]);
                    String datum = sp[1];
                    String vremePorudzbine = sp[2];
                    String adresaPolaska = sp[3];
                    String adresaDestinacije = sp[4];
                    Musterija musterija = NadjiMusteriju(Integer.parseInt(sp[5]));

                    EStatusVoznje status = EStatusVoznje.values()[Integer.parseInt(sp[9])];
                    Boolean obrisan = Boolean.parseBoolean(sp[10]);

                    Voznja voznja = new Voznja();
                    voznja.setId(id);
                    voznja.setDatum(datum);
                    voznja.setVremePorudzbine(vremePorudzbine);
                    voznja.setAdresaPolaska(adresaPolaska);
                    voznja.setAdresaDestinacije(adresaDestinacije);
                    voznja.setMusterija(musterija);
                    voznja.setStatus(status);
                    voznja.setObrisan(obrisan);
                    musterija.getNjegoveVoznje().add(voznja);

                    voznje.add(voznja);
                }else if(Integer.parseInt(sp[9]) == 1) {
                    int id = Integer.parseInt(sp[0]);
                    String datum = sp[1];
                    String vremePorudzbine = sp[2];
                    String adresaPolaska = sp[3];
                    String adresaDestinacije = sp[4];
                    Musterija musterija = NadjiMusteriju(Integer.parseInt(sp[5]));
                    Vozac vozac = nadjiVozaca(Integer.parseInt(sp[6]));
                    EStatusVoznje status = EStatusVoznje.values()[Integer.parseInt(sp[9])];
                    Boolean obrisan = Boolean.parseBoolean(sp[10]);

                    Voznja voznja = new Voznja();
                    voznja.setId(id);
                    voznja.setDatum(datum);
                    voznja.setVremePorudzbine(vremePorudzbine);
                    voznja.setAdresaPolaska(adresaPolaska);
                    voznja.setAdresaDestinacije(adresaDestinacije);
                    voznja.setMusterija(musterija);
                    voznja.setVozac(vozac);
                    voznja.setStatus(status);
                    voznja.setObrisan(obrisan);
                    musterija.getNjegoveVoznje().add(voznja);

                    voznje.add(voznja);
                }

                else if(Integer.parseInt(sp[9]) == 2) {
                    int id = Integer.parseInt(sp[0]);
                    String datum = sp[1];
                    String vremePorudzbine = sp[2];
                    String adresaPolaska = sp[3];
                    String adresaDestinacije = sp[4];
                    Musterija musterija = NadjiMusteriju(Integer.parseInt(sp[5]));
                    Vozac vozac = nadjiVozaca(Integer.parseInt(sp[6]));
                    EStatusVoznje status = EStatusVoznje.values()[Integer.parseInt(sp[9])];
                    Boolean obrisan = Boolean.parseBoolean(sp[10]);

                    Voznja voznja = new Voznja();
                    voznja.setId(id);
                    voznja.setDatum(datum);
                    voznja.setVremePorudzbine(vremePorudzbine);
                    voznja.setAdresaPolaska(adresaPolaska);
                    voznja.setAdresaDestinacije(adresaDestinacije);
                    voznja.setMusterija(musterija);
                    voznja.setVozac(vozac);
                    voznja.setStatus(status);
                    voznja.setObrisan(obrisan);
                    musterija.getNjegoveVoznje().add(voznja);

                    voznje.add(voznja);
                }

                else if(Integer.parseInt(sp[9]) == 4) {
                    int id = Integer.parseInt(sp[0]);
                    String datum = sp[1];
                    String vremePorudzbine = sp[2];
                    String adresaPolaska = sp[3];
                    String adresaDestinacije = sp[4];
                    Musterija musterija = NadjiMusteriju(Integer.parseInt(sp[5]));
                    Vozac vozac = nadjiVozaca(Integer.parseInt(sp[6]));
                    String brojPredjenihKilometara = sp[7];
                    String trajanjeVoznje = sp[8];
                    EStatusVoznje status = EStatusVoznje.values()[Integer.parseInt(sp[9])];
                    Boolean obrisan = Boolean.parseBoolean(sp[10]);

                    Voznja voznja = new Voznja();
                    voznja.setId(id);
                    voznja.setDatum(datum);
                    voznja.setVremePorudzbine(vremePorudzbine);
                    voznja.setAdresaPolaska(adresaPolaska);
                    voznja.setAdresaDestinacije(adresaDestinacije);
                    voznja.setMusterija(musterija);
                    voznja.setVozac(vozac);
                    voznja.setBrojPredjenihKilometara(brojPredjenihKilometara);
                    voznja.setTrajanjeVoznje(trajanjeVoznje);
                    voznja.setStatus(status);
                    voznja.setObrisan(obrisan);
                    musterija.getNjegoveVoznje().add(voznja);

                    voznje.add(voznja);
                }



            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom citanja voznje iz datoteke");
        }
    }

    public void snimiVoznje(String putanja) {

        try {
            File file = new File(putanja);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String sadrzaj = "";
            for (Voznja voznja : voznje) {
                if(voznja.getStatus() == EStatusVoznje.ZAVRSENA) {


                    sadrzaj += voznja.getId() + "|" + voznja.getDatum() + "|"
                            + voznja.getVremePorudzbine() + "|"
                            + voznja.getAdresaPolaska() + "|" + voznja.getAdresaDestinacije() + "|"
                            + String.valueOf(voznja.getMusterija().getId()) + "|"
                            + String.valueOf(voznja.getVozac().getId()) + "|"
                            + voznja.getBrojPredjenihKilometara() + "|" + voznja.getTrajanjeVoznje() + "|"
                            + voznja.getStatus().ordinal() + "|" + voznja.isObrisan() + "\n";

                }
                else if(voznja.getStatus() == EStatusVoznje.KREIRANA) {

                    sadrzaj += voznja.getId() + "|" + voznja.getDatum() + "|"
                            + voznja.getVremePorudzbine() + "|"
                            + voznja.getAdresaPolaska() + "|" + voznja.getAdresaDestinacije() + "|"
                            + String.valueOf(voznja.getMusterija().getId()) + "|"
                            + "--" + "|"
                            + "--" + "|" + "--" + "|"
                            + voznja.getStatus().ordinal() + "|" + voznja.isObrisan() + "\n";
                }

                else if(voznja.getStatus() == EStatusVoznje.DODELJENA) {

                    sadrzaj += voznja.getId() + "|" + voznja.getDatum() + "|"
                            + voznja.getVremePorudzbine() + "|"
                            + voznja.getAdresaPolaska() + "|" + voznja.getAdresaDestinacije() + "|"
                            + String.valueOf(voznja.getMusterija().getId()) + "|"
                            + String.valueOf(voznja.getVozac().getId()) + "|"
                            + "--" + "|" + "--" + "|"
                            + voznja.getStatus().ordinal() + "|" + voznja.isObrisan() + "\n";
                }

                else if(voznja.getStatus() == EStatusVoznje.ODBIJENA) {

                    sadrzaj += voznja.getId() + "|" + voznja.getDatum() + "|"
                            + voznja.getVremePorudzbine() + "|"
                            + voznja.getAdresaPolaska() + "|" + voznja.getAdresaDestinacije() + "|"
                            + String.valueOf(voznja.getMusterija().getId()) + "|"
                            + String.valueOf(voznja.getVozac().getId()) + "|"
                            + "--" + "|" + "--" + "|"
                            + voznja.getStatus().ordinal() + "|" + voznja.isObrisan() + "\n";
                }

                else if(voznja.getStatus() == EStatusVoznje.PRIHVACENA) {

                    sadrzaj += voznja.getId() + "|" + voznja.getDatum() + "|"
                            + voznja.getVremePorudzbine() + "|"
                            + voznja.getAdresaPolaska() + "|" + voznja.getAdresaDestinacije() + "|"
                            + String.valueOf(voznja.getMusterija().getId()) + "|"
                            + String.valueOf(voznja.getVozac().getId()) + "|"
                            + "--" + "|" + "--" + "|"
                            + voznja.getStatus().ordinal() + "|" + voznja.isObrisan() + "\n";
                }
            }
            writer.write(sadrzaj);
            writer.close();
        } catch (Exception e) {
            System.out.println("Greska prilikom upisa voznje u fajl.");
        }
    }

    public void ucitajTaksiSluzbe(String putanja){

        try {
            File file = new File(putanja);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linija = null;
            while((linija = reader.readLine()) != null) {
                String[] sp = linija.split("\\|");
                int pib = Integer.parseInt(sp[0]);
                String naziv = sp[1];
                String adresa = sp[2];
                Double cenaStartaVoznje = Double.parseDouble(sp[3]);
                Double cenaPoKilometru = Double.parseDouble(sp[4]);
                TaksiSluzba taksiSluzba = new TaksiSluzba(pib, naziv, adresa, cenaStartaVoznje, cenaPoKilometru);
                taksiSluzbe.add(taksiSluzba);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom citanja taksiSluzbe iz datoteke");
        }
    }

    public void snimiTaksiSluzbe() {
        File file = new File("src/files/taksiSluzbe.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String sadrzaj = "";
            for (TaksiSluzba taksiSluzba : taksiSluzbe) {
                String linija_za_upis = taksiSluzba.getPib() + "|" + taksiSluzba.getNaziv() + "|" + taksiSluzba.getAdresa() + "|"
                        + taksiSluzba.getCenaStartaVoznje() + "|" + taksiSluzba.getCenaPoKilometru() + "\n";
                writer.write(linija_za_upis);
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Greska prilikom upisa taksiSluzbe u fajl.");
        }
    }

    public Korisnik login(String korisnickoIme, String lozinka) {
        for (Korisnik korisnik : korisnici) {
            if(korisnik.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
                    korisnik.getLozinka().equals(lozinka) && !korisnik.isObrisan()) {
                return korisnik;
            }
        }
        return null;
    }




    //Trazimo neobrisane korisnike

    public ArrayList<Dispecer> sviNeobrisaniDispeceri() {
        ArrayList<Dispecer> neobrisani = new ArrayList<Dispecer>();
        for (Dispecer dispecer : dispeceri) {
            if(!dispecer.isObrisan()) {
                neobrisani.add(dispecer);
            }
        }
        return neobrisani;
    }

    public ArrayList<Vozac> sviNeobrisaniVozaci() {
        ArrayList<Vozac> neobrisani = new ArrayList<Vozac>();
        for (Vozac vozac : vozaci) {
            if(!vozac.isObrisan()) {
                neobrisani.add(vozac);
            }
        }
        return neobrisani;
    }

    public ArrayList<Musterija> sveNeobrisaneMusterije() {
        ArrayList<Musterija> neobrisani = new ArrayList<Musterija>();
        for (Musterija musterija : musterije) {
            if(!musterija.isObrisan()) {
                neobrisani.add(musterija);
            }
        }
        return neobrisani;
    }

    public ArrayList<Automobil> sviNeobrisaniAutomobili() {
        ArrayList<Automobil> neobrisani = new ArrayList<Automobil>();
        for (Automobil automobil : automobili) {
            if(!automobil.isObrisan()) {
                neobrisani.add(automobil);
            }
        }
        return neobrisani;
    }

    public ArrayList<Voznja> sveNeobrisaneVoznje() {
        ArrayList<Voznja> neobrisane = new ArrayList<Voznja>();
        for (Voznja voznja : voznje) {
            if(!voznja.isObrisan()) {
                neobrisane.add(voznja);
            }
        }
        return neobrisane;
    }

// Pronalazenje po korisnikom imenu

    public Musterija NadjiMusterijuPoKorisnickomImenu(String korisnickoIme) {
        for(Musterija a : this.musterije) {
            if (a.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)) {
                return a;
            }
        }
        return null;
    }

    public Korisnik NadjiKorisnikaPoKorisnickomImenu(String korisnickoIme) {
        for(Korisnik a : this.korisnici) {
            if (a.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)) {
                return a;
            }
        }
        return null;
    }

    public Vozac NadjiVozacaPoKorisnickomImenu(String korisnickoIme) {
        for(Vozac a : this.vozaci) {
            if (a.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)) {
                return a;
            }
        }
        return null;
    }

    public Dispecer NadjiDispeceraPoKorisnickomImenu(String korisnickoIme) {
        for(Dispecer a : this.dispeceri) {
            if (a.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)) {
                return a;
            }
        }
        return null;
    }
// Pronalazenje korisnika po ID-u

    public Musterija NadjiMusteriju(int id) {
        for(Musterija a : this.musterije) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public Vozac NadjiVozaca(int id) {
        for(Vozac a : this.vozaci) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }



    public Dispecer NadjiDispecera(int id) {
        for(Dispecer a : this.dispeceri) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }



    public TaksiSluzba NadjiTaksiSluzbu(int pib) {
        for(TaksiSluzba a : this.taksiSluzbe) {
            if (a.getPib() == pib) {
                return a;
            }
        }
        return null;
    }

    public Voznja NadjiVoznju(int id) {
        for(Voznja a : this.voznje) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public Automobil NadjiAutomobil(int brojTaksiVozila) {
        for(Automobil a : this.automobili) {
            if (a.getBrojTaksiVozila() == brojTaksiVozila) {
                return a;
            }
        }
        return null;
    }
    //Pronalazenja automobila po registraciji
    public Automobil pronadjiAutomobilPoRegistraciji(String registracija) {
        for (Automobil automobil : this.sviNeobrisaniAutomobili()) {
            if(automobil.getRegistracija().equalsIgnoreCase(registracija)) {
                return automobil;
            }
        }
        return null;
    }


    //Specificne pretrage preko druge klase
    public Vozac pronadjiAutomobil(Automobil id) {
        for (Vozac vozac : this.vozaci) {
            if(vozac.getAutomobil() == id) {
                return vozac;
            }
        }
        return null;
    }

    public Vozac pronadjiVozaca(Voznja voznja) {
        for (Vozac vozac : sviNeobrisaniVozaci()) {
            if (vozac.getVoznje().contains(voznja)) {
                return vozac;
            }
        }
        return null;
    }

    public Voznja pronadjiVoznju(String adresaPolaska) {
        for (Vozac vozac : vozaci) {
            for(Voznja voznja : vozac.getVoznje()) {
                if(voznja.getAdresaPolaska().equalsIgnoreCase(adresaPolaska)) {
                    return voznja;
                }
            }
        }
        return null;
    }

/*    public void snimiSve() {
		snimiAutomobile();
		snimiDispecere();
		snimiMusterije();
		snimiTaksiSluzbe();
	    snimiVozace();
		snimiVoznje();
}*/

   private void snimiMusterije() {
    }

    private void snimiVozace() {
    }
    private void snimiDispecere() {
    }
    private void snimiVoznje() {
    }



}
