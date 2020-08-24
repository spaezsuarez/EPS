package View.Medico;

import View.Medico.CrearAgendaParte1;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Persistence.AgendaDAO;
import java.sql.SQLException;

public class InterfazMedico extends JFrame {

    public JPanel panel;
    private String tipoDocumentoRef;
    private long iDMedicoRef;
    private AgendaDAO agenda;

    public InterfazMedico(long iDMedico, String tipodocumento) {

        iDMedicoRef = iDMedico;
        tipoDocumentoRef = tipodocumento;
        agenda = AgendaDAO.getReference();
        initCompo();
        mostrar();
    }

    public void initCompo() {
        setSize(600, 200);
        setTitle("Opciones del medico");
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

        JButton CreaAgen = new JButton("Crear una agenda");
        CreaAgen.setBounds(200, 70, 200, 30);
        panel.add(CreaAgen);


        CreaAgen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CrearAgendaParte1 Agen1 = new CrearAgendaParte1(iDMedicoRef, tipoDocumentoRef, agenda.crearAgendaID());
                    Agen1.setVisible(true);
                    Agen1.setLocationRelativeTo(null);
                }catch(SQLException z){

                }
            }
        });

    }

}
