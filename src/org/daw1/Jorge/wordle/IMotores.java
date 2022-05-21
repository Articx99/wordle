/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw1.Jorge.wordle;

import java.io.File;
import java.util.Set;

/**
 *
 * @author alumno
 */
public interface IMotores {
    public boolean checkPalabra(String string);

    public String obtenerPalabraAleatoria();
    
    public void createFile(File file);
    
    public Set<String> cargarFichero(File file);
    
    public Set<String> cargarFichero();
    
    public boolean  addPalabra(String palabra); 
    
    public boolean removePalabra(String palabra);
    
    
}
