package View.AfiliadoBeneficiario;

import View.AfiliadoBeneficiario.CancelarCita;
import View.AfiliadoBeneficiario.ConsultarCita;
import View.AfiliadoBeneficiario.SolicitarCita;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InterfazAfiliadoBeneficiario extends JFrame {

    private JPanel panel;
    private long iDAfiliadoRef;
    private String TipoAfiliadoRef;

    public InterfazAfiliadoBeneficiario(String tDAfiliado, long iDAfiliado) {
        iDAfiliadoRef = iDAfiliado;
        TipoAfiliadoRef = tDAfiliado;
        initCompo();
        mostrar();
    }

    public void initCompo() {
        setSize(600, 270);
        setTitle("Opciones Afiliado/Beneficiario");
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void mostrar() {
        JLabel Titulo = new JLabel("¿Que acción desea realizar?", SwingConstants.CENTER);
        Titulo.setBounds(0, 10, 600, 30);
        Titulo.setFont(new Font("Serif", Font.BOLD, 22));
        Titulo.setForeground(new Color(21, 67, 96));
        panel.add(Titulo);

        JButton ACita = new JButton("Solicitar una cita");
        ACita.setBounds(200, 70, 200, 30);
        panel.add(ACita);

        JButton ConsCita = new JButton("Consultar una cita");
        ConsCita.setBounds(200, 120, 200, 30);
        panel.add(ConsCita);

        JButton CanCita = new JButton("Cancelar una cita");
        CanCita.setBounds(200, 170, 200, 30);
        panel.add(CanCita);

        ACita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                SolicitarCita ISoliCita = new SolicitarCita(TipoAfiliadoRef, iDAfiliadoRef);
                ISoliCita.setVisible(true);
                ISoliCita.setLocationRelativeTo(null);
            }
        });

        ConsCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultarCita Aconscita = new ConsultarCita(iDAfiliadoRef);
                Aconscita.setVisible(true);
                Aconscita.setLocationRelativeTo(null);
            }
        });

        CanCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CancelarCita cCita = new CancelarCita(iDAfiliadoRef);
            }
        });

    }

}
