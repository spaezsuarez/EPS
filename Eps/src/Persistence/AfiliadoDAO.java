package Persistence;

import Models.DatosConsulCita;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import Controllers.ControladorConsulCitas;
import Models.DatosSolicCita;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.util.Random;

import Models.DatosSolicCita;

public class AfiliadoDAO extends DAO {

    private static AfiliadoDAO afiliado;

    private AfiliadoDAO() {
        super();
    }

    public static AfiliadoDAO getReference() {
        if (afiliado == null) {
            afiliado = new AfiliadoDAO();
        }
        return afiliado;
    }

    public ArrayList consultarCitas(long iDUsuario, Date fecha) throws SQLException {
        String comandoSQL = "SELECT k_cita,n_nombretipocita FROM cita,afiliado_beneficiario,tipo_cita "
                + "WHERE cita.k_numerodocumentoab=afiliado_beneficiario.k_numerodocumento "
                + "AND cita.k_tipocita=tipo_cita.k_tipocita "
                + "AND cita.f_cita=? "
                + "AND afiliado_beneficiario.k_numerodocumento=?;";
        PreparedStatement ps = conexion.prepareStatement(comandoSQL);
        ps.setDate(1, fecha);
        ps.setLong(2, iDUsuario);
        ResultSet rs = ps.executeQuery();
        ArrayList<ArrayList> arregloTocho = new ArrayList<ArrayList>();
        ArrayList<Integer> arregloIds = new ArrayList<Integer>();
        ArrayList<String> arregloTipos = new ArrayList<String>();
        while (rs.next()) {
            arregloIds.add(rs.getInt("k_cita"));
            arregloTipos.add(rs.getString("n_nombretipocita"));
        }
        arregloTocho.add(arregloIds);
        arregloTocho.add(arregloTipos);
        return arregloTocho;
    }

    public ArrayList consultarCitas2(long iDUsuario, Date fecha) throws SQLException {

        String comandoSQL = "SELECT especialidad.n_nombreespecialidad, tipo_cita.n_nombretipocita, usuario.n_nombre, "
                + "		h_inicial, sede.n_nombresede, sede.n_direccion, "
                + "		especialidad_medico_consultorio.k_numeroconsultorio "
                + "FROM cita, afiliado_beneficiario, tipo_cita, especialidad, medico, "
                + "		especialidad_medico_consultorio, usuario, sede "
                + "WHERE cita.k_numerodocumentoab = afiliado_beneficiario.k_numerodocumento "
                + "AND cita.k_tipocita=tipo_cita.k_tipocita "
                + "AND especialidad_medico_consultorio.k_especialidad = especialidad.k_especialidad "
                + "AND especialidad_medico_consultorio.k_sede = sede.k_sede "
                + "AND especialidad_medico_consultorio.k_numerodocumento = medico.k_numerodocumento "
                + "AND usuario.k_numerodocumento = medico.k_numerodocumento "
                + "AND cita.k_especialidad = especialidad.k_especialidad "
                + "AND cita.k_numerodocumentomedico = medico.k_numerodocumento "
                + "AND cita.f_cita=? "
                + "AND afiliado_beneficiario.k_numerodocumento=?; ";

        PreparedStatement ps = conexion.prepareStatement(comandoSQL);
        ps.setDate(1, fecha);
        ps.setLong(2, iDUsuario);
        ResultSet rs = ps.executeQuery();

        ArrayList<DatosConsulCita> arregloCitas2 = new ArrayList<DatosConsulCita>();

        while (rs.next()) {
            DatosConsulCita citaTemp = new DatosConsulCita(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            arregloCitas2.add(citaTemp);
        }

        return arregloCitas2;

    }

    public ArrayList verificarCondiciones(long idUsuario, String especialidad) throws SQLException {
        String comandoSQL = "SELECT n_nombreespecialidad "
                + "FROM cita, especialidad "
                + "WHERE cita.k_numerodocumentoab = ? "
                + "AND cita.k_especialidad = especialidad.k_especialidad "
                + "AND especialidad.n_nombreespecialidad = ?; ";
        PreparedStatement ps = conexion.prepareStatement(comandoSQL);
        ps.setLong(1, idUsuario);
        ps.setString(2, especialidad);
        ResultSet rs = ps.executeQuery();

        ArrayList<String> arregloVerif = new ArrayList<String>();
        String verif;

        while (rs.next()) {
            verif = rs.getString(1);
            arregloVerif.add(verif);
        }

        return arregloVerif;
    }

    public ArrayList verificarCondiciones2(long idUsuario) throws SQLException {
        String comandoSQL = "SELECT pago.n_estado "
                + "FROM cita, pago "
                + "WHERE cita.k_numerodocumentoab = ? "
                + "AND cita.k_cita = pago.k_cita "
                + "AND pago.n_estado = 'Pendiente de pago'; ";
        PreparedStatement ps = conexion.prepareStatement(comandoSQL);
        ps.setLong(1, idUsuario);
        ResultSet rs = ps.executeQuery();

        ArrayList<String> arregloVerif = new ArrayList<String>();
        String verif;

        while (rs.next()) {
            verif = rs.getString(1);
            arregloVerif.add(verif);
        }

        return arregloVerif;
    }

    public ArrayList consultarCitasDisponibles(String especialidad, String tipoCita) throws SQLException {
        String comandoSQL = "SELECT usuario.n_nombre, f_cita, h_inicial, sede.n_nombresede, sede.n_direccion, "
                + "especialidad_medico_consultorio.k_numeroconsultorio, k_cita "
                + "FROM cita, tipo_cita, especialidad, medico, "
                + "especialidad_medico_consultorio, usuario, sede "
                + "WHERE cita.k_tipocita = tipo_cita.k_tipocita "
                + "AND especialidad_medico_consultorio.k_especialidad = especialidad.k_especialidad "
                + "AND especialidad_medico_consultorio.k_sede = sede.k_sede "
                + "AND especialidad_medico_consultorio.k_numerodocumento = medico.k_numerodocumento "
                + "AND usuario.k_numerodocumento = medico.k_numerodocumento "
                + "AND cita.k_especialidad = especialidad.k_especialidad "
                + "AND cita.k_numerodocumentomedico = medico.k_numerodocumento "
                + "AND cita.n_estado = 'Disponible' "
                + "AND especialidad.n_nombreespecialidad =? "
                + "AND tipo_cita.n_nombretipocita =? ";
        PreparedStatement ps = conexion.prepareStatement(comandoSQL);
        ps.setString(1, especialidad);
        ps.setString(2, tipoCita);
        ResultSet rs = ps.executeQuery();

        ArrayList<DatosSolicCita> arregloCitas = new ArrayList<DatosSolicCita>();

        while (rs.next()) {
            DatosSolicCita citaTemp = new DatosSolicCita(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
            arregloCitas.add(citaTemp);
        }

        return arregloCitas;
    }

    public void AñadirABCita(long idCita, String TidUsuario, long iDUsuario) throws SQLException {
        String consulta = "UPDATE cita SET n_estado = 'No Disponible', k_tipodocumentoab =?, "
                + "k_numerodocumentoab =? "
                + "WHERE k_cita =? ;";
        PreparedStatement st = conexion.prepareStatement(consulta);
        st.setString(1, TidUsuario);
        st.setLong(2, iDUsuario);
        st.setLong(3, idCita);
        st.executeUpdate();
    }

    public long crearPagoID() throws SQLException {
        Random r = new Random();
        long valorID = r.nextInt(9999);
        String query = "SELECT * FROM pago;";
        PreparedStatement ps = conexion.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getInt("k_pago") == valorID) {
                valorID = r.nextInt(999999);
            }
        }
        return valorID;
    }

    public void CrearPagoCita(long idPago, Date fecha, long idCita) throws SQLException {
        String consulta = "INSERT INTO pago (k_pago, n_estado, f_pago, f_limitepago, k_cita) "
                + "VALUES "
                + "(?, 'Pendiente de pago', NULL, ?, ?);";
        PreparedStatement st = conexion.prepareStatement(consulta);
        st.setLong(1, idPago);
        st.setDate(2, fecha);
        st.setLong(3, idCita);

        st.executeUpdate();
    }

    public long CuotaPagar(long idUsuario) throws SQLException {
        String comandoSQL = "SELECT q_precio FROM afiliado_beneficiario, categoria "
                + "WHERE afiliado_beneficiario.k_numerodocumento =? "
                + "AND afiliado_beneficiario.k_categoria = categoria.k_categoria; ";
        PreparedStatement ps = conexion.prepareStatement(comandoSQL);
        ps.setLong(1, idUsuario);
        ResultSet rs = ps.executeQuery();

        long cuota = 0;

        while (rs.next()) {
            cuota = rs.getInt(1);
        }

        return cuota;
    }

    public ArrayList<String> getEspecialidades() throws SQLException {

        ArrayList<String> nombresEspecialidades = new ArrayList<String>();
        String consulta = "SELECT n_nombreespecialidad FROM especialidad;";
        PreparedStatement ps = conexion.prepareStatement(consulta);
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            nombresEspecialidades.add(result.getString("n_nombreespecialidad"));
        }
        return nombresEspecialidades;
    }

    public ArrayList<String> getTiposCitas() throws SQLException {

        ArrayList<String> nombreTipos = new ArrayList<String>();
        String consulta = "SELECT n_nombreTipoCita FROM tipo_cita;";
        PreparedStatement ps = conexion.prepareStatement(consulta);
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            nombreTipos.add(result.getString("n_nombreTipoCita"));
        }
        return nombreTipos;
    }

    public Time consultarHora(long iDUsuario, Date fecha, int iDCita) throws SQLException {
        Time retorno = null;
        String comandoSQL = "SELECT h_inicial FROM cita,afiliado_beneficiario "
                + "WHERE cita.k_numerodocumentoab=afiliado_beneficiario.k_numerodocumento "
                + "AND f_cita = ? "
                + "AND k_numerodocumento = ? "
                + "AND k_cita = ?";
        PreparedStatement ps = conexion.prepareStatement(comandoSQL);
        ps.setDate(1, fecha);
        ps.setLong(2, iDUsuario);
        ps.setInt(3, iDCita);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            retorno = rs.getTime("h_inicial");
        }
        return retorno;
    }

    public void actualizarCita(long iDUsuario, long iDCita) throws SQLException {
        String consulta = "UPDATE cita SET n_estado = 'Disponible', k_tipodocumentoab = null, k_numerodocumentoab = null WHERE k_cita = ? AND k_numerodocumentoab = ?;";
        PreparedStatement st = conexion.prepareStatement(consulta);
        st.setLong(1, iDCita);
        st.setLong(2, iDUsuario);
        st.executeUpdate();
    }

    public void eliminarPago(long iDCita) throws SQLException {
        String consulta = "DELETE FROM pago WHERE k_cita= ?;";
        PreparedStatement st = conexion.prepareStatement(consulta);
        st.setLong(1, iDCita);
        st.executeUpdate();
    }

    public void eliminarRegistro(long iDCita) throws SQLException {
        String consulta = "DELETE FROM registro WHERE k_cita= ?;";
        PreparedStatement st = conexion.prepareStatement(consulta);
        st.setLong(1, iDCita);
        st.executeUpdate();
    }

    public int getHorasCancelacion() throws SQLException {
        String consulta = "SELECT q_nrohorascancelacioncita FROM eps WHERE n_nombreeps = 'CoSalud';";
        PreparedStatement st = conexion.prepareStatement(consulta);
        ResultSet result = st.executeQuery();
        int retorno = 0;
        while (result.next()) {
            retorno = result.getInt("q_nrohorascancelacioncita");
        }
        return retorno;
    }

    public int getMulta(long iD) throws SQLException {
        String consulta = "SELECT q_multa "
                + "FROM afiliado_beneficiario,categoria "
                + "WHERE afiliado_beneficiario.k_categoria=categoria.k_categoria "
                + "AND afiliado_beneficiario.k_numerodocumento=?;";
        PreparedStatement st = conexion.prepareStatement(consulta);
        st.setLong(1, iD);
        ResultSet result = st.executeQuery();
        int retorno = 0;
        while (result.next()) {
            retorno = result.getInt("q_multa");
        }
        return retorno;
    }

}
