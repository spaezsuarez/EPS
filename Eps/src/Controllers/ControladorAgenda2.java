package Controllers;

import java.util.ArrayList;
import Models.DatosAgenda2;

public class ControladorAgenda2 {
    
    ArrayList<DatosAgenda2> arregloAgenda2 = new ArrayList<DatosAgenda2>();

    public ControladorAgenda2(){
        DatosAgenda2 agendaTemp = new DatosAgenda2("Odontologia" , "Control", 14, "2:30");
        DatosAgenda2 agendaTemp1 = new DatosAgenda2("Pediatria", "Lectura", 11, "1:15");
        arregloAgenda2.add(agendaTemp);
        arregloAgenda2.add(agendaTemp1);
    }

}