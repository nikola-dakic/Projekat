package gui;

import gui.formeZaPrikaz.DispeceriProzor;
import gui.formeZaPrikaz.MusterijeProzor;
import gui.formeZaPrikaz.VozaciProzor;
import gui.formeZaPrikaz.VoznjeProzor;
import model.Dispecer;
import util.SveListe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlavniProzorDispeceri extends JFrame {

    private JMenuBar mainMenu = new JMenuBar();
    private JMenu dispeceriMenu = new JMenu("Dispeceri");
    private JMenuItem dispeceriItem = new JMenuItem("Prikaz dispecera");
    private JMenu MusterijeMenu = new JMenu("Musterije");
    private JMenuItem musterijeItem = new JMenuItem("Prikaz musterija");
    private JMenu VozaciMenu = new JMenu("Vozaci");
    private JMenuItem vozaciItem = new JMenuItem("Prikaz vozaca");
    private JMenu VoznjeMenu = new JMenu("Voznje");
    private JMenuItem voznjeItem = new JMenuItem("Prikaz voznji");

    private SveListe sl;
    private Dispecer prijavljeniKorisnik;

    public GlavniProzorDispeceri(SveListe sl, Dispecer prijavljeniKorisnik) {
        this.sl = sl;
        this.prijavljeniKorisnik = prijavljeniKorisnik;
        setTitle("Dispecer: " + prijavljeniKorisnik.getKorisnickoIme());
        setSize(560, 400);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();
        initActions();
    }

    private void initMenu() {
        setJMenuBar(mainMenu);
        mainMenu.add(dispeceriMenu);
        mainMenu.add(MusterijeMenu);
        mainMenu.add(VozaciMenu);
        mainMenu.add(VoznjeMenu);
        dispeceriMenu.add(dispeceriItem);
        MusterijeMenu.add(musterijeItem);
        VozaciMenu.add(vozaciItem);
        VoznjeMenu.add(voznjeItem);
    }

    private void initActions() {

        dispeceriItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                DispeceriProzor dp = new DispeceriProzor(sl);
                dp.setVisible(true);

            }
        });

        musterijeItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                MusterijeProzor mp = new MusterijeProzor(sl);
                mp.setVisible(true);

            }
        });

        vozaciItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                VozaciProzor vp = new VozaciProzor(sl);
                vp.setVisible(true);
            }
        });

        voznjeItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                VoznjeProzor voznjep = new VoznjeProzor(sl);
                voznjep.setVisible(true);

            }
        });

    }
}
