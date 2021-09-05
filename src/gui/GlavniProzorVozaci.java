package gui;

import gui.formeZaPrikaz.VoznjeZaVozacaProzor;
import model.Vozac;
import model.Voznja;
import util.SveListe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlavniProzorVozaci extends JFrame {

    private JMenuBar mainMenu = new JMenuBar();
    private JMenu voznjeMenu = new JMenu("Voznje");
    private JMenuItem voznjeItem = new JMenuItem("Voznje");

    private SveListe sl;
    private Vozac prijavljeniKorisnik;
    private Voznja voznja;

    public GlavniProzorVozaci(SveListe sl, Vozac prijavljeniKorisnik) {
        this.sl = sl;
        this.prijavljeniKorisnik = prijavljeniKorisnik;
        setTitle("Vozac: " + prijavljeniKorisnik.getKorisnickoIme());
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();
        initActions();
    }

    private void initMenu() {
        setJMenuBar(mainMenu);
        mainMenu.add(voznjeMenu);
        voznjeMenu.add(voznjeItem);
    }

    private void initActions() {

        voznjeItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                VoznjeZaVozacaProzor vzvp = new VoznjeZaVozacaProzor(sl, prijavljeniKorisnik);
                vzvp.setVisible(true);

            }
        });

    }


}