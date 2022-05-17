/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw1.Jorge.wordle.motores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.daw1.Jorge.wordle.IMotores;
import org.daw1.Jorge.wordle.io.GestionAlmacenamiento;

/**
 *
 * @author alumno
 */
public class MotorFicheroTexto implements IMotores{
    File fileActual = null;
    @Override
    public boolean checkPalabra(String palabra){
        
        return palabra.matches("[A-Za-z]{5}") && existeFichero(fileActual);
    }
    @Override
    public String obtenerPalabraAleatoria(){
        String alPalabra = "";
        Set<String> al = cargarFichero(fileActual);
        String[] palabras = al.toArray(new String[al.size()]);
        alPalabra = palabras[randomNumber(palabras.length)];
        return alPalabra;
    }
    
    private int randomNumber(int n){
        Random rndm = new Random();
        int numAl = rndm.nextInt(n);
        return numAl;
    }
    
    @Override
    public Set<String> cargarFichero(File file){
        Set<String> sb = new HashSet<>();
        if(file != null && file.exists() && file.canRead()){
            
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                String linea = br.readLine();
                while(linea != null){
                    sb.add(linea+"\n"); 
                    linea = br.readLine();
                }
                return sb;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MotorFicheroTexto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MotorFicheroTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return sb;
    }
    public boolean existeFichero(File file){
        return file.exists();
    }
    @Override
    public void createFile(File file){
        fileActual = file;
        if(!file.exists()){
            try {
                file.createNewFile();
                
                guardarTexto(file);
                
            } catch (IOException ex) {
                Logger.getLogger(MotorFicheroTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public boolean guardarTexto(File file) throws IOException{
        if((file.exists() && file.canWrite()) || (!file.exists() && file.getParentFile().canWrite())){
            try(Writer wr = new BufferedWriter(new FileWriter(file))){
                String[] palabrasTest = {"pablo","samue","jorge","juana"};
                Set<String> s = new HashSet<>(Arrays.asList(palabrasTest));                
                Iterator it = s.iterator();         
                while(it.hasNext()){               
                    wr.write(it.next().toString()+"\n");
                }
                return true;
           
            }
        }
        else{
            return false;
        }
            
        
    }
    
   
    
}
