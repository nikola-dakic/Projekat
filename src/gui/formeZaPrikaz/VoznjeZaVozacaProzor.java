package gui.formeZaPrikaz;

import enums.EStatusVoznje;
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
import java.util.ArrayList;

public class VoznjeZaVozacaProzor extends JFrame {

    private JToolBar mainToolbar = new JToolBar();
    private JButton btnPotvrdiVoznju = new JButton();
    private JButton btnOdbijVoznju = new JButton();
    private JButton btnZavrsiVoznju = new JButton();

    private DefaultTableModel tableModel;
    private JTable voznjeTabela;

    private SveListe sl;
    private Vozac vozac;
    private Musterija musterija;
    private ArrayList<Voznja> VozaceveVoznje = new ArrayList<Voznja>();

    public VoznjeZaVozacaProzor(SveListe sl, Vozac vozac) {
        this.sl = sl;
        this.vozac = vozac;
        setTitle("Voznje za vozaca");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initActions();
    }

    private void initGUI() {
        ImageIcon acceptIcon = new ImageIcon(getClass().getResource("/slike/accept.png"));
        btnPotvrdiVoznju.setIcon(acceptIcon);
        ImageIcon declineIcon = new ImageIcon(getClass().getResource("/slike/decline.png"));
        btnOdbijVoznju.setIcon(declineIcon);
        ImageIcon finishIcon = new ImageIcon(getClass().getResource("/slike/finish.png"));
        btnZavrsiVoznju.setIcon(finishIcon);

        mainToolbar.add(btnPotvrdiVoznju);
        mainToolbar.add(btnOdbijVoznju);
        mainToolbar.add(btnZavrsiVoznju);

        add(mainToolbar, BorderLayout.NORTH);
        mainToolbar.setFloatable(false);

        for(Voznja voznja:sl.getVoznje()) {
            if(voznja.getVozac().getIme().equalsIgnoreCase(vozac.getIme())) {
                VozaceveVoznje.add(voznja);
            }
        }

        String[] zaglavlja = new String[] {"ID", "Datum", "Vreme porudzbine", "Polazak", "Odrediste",
                "Musterija", "Vozac", "Broj predjenih km", "Trajanje voznje", "Status"};
        Object[][] sadrzaj = new Object[this.VozaceveVoznje.size()][zaglavlja.length];

        for(int i=0; i< this.VozaceveVoznje.size(); i++) {
            Voznja voznja = this.VozaceveVoznje.get(i);
            Musterija musterija = voznja.getMusterija();
            Vozac vozac = voznja.getVozac();
            sadrzaj[i][0] = voznja.getId();
            sadrzaj[i][1] = voznja.getDatum();
            sadrzaj[i][2] = voznja.getVremePorudzbine();
            sadrzaj[i][3] = voznja.getAdresaPolaska();
            sadrzaj[i][4] = voznja.getAdresaDestinacije();
            sadrzaj[i][5] = musterija.isObrisan() ? "Obrisana musterija" : musterija.getIme() + " " + musterija.getPrezime();
            sadrzaj[i][6] = vozac.isObrisan() ? "Obrisan vozac" : vozac.getIme() + " " + vozac.getPrezime();
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

        btnOdbijVoznju.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();

                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }
                else if(EStatusVoznje.DODELJENA == EStatusVoznje.valueOf(tableModel.getValueAt(red, 9).toString())){

                    int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
                    Voznja voznja = sl.NadjiVoznju(id);

                    int izbor = JOptionPane.showConfirmDialog(null,
                            "Da li ste sigurni da zelite da odbijete voznju?",
                            id + " - Potvrda odbijanja", JOptionPane.YES_NO_OPTION);
                    if(izbor == JOptionPane.YES_OPTION) {

                        voznja.setStatus(EStatusVoznje.ODBIJENA);

                        sl.snimiVoznje(Main.VOZNJE_FAJL);
                    }
                }

            }

        });

        btnPotvrdiVoznju.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();

                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }
                else if (EStatusVoznje.DODELJENA == EStatusVoznje.valueOf(tableModel.getValueAt(red, 9).toString())){
                    int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
                    Voznja voznja = sl.NadjiVoznju(id);

                    int izbor = JOptionPane.showConfirmDialog(null,
                            "Da li ste sigurni da zelite da prihvatite voznju?",
                            id + " - Potvrda prihvatanja voznje", JOptionPane.YES_NO_OPTION);
                    if(izbor == JOptionPane.YES_OPTION) {
                        voznja.setStatus(EStatusVoznje.PRIHVACENA);

                        sl.snimiVoznje(Main.VOZNJE_FAJL);
                    }
                }
            }
        });

        btnZavrsiVoznju.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else if(EStatusVoznje.ODBIJENA == EStatusVoznje.valueOf(tableModel.getValueAt(red, 9).toString()) ||
                        EStatusVoznje.DODELJENA == EStatusVoznje.valueOf(tableModel.getValueAt(red, 9).toString())){
                    JOptionPane.showMessageDialog(null, "Voznja nije prihvacena, radnja ne moze biti izvrsena. Voznja mora biti prihvacena da bi mogla da se zavrsi.", "Greska", JOptionPane.WARNING_MESSAGE);

                }else if(EStatusVoznje.ZAVRSENA == EStatusVoznje.valueOf(tableModel.getValueAt(red, 9).toString()) ) {
                    JOptionPane.showMessageDialog(null, "Voznja je vec zavrsena. Molimo odaberite drugu voznju.", "Greska", JOptionPane.WARNING_MESSAGE);
                } else if(EStatusVoznje.PRIHVACENA == EStatusVoznje.valueOf(tableModel.getValueAt(red, 9).toString())) {
                    int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
                    Voznja voznja = sl.NadjiVoznju(id);
                    if(voznja == null) {
                        JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja voznje po id-u.\n              Osvezite prozor kako bi se ucitali izmenjeni podaci.", "Greska", JOptionPane.WARNING_MESSAGE);
                    }else {
                        ZavrsiVoznjuProzor zvp = new ZavrsiVoznjuProzor(sl, voznja);
                        zvp.setVisible(true);
                    }
                }
            }
        });
    }

}
