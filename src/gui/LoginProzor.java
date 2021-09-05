package gui;

import model.Dispecer;
import model.Korisnik;
import model.Musterija;
import model.Vozac;
import net.miginfocom.swing.MigLayout;
import util.SveListe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginProzor extends JFrame {

    private JLabel lblGreeting = new JLabel("Dobrodosli, molimo da se prijavite.");

    private JLabel lblUsername = new JLabel("Korisnicko ime: ");
    private JTextField txtKorisnickoIme = new JTextField(20);

    private JLabel lblPassword = new JLabel("Lozinka: ");
    private JPasswordField pfLozinka = new JPasswordField(20);

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private SveListe sl;

    public LoginProzor(SveListe sl) {
        this.sl = sl;
        setTitle("Prijava");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initGUI();
        initActions();
        pack();
        getRootPane().setDefaultButton(btnOk);
    }

    public void initGUI() {
        MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
        setLayout(mig);

        add(lblGreeting, "span 2");
        add(lblUsername);
        add(txtKorisnickoIme);
        add(lblPassword);
        add(pfLozinka);
        add(new JLabel());
        add(btnOk, "split 2");
        add(btnCancel);
        getRootPane().setDefaultButton(btnOk);
    }

    public void initActions() {
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginProzor.this.dispose();
                LoginProzor.this.setVisible(false);
            }
        });

        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String korisnickoIme = txtKorisnickoIme.getText().trim();
                String sifra = new String(pfLozinka.getPassword()).trim();

                if(korisnickoIme.equals("") || sifra.equals("")) {
                    JOptionPane.showMessageDialog(null, "Niste uneli sve podatke" , "Greska" , JOptionPane.WARNING_MESSAGE);
                }else {
                    Korisnik prijavljeni = sl.login(korisnickoIme, sifra);
                    if(prijavljeni == null) {
                        JOptionPane.showMessageDialog(null, "Pogresni login podaci", "Greska", JOptionPane.WARNING_MESSAGE);
                    }else  {
                        for (Musterija m : sl.getMusterije()) {
                            if(m.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) && m.getLozinka().equals(sifra)) {
                                LoginProzor.this.dispose();
                                LoginProzor.this.setVisible(false);
                                GlavniProzorMusterije lpm = new GlavniProzorMusterije(sl, m);
                                lpm.setVisible(true);
                            }
                        }
                        for (Dispecer d: sl.getDispeceri()) {
                            if(d.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) && d.getLozinka().equals(sifra)) {
                                LoginProzor.this.dispose();
                                LoginProzor.this.setVisible(false);
                                GlavniProzorDispeceri lpd = new GlavniProzorDispeceri(sl, d);
                                lpd.setVisible(true);
                            }
                        }
                        for (Vozac v: sl.getVozaci()) {
                            if(v.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) && v.getLozinka().equals(sifra)) {
                                LoginProzor.this.dispose();
                                LoginProzor.this.setVisible(false);
                                GlavniProzorVozaci lpd = new GlavniProzorVozaci(sl, v);
                                lpd.setVisible(true);
                            }
                        }
                    }
                }
            }
        });
    }
}