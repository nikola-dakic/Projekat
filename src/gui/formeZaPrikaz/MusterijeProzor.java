package gui.formeZaPrikaz;

import gui.formeZaDodavanjeIIzmenu.MusterijeForma;
import main.Main;
import model.Musterija;
import util.SveListe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusterijeProzor extends JFrame {


    private JToolBar mainToolbar = new JToolBar();
    private JButton btnAdd = new JButton();
    private JButton btnEdit = new JButton();
    private JButton btnDelete = new JButton();

    private DefaultTableModel tableModel;
    private JTable musterijeTabela;

    private SveListe sl;

    public MusterijeProzor(SveListe sl) {
        this.sl = sl;
        setTitle("Musterije");
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
                "Broj telefona"};
        Object[][] sadrzaj = new Object[sl.sveNeobrisaneMusterije().size()][zaglavlja.length];

        for(int i=0; i< sl.sveNeobrisaneMusterije().size(); i++) {
            Musterija musterija = sl.sveNeobrisaneMusterije().get(i);
            sadrzaj[i][0] = musterija.getId();
            sadrzaj[i][1] = musterija.getKorisnickoIme();
            sadrzaj[i][2] = musterija.getLozinka();
            sadrzaj[i][3] = musterija.getIme();
            sadrzaj[i][4] = musterija.getPrezime();
            sadrzaj[i][5] = musterija.getJmbg();
            sadrzaj[i][6] = musterija.getAdresa();
            sadrzaj[i][7] = musterija.getPol();
            sadrzaj[i][8] = musterija.getBrojTelefona();
        }

        tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
        musterijeTabela = new JTable(tableModel);

        musterijeTabela.setRowSelectionAllowed(true);
        musterijeTabela.setColumnSelectionAllowed(false);
        musterijeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        musterijeTabela.setDefaultEditor(Object.class, null);
        musterijeTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(musterijeTabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initActions() {

        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int red = musterijeTabela.getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    String korisnickoIme = tableModel.getValueAt(red, 1).toString();
                    Musterija musterija = sl.NadjiMusterijuPoKorisnickomImenu(korisnickoIme);

                    int izbor = JOptionPane.showConfirmDialog(null,
                            "Da li ste sigurni da zelite da obrisete musteriju?",
                            korisnickoIme + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                    if(izbor == JOptionPane.YES_OPTION) {
                        musterija.setObrisan(true);
                        tableModel.removeRow(red);
                        sl.snimiMusterije(Main.MUSTERIJE_FAJL);
                    }
                }

            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusterijeForma mf = new MusterijeForma(sl, null);
                mf.setVisible(true);
            }
        });

        btnEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = musterijeTabela.getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String korisnickoIme = tableModel.getValueAt(red, 1).toString();
                    Musterija musterija = sl.NadjiMusterijuPoKorisnickomImenu(korisnickoIme);
                    if(musterija == null) {
                        JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja musterije sa tim korisnickim imenom.", "Greska", JOptionPane.WARNING_MESSAGE);
                    }else {
                        MusterijeForma mf = new MusterijeForma(sl, musterija);
                        mf.setVisible(true);
                    }
                }
            }
        });

    }

}
