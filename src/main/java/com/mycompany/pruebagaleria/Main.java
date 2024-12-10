/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebagaleria;

import static com.mycompany.pruebagaleria.PruebaGaleria.imagenValida;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Alumne
 */
public class Main{
    static Color Transparente = new Color(0,0,0,0);
    
    
    public static void main(String[] args) {
        
        // Crear la ventana principal
        JFrame frame = new JFrame("Botón Transparente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        
        Panel pa = new Panel();
        pa.addImageDirectory("C:\\Users\\Alumne\\Documents\\Trasteo");
        pa.addImage("C:\\Users\\Alumne\\Pictures\\Screenshots\\Captura de pantalla 2024-11-21 115855.png");
        pa.setVisible(true);
        frame.add(pa);
        
        // Hacer visible la ventana
        frame.setVisible(true);
    }
}
