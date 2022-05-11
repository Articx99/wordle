/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw1.Jorge.wordle.motores;

import org.daw1.Jorge.wordle.IMotores;

/**
 *
 * @author alumno
 */
public class MotorFicheroTexto implements IMotores{
    @Override
    public boolean checkPalabra(String palabra){
        return palabra.matches("[A-Z-a-z]{5}");
    }
    @Override
    public String obtenerPalabraAleatoria(){
        return "";
    }
    
}
