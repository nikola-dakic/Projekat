package gui.formeZaPrikaz;

import gui.formeZaDodavanjeIIzmenu.VozaciForma;
import main.Main;
import model.Automobil;
import model.Vozac;
import util.SveListe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VozaciProzor extends JFrame {

    private JToolBar mainToolbar = new JToolBar();
    private JButton btnAdd = new JButton();
    private JButton btnEdit = new JButton();
    private JButton btnDelete = new JButton();

    private DefaultTableModel tableModel;
    private JTable vozaciTabela;

    private SveListe sl;

    public VozaciProzor(SveListe sl) {
        this.sl = sl;
        setTitle("Vozaci");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initActions();
    }

    private void initGUI() {
        ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
        btnAdd.setIcon(addIcon);
        ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
        btnEdit.setIcon(editIcon);
        ImageIcon removeIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
        btnDelete.setIcon(removeIcon);

        mainToolbar.add(btnAdd);
        mainToolbar.add(btnEdit);
        mainToolbar.add(btnDelete);
        add(mainToolbar, BorderLayout.NORTH);
        mainToolbar.setFloatable(false);

        String[] zaglavlja = new String[] {"ID", "Korisnicko ime", "Lozinka", "Ime", "Prezime", "JMBG", "Adresa", "Pol",
                "Broj telefona", "Plata", "Broj clanske karte", "Automobil"};
        Object[][] sadrzaj = new Object[sl.sviNeobrisaniVozaci().size()][zaglavlja.length];

        for(int i=0; i< sl.sviNeobrisaniVozaci().size(); i++) {
            Vozac vozac = sl.sviNeobrisaniVozaci().get(i);
            Automobil automobil = sl.sviNeobrisaniAutomobili().get(i);
            sadrzaj[i][0] = vozac.getId();
            sadrzaj[i][1] = vozac.getKorisnickoIme();
            sadrzaj[i][2] = vozac.getLozinka();
            sadrzaj[i][3] = vozac.getIme();
            sadrzaj[i][4] = vozac.getPrezime();
            sadrzaj[i][5] = vozac.getJmbg();
            sadrzaj[i][6] = vozac.getAdresa();
            sadrzaj[i][7] = vozac.getPol();
            sadrzaj[i][8] = vozac.getBrojTelefona();
            sadrzaj[i][9] = vozac.getPlata();
            sadrzaj[i][10] = vozac.getBrojClanskeKarte();
            sadrzaj[i][11] = automobil == null ? "--" : vozac.getAutomobil().getBrojTaksiVozila();
        }

        tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
        vozaciTabela = new JTable(tableModel);

        vozaciTabela.setRowSelectionAllowed(true);
        vozaciTabela.setColumnSelectionAllowed(false);
        vozaciTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vozaciTabela.setDefaultEditor(Object.class, null);
        vozaciTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(vozaciTabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initActions() {

        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int red = vozaciTabela.getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    String korisnickoIme = tableModel.getValueAt(red, 1).toString();
                    Vozac vozac = sl.NadjiVozacaPoKorisnickomImenu(korisnickoIme);

                    int izbor = JOptionPane.showConfirmDialog(null,
                            "Da li ste sigurni da zelite da obrisete vozaca?",
                            korisnickoIme + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                    if(izbor == JOptionPane.YES_OPTION) {
                        vozac.setObrisan(true);
                        tableModel.removeRow(red);
                        sl.snimiVozace(Main.VOZACI_FAJL);
                    }
                }

            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VozaciForma vf = new VozaciForma(sl, null);
                vf.setVisible(true);
            }
        });

        btnEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = vozaciTabela.getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String korisnickoIme = tableModel.getValueAt(red, 1).toString();
                    Vozac vozac = sl.NadjiVozacaPoKorisnickomImenu(korisnickoIme);
                    if(vozac == null) {
                        JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja dispecera sa tim korisnickim imenom.", "Greska", JOptionPane.WARNING_MESSAGE);
                    }else {
                        VozaciForma vf = new VozaciForma(sl, vozac);
                        vf.setVisible(true);
                    }
                }
            }
        });

    }

}
