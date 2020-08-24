package Models;

import java.sql.Date;

public class Usuario {

    private String tipoDocumento;
    private long numeroDocumento;
    private String nombreUsuario;
    private String sexo;
    private Date fechaNacimiento;
    private long telefonoContacto;
    private long telefonoCelular;
    private String correo;
    private int epsKey;

    public Usuario(String tipoDocumento, long numeroDocumento, String nombreUsuario, String sexo, Date fechaNacimiento, long telefonoContacto, long telefonoCelular, String correo, int epsKey) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombreUsuario = nombreUsuario;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefonoContacto = telefonoContacto;
        this.telefonoCelular = telefonoCelular;
        this.correo = correo;
        this.epsKey = epsKey;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getSexo() {
        return sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public long getTelefonoContacto() {
        return telefonoContacto;
    }

    public long getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(long telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEpsKey() {
        return epsKey;
    }

}
