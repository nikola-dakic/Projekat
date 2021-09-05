package gui.formeZaDodavanjeIIzmenu;

import enums.EPol;
import main.Main;
import model.Automobil;
import model.Korisnik;
import model.Vozac;
import model.Voznja;
import net.miginfocom.swing.MigLayout;
import util.SveListe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VozaciForma extends JFrame {

    private JLabel lblId = new JLabel("ID: ");
    private JTextField txtId = new JTextField(20);
    private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime: ");
    private JTextField txtKorisnickoIme = new JTextField(20);
    private JLabel lblLozinka = new JLabel("Lozinka: ");
    private JPasswordField pfLozinka = new JPasswordField(20);
    private JLabel lblIme = new JLabel("Ime: ");
    private JTextField txtIme = new JTextField(20);
    private JLabel lblPrezime = new JLabel("Prezime: ");
    private JTextField txtPrezime = new JTextField(20);
    private JLabel lblJmbg = new JLabel("JMBG: ");
    private JTextField txtJmbg = new JTextField(20);
    private JLabel lblAdresa = new JLabel("Adresa: ");
    private JTextField txtAdresa = new JTextField(20);
    private JLabel lblPol = new JLabel("Pol: ");
    private JComboBox<EPol> cbPol = new JComboBox<EPol>(EPol.values());
    private JLabel lblBrTelefona = new JLabel("Broj telefona: ");
    private JTextField txtBrTelefona = new JTextField(20);
    private JLabel lblPlata = new  JLabel("Plata: ");
    private JTextField txtPlata = new JTextField(20);
    private JLabel lblbrojClanskeKarteUdruzenjaTaksista = new JLabel("Broj clanske karte: ");
    private JTextField txtbrojClanskeKarteUdruzenjaTaksista = new JTextField(20);
    private JLabel lblAutomobil = new JLabel("Automobil: ");
    private JComboBox<String> cbAutomobil = new JComboBox<String>();

    //dodati ostale fieldove za atribute!!!

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private SveListe sl;
    private Vozac vozac;

    public VozaciForma(SveListe sl, Vozac vozac) {
        this.sl = sl;
        this.vozac = vozac;
        if(vozac == null) {
            setTitle("Dodavanje vozaca");
        }else {
            setTitle("Izmena podataka - " + vozac.getKorisnickoIme());
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initActions();
        setResizable(true);
        pack();
        getRootPane().setDefaultButton(btnOk);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][][][][][][][]20[]");
        setLayout(layout);

        if(vozac!= null) {
            popuniPolja();
        }
        ArrayList<String> prva = new ArrayList<String>();
        ArrayList<String> druga = new ArrayList<String>();

        for(Automobil a : this.sl.getAutomobili()) {
            prva.add(a.getRegistracija());
        }
        for(Vozac v : this.sl.getVozaci()) {
            druga.add(v.getAutomobil().getRegistracija());
        }
        prva.removeAll(druga);
        for(String s : prva) {
            cbAutomobil.addItem(s);
        }


        add(lblId);
        add(txtId);
        add(lblKorisnickoIme);
        add(txtKorisnickoIme);
        add(lblLozinka);
        add(pfLozinka);
        add(lblIme);
        add(txtIme);
        add(lblPrezime);
        add(txtPrezime);
        add(lblJmbg);
        add(txtJmbg);
        add(lblAdresa);
        add(txtAdresa);
        add(lblPol);
        add(cbPol);
        add(lblBrTelefona);
        add(txtBrTelefona);
        add(lblPlata);
        add(txtPlata);
        add(lblbrojClanskeKarteUdruzenjaTaksista);
        add(txtbrojClanskeKarteUdruzenjaTaksista);
        add(lblAutomobil);
        add(cbAutomobil);
        add(new JLabel());
        add(btnOk, "split 2");
        add(btnCancel);
    }

    private void initActions() {

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija()) {
                    int id = Integer.parseInt(txtId.getText().trim());
                    String korisnickoIme = txtKorisnickoIme.getText().trim();
                    String lozinka = new String(pfLozinka.getPassword()).trim();
                    String ime = txtIme.getText().trim();
                    String prezime = txtPrezime.getText().trim();
                    String jmbg = txtJmbg.getText().trim();
                    String adresa = txtAdresa.getText().trim();
                    EPol pol = (EPol)cbPol.getSelectedItem();
                    String brojTelefona = txtBrTelefona.getText().trim();
                    double plata = Double.parseDouble(txtPlata.getText().trim());
                    String brojClanskeKarteUdruzenjaTaksista = txtbrojClanskeKarteUdruzenjaTaksista.getText().trim();
                    String auto = cbAutomobil.getSelectedItem().toString();

                    Automobil automobil = sl.pronadjiAutomobilPoRegistraciji(auto);

                    if(vozac == null) {
                        Vozac novi = new Vozac(id, korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plata, brojClanskeKarteUdruzenjaTaksista, automobil, false, new ArrayList<Voznja>());
                        sl.dodajVozaca(novi);
                    }else {
                        vozac.setLozinka(lozinka);
                        vozac.setIme(ime);
                        vozac.setPrezime(prezime);
                        vozac.setJmbg(jmbg);
                        vozac.setAdresa(adresa);
                        vozac.setPol(pol);
                        vozac.setBrojTelefona(brojTelefona);
                        vozac.setPlata(plata);
                        vozac.setBrojClanskeKarte(brojClanskeKarteUdruzenjaTaksista);
                        vozac.setAutomobil(automobil);


                    }
                    sl.snimiVozace(Main.VOZACI_FAJL);
                    VozaciForma.this.dispose();
                    VozaciForma.this.setVisible(false);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VozaciForma.this.dispose();
                VozaciForma.this.setVisible(false);

            }
        });

    }

    private void popuniPolja() {
        txtId.setText(String.valueOf(vozac.getId()));
        txtId.setEnabled(false);
        txtKorisnickoIme.setText(vozac.getKorisnickoIme());
        pfLozinka.setText(vozac.getLozinka());
        txtIme.setText(vozac.getIme());
        txtPrezime.setText(vozac.getPrezime());
        txtJmbg.setText(vozac.getJmbg());
        txtAdresa.setText(vozac.getAdresa());
        cbPol.setSelectedItem(vozac.getPol());
        txtBrTelefona.setText(vozac.getBrojTelefona());
        txtPlata.setText(String.valueOf(vozac.getPlata()));
        txtbrojClanskeKarteUdruzenjaTaksista.setText(vozac.getBrojClanskeKarte());
        cbAutomobil.setSelectedItem(String.valueOf(vozac.getAutomobil().getRegistracija()));


    }
    private boolean validacija() {

        boolean ok = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";

        if(txtId.getText().trim().equals("")) {
            poruka += "- Unesite ID\n";
            ok = false;
        }else if(vozac == null) {
            int id = Integer.parseInt(txtId.getText().trim());
            Vozac pronadjeni = sl.NadjiVozaca(id);
            if(pronadjeni != null && !pronadjeni.isObrisan()) {
                poruka += "- Vozac sa unetim id-em vec postoji\n";
                ok = false;
            }
        }

        if(txtKorisnickoIme.getText().trim().equals("")) {
            poruka += "- Unesite korisnicko ime\n";
            ok = false;
        }else if(vozac == null){
            String korIme = txtKorisnickoIme.getText().trim();
            Korisnik pronadjeni = sl.NadjiKorisnikaPoKorisnickomImenu(korIme);
            if(pronadjeni != null && !pronadjeni.isObrisan()) {
                poruka += "- Korisnik sa unetim korisnickim imenom vec postoji\n";
                ok = false;
            }
        }

        String lozinka = new String(pfLozinka.getPassword()).trim();
        if(lozinka.equals("")) {
            poruka += "- Unesite lozinku\n";
            ok = false;
        }


        if(txtIme.getText().trim().equals("")) {
            poruka += "- Unesite ime\n";
            ok = false;
        }


        if(txtJmbg.getText().trim().equals("")) {
            poruka += "- Unesite jmbg\n";
            ok = false;
        }

        if(txtPrezime.getText().trim().equals("")) {
            poruka += "- Unesite prezime\n";
            ok = false;
        }

        if(txtAdresa.getText().trim().equals("")) {
            poruka += "- Unesite adresu\n";
            ok = false;
        }

        if(txtBrTelefona.getText().trim().equals("")) {
            poruka += "- Unesite broj telefona\n";
            ok = false;
        }

        if(txtPlata.getText().trim().equals("")) {
            poruka += "- Unesite platu\n";
            ok = false;
        }else {
            try {
                Double.parseDouble(txtPlata.getText().trim());
            }catch (NumberFormatException e) {
                poruka += "- Plata mora biti unesena kao numericka vrednost\n";
                ok = false;
            }
        }

        if(txtbrojClanskeKarteUdruzenjaTaksista.getText().trim().equals("")) {
            poruka += "- Unesite broj clanske karte\n";
            ok = false;
        }

        //dodati ostalo


        if(ok == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }

        return ok;
    }

}
