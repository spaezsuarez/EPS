package Models;


public class DatosConsulCita {
    
    private String  especialidad, tipoCita, medicoTratante, hora, sede, direccion;
    private int consultorio;

    public String getEspecialidad() {
        return especialidad;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public String getMedicoTratante() {
        return medicoTratante;
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
    
    public DatosConsulCita(String especialidad, String tipoCita, String medicoTratante, String hora, String sede, String direccion, int consultorio){
        this.especialidad = especialidad;
        this.tipoCita = tipoCita;
        this.medicoTratante = medicoTratante;
        this.hora = hora;
        this.sede = sede;
        this.direccion = direccion;
        this.consultorio = consultorio;
    }
}

