package gui.formeZaPrikaz;

import enums.EStatusVoznje;
import main.Main;
import model.Voznja;
import net.miginfocom.swing.MigLayout;
import util.SveListe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZavrsiVoznjuProzor extends JFrame {

    private JLabel lblBrojPredjenihKilometara = new JLabel("Broj predjenih kilometara: ");
    private JTextField txtBrojPredjenihKilometara = new JTextField(20);
    private JLabel lblTrajanjeVoznje = new JLabel("Trajanje voznje: ");
    private JTextField txtTrajanjeVoznje= new JTextField(20);
    private JButton btnZavrsiVoznju = new JButton("Zavrsi voznju");
    private JButton btnCancel = new JButton("Cancel");

    private SveListe sl;
    private Voznja voznja;

    public ZavrsiVoznjuProzor(SveListe sl, Voznja voznja) {
        this.sl = sl;
        this.voznja = voznja;
        setTitle("Zavrsi voznju - " + voznja.getDatum());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initActions();
        setResizable(true);
        pack();
        getRootPane().setDefaultButton(btnZavrsiVoznju);
    }

    private void initGUI() {

        MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[]10[]10[]");
        setLayout(mig);

        add(lblBrojPredjenihKilometara);
        add(txtBrojPredjenihKilometara);
        add(lblTrajanjeVoznje);
        add(txtTrajanjeVoznje);
        add(new JLabel());
        add(btnZavrsiVoznju, "split 2");
        add(btnCancel);

        getRootPane().setDefaultButton(btnZavrsiVoznju);
    }

    private void initActions() {

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ZavrsiVoznjuProzor.this.dispose();
                ZavrsiVoznjuProzor.this.setVisible(false);

            }
        });

        btnZavrsiVoznju.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                voznja.setBrojPredjenihKilometara(txtBrojPredjenihKilometara.getText().trim());
                voznja.setTrajanjeVoznje(txtTrajanjeVoznje.getText().trim());

                voznja.setStatus(EStatusVoznje.ZAVRSENA);
                voznja.setObrisan(false);

                sl.snimiVoznje(Main.VOZNJE_FAJL);

                ZavrsiVoznjuProzor.this.dispose();
                ZavrsiVoznjuProzor.this.setVisible(false);

            }
        });
    }

}