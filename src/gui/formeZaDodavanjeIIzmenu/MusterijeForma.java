package gui.formeZaDodavanjeIIzmenu;

import enums.EPol;
import main.Main;
import model.Korisnik;
import model.Musterija;
import model.Voznja;
import net.miginfocom.swing.MigLayout;
import util.SveListe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MusterijeForma extends JFrame {

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


    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private SveListe sl;
    private Musterija musterija;

    public MusterijeForma(SveListe sl, Musterija musterija) {
        this.sl = sl;
        this.musterija = musterija;
        if(musterija == null) {
            setTitle("Dodavanje musterije");
        }else {
            setTitle("Izmena podataka - " + musterija.getKorisnickoIme());
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

        if(musterija!= null) {
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


                    if(musterija == null) {
                        Musterija novi = new Musterija(id, korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, new ArrayList<Voznja>(), false);
                        sl.dodajMusteriju(novi);
                    }else {
                        musterija.setLozinka(lozinka);
                        musterija.setIme(ime);
                        musterija.setPrezime(prezime);
                        musterija.setJmbg(jmbg);
                        musterija.setAdresa(adresa);
                        musterija.setPol(pol);
                        musterija.setBrojTelefona(brojTelefona);

                    }
                    sl.snimiMusterije(Main.MUSTERIJE_FAJL);
                    MusterijeForma.this.dispose();
                    MusterijeForma.this.setVisible(false);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MusterijeForma.this.dispose();
                MusterijeForma.this.setVisible(false);

            }
        });

    }

    private void popuniPolja() {
        txtId.setText(String.valueOf(musterija.getId()));
        txtId.setEnabled(false);
        txtKorisnickoIme.setText(musterija.getKorisnickoIme());
        pfLozinka.setText(musterija.getLozinka());
        txtIme.setText(musterija.getIme());
        txtPrezime.setText(musterija.getPrezime());
        txtJmbg.setText(musterija.getJmbg());
        txtAdresa.setText(musterija.getAdresa());
        cbPol.setSelectedItem(musterija.getPol());
        txtBrTelefona.setText(musterija.getBrojTelefona());

    }
    private boolean validacija() {

        boolean ok = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";

        if(txtId.getText().trim().equals("")) {
            poruka += "- Unesite ID\n";
            ok = false;
        }else if(musterija == null) {
            int id = Integer.parseInt(txtId.getText().trim());
            Musterija pronadjeni = sl.NadjiMusteriju(id);
            if(pronadjeni != null && !pronadjeni.isObrisan()) {
                poruka += "- Musterija sa unetim id-em vec postoji\n";
                ok = false;
            }
        }

        if(txtKorisnickoIme.getText().trim().equals("")) {
            poruka += "- Unesite korisnicko ime\n";
            ok = false;
        }else if(musterija == null){
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

        //voznje


        if(ok == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }

        return ok;
    }

}
