/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw1.Jorge.wordle.motores;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.daw1.Jorge.wordle.IMotores;
import org.daw1.Jorge.wordle.io.GestionAlmacenamiento;

/**
 *
 * @author alumno
 */
public class MotorFicheroTexto implements IMotores{
    @Override
    public boolean checkPalabra(String palabra){
        return palabra.matches("[A-Za-z]{5}");
    }
    @Override
    public String obtenerPalabraAleatoria(File file){
        try {
            org.daw1.Jorge.wordle.io.GestionAlmacenamiento gm = new org.daw1.Jorge.wordle.io.GestionAlmacenamiento();
            Set<String> al = gm.cargarFichero(file);
            
            return "";
        } catch (IOException ex) {
            Logger.getLogger(MotorFicheroTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
   
    
}
