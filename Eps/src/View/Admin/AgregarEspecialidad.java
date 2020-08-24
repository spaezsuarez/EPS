package View.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Persistence.AdministradorDAO;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import Models.Medico;
import Models.EspecialidadMedicoConsultorio;
import java.awt.event.ItemEvent;

public class AgregarEspecialidad extends JFrame implements ActionListener {

    private JPanel panel;
    private Medico medico;
    private ArrayList<EspecialidadMedicoConsultorio> especialidades;
    private JLabel Titulo, Espe, Sede, Consul, tHorario;
    private JComboBox CEspe, CSede, CConsul;
    private JButton Registrar, AOtra;
    private JComboBox<String> horario;
    private AdministradorDAO controller = AdministradorDAO.getReference();
    private Boolean verif = false;

    public AgregarEspecialidad(Medico medico) {
        initCompo();
        mostrar();
        SolicitarEspecialidades();
        SolicitarSedes();
        this.medico = medico;
        especialidades = new ArrayList<>();
    }

    public void initCompo() {
        setSize(600, 270);
        setTitle("Agregar especialidad(es) al médico");
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void mostrar() {
        Titulo = new JLabel("Agregar especialidad(es) al médico", SwingConstants.CENTER);
        Titulo.setBounds(0, 10, 600, 30);
        Titulo.setFont(new Font("Serif", Font.BOLD, 22));
        Titulo.setForeground(new Color(21, 67, 96));

        tHorario = new JLabel("Horario");
        tHorario.setBounds(100, 60, 100, 30);

        Espe = new JLabel("Especialidad");
        Espe.setBounds(100, 90, 100, 30);
        Espe.setFont(new Font("Serif", Font.BOLD, 14));

        Sede = new JLabel("Sede");
        Sede.setBounds(100, 120, 300, 30);
        Sede.setFont(new Font("Serif", Font.BOLD, 14));

        Consul = new JLabel("Consultorio");
        Consul.setBounds(100, 150, 300, 30);
        Consul.setFont(new Font("Serif", Font.BOLD, 14));

        horario = new JComboBox<>();
        horario.setBackground(Color.WHITE);
        horario.setBounds(305, 60, 200, 20);
        horario.addItem("");
        horario.addItem("Mañana");
        horario.addItem("Tarde");

        CEspe = new JComboBox();
        CEspe.setBounds(305, 95, 200, 20);

        CSede = new JComboBox();
        CSede.setBounds(305, 125, 200, 20);
        CSede.addItemListener((ItemEvent ie) -> {
            if (!CSede.getSelectedItem().toString().equals("")) {
                CConsul.removeAllItems();
                SolicitarConsultorios(CSede.getSelectedItem().toString());
            }
        });

        CConsul = new JComboBox();
        CConsul.setBounds(305, 150, 200, 20);

        Registrar = new JButton("Terminar registro");
        Registrar.setBounds(130, 190, 150, 30);
        Registrar.addActionListener(this);

        AOtra = new JButton("Añadir");
        AOtra.setBounds(300, 190, 150, 30);
        AOtra.addActionListener(this);

        panel.add(Titulo);
        panel.add(tHorario);
        panel.add(Espe);
        panel.add(Sede);
        panel.add(Consul);
        panel.add(horario);
        panel.add(CSede);
        panel.add(CEspe);
        panel.add(Registrar);
        panel.add(AOtra);
        panel.add(CConsul);
    }

    public void SolicitarEspecialidades() {
        try {
            CEspe.addItem("");
            ArrayList<String> nombresEspecialidades = new ArrayList<String>();
            nombresEspecialidades = controller.getEspecialidades();
            nombresEspecialidades.forEach(nombreEsp -> {
                CEspe.addItem(nombreEsp);
            });
        } catch (SQLException ex) {
            System.out.println("Fallo SQL: " + ex.getMessage());
        }
    }

    public void SolicitarSedes() {
        try {
            CSede.addItem("");
            ArrayList<String> nombresEspecialidades = new ArrayList<String>();
            nombresEspecialidades = controller.getSedes();
            nombresEspecialidades.forEach(nombreSede -> {
                CSede.addItem(nombreSede);
            });

        } catch (SQLException ex) {
            System.out.println("Fallo SQL: " + ex.getMessage());
        }
    }

    public void SolicitarConsultorios(String nombreSede) {
        try {
            CConsul.addItem("");
            ArrayList<String> iDsConsultorios = new ArrayList<String>();
            iDsConsultorios = controller.getConsultorios(nombreSede);
            iDsConsultorios.forEach(iDConsul -> {
                CConsul.addItem(iDConsul);
            });
        } catch (SQLException ex) {
            System.out.println("Fallo SQL: " + ex.getMessage());
        }
    }
  
    public boolean VerificarChecks() {
        if (horario.getSelectedItem().toString().equals("") || CEspe.getSelectedItem().toString().equals("") || CSede.getSelectedItem().toString().equals("") || CConsul.getSelectedItem().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Registrar) {
            if (verif) {
                try {
                    controller.registrarUsuario(medico);
                    controller.registrarMedico(medico);
                    controller.agregarEspecialidadesMedico(especialidades);
                    JOptionPane.showMessageDialog(this, "Medico " + medico.getNombreUsuario() + " Creado exitosamente ", "Estado registro", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (SQLException ex) {
                    System.out.println("Fallo SQL: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Agregue por lo menos una especialidad para poder completar el registro", "Estado registro", JOptionPane.WARNING_MESSAGE);
            }
        } else if (ae.getSource() == AOtra) {
            if (VerificarChecks()) {
                try {
                    EspecialidadMedicoConsultorio object;
                    object = new EspecialidadMedicoConsultorio(controller.getIDEspecialidad(CEspe.getSelectedItem().toString()),
                            medico.getTipoDocumento(), medico.getNumeroDocumento(), Long.valueOf(CConsul.getSelectedItem().toString()),
                            controller.getIDSede(CSede.getSelectedItem().toString()), String.valueOf(horario.getSelectedItem()));
                    especialidades.add(object);
                    verif = true;
                    horario.setSelectedItem("");
                    CEspe.setSelectedItem("");
                    CSede.setSelectedItem("");
                    CConsul.setSelectedItem("");
                } catch (SQLException ex) {
                    System.out.println("Fallo SQL:" + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Rellene todos los campos", "Estado registro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
