/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.daw1.Jorge.wordle.gui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author joric
 */
public final class MainGui extends javax.swing.JFrame {
    private static final org.daw1.Jorge.wordle.motores.MotorTest gm = new org.daw1.Jorge.wordle.motores.MotorTest(); 
    private static final java.awt.Color COLOR_VERDE = new java.awt.Color(51,102,0);
    private static final java.awt.Color COLOR_AMARILLO = new java.awt.Color(204,153,0);
    private static final java.awt.Color COLOR_ROJO = new java.awt.Color(204,0,0);
    private static final java.awt.Color COLOR_BLACK = new java.awt.Color(0,0,0);
    private static final int MAX_INTENTOS = 6;
    private static final int TAMNHO_PALABRA = 5;
    private static final File espanhol = new File("./data/españonPalabras");
    private static final File ingles = new File("./data/inglesPalabras");
    private static final File baseDeDatos = new File("./data/dbwordle.db");
    private final javax.swing.JLabel[][] labels = new javax.swing.JLabel[MAX_INTENTOS][TAMNHO_PALABRA];
    private int contadorFila = 0;
    private String palabraAleatoria = null;
    private File ficheroActual = null;
    private final Map<String,Set<Character>> palabrasEncontradas = new HashMap<>();
   
    
       
    private org.daw1.Jorge.wordle.IMotores tipoMotor = null;
    
    /**
     * Creates new form MainGui
     */
    public MainGui() {       
        initComponents();        
        inicializarLabesl();
       
        
        
    }
    /***
    public void checkLenguaje(){
        
        ficheroActual = new File("palabrasEspañol.txt");
        try {
            tipoMotor.createFile(ficheroActual);
            tipoMotor.cargarFichero(ficheroActual);
        } catch (IOException ex) {
            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
            }
               
    }
    * **/
    String palabraRed = "RED";
    String palabraGreen = "GREEN";
    String palabraYellow = "YELLOW";
    public void checkCorrect(int x){
        contadorFila++;        
        palabraAleatoria = palabraAleatoria.toUpperCase();
        JLabel[][] label = labels;    
        String text = this.palabrajTextField.getText().toUpperCase();
        char[] lAleatoria = palabraAleatoria.toCharArray();
        char[] lInput = text.toCharArray();
        if(contadorFila < MAX_INTENTOS){
                                                            
            int n = 0;
            for(int i = 0; i < text.length();i++){
                if(lInput[i] == lAleatoria[i]){
                    label[x][i].setVisible(true);                   
                    label[x][i].setText(Character.toString(lInput[i]));                            
                    label[x][i].setForeground(COLOR_VERDE);
                    palabrasEncontradas.get(palabraGreen).add(lInput[i]);
                    palabrasEncontradas.get(palabraYellow).remove(lInput[i]);
                    n++;
                }
                else if(palabraAleatoria.contains(lInput[i]+"")){
                    label[x][i].setVisible(true);                   
                    label[x][i].setText(Character.toString(lInput[i]));
                    
                    
                    if(palabrasEncontradas.get(palabraGreen).contains(lInput[i])){
                        label[x][i].setForeground(COLOR_BLACK);
                        }
                    else{
                        label[x][i].setForeground(COLOR_AMARILLO);
                        palabrasEncontradas.get(palabraYellow).add(lInput[i]);
                    }
                          
                    
                    
                      
                }
                else{
                    label[x][i].setVisible(true);                   
                    label[x][i].setText(Character.toString(lInput[i]));                            
                    label[x][i].setForeground(COLOR_ROJO);
                    palabrasEncontradas.get(palabraRed).add(lInput[i]);
            }
            
                if(n == 5){
                    if((this.contadorFila+1) == 1){
                        this.finaljLabel.setText("Has Ganado en "+(this.contadorFila)+" intento!!!");
                         this.finaljLabel.setForeground(COLOR_VERDE);
                        this.enviarjButton.setEnabled(false);
                    }
                    else{
                        this.finaljLabel.setText("Has Ganado en "+(this.contadorFila)+" intentos!!!");
                        this.finaljLabel.setForeground(COLOR_VERDE);
                        this.enviarjButton.setEnabled(false);
                    }
                    
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(palabrasEncontradas.get(palabraRed));
            sb.replace(0,1, "");
            sb.replace(sb.length()-1,sb.length(), "");
            this.maljLabel.setText(sb.toString());
            sb.delete(0, sb.length());
            sb.append(palabrasEncontradas.get(palabraGreen));
            sb.replace(0,1, "");
            sb.replace(sb.length()-1,sb.length(), "");
            this.bienjLabel.setText(sb.toString());
            sb.delete(0, sb.length());
            sb.append(palabrasEncontradas.get(palabraYellow));
            sb.replace(0,1, "");
            sb.replace(sb.length()-1,sb.length(), "");
            this.existenjLabel.setText(sb.toString());
            
            if(contadorFila == MAX_INTENTOS -1 && n != 5){
                this.enviarjButton.setEnabled(false);
                this.finaljLabel.setText("Has perdido!!!");
                this.finaljLabel.setForeground(COLOR_ROJO);
            }
        }
        
               
    }
    
    public final void nuevaPartida(){
        String idioma = "";
        limpiarLabels();
        contadorFila = 0;
        palabrasEncontradas.put(palabraRed, new TreeSet<>());
        palabrasEncontradas.put(palabraGreen, new TreeSet<>());
        palabrasEncontradas.put(palabraYellow, new TreeSet<>());
        if(this.jRadioButtonMenuItemEspanhol.isSelected()){
            idioma = "es";
        }
        else{
            idioma = "gl";
        }
        this.enviarjButton.setEnabled(true);
        if(this.jRadioButtonMenuItemEspanhol.isSelected() && this.jRadioButtonArchivoTexto.isSelected()){
            ficheroActual = espanhol;
            tipoMotor = new org.daw1.Jorge.wordle.motores.MotorFicheroTexto();
            tipoMotor.createFile(ficheroActual);
            palabraAleatoria = tipoMotor.obtenerPalabraAleatoria();
                    
        }
        else if(this.jRadioButtonMenuItemEspanhol.isSelected() && this.jRadioButtonTest.isSelected()){  
            tipoMotor = new org.daw1.Jorge.wordle.motores.MotorTest();
            palabraAleatoria = tipoMotor.obtenerPalabraAleatoria();
            
        }
        else if(this.jRadioButtonMenuItemGal.isSelected() && this.jRadioButtonTest.isSelected()){
            tipoMotor = new org.daw1.Jorge.wordle.motores.MotorTest();    
            palabraAleatoria = tipoMotor.obtenerPalabraAleatoria();
        }
        else if(this.jRadioButtonMenuItemGal.isSelected() && this.jRadioButtonArchivoTexto.isSelected()){
            tipoMotor = new org.daw1.Jorge.wordle.motores.MotorFicheroTexto();
            ficheroActual = ingles;
            tipoMotor.createFile(ficheroActual);
        }
        else if(this.jRadioButtonMenuItemEspanhol.isSelected() || this.jRadioButtonMenuItemGal.isSelected()  && this.jRadioButtonBasesDatos.isSelected()){
            tipoMotor = new org.daw1.Jorge.wordle.motores.MotorBasesDatos(idioma);
            ficheroActual = baseDeDatos;   
            palabraAleatoria = tipoMotor.obtenerPalabraAleatoria();
        }
    }
    
    public final void limpiarLabels(){
        for(int i = 1; i <= MAX_INTENTOS;i++){
            for(int j = 1;j <= TAMNHO_PALABRA; j++){
                labels[i-1][j-1].setText("");
                
            }
        }
        this.existenjLabel.setText("");
        this.maljLabel.setText("");
        this.bienjLabel.setText("");
        this.finaljLabel.setText("");
        

    }
    public final void inicializarLabesl(){
        this.enviarjButton.setEnabled(false);
        
        for(int i = 1; i <= MAX_INTENTOS;i++){
            for(int j = 1;j <= TAMNHO_PALABRA; j++){
                try {
                    String nombreLabel = "jLabel"+i+"_"+j;
                    javax.swing.JLabel aux = (javax.swing.JLabel)this.getClass().getDeclaredField(nombreLabel).get(this);
                    labels[i-1][j-1] = aux;
                    
                    
                } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
                }
                JLabel label =labels[i-1][j-1];
                label.setVisible(false);
                
            }
        }
        File ruta = new File("./data/");
        if(!ruta.isDirectory()){
            ruta.mkdir();
        }
        
       
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        MotoresbuttonGroup = new javax.swing.ButtonGroup();
        IdiomabuttonGroup = new javax.swing.ButtonGroup();
        MainjPanel = new javax.swing.JPanel();
        ZonaLetrasjPanel = new javax.swing.JPanel();
        jLabel1_1 = new javax.swing.JLabel();
        jLabel1_2 = new javax.swing.JLabel();
        jLabel1_3 = new javax.swing.JLabel();
        jLabel1_4 = new javax.swing.JLabel();
        jLabel1_5 = new javax.swing.JLabel();
        jLabel2_1 = new javax.swing.JLabel();
        jLabel2_2 = new javax.swing.JLabel();
        jLabel2_3 = new javax.swing.JLabel();
        jLabel2_4 = new javax.swing.JLabel();
        jLabel2_5 = new javax.swing.JLabel();
        jLabel3_1 = new javax.swing.JLabel();
        jLabel3_2 = new javax.swing.JLabel();
        jLabel3_3 = new javax.swing.JLabel();
        jLabel3_4 = new javax.swing.JLabel();
        jLabel3_5 = new javax.swing.JLabel();
        jLabel4_1 = new javax.swing.JLabel();
        jLabel4_2 = new javax.swing.JLabel();
        jLabel4_3 = new javax.swing.JLabel();
        jLabel4_4 = new javax.swing.JLabel();
        jLabel4_5 = new javax.swing.JLabel();
        jLabel5_1 = new javax.swing.JLabel();
        jLabel5_2 = new javax.swing.JLabel();
        jLabel5_3 = new javax.swing.JLabel();
        jLabel5_4 = new javax.swing.JLabel();
        jLabel5_5 = new javax.swing.JLabel();
        jLabel6_1 = new javax.swing.JLabel();
        jLabel6_2 = new javax.swing.JLabel();
        jLabel6_3 = new javax.swing.JLabel();
        jLabel6_4 = new javax.swing.JLabel();
        jLabel6_5 = new javax.swing.JLabel();
        BottomjPanel = new javax.swing.JPanel();
        estadojPanel = new javax.swing.JPanel();
        maljPanel = new javax.swing.JPanel();
        maljLabel = new javax.swing.JLabel();
        existenjPanel2 = new javax.swing.JPanel();
        existenjLabel = new javax.swing.JLabel();
        bienjPanel3 = new javax.swing.JPanel();
        bienjLabel = new javax.swing.JLabel();
        inputPalabrasjPanel = new javax.swing.JPanel();
        palabrajTextField = new javax.swing.JTextField();
        enviarjButton = new javax.swing.JButton();
        exitojPanel4 = new javax.swing.JPanel();
        finaljLabel = new javax.swing.JLabel();
        errorjPanel3 = new javax.swing.JPanel();
        errorjLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        nuevaPartidajMenuItem = new javax.swing.JMenuItem();
        salirjMenuItem = new javax.swing.JMenuItem();
        jMenuMotores = new javax.swing.JMenu();
        ajustesMotorjMenuItem = new javax.swing.JMenuItem();
        jRadioButtonTest = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonArchivoTexto = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonBasesDatos = new javax.swing.JRadioButtonMenuItem();
        jMenuIdioma = new javax.swing.JMenu();
        jRadioButtonMenuItemEspanhol = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemGal = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JorgeRodriguez");
        setMinimumSize(new java.awt.Dimension(620, 540));

        MainjPanel.setMinimumSize(new java.awt.Dimension(620, 544));
        MainjPanel.setPreferredSize(new java.awt.Dimension(500, 472));
        MainjPanel.setLayout(new java.awt.BorderLayout());

        ZonaLetrasjPanel.setLayout(new java.awt.GridLayout(6, 5));

        jLabel1_1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1_1.setForeground(new java.awt.Color(187, 187, 187));
        jLabel1_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1_1.setText("A");
        ZonaLetrasjPanel.add(jLabel1_1);

        jLabel1_2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1_2.setForeground(new java.awt.Color(187, 187, 187));
        jLabel1_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1_2.setText("A");
        ZonaLetrasjPanel.add(jLabel1_2);

        jLabel1_3.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1_3.setForeground(new java.awt.Color(187, 187, 187));
        jLabel1_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1_3.setText("A");
        ZonaLetrasjPanel.add(jLabel1_3);

        jLabel1_4.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1_4.setForeground(new java.awt.Color(187, 187, 187));
        jLabel1_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1_4.setText("A");
        ZonaLetrasjPanel.add(jLabel1_4);

        jLabel1_5.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1_5.setForeground(new java.awt.Color(187, 187, 187));
        jLabel1_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1_5.setText("A");
        ZonaLetrasjPanel.add(jLabel1_5);

        jLabel2_1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2_1.setForeground(new java.awt.Color(187, 187, 187));
        jLabel2_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2_1.setText("A");
        ZonaLetrasjPanel.add(jLabel2_1);

        jLabel2_2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2_2.setForeground(new java.awt.Color(187, 187, 187));
        jLabel2_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2_2.setText("A");
        ZonaLetrasjPanel.add(jLabel2_2);

        jLabel2_3.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2_3.setForeground(new java.awt.Color(187, 187, 187));
        jLabel2_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2_3.setText("A");
        ZonaLetrasjPanel.add(jLabel2_3);

        jLabel2_4.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2_4.setForeground(new java.awt.Color(187, 187, 187));
        jLabel2_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2_4.setText("A");
        ZonaLetrasjPanel.add(jLabel2_4);

        jLabel2_5.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2_5.setForeground(new java.awt.Color(187, 187, 187));
        jLabel2_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2_5.setText("A");
        ZonaLetrasjPanel.add(jLabel2_5);

        jLabel3_1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel3_1.setForeground(new java.awt.Color(187, 187, 187));
        jLabel3_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3_1.setText("A");
        ZonaLetrasjPanel.add(jLabel3_1);

        jLabel3_2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel3_2.setForeground(new java.awt.Color(187, 187, 187));
        jLabel3_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3_2.setText("A");
        ZonaLetrasjPanel.add(jLabel3_2);

        jLabel3_3.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel3_3.setForeground(new java.awt.Color(187, 187, 187));
        jLabel3_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3_3.setText("A");
        ZonaLetrasjPanel.add(jLabel3_3);

        jLabel3_4.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel3_4.setForeground(new java.awt.Color(187, 187, 187));
        jLabel3_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3_4.setText("A");
        ZonaLetrasjPanel.add(jLabel3_4);

        jLabel3_5.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel3_5.setForeground(new java.awt.Color(187, 187, 187));
        jLabel3_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3_5.setText("A");
        ZonaLetrasjPanel.add(jLabel3_5);

        jLabel4_1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel4_1.setForeground(new java.awt.Color(187, 187, 187));
        jLabel4_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4_1.setText("A");
        ZonaLetrasjPanel.add(jLabel4_1);

        jLabel4_2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel4_2.setForeground(new java.awt.Color(187, 187, 187));
        jLabel4_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4_2.setText("A");
        ZonaLetrasjPanel.add(jLabel4_2);

        jLabel4_3.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel4_3.setForeground(new java.awt.Color(187, 187, 187));
        jLabel4_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4_3.setText("A");
        ZonaLetrasjPanel.add(jLabel4_3);

        jLabel4_4.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel4_4.setForeground(new java.awt.Color(187, 187, 187));
        jLabel4_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4_4.setText("A");
        ZonaLetrasjPanel.add(jLabel4_4);

        jLabel4_5.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel4_5.setForeground(new java.awt.Color(187, 187, 187));
        jLabel4_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4_5.setText("A");
        ZonaLetrasjPanel.add(jLabel4_5);

        jLabel5_1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel5_1.setForeground(new java.awt.Color(187, 187, 187));
        jLabel5_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5_1.setText("A");
        ZonaLetrasjPanel.add(jLabel5_1);

        jLabel5_2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel5_2.setForeground(new java.awt.Color(187, 187, 187));
        jLabel5_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5_2.setText("A");
        ZonaLetrasjPanel.add(jLabel5_2);

        jLabel5_3.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel5_3.setForeground(new java.awt.Color(187, 187, 187));
        jLabel5_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5_3.setText("A");
        ZonaLetrasjPanel.add(jLabel5_3);

        jLabel5_4.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel5_4.setForeground(new java.awt.Color(187, 187, 187));
        jLabel5_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5_4.setText("A");
        ZonaLetrasjPanel.add(jLabel5_4);

        jLabel5_5.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel5_5.setForeground(new java.awt.Color(187, 187, 187));
        jLabel5_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5_5.setText("A");
        ZonaLetrasjPanel.add(jLabel5_5);

        jLabel6_1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel6_1.setForeground(new java.awt.Color(187, 187, 187));
        jLabel6_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6_1.setText("A");
        ZonaLetrasjPanel.add(jLabel6_1);

        jLabel6_2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel6_2.setForeground(new java.awt.Color(187, 187, 187));
        jLabel6_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6_2.setText("A");
        ZonaLetrasjPanel.add(jLabel6_2);

        jLabel6_3.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel6_3.setForeground(new java.awt.Color(187, 187, 187));
        jLabel6_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6_3.setText("A");
        ZonaLetrasjPanel.add(jLabel6_3);

        jLabel6_4.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel6_4.setForeground(new java.awt.Color(187, 187, 187));
        jLabel6_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6_4.setText("A");
        ZonaLetrasjPanel.add(jLabel6_4);

        jLabel6_5.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel6_5.setForeground(new java.awt.Color(187, 187, 187));
        jLabel6_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6_5.setText("A");
        ZonaLetrasjPanel.add(jLabel6_5);

        MainjPanel.add(ZonaLetrasjPanel, java.awt.BorderLayout.CENTER);

        BottomjPanel.setPreferredSize(new java.awt.Dimension(200, 100));
        BottomjPanel.setLayout(new java.awt.GridLayout(2, 2));

        estadojPanel.setLayout(new java.awt.GridLayout(3, 0));

        maljPanel.setLayout(new java.awt.GridLayout(1, 0));

        maljLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        maljLabel.setForeground(new java.awt.Color(204, 0, 0));
        maljLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        maljLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        maljPanel.add(maljLabel);

        estadojPanel.add(maljPanel);

        existenjPanel2.setLayout(new java.awt.GridLayout(1, 0));

        existenjLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        existenjLabel.setForeground(new java.awt.Color(255, 204, 0));
        existenjPanel2.add(existenjLabel);

        estadojPanel.add(existenjPanel2);

        bienjPanel3.setLayout(new java.awt.GridLayout(1, 0));

        bienjLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        bienjLabel.setForeground(new java.awt.Color(51, 102, 0));
        bienjPanel3.add(bienjLabel);

        estadojPanel.add(bienjPanel3);

        BottomjPanel.add(estadojPanel);

        palabrajTextField.setPreferredSize(new java.awt.Dimension(130, 25));
        inputPalabrasjPanel.add(palabrajTextField);

        enviarjButton.setText("Enviar");
        enviarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarjButtonActionPerformed(evt);
            }
        });
        inputPalabrasjPanel.add(enviarjButton);

        BottomjPanel.add(inputPalabrasjPanel);

        exitojPanel4.setToolTipText("");
        exitojPanel4.setLayout(new java.awt.GridBagLayout());

        finaljLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 104, 18, 105);
        exitojPanel4.add(finaljLabel, gridBagConstraints);

        BottomjPanel.add(exitojPanel4);

        errorjPanel3.setLayout(new java.awt.GridBagLayout());

        errorjLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        errorjLabel.setForeground(new java.awt.Color(204, 0, 0));
        errorjLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 112, 18, 113);
        errorjPanel3.add(errorjLabel, gridBagConstraints);

        BottomjPanel.add(errorjPanel3);

        MainjPanel.add(BottomjPanel, java.awt.BorderLayout.PAGE_END);

        jMenuArchivo.setText("Archivo");

        nuevaPartidajMenuItem.setText("Nueva partida");
        nuevaPartidajMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaPartidajMenuItemActionPerformed(evt);
            }
        });
        jMenuArchivo.add(nuevaPartidajMenuItem);

        salirjMenuItem.setText("salir");
        salirjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirjMenuItemActionPerformed(evt);
            }
        });
        jMenuArchivo.add(salirjMenuItem);

        jMenuBar1.add(jMenuArchivo);

        jMenuMotores.setText("Motores");

        ajustesMotorjMenuItem.setText("Ajustes motor");
        ajustesMotorjMenuItem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ajustesMotorjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajustesMotorjMenuItemActionPerformed(evt);
            }
        });
        jMenuMotores.add(ajustesMotorjMenuItem);

        MotoresbuttonGroup.add(jRadioButtonTest);
        jRadioButtonTest.setText("MotorTest");
        jRadioButtonTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTestActionPerformed(evt);
            }
        });
        jMenuMotores.add(jRadioButtonTest);

        MotoresbuttonGroup.add(jRadioButtonArchivoTexto);
        jRadioButtonArchivoTexto.setSelected(true);
        jRadioButtonArchivoTexto.setText("MotorFicheros");
        jRadioButtonArchivoTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonArchivoTextoActionPerformed(evt);
            }
        });
        jMenuMotores.add(jRadioButtonArchivoTexto);

        MotoresbuttonGroup.add(jRadioButtonBasesDatos);
        jRadioButtonBasesDatos.setText("MotorBasesDeDatos");
        jRadioButtonBasesDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonBasesDatosActionPerformed(evt);
            }
        });
        jMenuMotores.add(jRadioButtonBasesDatos);

        jMenuBar1.add(jMenuMotores);

        jMenuIdioma.setText("Idioma");

        IdiomabuttonGroup.add(jRadioButtonMenuItemEspanhol);
        jRadioButtonMenuItemEspanhol.setSelected(true);
        jRadioButtonMenuItemEspanhol.setText("español");
        jRadioButtonMenuItemEspanhol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItemEspanholActionPerformed(evt);
            }
        });
        jMenuIdioma.add(jRadioButtonMenuItemEspanhol);

        IdiomabuttonGroup.add(jRadioButtonMenuItemGal);
        jRadioButtonMenuItemGal.setText("Gallego");
        jRadioButtonMenuItemGal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItemGalActionPerformed(evt);
            }
        });
        jMenuIdioma.add(jRadioButtonMenuItemGal);

        jMenuBar1.add(jMenuIdioma);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainjPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarjButtonActionPerformed
        this.errorjLabel.setText("");
        if(this.jRadioButtonArchivoTexto.isSelected()){
            if(tipoMotor.checkPalabra(this.palabrajTextField.getText()) && tipoMotor.cargarFichero(ficheroActual).contains(this.palabrajTextField.getText())){
                checkCorrect(contadorFila);
                
            }
            else if(ficheroActual == null){
                this.errorjLabel.setText("El fichero no existe");
            }    
            else if(!tipoMotor.checkPalabra(this.palabrajTextField.getText())){
                this.errorjLabel.setText("Error de caracteres");
            }    
            else{
                this.errorjLabel.setText("La palabra no existe.");


            }
        }
        else if(this.jRadioButtonTest.isSelected()){
            if(tipoMotor.checkPalabra(this.palabrajTextField.getText())){
                checkCorrect(contadorFila);
                
            }
            else{
                this.errorjLabel.setText("Error de caracteres");
            }
        }
        else if(this.jRadioButtonBasesDatos.isSelected()){
            if(tipoMotor.checkPalabra(this.palabrajTextField.getText()) && tipoMotor.cargarFichero().contains(this.palabrajTextField.getText().toUpperCase())){
                checkCorrect(contadorFila);
            }   
            else if(!tipoMotor.checkPalabra(this.palabrajTextField.getText().toUpperCase())){
                this.errorjLabel.setText("Error de caracteres");
            }    
            else{
                this.errorjLabel.setText("La palabra no existe.");


            }
            
        }
           
        
        
        
                
    }//GEN-LAST:event_enviarjButtonActionPerformed

    private void jRadioButtonBasesDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBasesDatosActionPerformed
        String idioma = "";
        if(this.jRadioButtonMenuItemEspanhol.isSelected()){
            idioma = "es";
        }
        else{
            idioma = "gl";
        }
        tipoMotor = new org.daw1.Jorge.wordle.motores.MotorBasesDatos(idioma);
        nuevaPartida();
    }//GEN-LAST:event_jRadioButtonBasesDatosActionPerformed

    private void jRadioButtonMenuItemEspanholActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemEspanholActionPerformed
        if(this.jRadioButtonMenuItemEspanhol.isSelected() && this.jRadioButtonArchivoTexto.isSelected()){
            ficheroActual = espanhol;
            tipoMotor.createFile(ficheroActual);
        }
        else if(this.jRadioButtonMenuItemEspanhol.isSelected() && this.jRadioButtonTest.isSelected()){
            ficheroActual = espanhol;
            
        }
        else{
            ficheroActual = baseDeDatos;
       
        }
        
    }//GEN-LAST:event_jRadioButtonMenuItemEspanholActionPerformed

    private void nuevaPartidajMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaPartidajMenuItemActionPerformed
        nuevaPartida();
        
        
    }//GEN-LAST:event_nuevaPartidajMenuItemActionPerformed

    private void jRadioButtonMenuItemGalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemGalActionPerformed
        if(this.jRadioButtonMenuItemGal.isSelected() && this.jRadioButtonArchivoTexto.isSelected()){
            ficheroActual = ingles;
            tipoMotor.createFile(ficheroActual);
        }
         else if(this.jRadioButtonMenuItemGal.isSelected() && this.jRadioButtonTest.isSelected()){
            ficheroActual = ingles;
            
        }
        else{
            ficheroActual = baseDeDatos;
            
        }
    }//GEN-LAST:event_jRadioButtonMenuItemGalActionPerformed

    private void jRadioButtonTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTestActionPerformed
        tipoMotor = new org.daw1.Jorge.wordle.motores.MotorTest();
        nuevaPartida();
    }//GEN-LAST:event_jRadioButtonTestActionPerformed

    private void jRadioButtonArchivoTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonArchivoTextoActionPerformed
        tipoMotor = new org.daw1.Jorge.wordle.motores.MotorFicheroTexto();
        nuevaPartida();
        
    }//GEN-LAST:event_jRadioButtonArchivoTextoActionPerformed

    private void ajustesMotorjMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajustesMotorjMenuItemActionPerformed
        ajustesPanelGui ajustesMotor = new ajustesPanelGui(this,true,this.tipoMotor,this.ficheroActual);
        
        ajustesMotor.setVisible(true);
    }//GEN-LAST:event_ajustesMotorjMenuItemActionPerformed

    private void salirjMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirjMenuItemActionPerformed
       System.exit(0);
    }//GEN-LAST:event_salirjMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BottomjPanel;
    private javax.swing.ButtonGroup IdiomabuttonGroup;
    private javax.swing.JPanel MainjPanel;
    private javax.swing.ButtonGroup MotoresbuttonGroup;
    private javax.swing.JPanel ZonaLetrasjPanel;
    private javax.swing.JMenuItem ajustesMotorjMenuItem;
    private javax.swing.JLabel bienjLabel;
    private javax.swing.JPanel bienjPanel3;
    private javax.swing.JButton enviarjButton;
    private javax.swing.JLabel errorjLabel;
    private javax.swing.JPanel errorjPanel3;
    private javax.swing.JPanel estadojPanel;
    private javax.swing.JLabel existenjLabel;
    private javax.swing.JPanel existenjPanel2;
    private javax.swing.JPanel exitojPanel4;
    private javax.swing.JLabel finaljLabel;
    private javax.swing.JPanel inputPalabrasjPanel;
    private javax.swing.JLabel jLabel1_1;
    private javax.swing.JLabel jLabel1_2;
    private javax.swing.JLabel jLabel1_3;
    private javax.swing.JLabel jLabel1_4;
    private javax.swing.JLabel jLabel1_5;
    private javax.swing.JLabel jLabel2_1;
    private javax.swing.JLabel jLabel2_2;
    private javax.swing.JLabel jLabel2_3;
    private javax.swing.JLabel jLabel2_4;
    private javax.swing.JLabel jLabel2_5;
    private javax.swing.JLabel jLabel3_1;
    private javax.swing.JLabel jLabel3_2;
    private javax.swing.JLabel jLabel3_3;
    private javax.swing.JLabel jLabel3_4;
    private javax.swing.JLabel jLabel3_5;
    private javax.swing.JLabel jLabel4_1;
    private javax.swing.JLabel jLabel4_2;
    private javax.swing.JLabel jLabel4_3;
    private javax.swing.JLabel jLabel4_4;
    private javax.swing.JLabel jLabel4_5;
    private javax.swing.JLabel jLabel5_1;
    private javax.swing.JLabel jLabel5_2;
    private javax.swing.JLabel jLabel5_3;
    private javax.swing.JLabel jLabel5_4;
    private javax.swing.JLabel jLabel5_5;
    private javax.swing.JLabel jLabel6_1;
    private javax.swing.JLabel jLabel6_2;
    private javax.swing.JLabel jLabel6_3;
    private javax.swing.JLabel jLabel6_4;
    private javax.swing.JLabel jLabel6_5;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuIdioma;
    private javax.swing.JMenu jMenuMotores;
    private javax.swing.JRadioButtonMenuItem jRadioButtonArchivoTexto;
    private javax.swing.JRadioButtonMenuItem jRadioButtonBasesDatos;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemEspanhol;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemGal;
    private javax.swing.JRadioButtonMenuItem jRadioButtonTest;
    private javax.swing.JLabel maljLabel;
    private javax.swing.JPanel maljPanel;
    private javax.swing.JMenuItem nuevaPartidajMenuItem;
    private javax.swing.JTextField palabrajTextField;
    private javax.swing.JMenuItem salirjMenuItem;
    // End of variables declaration//GEN-END:variables
}
