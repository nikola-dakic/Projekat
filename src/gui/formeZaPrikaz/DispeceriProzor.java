package gui.formeZaPrikaz;

import gui.formeZaDodavanjeIIzmenu.DispeceriForma;
import main.Main;
import model.Dispecer;
import util.SveListe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DispeceriProzor extends JFrame {

    private JToolBar mainToolbar = new JToolBar();
    private JButton btnAdd = new JButton();
    private JButton btnEdit = new JButton();
    private JButton btnDelete = new JButton();

    private DefaultTableModel tableModel;
    private JTable dispeceriTabela;

    private SveListe sl;

    public DispeceriProzor(SveListe sl) {
        this.sl = sl;
        setTitle("Dispeceri");
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
                "Broj telefona", "Plata", "Broj telefonske linije", "Telefonsko odjeljenje"};
        Object[][] sadrzaj = new Object[sl.sviNeobrisaniDispeceri().size()][zaglavlja.length];

        for(int i=0; i< sl.sviNeobrisaniDispeceri().size(); i++) {
            Dispecer dispecer = sl.sviNeobrisaniDispeceri().get(i);
            sadrzaj[i][0] = dispecer.getId();
            sadrzaj[i][1] = dispecer.getKorisnickoIme();
            sadrzaj[i][2] = dispecer.getLozinka();
            sadrzaj[i][3] = dispecer.getIme();
            sadrzaj[i][4] = dispecer.getPrezime();
            sadrzaj[i][5] = dispecer.getJmbg();
            sadrzaj[i][6] = dispecer.getAdresa();
            sadrzaj[i][7] = dispecer.getPol();
            sadrzaj[i][8] = dispecer.getBrojTelefona();
            sadrzaj[i][9] = dispecer.getPlata();
            sadrzaj[i][10] = dispecer.getBrojTelefonskeLinije();
            sadrzaj[i][11] = dispecer.getTelefonskaOdeljenja();
        }

        tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
        dispeceriTabela = new JTable(tableModel);

        dispeceriTabela.setRowSelectionAllowed(true);
        dispeceriTabela.setColumnSelectionAllowed(false);
        dispeceriTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dispeceriTabela.setDefaultEditor(Object.class, null);
        dispeceriTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(dispeceriTabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initActions() {

        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int red = dispeceriTabela.getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    String korisnickoIme = tableModel.getValueAt(red, 1).toString();
                    Dispecer dispecer = sl.NadjiDispeceraPoKorisnickomImenu(korisnickoIme);

                    int izbor = JOptionPane.showConfirmDialog(null,
                            "Da li ste sigurni da zelite da obrisete dispecera?",
                            korisnickoIme + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                    if(izbor == JOptionPane.YES_OPTION) {
                        dispecer.setObrisan(true);
//						}
                        tableModel.removeRow(red);
                        sl.snimiDispecere(Main.DISPECERI_FAJL);
                    }
                }

            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DispeceriForma df = new DispeceriForma(sl, null);
                df.setVisible(true);
            }
        });

        btnEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = dispeceriTabela.getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String korisnickoIme = tableModel.getValueAt(red, 1).toString();
                    Dispecer dispecer = sl.NadjiDispeceraPoKorisnickomImenu(korisnickoIme);
                    if(dispecer == null) {
                        JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja dispecera sa tim korisnickim imenom.", "Greska", JOptionPane.WARNING_MESSAGE);
                    }else {
                        DispeceriForma df = new DispeceriForma(sl, dispecer);
                        df.setVisible(true);
                    }
                }
            }
        });
    }
}