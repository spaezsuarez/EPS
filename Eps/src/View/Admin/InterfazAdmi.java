package View.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InterfazAdmi extends JFrame {

    private JPanel panel;

    public InterfazAdmi() {
        this.initCompo();
        this.mostrar();
    }

    public void initCompo() {
        setSize(600, 270);
        setTitle("Opciones del administrador");
        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void mostrar() {
        JLabel Titulo = new JLabel("¿Que acción desea realizar?", SwingConstants.CENTER);
        Titulo.setBounds(0, 10, 600, 30);
        Titulo.setFont(new Font("Serif", Font.BOLD, 22));
        Titulo.setForeground(new Color(21, 67, 96));
        panel.add(Titulo);

        JButton RegisMedic = new JButton("Registrar un medico");
        RegisMedic.setBounds(200, 90, 200, 30);
        panel.add(RegisMedic);

        JButton RegisAB = new JButton("Registrar un afiliado/beneficiario");
        RegisAB.setBounds(150, 160, 300, 30);
        panel.add(RegisAB);

        RegisMedic.addActionListener((ActionEvent e) -> {
            RegistrarMedico IRegisMedic = new RegistrarMedico();
        });
        RegisAB.addActionListener((ActionEvent e) -> {
            RegistrarAB IRegisAB = new RegistrarAB();
        });
    }
}
