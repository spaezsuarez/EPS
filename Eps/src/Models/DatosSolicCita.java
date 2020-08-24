package Models;


public class DatosSolicCita {
    
    private String medicoTratante, fecha, hora, sede, direccion;
    private int consultorio, idCita;

    public String getMedicoTratante() {
        return medicoTratante;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getSede() {
        return sede;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getConsultorio() {
        return consultorio;
    }

    public int getIdCita() {
        return idCita;
    }
    
    public DatosSolicCita(String medicoTratante, String fecha, String hora, String sede, String direccion, int consultorio, int idCita){
        this.medicoTratante = medicoTratante;
        this.fecha = fecha;
        this.hora = hora;
        this.sede = sede;
        this.direccion = direccion;
        this.consultorio = consultorio;
        this.idCita = idCita;
    }
}
