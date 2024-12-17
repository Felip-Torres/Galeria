/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebagaleria;

import static com.mycompany.pruebagaleria.PruebaGaleria.imagenValida;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
        frame.setSize(450, 450);
        
        Panel pa = new Panel();

        pa.setVisible(true);
        frame.add(pa);

                // Añadir un ComponentListener para ajustar el tamaño del Panel y sus componentes
                frame.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        pa.adjustComponentsSize(frame.getWidth(), frame.getHeight());
                    }
                });
        
                // Hacer visible la ventana
                frame.setVisible(true);
    }
}
