/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw1.Jorge.wordle;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.sql.SQLException;
/**
 *
 * @author alumno
 */
public interface IMotores {
    public boolean checkPalabra(String string);

    public String obtenerPalabraAleatoria() throws SQLException, IOException;
    
    public void createFile(File file)throws IOException;
    
    public Set<String> cargarFichero(File file)throws IOException;
        
    public boolean  addPalabra(String palabra) throws SQLException, IOException; 
    
    public boolean removePalabra(String palabra) throws SQLException, IOException;
    
    public boolean existePalabra(String palabra) throws SQLException;
    
    
}
