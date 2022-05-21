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
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.daw1.Jorge.wordle.IMotores;


/**
 *
 * @author alumno
 */
public class MotorFicheroTexto implements IMotores{
    File ficheroActual = null;
    @Override
    public boolean checkPalabra(String palabra){        
        return palabra.matches("[A-Za-z]{5}") && existeFichero(ficheroActual);
    }
    
    @Override
    public String obtenerPalabraAleatoria(){        
        Set<String> al = cargarFichero(ficheroActual);
        String[] palabras = al.toArray(new String[al.size()]);
        String alPalabra = palabras[randomNumber(palabras.length)];
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
                    sb.add(linea); 
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
        ficheroActual = file;
        if(!file.exists()){
            try {
                file.createNewFile();
                String[] palabrasTest = {"pablo","samue","jorge","juana","holas","casas","ramar"};
                
                guardarTexto(file,Arrays.asList(palabrasTest));
                
            } catch (IOException ex) {
                Logger.getLogger(MotorFicheroTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public boolean guardarTexto(File file,List<String> palabras) throws IOException{
        if((file.exists() && file.canWrite()) || (!file.exists() && file.getParentFile().canWrite())){
            try(Writer wr = new BufferedWriter(new FileWriter(file))){
                
                Set<String> s = new HashSet<>(palabras);                
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

    @Override
    public boolean addPalabra(String palabra) {
        List<String> a = new LinkedList<>();
        a.addAll(cargarFichero(ficheroActual));
        if(!a.contains(palabra)){
            try {
                a.add(palabra);
                guardarTexto(ficheroActual,a);
                return true;
            } catch (IOException ex) {
                Logger.getLogger(MotorFicheroTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    @Override
    public boolean removePalabra(String palabra) {
        List<String> a = new LinkedList<>();
        a.addAll(cargarFichero(ficheroActual));
        if(a.contains(palabra)){
            try {
                a.remove(palabra);
                guardarTexto(ficheroActual,a);
                return true;
            } catch (IOException ex) {
                Logger.getLogger(MotorFicheroTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public Set<String> cargarFichero() {
        return null; 
    }
    
   
    
}
