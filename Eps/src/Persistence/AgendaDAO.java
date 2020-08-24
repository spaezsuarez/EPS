package Persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


public class AgendaDAO extends DAO {

    private static AgendaDAO agenda;

    private AgendaDAO() {
        super();
    }

    public static AgendaDAO getReference() {
        if (agenda == null) {
            agenda = new AgendaDAO();
        }
        return agenda;
    }

    public void registrarAgenda(long NumeroDocumento, String tipodocumento, long idAgenda, int mes, int dia) throws SQLException {

        String query = "INSERT INTO agenda VALUES (?,?,?,?,?);";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, tipodocumento);
        ps.setLong(2, NumeroDocumento);
        ps.setLong(3, idAgenda);
        ps.setInt(4, mes);
        ps.setInt(5, dia);
        ps.execute();

    }

    public long crearAgendaID() throws SQLException{
        Random r = new Random();
        long valorID = r.nextInt(9999);
        String query = "SELECT * FROM agenda;";
        PreparedStatement ps = conexion.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getInt("k_agenda") == valorID) {
                valorID = r.nextInt(9999);
            }
        }
        return valorID;
    }
}