
package View;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

/**
 *
 * @author Neraky
 */
public class AsignarCita extends JFrame {

    public JPanel panel;


    public AsignarCita() {
        setSize(800, 600);
        setTitle("Asignacion de citas");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600));

        Paneles();
        Etiquetas();
        Botones();
        BotonesSeleccion();
        Horario();
        this.getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void Paneles() {
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setBackground(Color.CYAN);
        panel.setLayout(null);



    }
    private void Etiquetas(){

        JLabel etiqueta = new JLabel("ASIGNACION DE CITAS",SwingConstants.CENTER);
        etiqueta.setBounds(200, 20, 400, 40);
        etiqueta.setForeground(Color.DARK_GRAY);
        etiqueta.setOpaque(true);
        etiqueta.setFont(new Font("arial", Font.BOLD, 30));
        etiqueta.setBackground(Color.CYAN);

        JLabel etiquetaEspecialidad = new JLabel("SELECCIONE LA ESPECIALIDAD",SwingConstants.CENTER);
        etiquetaEspecialidad.setBounds(90, 150, 180, 20);
        etiquetaEspecialidad.setForeground(Color.DARK_GRAY);
        etiquetaEspecialidad.setOpaque(true);
        etiquetaEspecialidad.setFont(new Font("font", Font.BOLD, 12));
        etiquetaEspecialidad.setBackground(Color.CYAN);

        JLabel etiquetaHorarios = new JLabel("SELECCIONE EL HORARIO",SwingConstants.CENTER);
        etiquetaHorarios.setBounds(280, 150, 180, 20);
        etiquetaHorarios.setForeground(Color.DARK_GRAY);
        etiquetaHorarios.setOpaque(true);
        etiquetaHorarios.setFont(new Font("font", Font.BOLD, 12));
        etiquetaHorarios.setBackground(Color.CYAN);


        panel.add(etiqueta);
        panel.add(etiquetaEspecialidad);
        panel.add(etiquetaHorarios);
    }
    private void Botones(){
        JButton botonEspecialidad = new JButton("Verificar disponibilidad");
        botonEspecialidad.setBounds(300, 500, 200, 30);
        panel.add(botonEspecialidad);
    }

    private void BotonesSeleccion(){
        JRadioButton medicinaGeneral = new JRadioButton("Medicina General",false);
        medicinaGeneral.setBounds(100, 200, 170, 40);
        medicinaGeneral.setFont(new Font("font", Font.BOLD, 15));
        panel.add(medicinaGeneral);

        JRadioButton pediatria = new JRadioButton("Pediatria",false);
        pediatria.setBounds(100, 250, 130, 40);
        pediatria.setFont(new Font("font", Font.BOLD, 15));
        panel.add(pediatria);

        JRadioButton oftalmologia = new JRadioButton("Oftalmologia",false);
        oftalmologia.setBounds(100, 300, 130, 40);
        oftalmologia.setFont(new Font("font", Font.BOLD, 15));
        panel.add(oftalmologia);

        JRadioButton odontologia = new JRadioButton("Odontologia",false);
        odontologia.setBounds(100, 350, 130, 40);
        odontologia.setFont(new Font("font", Font.BOLD, 15));
        panel.add(odontologia);

        ButtonGroup especialidad = new ButtonGroup();
        especialidad.add(medicinaGeneral);
        especialidad.add(pediatria);
        especialidad.add(odontologia);
        especialidad.add(oftalmologia);



    }
    private void Horario(){
        String [] horarios = {"8:00 am - 9:00 am","9:00 am - 10:00 am","10:00 am - 11:00 am",
                              "11:00 am - 12:00 pm","12:00 pm - 1:00 pm","1:00 pm - 2:00 pm",
                              "2:00 pm - 3:00 pm","3:00 pm - 4:00 pm","4:00 pm - 5:00 pm"};

        JComboBox listaHorarios = new JComboBox(horarios);
        listaHorarios.setBounds(300, 200, 150, 30);
        panel.add(listaHorarios);
    }

}