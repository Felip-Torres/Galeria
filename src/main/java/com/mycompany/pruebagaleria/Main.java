/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebagaleria;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
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
        frame.setSize(450, 450);
        
        Panel pa = new Panel();
        pa.setVisible(true);
        frame.add(pa);
        
        // Hacer visible la ventana
        frame.setVisible(true);
    }
}
