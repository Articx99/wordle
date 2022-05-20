/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw1.Jorge.wordle.motores;

import java.io.File;
import java.util.Set;
import org.daw1.Jorge.wordle.IMotores;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.common.base.Preconditions;
import java.util.TreeSet;
/**
 *
 * @author alumno
 */
public class MotorBasesDatos implements IMotores{
    private final static String URL = "jdbc:sqlite:data/dbwordle.db";
    private String idioma = "";

    public MotorBasesDatos(String idioma){
        Preconditions.checkArgument(idioma.equalsIgnoreCase("es") || idioma.equalsIgnoreCase("gal"));
        this.idioma = idioma;
    }
    
    @Override
    public Set<String> cargarFichero(File file) {
        return null; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkPalabra(String palabra) {
        return palabra.matches("[A-Za-z]{5}"); 
    }

    @Override
    public void createFile(File file) {
        
    }

    @Override
    public Set<String> cargarFichero() {
        Set<String> s = new TreeSet<>();
        if(idioma.equalsIgnoreCase("es")){
            try(Connection conn = DriverManager.getConnection(URL);
                Statement consulta = conn.createStatement();

                ResultSet rs = consulta.executeQuery("SELECT palabra FROM palabras WHERE lang = 'es'")){
                    while(rs.next()){
                        s.add(rs.getString("palabra"));
                    }    
                } catch (SQLException ex) { 
                Logger.getLogger(MotorBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }
        else if(idioma.equalsIgnoreCase("gl")){
            try(Connection conn = DriverManager.getConnection(URL);
                Statement consulta = conn.createStatement();

                ResultSet rs = consulta.executeQuery("SELECT palabra FROM palabras WHERE lang = 'gl'")){
                    while(rs.next()){
                        s.add(rs.getString("palabra"));
                    }    
                } catch (SQLException ex) { 
                Logger.getLogger(MotorBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }
        
        return s;
    }

    @Override
    public String obtenerPalabraAleatoria() {
             
        Set<String> al = cargarFichero();
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
    public boolean addPalabra(String palabra) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removePalabra(String palabra) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
    

    
    
    
}
