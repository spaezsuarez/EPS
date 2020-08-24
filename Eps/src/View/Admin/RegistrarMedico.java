package View.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import Models.Medico;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class RegistrarMedico extends JFrame {

    private JPanel panel;
    private JLabel Titulo, Nom, DM, Tipo, Apell, Sexo, FN, Cont, TC;
    private JComboBox MTipo;
    private JLabel ID, NC, Correo, FA, RM;
    private JTextField CID, CApell, CTC, CNom, CNC, CCorreo, CRM;
    private JComboBox MSexo;
    private JFormattedTextField CFN;
    private JButton btn;
    private SimpleDateFormat formatterDateSQL = new SimpleDateFormat("yyyy-MM-dd");
    private java.util.Date fechaUtil;
    private java.sql.Date fechaSQL;

    public RegistrarMedico() {
        initCompo();
        mostrar();
    }

    public void initCompo() {
        setSize(600, 660);
        setTitle("Registrar un nuevo medico");
        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void mostrar() {

        Titulo = new JLabel("Registrar un nuevo medico", SwingConstants.CENTER);
        Titulo.setBounds(0, 10, 600, 30);
        Titulo.setFont(new Font("Serif", Font.BOLD, 22));
        Titulo.setForeground(new Color(21, 67, 96));
        panel.add(Titulo);

        DM = new JLabel("Datos del médico");
        DM.setBounds(5, 50, 600, 30);
        DM.setFont(new Font("Serif", Font.BOLD, 18));
        DM.setForeground(new Color(21, 67, 96));
        panel.add(DM);

        Tipo = new JLabel("Tipo identificación *");
        Tipo.setBounds(5, 80, 300, 30);
        Tipo.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(Tipo);

        MTipo = new JComboBox();
        MTipo.addItem("Registro civil");
        MTipo.addItem("Tarjeta de identidad");
        MTipo.addItem("Cedula de ciudadania");
        MTipo.addItem("Cedula de extrangeria");
        MTipo.setBounds(5, 115, 200, 20);
        MTipo.setBackground(Color.white);
        panel.add(MTipo);

        ID = new JLabel("Número identificación *");
        ID.setBounds(305, 80, 300, 30);
        ID.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(ID);

        CID = new JTextField();
        CID.setBounds(305, 115, 200, 20);
        panel.add(CID);

        Nom = new JLabel("Nombre(s) *");
        Nom.setBounds(5, 140, 300, 30);
        Nom.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(Nom);

        CNom = new JTextField();
        CNom.setBounds(5, 175, 200, 20);
        panel.add(CNom);

        Apell = new JLabel("Apellido(s) *");
        Apell.setBounds(305, 140, 300, 30);
        Apell.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(Apell);

        CApell = new JTextField();
        CApell.setBounds(305, 175, 200, 20);
        panel.add(CApell);

        Sexo = new JLabel("Sexo *");
        Sexo.setBounds(5, 200, 300, 30);
        Sexo.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(Sexo);

        MSexo = new JComboBox();
        MSexo.addItem("F");
        MSexo.addItem("M");
        MSexo.setBounds(5, 235, 200, 20);
        MSexo.setBackground(Color.white);
        panel.add(MSexo);

        FN = new JLabel("Fecha de nacimiento (YYYY/MM/DD) *");
        FN.setBounds(305, 200, 300, 30);
        FN.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(FN);

        try {
            CFN = new JFormattedTextField(new MaskFormatter("####-##-##"));
            CFN.setBounds(350, 235, 120, 20);
            CFN.setFont(new Font("font", Font.PLAIN, 12));
            CFN.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(CFN);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Los datos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        Cont = new JLabel("Contacto");
        Cont.setBounds(5, 260, 600, 30);
        Cont.setFont(new Font("Serif", Font.BOLD, 18));
        Cont.setForeground(new Color(21, 67, 96));
        panel.add(Cont);

        TC = new JLabel("Teléfono casa");
        TC.setBounds(5, 290, 300, 30);
        TC.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(TC);

        CTC = new JTextField();
        CTC.setBounds(5, 325, 200, 20);
        panel.add(CTC);

        NC = new JLabel("Teléfono celular *");
        NC.setBounds(305, 290, 300, 30);
        NC.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(NC);

        CNC = new JTextField();
        CNC.setBounds(305, 325, 200, 20);
        panel.add(CNC);

        Correo = new JLabel("Correo electrónico *");
        Correo.setBounds(5, 350, 300, 30);
        Correo.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(Correo);

        CCorreo = new JTextField();
        CCorreo.setBounds(5, 385, 200, 20);
        panel.add(CCorreo);

        FA = new JLabel("Formación académica");
        FA.setBounds(5, 410, 300, 30);
        FA.setFont(new Font("Serif", Font.BOLD, 18));
        FA.setForeground(new Color(21, 67, 96));
        panel.add(FA);

        RM = new JLabel("Registro médico *");
        RM.setBounds(5, 440, 300, 30);
        RM.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(RM);

        CRM = new JTextField();
        CRM.setBounds(5, 475, 200, 20);
        panel.add(CRM);

        btn = new JButton("Agregar especialidad(es)");
        btn.setBounds(350, 560, 200, 30);
        btn.addActionListener((ActionEvent ae) -> {
            try {
                fechaUtil = formatterDateSQL.parse(CFN.getText());
                fechaSQL = new java.sql.Date(fechaUtil.getTime());
                String telefonoCasa = CTC.getText();
                Medico medico = null;
                
                if(telefonoCasa.equals("")){
                     medico = new Medico(CRM.getText(), String.valueOf(MTipo.getSelectedItem()),
                        Long.valueOf(CID.getText()), (CNom.getText() + " " + CApell.getText()), String.valueOf(MSexo.getSelectedItem()),
                        fechaSQL,0,Long.valueOf(CNC.getText()), Correo.getText(), 1);
                }else{
                    medico = new Medico(CRM.getText(), String.valueOf(MTipo.getSelectedItem()),
                        Long.valueOf(CID.getText()), (CNom.getText() + " " + CApell.getText()), String.valueOf(MSexo.getSelectedItem()),
                        fechaSQL, Long.valueOf(telefonoCasa), Long.valueOf(CNC.getText()), Correo.getText(), 1);
                }
                              
                AgregarEspecialidad agregarEspecialidad = new AgregarEspecialidad(medico);
                agregarEspecialidad.setLocationRelativeTo(null);
                agregarEspecialidad.setVisible(true);
                dispose();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Los datos deben estar diligenciados", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException a) {
                JOptionPane.showMessageDialog(null, "Asegurese que los datos estan bien diligenciados", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btn);

        JButton botonBack = new JButton("Back");
        botonBack.setBounds(50, 560, 100, 30);
        panel.add(botonBack);
        botonBack.addActionListener((ActionEvent ae) -> {
            dispose();
        });

    }
}
