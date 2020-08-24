package View.Medico;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import Persistence.AgendaDAO;


public class CrearAgendaParte1 extends JFrame{
    
    
    public JPanel panel;
    private AgendaDAO agenda;
    private long idMedicoC;
    private String tipoDocumentoC;
    private long idAgendaC;

    public CrearAgendaParte1(long idMedico, String tipodocumento, long idAgenda){
        idMedicoC = idMedico;
        tipoDocumentoC = tipodocumento;
        idAgendaC = idAgenda;
        agenda = AgendaDAO.getReference();
        initCompo();
        mostrar();
    }
    
    public void initCompo(){
        setSize(600,250); 
        setTitle("Crear una agenda");
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void mostrar(){
        JLabel Titulo = new JLabel("Crear una agenda", SwingConstants.CENTER);
        Titulo.setBounds(0, 10, 600, 30);
        Titulo.setFont(new Font("Serif", Font.BOLD, 22)); 
        Titulo.setForeground(new Color(21, 67, 96));
        panel.add(Titulo);
        
        JLabel MAgen = new JLabel("Digite el mes");
        MAgen.setBounds(100, 90, 100, 30);
        MAgen.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(MAgen);
        
        JTextField CMAgen = new JTextField();
        CMAgen.setBounds(305, 95, 200, 20);
        panel.add(CMAgen);
        
        JLabel AAgen = new JLabel("Digite el año");
        AAgen.setBounds(100, 120, 300, 30);
        AAgen.setFont(new Font("Serif", Font.BOLD, 14)); 
        panel.add(AAgen);
        
        JTextField CAAgen = new JTextField();
        CAAgen.setBounds(305, 125, 200, 20);
        panel.add(CAAgen);
        
        JButton Continuar = new JButton("Continuar");
        Continuar.setBounds(250, 165, 100, 30);
        panel.add(Continuar);
        
        Continuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    agenda.registrarAgenda(idMedicoC, tipoDocumentoC, idAgendaC, Integer.parseInt(CMAgen.getText()), Integer.parseInt(CAAgen.getText()));
                    CrearAgendaParte2 Cont = new CrearAgendaParte2(idMedicoC, tipoDocumentoC, idAgendaC, Integer.parseInt(CMAgen.getText()), Integer.parseInt(CAAgen.getText()) );
                    Cont.setVisible(true);
                    Cont.setLocationRelativeTo(null);
                } catch(SQLException z) {
                    System.out.println(z.getMessage());
                }
            }	
        });
        
    }
}
