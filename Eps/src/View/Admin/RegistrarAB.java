package View.Admin;

import Models.Afiliado;
import Persistence.AdministradorDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class RegistrarAB extends JFrame {

    private JPanel panel;
    private AdministradorDAO control;
    java.util.Date fechaUtil;
    java.sql.Date fechaSQL;

    public RegistrarAB() {
        initCompo();
        mostrar();
        control = AdministradorDAO.getReference();
        setAlwaysOnTop(true);
    }

    public void initCompo() {
        setSize(600, 800);
        setTitle("Registrar un nuevo afiliado/beneficiario");
        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void mostrar() {
        SimpleDateFormat formatterDateSQL = new SimpleDateFormat("yyyy-MM-dd");
        MaskFormatter formatterDate = null;

        try {
            formatterDate = new MaskFormatter("####-##-##");
        } catch (ParseException e) {
            System.out.println("Fallo en el parse: " + e.getMessage());
        }

        JLabel Titulo = new JLabel("Registrar un nuevo afiliado/beneficiario", SwingConstants.CENTER);
        Titulo.setBounds(0, 10, 600, 30);
        Titulo.setFont(new Font("Serif", Font.BOLD, 22));
        Titulo.setForeground(new Color(21, 67, 96));

        JLabel labelDatosAB = new JLabel("Datos del usuario");
        labelDatosAB.setBounds(5, 50, 600, 30);
        labelDatosAB.setFont(new Font("Serif", Font.BOLD, 18));
        labelDatosAB.setForeground(new Color(21, 67, 96));

        JLabel labelTipoIdentificacion = new JLabel("Tipo identificación *");
        labelTipoIdentificacion.setBounds(5, 80, 300, 30);
        labelTipoIdentificacion.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelNumeroID = new JLabel("Número identificación *");
        labelNumeroID.setBounds(305, 80, 300, 30);
        labelNumeroID.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelNombres = new JLabel("Nombre(s) *");
        labelNombres.setBounds(5, 140, 300, 30);
        labelNombres.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelApellidos = new JLabel("Apellido(s) *");
        labelApellidos.setBounds(305, 140, 300, 30);
        labelApellidos.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelSexo = new JLabel("Sexo *");
        labelSexo.setBounds(5, 200, 300, 30);
        labelSexo.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelFechaNacimiento = new JLabel("Fecha de nacimiento (YYYY/MM/DD) *");
        labelFechaNacimiento.setBounds(305, 200, 300, 30);
        labelFechaNacimiento.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelTelefonoContacto = new JLabel("Contacto");
        labelTelefonoContacto.setBounds(5, 260, 600, 30);
        labelTelefonoContacto.setFont(new Font("Serif", Font.BOLD, 18));
        labelTelefonoContacto.setForeground(new Color(21, 67, 96));

        JLabel labelTelefonoCasa = new JLabel("Teléfono casa");
        labelTelefonoCasa.setBounds(5, 290, 300, 30);
        labelTelefonoCasa.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelTelefonoCelular = new JLabel("Teléfono celular *");
        labelTelefonoCelular.setBounds(305, 290, 300, 30);
        labelTelefonoCelular.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelCorreo = new JLabel("Correo electrónico *");
        labelCorreo.setBounds(5, 350, 300, 30);
        labelCorreo.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelAfiliacion = new JLabel("Afiliación");
        labelAfiliacion.setBounds(5, 410, 300, 30);
        labelAfiliacion.setFont(new Font("Serif", Font.BOLD, 18));
        labelAfiliacion.setForeground(new Color(21, 67, 96));

        JLabel labelTipoAfiliacion = new JLabel("Tipo de afiliación *");
        labelTipoAfiliacion.setBounds(5, 440, 300, 30);
        labelTipoAfiliacion.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelEstado = new JLabel("Estado *");
        labelEstado.setBounds(305, 440, 300, 30);
        labelEstado.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelCategoria = new JLabel("Categoria *");
        labelCategoria.setBounds(5, 500, 300, 30);
        labelCategoria.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelAfiliadoRelacionado = new JLabel("Afiliado relacionado");
        labelAfiliadoRelacionado.setBounds(5, 560, 300, 30);
        labelAfiliadoRelacionado.setFont(new Font("Serif", Font.BOLD, 18));
        labelAfiliadoRelacionado.setForeground(new Color(21, 67, 96));

        JLabel labelTipoID = new JLabel("Tipo identificación");
        labelTipoID.setBounds(5, 590, 300, 30);
        labelTipoID.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelNumeroIDAfiliado = new JLabel("Número identificación");
        labelNumeroIDAfiliado.setBounds(305, 590, 300, 30);
        labelNumeroIDAfiliado.setFont(new Font("Serif", Font.BOLD, 14));

        JComboBox comboTipoID = new JComboBox();
        comboTipoID.addItem("Registro civil");
        comboTipoID.addItem("Tarjeta de identidad");
        comboTipoID.addItem("Cedula de ciudadania");
        comboTipoID.addItem("Cedula de extrangeria");
        comboTipoID.setBounds(5, 115, 200, 20);
        comboTipoID.setBackground(Color.white);

        JComboBox comboEstado = new JComboBox();
        comboEstado.addItem("Activo");
        comboEstado.addItem("Inactivo");
        comboEstado.addItem("En mora");
        comboEstado.setBounds(305, 475, 200, 20);
        comboEstado.setBackground(Color.white);

        JComboBox comboCategoria = new JComboBox();
        comboCategoria.addItem("A");
        comboCategoria.addItem("B");
        comboCategoria.addItem("C");
        comboCategoria.setBounds(5, 535, 200, 20);
        comboCategoria.setBackground(Color.white);

        JComboBox comboTipoIDAfiRelacionado = new JComboBox();
        comboTipoIDAfiRelacionado.addItem("Registro civil");
        comboTipoIDAfiRelacionado.addItem("Tarjeta de identidad");
        comboTipoIDAfiRelacionado.addItem("Cedula de ciudadania");
        comboTipoIDAfiRelacionado.addItem("Cedula de extrangeria");
        comboTipoIDAfiRelacionado.setBounds(5, 625, 200, 20);
        comboTipoIDAfiRelacionado.setBackground(Color.white);

        JComboBox comboSexo = new JComboBox();
        comboSexo.addItem("F");
        comboSexo.addItem("M");
        comboSexo.setBounds(5, 235, 200, 20);
        comboSexo.setBackground(Color.white);

        JTextField textoIDAfiRela = new JTextField();
        textoIDAfiRela.setBounds(305, 625, 200, 20);
        textoIDAfiRela.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (textoIDAfiRela.getText().length() >= 12) {
                    e.consume();
                }
                if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == ' ') {
                    e.consume();
                }
            }
        });

        JComboBox comboTipoAfiliacion = new JComboBox();
        comboTipoAfiliacion.addItem("Afiliado");
        comboTipoAfiliacion.addItem("Beneficiario");
        comboTipoAfiliacion.setBounds(5, 475, 200, 20);
        comboTipoAfiliacion.addItemListener((ItemEvent ie) -> {
            if (comboTipoAfiliacion.getSelectedItem().toString().equals("Afiliado")) {
                textoIDAfiRela.setText(null);
                textoIDAfiRela.setEnabled(false);
                comboTipoIDAfiRelacionado.setEnabled(false);
                comboTipoIDAfiRelacionado.setSelectedIndex(0);
            } else if (comboTipoAfiliacion.getSelectedItem().toString().equals("Beneficiario")) {
                textoIDAfiRela.setText(null);
                textoIDAfiRela.setEnabled(true);
                comboTipoIDAfiRelacionado.setEnabled(true);
                comboTipoIDAfiRelacionado.setSelectedIndex(0);
            }
        });
        comboTipoAfiliacion.setSelectedItem("Beneficiario");

        JTextField textoID = new JTextField();
        textoID.setBounds(305, 115, 200, 20);
        textoID.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (textoID.getText().length() >= 12) {
                    e.consume();
                }
                if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == ' ') {
                    e.consume();
                }
            }
        });

        JTextField textoNombres = new JTextField();
        textoNombres.setBounds(5, 175, 200, 20);
        textoNombres.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (textoNombres.getText().length() >= 25) {
                    e.consume();
                }
            }
        });

        JTextField textoApellidos = new JTextField();
        textoApellidos.setBounds(305, 175, 200, 20);
        textoApellidos.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (textoApellidos.getText().length() >= 25) {
                    e.consume();
                }
            }
        });
        JFormattedTextField textoFechaNacimiento = new JFormattedTextField(formatterDate);
        textoFechaNacimiento.setBounds(305, 235, 200, 20);
        textoFechaNacimiento.setText("textoFechaNacimiento");
        textoFechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField textoTelefonoCasa = new JTextField();
        textoTelefonoCasa.setBounds(5, 325, 200, 20);
        textoTelefonoCasa.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (textoTelefonoCasa.getText().length() >= 10) {
                    e.consume();
                }
                if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == ' ') {
                    e.consume();
                }
            }
        });

        JTextField textoTelefonoCelular = new JTextField();
        textoTelefonoCelular.setBounds(305, 325, 200, 20);
        textoTelefonoCelular.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (textoTelefonoCelular.getText().length() >= 12) {
                    e.consume();
                }
                if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == ' ') {
                    e.consume();
                }
            }
        });

        JTextField textoCorreo = new JTextField();
        textoCorreo.setBounds(5, 385, 200, 20);
        textoCorreo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (textoCorreo.getText().length() >= 45) {
                    e.consume();
                }
            }
        });

        JButton botonBack = new JButton("Back");
        botonBack.setBounds(50, 700, 100, 30);

        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.setBounds(400, 700, 100, 30);

        botonBack.addActionListener((ActionEvent ae) -> {
            dispose();
        });
        botonRegistrar.addActionListener((ActionEvent ae) -> {
            if (comboTipoAfiliacion.getSelectedItem().toString().equals("Afiliado")) {
                if (textoID.getText().equals(null) || textoNombres.getText().equals(null)
                        || textoApellidos.getText().equals(null) || textoFechaNacimiento.getText().equals(null)
                        || textoTelefonoCasa.getText().equals(null) || textoTelefonoCelular.getText().equals(null)
                        || textoCorreo.getText().equals(null)) {
                    JOptionPane.showMessageDialog(this, "Rellene todos los campos necesarios", "Estado registro", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        fechaUtil = formatterDateSQL.parse(textoFechaNacimiento.getText());
                        fechaSQL = new java.sql.Date(fechaUtil.getTime());
                        Afiliado afiliado = new Afiliado(comboTipoID.getSelectedItem().toString(), Long.valueOf(textoID.getText()), textoNombres.getText() + " " + textoApellidos.getText(),
                                comboSexo.getSelectedItem().toString(), fechaSQL, Long.valueOf(textoTelefonoCasa.getText()), Long.valueOf(textoTelefonoCelular.getText()),
                                textoCorreo.getText(), 1, comboTipoAfiliacion.getSelectedItem().toString(), comboEstado.getSelectedItem().toString(), comboCategoria.getSelectedItem().toString());
                        control.registrarUsuario(afiliado);
                        control.registrarAB(afiliado);
                        JOptionPane.showMessageDialog(this, "Registro exitoso", "Estado registro", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } catch (SQLException ex) {
                        System.out.println("Fallo SQL: " + ex.getMessage());
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(this, "Los datos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (comboTipoAfiliacion.getSelectedItem().toString().equals("Beneficiario")) {
                if (textoID.getText().equals(null) || textoNombres.getText().equals(null)
                        || textoApellidos.getText().equals(null) || textoFechaNacimiento.getText().equals(null)
                        || textoTelefonoCasa.getText().equals(null) || textoTelefonoCelular.getText().equals(null)
                        || textoCorreo.getText().equals(null) || textoIDAfiRela.getText().equals(null)) {
                    JOptionPane.showMessageDialog(null, "Rellene todos los campos necesarios", "Estado registro", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        fechaUtil = formatterDateSQL.parse(textoFechaNacimiento.getText());
                        fechaSQL = new java.sql.Date(fechaUtil.getTime());
                        Afiliado afiliado = new Afiliado(comboTipoID.getSelectedItem().toString(), Long.valueOf(textoID.getText()), textoNombres.getText() + " " + textoApellidos.getText(),
                                comboSexo.getSelectedItem().toString(), fechaSQL, Long.valueOf(textoTelefonoCasa.getText()), Long.valueOf(textoTelefonoCelular.getText()),
                                textoCorreo.getText(), 1, comboTipoAfiliacion.getSelectedItem().toString(), comboEstado.getSelectedItem().toString(), comboCategoria.getSelectedItem().toString(),
                                comboTipoIDAfiRelacionado.getSelectedItem().toString(), Long.valueOf(textoIDAfiRela.getText()));
                        control.registrarUsuario(afiliado);
                        control.registrarAB(afiliado);
                        JOptionPane.showMessageDialog(this, "Registración exitosa", "Estado registro", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } catch (SQLException ex) {
                        System.out.println("Fallo SQL: " + ex.getMessage());
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(this, "Los datos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panel.add(Titulo);
        panel.add(labelDatosAB);
        panel.add(labelTipoIdentificacion);
        panel.add(labelNumeroID);
        panel.add(labelNombres);
        panel.add(labelApellidos);
        panel.add(labelSexo);
        panel.add(labelFechaNacimiento);
        panel.add(labelTelefonoContacto);
        panel.add(labelTelefonoCasa);
        panel.add(labelTelefonoCelular);
        panel.add(labelCorreo);
        panel.add(labelAfiliacion);
        panel.add(labelTipoAfiliacion);
        panel.add(labelEstado);
        panel.add(labelCategoria);
        panel.add(labelAfiliadoRelacionado);
        panel.add(labelTipoID);
        panel.add(labelNumeroIDAfiliado);
        panel.add(comboTipoID);
        panel.add(comboEstado);
        panel.add(comboCategoria);
        panel.add(comboTipoIDAfiRelacionado);
        panel.add(comboSexo);
        panel.add(textoID);
        panel.add(textoNombres);
        panel.add(textoApellidos);
        panel.add(textoFechaNacimiento);
        panel.add(textoTelefonoCasa);
        panel.add(textoTelefonoCasa);
        panel.add(textoTelefonoCelular);
        panel.add(textoCorreo);
        panel.add(comboTipoAfiliacion);
        panel.add(textoIDAfiRela);
        panel.add(botonRegistrar);
        panel.add(botonBack);
    }
}
