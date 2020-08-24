package Persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;


public class Agenda2DAO extends DAO{

    private static Agenda2DAO agenda;
    
    private Agenda2DAO() {
        super();
    }

    public static Agenda2DAO getReference() {
        if (agenda == null) {
            agenda = new Agenda2DAO();
        }
        return agenda;
    }

    public long crearCitaID() throws SQLException{
        Random r = new Random();
        long valorID = r.nextInt(9999);
        String query = "SELECT * FROM cita;";
        PreparedStatement ps = conexion.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getInt("k_cita") == valorID) {
                valorID = r.nextInt(9999);
            }
        }
        return valorID;
    }

    public java.sql.Time setHoraFinal(java.sql.Time horainicial, String tipocita) throws SQLException{

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horainicial);

        switch(tipocita){
            case "Prioritaria":
                calendar.add(Calendar.MINUTE, 50);
                break;
            case "Primera vez":
                calendar.add(Calendar.MINUTE, 30);
                break;
            case "Control":
                calendar.add(Calendar.MINUTE, 40);
                break;
            case "Lectura de exámenes":
                calendar.add(Calendar.MINUTE, 20);
                break;
        }

        java.util.Date fechaUTIL = calendar.getTime();
        java.sql.Time fechaSQL = new java.sql.Time(fechaUTIL.getTime());

        return fechaSQL;

    }

    public java.sql.Date setFecha(int dia, int mes, int año) throws SQLException{

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, año);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.DAY_OF_MONTH, dia);

        java.util.Date fechaUTIL = calendar.getTime();
        java.sql.Date fechaSQL = new java.sql.Date(fechaUTIL.getTime());
        
        return fechaSQL;

    }

    public int setTipoCita(String tipocita){

        int idCita;
        idCita = 0;

        try{
            
            String query = "SELECT * FROM tipo_cita WHERE n_nombretipocita = ?;";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, tipocita);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                idCita = rs.getInt("k_tipocita");
            }
        }catch(SQLException z){

        }

        return idCita;
    }

    public int getIDEspecialidad(String nombreEspecialidad) throws SQLException{
        
        int idEspecialidad = 0;
        String consulta = "SELECT k_especialidad FROM especialidad WHERE especialidad.n_nombreespecialidad = ? ";
        PreparedStatement ps = conexion.prepareStatement(consulta);
        ps.setString(1, nombreEspecialidad);
        ResultSet result = ps.executeQuery();
        
        while(result.next()){
            idEspecialidad = result.getInt(1);
        }
        
        return idEspecialidad;
        
    }

    public void registrarCita(long idCita, Time h_inicial, Time h_final, Date fechaCita, String estadoCita, long idAgenda, String tipodocumentoMedico, long idMedico, long idTipoCita, String tipodocumentoAB, long idAB, int idEspecialidad) throws SQLException {

        String query = "INSERT INTO cita VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setLong(1, idCita);
        ps.setTime(2, h_inicial);
        ps.setTime(3, h_final);
        ps.setDate(4, fechaCita);
        ps.setString(5, estadoCita);
        ps.setLong(6, idAgenda);
        ps.setString(7, tipodocumentoMedico);
        ps.setLong(8, idMedico);
        ps.setLong(9, idTipoCita);
        ps.setString(10, tipodocumentoAB);
        ps.setNull(11, java.sql.Types.INTEGER);
        ps.setLong(12, idEspecialidad);
        ps.execute();

    }

}