package Persistence;

import Models.Afiliado;
import Models.EspecialidadMedicoConsultorio;
import Models.Medico;
import Models.Usuario;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDAO extends DAO {

    private static AdministradorDAO administrador;

    private AdministradorDAO() {
        super();
    }

    public static AdministradorDAO getReference() {
        if (administrador == null) {
            administrador = new AdministradorDAO();
        }
        return administrador;
    }

    public int getIDSede(String nombreSede) throws SQLException {

        int idSede = 0;
        String consulta = "SELECT k_sede FROM sede WHERE n_nombreSede = ? ";
        PreparedStatement ps = conexion.prepareStatement(consulta);
        ps.setString(1, nombreSede);
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            idSede = result.getInt(1);
        }

        return idSede;

    }

    public int getIDEspecialidad(String nombreEspecialidad) throws SQLException {

        int idEspecialidad = 0;
        String consulta = "SELECT k_especialidad FROM especialidad WHERE especialidad.n_nombreespecialidad = ? ";
        PreparedStatement ps = conexion.prepareStatement(consulta);
        ps.setString(1, nombreEspecialidad);
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            idEspecialidad = result.getInt(1);
        }

        return idEspecialidad;

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

    public ArrayList<String> getSedes() throws SQLException {

        ArrayList<String> nombresSedes = new ArrayList<String>();
        String consulta = "SELECT n_nombresede FROM sede;";
        PreparedStatement ps = conexion.prepareStatement(consulta);
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            nombresSedes.add(result.getString("n_nombresede"));
        }
        return nombresSedes;
    }

    public ArrayList<String> getConsultorios(String nombreSede) throws SQLException {

        ArrayList<String> iDsConsultorios = new ArrayList<String>();
        String consulta = "SELECT k_numeroconsultorio FROM consultorio,sede "
                + "WHERE consultorio.k_sede=sede.k_sede "
                + "AND sede.n_nombresede=?;";
        PreparedStatement ps = conexion.prepareStatement(consulta);
        ps.setString(1, nombreSede);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            iDsConsultorios.add(result.getString("k_numeroconsultorio"));
        }
        return iDsConsultorios;
    }

    private void addEspecialidadMedicoConsultorio(EspecialidadMedicoConsultorio element) throws SQLException {

        String consulta = "INSERT INTO Especialidad_Medico_Consultorio VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(consulta);
        ps.setInt(1, element.getIdEspecialidad());
        ps.setString(2, element.getTipoDocumento());
        ps.setLong(3, element.getNumeroDocumento());
        ps.setLong(4, element.getNumeroConsultorio());
        ps.setInt(5, element.getIdSede());
        ps.setString(6, element.getHorario());

        ps.execute();

    }

    public void agregarEspecialidadesMedico(ArrayList<EspecialidadMedicoConsultorio> list) throws SQLException {

        for (int i = 0; i < list.size(); i++) {
            addEspecialidadMedicoConsultorio(list.get(i));
        }

    }

    public void registrarUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuario VALUES(?,?,?,?,?,?,?,?,?);";
        PreparedStatement st = conexion.prepareStatement(query);
        st.setString(1, usuario.getTipoDocumento());
        st.setLong(2, usuario.getNumeroDocumento());
        st.setString(3, usuario.getNombreUsuario());
        st.setString(4, usuario.getSexo());
        st.setDate(5, usuario.getFechaNacimiento());
        if(usuario.getTelefonoContacto() == 0){
            st.setNull(6,java.sql.Types.INTEGER);
        }else{
            st.setLong(6, usuario.getTelefonoContacto());
        }
        st.setLong(7, usuario.getTelefonoCelular());
        st.setString(8, usuario.getCorreo());
        st.setInt(9, usuario.getEpsKey());
        st.execute();
    }

    public void registrarMedico(Medico medico) throws SQLException {
        String query = "INSERT INTO medico VALUES(?,?,?);";
        PreparedStatement st = conexion.prepareStatement(query);
        st.setString(1, medico.getTipoDocumento());
        st.setLong(2, medico.getNumeroDocumento());
        st.setString(3, medico.getRegistroMedico());
        st.execute();
    }

    public void registrarAB(Afiliado ABReferencia) throws SQLException {
        String comandoSQL = ("INSERT INTO afiliado_beneficiario  VALUES (?, ?, ?, ?, ?, ?, ?)");
        PreparedStatement ps = conexion.prepareStatement(comandoSQL);
        ps.setString(1, ABReferencia.getTipoDocumento());
        ps.setLong(2, ABReferencia.getNumeroDocumento());
        ps.setString(3, ABReferencia.getTipoAfiliacion());
        ps.setString(4, ABReferencia.getEstado());
        if (ABReferencia.getCategoria().equals("A")) {
            ps.setInt(5, 1);
        } else if (ABReferencia.getCategoria().equals("B")) {
            ps.setInt(5, 2);
        } else if (ABReferencia.getCategoria().equals("C")) {
            ps.setInt(5, 3);
        }
        ps.setString(6, ABReferencia.getTipoDocumentoAfiliado());
        if (ABReferencia.getNumeroDocumentoAfiliado() == 0) {
            ps.setNull(7, java.sql.Types.INTEGER);
        } else {
            ps.setLong(7, ABReferencia.getNumeroDocumentoAfiliado());
        }
        ps.execute();
    }

}
