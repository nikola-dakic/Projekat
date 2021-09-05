package gui.formeZaPrikaz;

import enums.EStatusVoznje;
import gui.formeZaDodavanjeIIzmenu.VoznjeForma;
import main.Main;
import model.Musterija;
import model.Vozac;
import model.Voznja;
import util.SveListe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VoznjeProzor extends JFrame {

    private JToolBar mainToolbar = new JToolBar();
    private JButton btnAdd = new JButton();
    private JButton btnEdit = new JButton();
    private JButton btnDelete = new JButton();
    private JButton btnDodeliVoznjuVozacu = new JButton();

    private DefaultTableModel tableModel;
    private JTable voznjeTabela;

    private SveListe sl;

    public VoznjeProzor(SveListe sl) {
        this.sl = sl;
        setTitle("Voznje");
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
        ImageIcon dodeliVoznjuIcon = new ImageIcon(getClass().getResource("/slike/add2.png"));
        btnDodeliVoznjuVozacu.setIcon(dodeliVoznjuIcon);

        mainToolbar.add(btnAdd);
        mainToolbar.add(btnEdit);
        mainToolbar.add(btnDelete);
        mainToolbar.add(btnDodeliVoznjuVozacu);

        add(mainToolbar, BorderLayout.NORTH);
        mainToolbar.setFloatable(false);

        String[] zaglavlja = new String[] {"ID", "Datum", "Vreme porudzbine", "Polazak", "Odrediste",
                "Musterija", "Vozac", "Broj predjenih km", "Trajanje voznje", "Status"};
        Object[][] sadrzaj = new Object[sl.sveNeobrisaneVoznje().size()][zaglavlja.length];

        for(int i=0; i< sl.sveNeobrisaneVoznje().size(); i++) {
            Voznja voznja = sl.sveNeobrisaneVoznje().get(i);
            Musterija musterija = voznja.getMusterija();
            Vozac vozac = voznja.getVozac();
            sadrzaj[i][0] = voznja.getId();
            sadrzaj[i][1] = voznja.getDatum();
            sadrzaj[i][2] = voznja.getVremePorudzbine();
            sadrzaj[i][3] = voznja.getAdresaPolaska();
            sadrzaj[i][4] = voznja.getAdresaDestinacije();
            sadrzaj[i][5] = musterija.isObrisan() ? "Obrisana musterija" : musterija.getIme();
            sadrzaj[i][6] = vozac.isObrisan() ? "Obrisan vozac" : vozac.getIme();
            sadrzaj[i][7] = voznja.getBrojPredjenihKilometara();
            sadrzaj[i][8] = voznja.getTrajanjeVoznje();
            sadrzaj[i][9] = voznja.getStatus();
        }

        tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
        voznjeTabela = new JTable(tableModel);

        voznjeTabela.setRowSelectionAllowed(true);
        voznjeTabela.setColumnSelectionAllowed(false);
        voznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        voznjeTabela.setDefaultEditor(Object.class, null);
        voznjeTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(voznjeTabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initActions() {

        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
                    Voznja voznja = sl.NadjiVoznju(id);

                    int izbor = JOptionPane.showConfirmDialog(null,
                            "Da li ste sigurni da zelite da obrisete voznju?",
                            id + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                    if(izbor == JOptionPane.YES_OPTION) {
                        voznja.setObrisan(true);
                        tableModel.removeRow(red);
                        sl.snimiVoznje(Main.VOZNJE_FAJL);
                    }
                }

            }

        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VoznjeForma voznjef = new VoznjeForma(sl, null);
                voznjef.setVisible(true);
            }
        });

        btnEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
                    Voznja voznja = sl.NadjiVoznju(id);
                    if(voznja == null) {
                        JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja voznje po id-u.\n              Osvezite prozor kako bi se ucitali izmenjeni podaci.", "Greska", JOptionPane.WARNING_MESSAGE);
                    }else {
                        VoznjeForma voznjef = new VoznjeForma(sl, voznja);
                        voznjef.setVisible(true);
                    }
                }
            }
        });
        btnDodeliVoznjuVozacu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
                    Voznja voznja = sl.NadjiVoznju(id);
                    if(voznja == null || voznja.getStatus() != EStatusVoznje.KREIRANA) {
                        JOptionPane.showMessageDialog(null, "Ova voznja je vec dodeljenja vozacu, molimo odaberite drugu voznju.", "Greska", JOptionPane.WARNING_MESSAGE);
                    }else {
                        DodeliVoznjuVozacuProzor voznjef = new DodeliVoznjuVozacuProzor(sl, voznja);
                        voznjef.setVisible(true);
                    }
                }
            }
        });
    }
}
