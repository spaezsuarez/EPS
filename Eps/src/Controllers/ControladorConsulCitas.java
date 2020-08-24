/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DatosConsulCita;
import java.util.ArrayList;

/**
 *
 * @author katht
 */
public class ControladorConsulCitas {
    ArrayList<DatosConsulCita> arregloCitas2;
    
    public ControladorConsulCitas(ArrayList<DatosConsulCita> arregloCitas2){
        this.arregloCitas2 = arregloCitas2;
    }
    
    public DatosConsulCita obtenerCita(int numCita){
        return arregloCitas2.get(numCita);
    }
    
    public int getTamañoArreglo(){
        return arregloCitas2.size();
    }
}

