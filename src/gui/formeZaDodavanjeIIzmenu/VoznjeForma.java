package gui.formeZaDodavanjeIIzmenu;

import enums.EStatusVoznje;
import main.Main;
import model.Musterija;
import model.Vozac;
import model.Voznja;
import net.miginfocom.swing.MigLayout;
import util.SveListe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VoznjeForma extends JFrame {

    private JLabel lblId = new JLabel("ID: ");
    private JTextField txtId = new JTextField(20);
    private JLabel lblDatum = new JLabel("Datum: ");
    private JTextField txtDatum = new JTextField(20);
    private JLabel lblVremePorudzbine = new JLabel("Vreme porudzbine: ");
    private JTextField txtVremePorudzbine= new JTextField(20);
    private JLabel lblAdresaPolaska = new JLabel("Adresa polaska: ");
    private JTextField txtAdresaPolaska = new JTextField(20);
    private JLabel lblAdresaDestinacije = new JLabel("Adresa destinacije: ");
    private JTextField txtAdresaDestinacije = new JTextField(20);
    private JLabel lblMusterija = new JLabel("Musterija: ");
    private JComboBox<String> cbMusterija = new JComboBox<String>();
    private JLabel lblVozac = new JLabel("Vozac: ");
    private JComboBox<String> cbVozac = new JComboBox<String>();
    private JLabel lblBrojPredjenihKilometara = new JLabel("Broj predjenih kilometara: ");
    private JTextField txtBrojPredjenihKilometara = new JTextField(20);
    private JLabel lblTrajanjeVoznje = new JLabel("Trajanje voznje: ");
    private JTextField txtTrajanjeVoznje = new JTextField(20);
    private JLabel lblStatusVoznje = new JLabel("Status voznje: ");
    private JComboBox<EStatusVoznje> cbStatusVoznje = new JComboBox<EStatusVoznje>(EStatusVoznje.values());



    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private SveListe sl;
    private Voznja voznja;

    public VoznjeForma(SveListe sl, Voznja voznja) {
        this.sl = sl;
        this.voznja = voznja;
        if(voznja == null) {
            setTitle("Dodavanje voznje");
        }else {
            setTitle("Izmena podataka - " + voznja.getDatum());
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

        if(voznja!= null) {
            popuniPolja();
        }
        for(Musterija a: this.sl.getMusterije()) {
            cbMusterija.addItem(a.getKorisnickoIme());
        }

        for(Vozac a: this.sl.getVozaci()) {
            cbVozac.addItem(a.getKorisnickoIme());
        }


        add(lblId);
        add(txtId);
        add(lblDatum);
        add(txtDatum);
        add(lblVremePorudzbine);
        add(txtVremePorudzbine);
        add(lblAdresaPolaska);
        add(txtAdresaPolaska);
        add(lblAdresaDestinacije);
        add(txtAdresaDestinacije);
        add(lblMusterija);
        add(cbMusterija);
        add(lblVozac);
        add(cbVozac);
        add(lblBrojPredjenihKilometara);
        add(txtBrojPredjenihKilometara);
        add(lblTrajanjeVoznje);
        add(txtTrajanjeVoznje);
        add(lblStatusVoznje);
        add(cbStatusVoznje);

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
                    String datum = txtDatum.getText().trim();
                    String vremePorudzbine = txtVremePorudzbine.getText().trim();
                    String adresaPolaska = txtAdresaPolaska.getText().trim();
                    String adresaDestinacije = txtAdresaDestinacije.getText().trim();
                    String musterijaZaPronalazak = cbMusterija.getSelectedItem().toString();
                    String vozacZaPronalazak = cbVozac.getSelectedItem().toString();
                    String brojPredjenihKilometara = txtBrojPredjenihKilometara.getText().trim();
                    String trajanjeVoznje = txtTrajanjeVoznje.getText().trim();
                    EStatusVoznje status = (EStatusVoznje)cbStatusVoznje.getSelectedItem();


                    Musterija musterija = sl.NadjiMusterijuPoKorisnickomImenu(musterijaZaPronalazak);
                    Vozac vozac = sl.NadjiVozacaPoKorisnickomImenu(vozacZaPronalazak);

                    if(voznja == null) {
                        Voznja nova = new Voznja(id, datum, vremePorudzbine, adresaPolaska, adresaDestinacije, musterija, vozac, brojPredjenihKilometara, trajanjeVoznje, status, false);
                        sl.dodajVoznju(nova);
                    }else {
                        voznja.setDatum(datum);
                        voznja.setVremePorudzbine(vremePorudzbine);
                        voznja.setAdresaPolaska(adresaPolaska);
                        voznja.setAdresaDestinacije(adresaDestinacije);
                        voznja.setMusterija(musterija);
                        voznja.setVozac(vozac);
                        voznja.setBrojPredjenihKilometara(brojPredjenihKilometara);
                        voznja.setTrajanjeVoznje(trajanjeVoznje);
                        voznja.setStatus(status);

                    }
                    sl.snimiVoznje(Main.VOZNJE_FAJL);
                    VoznjeForma.this.dispose();
                    VoznjeForma.this.setVisible(false);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VoznjeForma.this.dispose();
                VoznjeForma.this.setVisible(false);

            }
        });

    }

    private void popuniPolja() {
        txtId.setText(String.valueOf(voznja.getId()));
        txtId.setEnabled(false);
        txtDatum.setText(voznja.getDatum());
        txtVremePorudzbine.setText(voznja.getVremePorudzbine());
        txtAdresaPolaska.setText(voznja.getAdresaPolaska());
        txtAdresaDestinacije.setText(voznja.getAdresaDestinacije());
        cbMusterija.setSelectedItem(String.valueOf(voznja.getMusterija().getKorisnickoIme()));
        cbVozac.setSelectedItem(String.valueOf(voznja.getVozac().getKorisnickoIme()));
        txtBrojPredjenihKilometara.setText(voznja.getBrojPredjenihKilometara());
        txtTrajanjeVoznje.setText(voznja.getTrajanjeVoznje());
        cbStatusVoznje.setSelectedItem(voznja.getStatus());
    }
    private boolean validacija() {

        boolean ok = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";

        if(txtId.getText().trim().equals("")) {
            poruka += "- Unesite ID\n";
            ok = false;
        }else if(voznja == null) {
            int id = Integer.parseInt(txtId.getText().trim());
            Voznja pronadjena = sl.NadjiVoznju(id);
            if(pronadjena != null && !pronadjena.isObrisan()) {
                poruka += "- Voznja sa unetim id-em vec postoji\n";
                ok = false;
            }
        }


        if(txtDatum.getText().trim().equals("")) {
            poruka += "- Unesite datum\n";
            ok = false;
        }


        if(txtVremePorudzbine.getText().trim().equals("")) {
            poruka += "- Unesite vreme porudzbine\n";
            ok = false;
        }

        if(txtAdresaPolaska.getText().trim().equals("")) {
            poruka += "- Unesite adresu polaska\n";
            ok = false;
        }

        if(txtAdresaDestinacije.getText().trim().equals("")) {
            poruka += "- Unesite adresu destinacije\n";
            ok = false;
        }


        if (txtBrojPredjenihKilometara.getText().trim().equals("")) {
            poruka += "- Unesite broj predjenih kilometara\n";
            ok = false;
        }


        if(txtTrajanjeVoznje.getText().trim().equals("")) {
            poruka += "- Unesite trajanje voznje\n";
            ok = false;
        }

        //dodati ostalo


        if(ok == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }

        return ok;
    }

}