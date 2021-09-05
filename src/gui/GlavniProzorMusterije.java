package gui;

import gui.formeZaPrikaz.NaruciVoznjuProzor;
import model.Musterija;
import util.SveListe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlavniProzorMusterije extends JFrame {

    private JMenuBar mainMenu = new JMenuBar();
    private JMenu rezervisiVoznju = new JMenu("Rezervisi voznju");
    private JMenuItem putemTelefonaItem = new JMenuItem("Telefonom");

    private SveListe sl;
    private Musterija prijavljeniKorisnik;

    public GlavniProzorMusterije(SveListe sl, Musterija prijavljeniKorisnik) {
        this.sl = sl;
        this.prijavljeniKorisnik = prijavljeniKorisnik;
        setTitle("Musterija: " + prijavljeniKorisnik.getKorisnickoIme());
        setSize(500, 500);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();
        initActions();
    }

    private void initMenu() {
        setJMenuBar(mainMenu);
        mainMenu.add(rezervisiVoznju);
        rezervisiVoznju.add(putemTelefonaItem);
    }

    private void initActions() {
        putemTelefonaItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                NaruciVoznjuProzor nvp = new NaruciVoznjuProzor(sl, prijavljeniKorisnik);
                nvp.setVisible(true);

            }
        });
    }
}