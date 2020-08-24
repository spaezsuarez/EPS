package View.Medico;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Persistence.Agenda2DAO;

public class CrearAgendaParte2 extends JFrame {

    /**
     *
     */

    private static final long serialVersionUID = 1L;
    SimpleDateFormat formatterDateSQL = new SimpleDateFormat("hh:mm:ss");
    public JPanel panel;
    private Agenda2DAO agenda;
    private long idMedicoC;
    private String tipoDocumentoC;
    private long idAgendaC;
    private int MesC;
    private int AñoC;
    private long idAfiliadoC;
    private String tipoDocumentoAB;
    private String EstadoCita;
    private java.util.Date fechaUtil;
    private Time fechaSQL;
    private JFormattedTextField CHICita;

    public CrearAgendaParte2(long idMedico, String tipodocumento, long idAgenda, int Mes, int Año) {
        idMedicoC = idMedico;
        agenda = Agenda2DAO.getReference();
        tipoDocumentoC = tipodocumento;
        idAgendaC = idAgenda;
        idAfiliadoC = 0;
        tipoDocumentoAB = null;
        MesC = Mes;
        AñoC = Año;
        EstadoCita = "Disponible";
        initCompo();
        mostrar();
    }

    public void initCompo() {
        setSize(600, 350);
        setTitle("Crear una agenda");
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void mostrar() {
        JLabel Titulo = new JLabel("Agregar una cita", SwingConstants.CENTER);
        Titulo.setBounds(0, 10, 600, 30);
        Titulo.setFont(new Font("Serif", Font.BOLD, 22));
        Titulo.setForeground(new Color(21, 67, 96));
        panel.add(Titulo);

        JLabel MAgen = new JLabel("Digite el dia");
        MAgen.setBounds(100, 90, 100, 30);
        MAgen.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(MAgen);

        JTextField CMAgen = new JTextField();
        CMAgen.setBounds(325, 95, 180, 20);
        panel.add(CMAgen);

        JLabel AAgen = new JLabel("Seleccione la especialidad");
        AAgen.setBounds(100, 120, 300, 30);
        AAgen.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(AAgen);

        JTextField CAAgen = new JTextField();
        CAAgen.setBounds(325, 125, 180, 20);
        panel.add(CAAgen);

        JLabel TiCita = new JLabel("Seleccione el tipo de cita");
        TiCita.setBounds(100, 150, 300, 30);
        TiCita.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(TiCita);

        JTextField CTiCita = new JTextField();
        CTiCita.setBounds(325, 155, 180, 20);
        panel.add(CTiCita);

        JLabel HICita = new JLabel("Seleccione la hora inicial de la cita");
        HICita.setBounds(100, 180, 400, 30);
        HICita.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(HICita);

        try{
            CHICita = new JFormattedTextField(new MaskFormatter("##:##:##"));
            CHICita.setBounds(325, 185, 180, 20);
            panel.add(CHICita);
        }catch(ParseException e){

        }

        JButton Continuar = new JButton("Continuar");
        Continuar.setBounds(300, 245, 100, 30);
        panel.add(Continuar);

        JButton Finalizar = new JButton("Finalizar");
        Finalizar.setBounds(200, 245, 100, 30);
        panel.add(Finalizar);

        Finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        Continuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fechaUtil = formatterDateSQL.parse(CHICita.getText());
                    fechaSQL = new java.sql.Time(fechaUtil.getTime());
                    agenda.registrarCita(agenda.crearCitaID(), fechaSQL,
                            agenda.setHoraFinal(fechaSQL, CTiCita.getText()),
                            agenda.setFecha(Integer.parseInt(CMAgen.getText()), MesC, AñoC), EstadoCita, idAgendaC,
                            tipoDocumentoC, idMedicoC, agenda.setTipoCita(CTiCita.getText()), tipoDocumentoAB,
                            idAfiliadoC, agenda.getIDEspecialidad(CAAgen.getText()));
                    CMAgen.setText("");
                    CAAgen.setText("");
                    CTiCita.setText("");
                    CHICita. setValue("");
                } catch (SQLException z) {
                    System.out.println(z.getMessage());
                } catch (ParseException e1) {
                    e1.printStackTrace();
                } catch (NullPointerException e2){
        
                }
            }

        });
        
    }
}
