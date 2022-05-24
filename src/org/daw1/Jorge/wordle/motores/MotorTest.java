/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw1.Jorge.wordle.motores;

import java.io.File;
import java.sql.SQLException;
import java.util.Set;
import org.daw1.Jorge.wordle.IMotores;

/**
 *
 * @author alumno
 */
public class MotorTest implements IMotores{

    @Override
    public boolean existePalabra(String palabra) throws SQLException {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    

    public MotorTest() {
    }

    @Override
    public boolean removePalabra(String palabra) {
        return true; //To change body of generated methods, choose Tools | Templates.
    }
    
 
    @Override
    public boolean  checkPalabra(String palabra){
        return palabra.matches("[A-Z-a-z]{5}");
    }
    @Override
    public String obtenerPalabraAleatoria(){
        return "CICLO";
    }

    @Override
    public void createFile(File file) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> cargarFichero(File file) {
        return null; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addPalabra(String palabra) {
        return true; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
