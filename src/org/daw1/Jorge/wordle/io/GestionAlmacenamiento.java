/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw1.Jorge.wordle.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class GestionAlmacenamiento {
    
    
    public void createFile(File file){
        try {
            file.createNewFile();
            
        } catch (IOException ex) {
            Logger.getLogger(GestionAlmacenamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public Set<String> cargarFichero(File fichero) throws IOException{
        if(fichero.exists()){
            Set<String> sb = new HashSet<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fichero))){
                String linea = br.readLine();
                while(linea != null){
                    sb.add(linea+"\n"); //(linea).append("\n");
                    linea = br.readLine();
                }
                return sb;
            }
        }
        else{
            return null;
        }
    }
    
}
