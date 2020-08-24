package View;

import View.Admin.InterfazAdmi;
import View.Medico.InterfazMedico;
import View.AfiliadoBeneficiario.InterfazAfiliadoBeneficiario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import Persistence.DAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class IniciarSesion extends JFrame {

    private JPanel panel;
    private DAO controlador;

    public IniciarSesion() {
        initCompo();
        mostrar();
        controlador = DAO.getReference();
    }

    public void initCompo() {
        setSize(600, 320);
        setTitle("Iniciar Sesión");
        setResizable(false);
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void mostrar() {
        JLabel Titulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        Titulo.setBounds(0, 10, 600, 30);
        Titulo.setFont(new Font("Serif", Font.BOLD, 22));
        Titulo.setForeground(new Color(21, 67, 96));
        panel.add(Titulo);

        JLabel TipoD = new JLabel("Tipo");
        TipoD.setBounds(100, 90, 300, 30);
        TipoD.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(TipoD);

        JLabel textTipoUsuario = new JLabel("¿Quien esta ingresando?");
        textTipoUsuario.setBounds(100, 120, 700, 30);
        textTipoUsuario.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(textTipoUsuario);

        JComboBox<String> TipoUsuario = new JComboBox<>();
        TipoUsuario.addItem("Medico");
        TipoUsuario.addItem("Afiliado/Beneficiario");
        TipoUsuario.setBounds(305, 125, 200, 20);
        panel.add(TipoUsuario);

        JComboBox<String> MTipo = new JComboBox<>();
        MTipo.addItem("Cedula de ciudadania");
        MTipo.addItem("Cedula de extrangeria");
        MTipo.addItem("Pasaporte");
        MTipo.addItem("Registro civil");
        MTipo.setBounds(305, 95, 200, 20);
        MTipo.setBackground(Color.white);
        panel.add(MTipo);

        JLabel ID = new JLabel("Número identificación *");
        ID.setBounds(100, 150, 300, 30);
        ID.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(ID);

        JTextField CID = new JTextField();
        CID.setBounds(305, 155, 200, 20);
        CID.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (CID.getText().length() >= 12) {
                    e.consume();
                }
                if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == ' ') {
                    e.consume();
                }
            }
        });
        panel.add(CID);

        JButton ISesion = new JButton("Iniciar Sesión");
        ISesion.setSize(new Dimension(150, 30));
        ISesion.setLocation(220, 200);
        panel.add(ISesion);

        JButton botonBack = new JButton("Exit");
        botonBack.setBounds(15, 250, 100, 30);
        botonBack.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        panel.add(botonBack);

        JButton Adminview = new JButton("Admin view");
        Adminview.setSize(new Dimension(150, 30));
        Adminview.setLocation(432, 250);
        Adminview.addActionListener((ActionEvent ae) -> {
            InterfazAdmi adminView = new InterfazAdmi();
        });
        panel.add(Adminview);

        ISesion.addActionListener((ActionEvent ae) -> {
            String Id = CID.getText();
            String tipoUsuario = String.valueOf(TipoUsuario.getSelectedItem());
            String tipoDocumento = String.valueOf(MTipo.getSelectedItem());
            if (tipoUsuario.equals("Medico")) {
                try {
                    if (controlador.getUser("medico", tipoDocumento, Long.parseLong(Id))) {
                        IniciarSesion.this.setVisible(false);
                        InterfazMedico medico = new InterfazMedico(Long.valueOf(CID.getText()), tipoDocumento);
                        medico.setVisible(true);
                        medico.setLocationRelativeTo(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Estado login", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException aex) {
                    JOptionPane.showMessageDialog(null, "Verifique que sus datos esten correctos", "Estado login", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException aex) {
                    JOptionPane.showMessageDialog(null, "Rellene correctamente todos los campos obligatorios", "Estado login", JOptionPane.ERROR_MESSAGE);
                }
            } else if (tipoUsuario.equals("Afiliado/Beneficiario")) {
                try {
                    if (controlador.getUser("afiliado_beneficiario", tipoDocumento, Long.parseLong(Id))) {
                        IniciarSesion.this.setVisible(false);
                        InterfazAfiliadoBeneficiario AB = new InterfazAfiliadoBeneficiario(tipoDocumento, Long.valueOf(CID.getText()));
                        AB.setVisible(true);
                        AB.setLocationRelativeTo(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Estado login", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException aex) {
                    JOptionPane.showMessageDialog(null, "Ha habido un problema verifique que los datos son correctos o que su usuario este activo ", "Estado login", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException aex) {
                    JOptionPane.showMessageDialog(null, "Rellene correctamente todos los campos obligatorios", "Estado login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
