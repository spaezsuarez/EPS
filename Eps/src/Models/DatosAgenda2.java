package Models;

import java.sql.Time;

public class DatosAgenda2 {
    
    private String especialidad; 
    private String tipocita;
    private int dia; 
    private String hora;

    public String getEspecialidad() {
        return especialidad;
    }

    public String getTipocita() {
        return tipocita;
    }

    public int getDia() {
        return dia;
    }

    public String getHora() {
        return hora;
    }

    public DatosAgenda2(String especialidad, String tipocita, int dia, String hora){
        this.especialidad = especialidad;
        this.tipocita = tipocita;
        this.dia = dia;
        this.hora = hora;
    }

}