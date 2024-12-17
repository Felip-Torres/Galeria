/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebagaleria;

import static com.mycompany.pruebagaleria.PruebaGaleria.imagenValida;
import java.awt.Color;
import java.awt.event.ActionEvent;
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
import javax.swing.Timer;

/**
 *
 * @author Alumne
 */
public class Main {

    static Color Transparente = new Color(0, 0, 0, 0);

    public static void main(String[] args) {

        // Crear la ventana principal
        JFrame frame = new JFrame("Botón Transparente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);

        Panel pa = new Panel();
        pa.addImageDirectory("C:\\Users\\Alumne\\Pictures\\Screenshots");
        pa.conexionAzure(
                "DefaultEndpointsProtocol=https;AccountName=felip;AccountKey=7kWGzzXhJ/KvyMF+J9P83bfc9Uyy3CY9twJ15tmuU3H/fccHALUvrP0fdvhgG79qp7Me7vX8EEke+AStP3kgeQ==;EndpointSuffix=core.windows.net",
                "fotos",
                "fondos"
        );

        pa.setVisible(true);
        frame.add(pa);
        
        // Temporizador para evitar ejecución continua
        Timer resizeTimer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ejecutar código al final del redimensionamiento
                pa.adjustComponentsSize(frame.getWidth(), frame.getHeight());
                System.out.println("Redimensionamiento finalizado.");
            }
        });
        resizeTimer.setRepeats(false);

        // Añadir un ComponentListener para ajustar el tamaño del Panel y sus componentes
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Reiniciar el temporizador cada vez que se redimensiona
                resizeTimer.restart();
            }
        });

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}
