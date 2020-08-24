package Models;


public class EspecialidadMedicoConsultorio {
    
    private int idEspecialidad;
    private String tipoDocumento;
    private long numeroDocumento;
    private long numeroConsultorio;
    private int idSede;
    private String horario;

    public EspecialidadMedicoConsultorio(int idEspecialidad, String tipoDocumento, long numeroDocumento, long numeroConsultorio, int idSede, String horario) {
        this.idEspecialidad = idEspecialidad;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.numeroConsultorio = numeroConsultorio;
        this.idSede = idSede;
        this.horario = horario;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    public long getNumeroConsultorio() {
        return numeroConsultorio;
    }

    public int getIdSede() {
        return idSede;
    }

    public String getHorario() {
        return horario;
    }
    
    @Override
    public String toString(){
        return "1.)" + idEspecialidad+"\n"+
                "2.)" + numeroConsultorio +"\n"+
                "3.)" + idSede+"\n"+
               "4.)" + horario +"\n" ;
    }
    
    
}
