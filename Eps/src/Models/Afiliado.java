package Models;

import java.sql.Date;

public class Afiliado extends Usuario {

    private String tipoAfiliacion;
    private String estado;
    private String categoria;
    private String tipoDocumentoAfiliado;
    private long numeroDocumentoAfiliado;

    public Afiliado(String tipoDocumento, long numeroDocumento, String nombreUsuario, String sexo, Date fechaNacimiento, long telefonoContacto, long telefonoCelular, String correo, int epsKey, String tipoAfiliacion, String estado, String categoria, String tipoDocumentoAfiliado, long numeroDocumentoAfiliado) {
        super(tipoDocumento, numeroDocumento, nombreUsuario, sexo, fechaNacimiento, telefonoContacto, telefonoCelular, correo, epsKey);
        this.tipoAfiliacion=tipoAfiliacion;
        this.estado=estado;
        this.categoria=categoria;
        this.tipoDocumentoAfiliado=tipoDocumentoAfiliado;
        this.numeroDocumentoAfiliado=numeroDocumentoAfiliado;
    }

    public Afiliado(String tipoDocumento, long numeroDocumento, String nombreUsuario, String sexo, Date fechaNacimiento, long telefonoContacto, long telefonoCelular, String correo, int epsKey, String tipoAfiliacion, String estado, String categoria) {
        super(tipoDocumento, numeroDocumento, nombreUsuario, sexo, fechaNacimiento, telefonoContacto, telefonoCelular, correo, epsKey);
        this.tipoAfiliacion=tipoAfiliacion;
        this.estado=estado;
        this.categoria=categoria;
        this.tipoDocumentoAfiliado=null;
        this.numeroDocumentoAfiliado=0;
    }
    
    public String getTipoAfiliacion() {
        return tipoAfiliacion;
    }

    public void setTipoAfiliacion(String tipoAfiliacion) {
        this.tipoAfiliacion = tipoAfiliacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipoDocumentoAfiliado() {
        return tipoDocumentoAfiliado;
    }

    public void setTipoDocumentoAfiliado(String tipoDocumentoAfiliado) {
        this.tipoDocumentoAfiliado = tipoDocumentoAfiliado;
    }

    public long getNumeroDocumentoAfiliado() {
        return numeroDocumentoAfiliado;
    }

    public void setNumeroDocumentoAfiliado(long numeroDocumentoAfiliado) {
        this.numeroDocumentoAfiliado = numeroDocumentoAfiliado;
    }

}
