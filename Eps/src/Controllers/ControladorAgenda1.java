package Controllers;

import java.util.ArrayList;
import Models.DatosAgenda1;

public class ControladorAgenda1 {
    
    ArrayList<DatosAgenda1> arregloAgenda1 = new ArrayList<DatosAgenda1>();

    public ControladorAgenda1(){
        DatosAgenda1 agendaTemp = new DatosAgenda1(06, 2020);
        DatosAgenda1 agendaTemp1 = new DatosAgenda1(07, 2019);
        arregloAgenda1.add(agendaTemp);
        arregloAgenda1.add(agendaTemp1);
    }

}