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
        Preconditions.checkArgument(idioma.equalsIgnoreCase("es") || idioma.equalsIgnoreCase("gl"));
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
    public boolean existePalabra(String palabra) throws SQLException {
        try(Connection conn = DriverManager.getConnection(URL);
                
            PreparedStatement ps =  conn.prepareStatement("SELECT count(*) as total FROM palabras WHERE lang = ? and palabra=?")){
            ps.setString(1, idioma);
            ps.setString(2, palabra);
            
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                return rs.getInt("total") > 0;
            }
        }
               
    }
    
    @Override
    public String obtenerPalabraAleatoria() throws SQLException{      
        String alPalabra = "";
        int maxLength;
        
        try(Connection conn = DriverManager.getConnection(URL);
                
            PreparedStatement ps =  conn.prepareStatement("SELECT count(*) as total FROM palabras WHERE lang = ?")){
            ps.setString(1, idioma);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                maxLength = rs.getInt("total");
            }
        
        }
        
        try(Connection conn = DriverManager.getConnection(URL);
               
            PreparedStatement ps =  conn.prepareStatement("SELECT palabra as p FROM palabras where lang = ? limit ?,1")){
            ps.setString(1, idioma);
            ps.setInt(2, randomNumber(maxLength));
            try(ResultSet rs = ps.executeQuery()){
                
                alPalabra = rs.getString("p");
            }
        }
        
        return alPalabra;
    
    }
    
    private int randomNumber(int n){
        Random rndm = new Random();
        int numAl = rndm.nextInt(n);
        return numAl;
    }
    
    
    @Override
    public boolean addPalabra(String palabra) throws SQLException{
        palabra = palabra.toUpperCase();  
        try(Connection conn = DriverManager.getConnection(URL);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO palabras (palabra, lang) VALUES(?, ?)")){
            ps.setString(1, palabra);
            ps.setString(2, idioma);
            int insertadas = ps.executeUpdate();
            return insertadas > 0;
        }            
    }

    @Override
    public boolean removePalabra(String palabra)throws SQLException {
        palabra = palabra.toUpperCase();   
        try(Connection conn = DriverManager.getConnection(URL);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM palabras WHERE palabra=? AND lang=?;")){
            ps.setString(1, palabra);
            ps.setString(2, idioma);
            int insertadas = ps.executeUpdate();
            return insertadas > 0;
        } 
    }

   
    
    
    

    
    
    
}
