package gui.formeZaDodavanjeIIzmenu;

import enums.EPol;
import enums.ETelefonskaOdeljenja;
import main.Main;
import model.Dispecer;
import model.Korisnik;
import net.miginfocom.swing.MigLayout;
import util.SveListe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DispeceriForma extends JFrame {

    //zavrseno
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
    private JLabel lblBrTelefonskeLinije = new  JLabel("Broj telefonske linije: ");
    private JTextField txtBrTelefonskeLinije = new JTextField(20);
    private JLabel lblTelefonskoOdeljenje = new JLabel("Broj telefonskog odeljenja: ");
    private JComboBox<ETelefonskaOdeljenja> cbTelefonskoOdeljenje =
            new JComboBox<ETelefonskaOdeljenja>(ETelefonskaOdeljenja.values());



    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private SveListe sl;
    private Dispecer dispecer;

    public DispeceriForma(SveListe sl, Dispecer dispecer) {
        this.sl = sl;
        this.dispecer = dispecer;
        if(dispecer == null) {
            setTitle("Dodavanje dispecera");
        }else {
            setTitle("Izmena podataka - " + dispecer.getKorisnickoIme());
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initActions();
        setResizable(false);
        pack();
        getRootPane().setDefaultButton(btnOk);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][][][][][][]20[]");
        setLayout(layout);

        if(dispecer != null) {
            popuniPolja();
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
        add(lblBrTelefonskeLinije);
        add(txtBrTelefonskeLinije);
        add(lblTelefonskoOdeljenje);
        add(cbTelefonskoOdeljenje);
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
                    String brojTelefonskeLinije = txtBrTelefonskeLinije.getText().trim();
                    ETelefonskaOdeljenja telefonskaOdeljenja = (ETelefonskaOdeljenja)cbTelefonskoOdeljenje.getSelectedItem();

                    // DODAVANJE:
                    if(dispecer == null) {
                        Dispecer novi = new Dispecer(id, korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plata, brojTelefonskeLinije, telefonskaOdeljenja, false);
                        sl.dodajDispecera(novi);
                        // IZMJENA:
                    }else {
                        dispecer.setKorisnickoIme(korisnickoIme);
                        dispecer.setLozinka(lozinka);
                        dispecer.setIme(ime);
                        dispecer.setPrezime(prezime);
                        dispecer.setJmbg(jmbg);
                        dispecer.setAdresa(adresa);
                        dispecer.setPol(pol);
                        dispecer.setBrojTelefona(brojTelefona);
                        dispecer.setPlata(plata);
                        dispecer.setBrojTelefonskeLinije(brojTelefonskeLinije);
                        dispecer.setTelefonskaOdeljenja(telefonskaOdeljenja);
                    }
                    sl.snimiDispecere(Main.DISPECERI_FAJL);
                    DispeceriForma.this.dispose();
                    DispeceriForma.this.setVisible(false);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DispeceriForma.this.dispose();
                DispeceriForma.this.setVisible(false);

            }
        });

    }

    private void popuniPolja() {
        txtId.setText(String.valueOf(dispecer.getId()));
        txtId.setEnabled(false);
        txtKorisnickoIme.setText(dispecer.getKorisnickoIme());
        pfLozinka.setText(dispecer.getLozinka());
        txtIme.setText(dispecer.getIme());
        txtPrezime.setText(dispecer.getPrezime());
        txtJmbg.setText(dispecer.getJmbg());
        txtAdresa.setText(dispecer.getAdresa());
        //za checkbox treba da postavimo da li je checkiran ili nije
        cbPol.setSelectedItem(dispecer.getPol());
        txtBrTelefona.setText(dispecer.getBrojTelefona());
        txtPlata.setText(String.valueOf(dispecer.getPlata()));
        txtBrTelefonskeLinije.setText(dispecer.getBrojTelefonskeLinije());
        cbTelefonskoOdeljenje.setSelectedItem(dispecer.getTelefonskaOdeljenja());

    }
    private boolean validacija() {

        boolean ok = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";

        if(txtId.getText().trim().equals("")) {
            poruka += "- Unesite ID\n";
            ok = false;
        }else if(dispecer == null) {
            int id = Integer.parseInt(txtId.getText().trim());
            Dispecer pronadjeni = sl.NadjiDispecera(id);
            if(pronadjeni != null && !pronadjeni.isObrisan()) {
                poruka += "- Dispecer sa unetim id-em vec postoji\n";
                ok = false;
            }
        }

        if(txtKorisnickoIme.getText().trim().equals("")) {
            poruka += "- Unesite korisnicko ime\n";
            ok = false;
        }else if(dispecer == null){
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

        if(txtBrTelefonskeLinije.getText().trim().equals("")) {
            poruka += "- Unesite broj telefonske linije\n";
            ok = false;
        }


        if(ok == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }

        return ok;
    }

}
