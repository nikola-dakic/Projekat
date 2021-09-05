package main;

import gui.LoginProzor;
import util.SveListe;

public class Main {

    public static String AUTOMOBILI_FAJL = "src/txt/automobili.txt";
    public static String DISPECERI_FAJL = "src/txt/dispeceri.txt";
    public static String MUSTERIJE_FAJL = "src/txt/musterije.txt";
    public static String TAKSI_SLUZBE_FAJL = "src/txt/taksiSluzbe.txt";
    public static String VOZACI_FAJL = "src/txt/vozaci.txt";
    public static String VOZNJE_FAJL = "src/txt/voznja.txt";

    public static void main(String[] args) {

        SveListe sl = new SveListe();
        sl.ucitajAutomobile(AUTOMOBILI_FAJL);
        sl.ucitajDispecere(DISPECERI_FAJL);
        sl.ucitajMusterije(MUSTERIJE_FAJL);
        sl.ucitajTaksiSluzbe(TAKSI_SLUZBE_FAJL);
        sl.ucitajVozace(VOZACI_FAJL);
        sl.ucitajVoznje(VOZNJE_FAJL);

        LoginProzor lp = new LoginProzor(sl);
        lp.setVisible(true);
    }
}
