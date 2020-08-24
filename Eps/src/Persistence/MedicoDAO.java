package Persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Models.DatosDiagnostico;
import java.sql.ResultSet;

public class MedicoDAO extends DAO {

    private static MedicoDAO medicoController;

    private MedicoDAO() {
        super();
    }

    public void agregarCita() {
    }

    public static MedicoDAO getReference() {
        if (medicoController == null) {
            medicoController = new MedicoDAO();
        }
        return medicoController;
    }

    public void registrarDiagnostico(long idRegistro, String diagnostico, String preinscripcion, long idCita) throws SQLException {
        String query = "INSERT INTO registro VALUES (?,?,?,?);";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setLong(1, idCita);
        ps.setString(2, diagnostico);
        ps.setString(3, preinscripcion);
        ps.setLong(4, idCita);
        ps.execute();
    }

    public DatosDiagnostico getDatosDiagnostico(long idCita) {
        DatosDiagnostico datos = null;
        try {
            String query = "SELECT usuario.n_nombre,medico.k_numerodocumento,cita.f_cita,cita.h_final\n"
                    + "	FROM usuario,medico,afiliado_beneficiario,cita\n"
                    + "	WHERE usuario.k_numeroDocumento = afiliado_beneficiario.k_numeroDocumento \n"
                    + "	AND usuario.k_tipoDocumento = afiliado_beneficiario.k_tipoDocumento\n"
                    + "	AND afiliado_beneficiario.k_numeroDocumento = cita.k_numeroDocumentoAB\n"
                    + "	AND afiliado_beneficiario.k_tipoDocumento = cita.k_tipoDocumentoAB\n"
                    + "	AND medico.k_numerodocumento = cita.k_numeroDocumentoMedico\n"
                    + "	AND medico.k_tipoDocumento = cita.k_tipoDocumentoMedico\n"
                    + "	AND cita.k_cita = ?"
                    + ";";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, idCita);
            ResultSet iterator = ps.executeQuery();
            while (iterator.next()) {
                datos = new DatosDiagnostico(iterator.getString(1), iterator.getLong(2), iterator.getDate(3), iterator.getTime(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return datos;
    }

}
