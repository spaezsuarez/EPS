package Models;

import java.sql.Date;
import java.sql.Time;


public class DatosDiagnostico {
    
    private String nombrePaciente;
    private long idMedico;
    private Date fechaCita;
    private Time horaFinal;

    public DatosDiagnostico(String nombrePaciente, long idMedico, Date fechaCita, Time horaFinal) {
        this.nombrePaciente = nombrePaciente;
        this.idMedico = idMedico;
        this.fechaCita = fechaCita;
        this.horaFinal = horaFinal;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public long getIdMedico() {
        return idMedico;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }
    
    
    
}
