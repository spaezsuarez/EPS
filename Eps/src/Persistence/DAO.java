package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import ignore.Keys;
import java.sql.PreparedStatement;

public class DAO {

    protected Connection conexion;
    private static DAO controlador;

    protected DAO() {
        try {
            establecerConexion();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DAO getReference() {
        if (controlador == null) {
            controlador = new DAO();
        }
        return controlador;
    }

    public void establecerConexion() throws SQLException {

        conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eps", Keys.user, Keys.password);
        System.out.println("Conexion a la base de datos exitosa " + conexion.getMetaData().getURL());

    }

    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Fallo: " + ex.getMessage());
        }
    }

    public boolean getUser(String tabla, String tipo, long id) throws SQLException {
        String consulta = "SELECT * FROM " + tabla + " WHERE k_tipodocumento = ? AND k_numerodocumento = ?;";
        PreparedStatement st = conexion.prepareStatement(consulta);
        st.setString(1, tipo);
        st.setLong(2, id);
        ResultSet user = st.executeQuery();
        while (user.next()) {

            if (tabla.equals("afiliado_beneficiario")) {
                if (user.getLong(2) == id && user.getString(1).equals(tipo) && user.getString(4).equals("Activo")) {
                    return true;
                }
            } else {
                if (user.getLong(2) == id && user.getString(1).equals(tipo)) {
                    return true;
                }
            }
        }
        return false;
    }

}
