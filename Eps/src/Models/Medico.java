package Models;

import java.sql.Date;


public class Medico extends Usuario {
    
    private String registroMedico;

    public Medico(String registroMedico, String tipoDocumento, long numeroDocumento, String nombreUsuario, String sexo, Date fechaNacimiento, long telefonoContacto, long telefonoCelular, String correo, int epsKey) {
        super(tipoDocumento, numeroDocumento, nombreUsuario, sexo, fechaNacimiento, telefonoContacto, telefonoCelular, correo, epsKey);
        this.registroMedico = registroMedico;
    }
    
    public String getRegistroMedico(){
        return this.registroMedico;
    }
}
