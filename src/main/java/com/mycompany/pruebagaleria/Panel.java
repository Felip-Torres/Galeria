/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebagaleria;

import static com.mycompany.pruebagaleria.Main.Transparente;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Alumne
 */
public class Panel extends JPanel{
    public Panel(){
        this.setSize(400, 400);
        this.setLayout(null);
        this.setBorder(new LineBorder(Color.BLACK));
        // Crear el botón
        JButton botonTransparente = new JButton("<");
        JButton botonTransparente2 = new JButton(">");
        
        botonTransparente.setBounds(0, 0, 50, this.getHeight());  // Posición y tamaño
        botonTransparente.setBackground(Transparente);  // Fondo transparente
        botonTransparente.setBorderPainted(false);  // Elimina el borde
        botonTransparente.setForeground(Transparente);
        botonTransparente.setOpaque(false);
        botonTransparente.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambia el color del texto al pasar el ratón por encima
                botonTransparente.setForeground(Color.BLACK); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaura el color del texto cuando el ratón sale del botón
                botonTransparente.setForeground(Transparente); 
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
 
            }
        });
        
        botonTransparente2.setBounds(this.getWidth()-50, 0, 50, this.getHeight());  // Posición y tamaño
        botonTransparente2.setBackground(Transparente);  // Fondo transparente
        botonTransparente2.setBorderPainted(false);  // Elimina el borde
        botonTransparente2.setForeground(Transparente);
        botonTransparente2.setOpaque(false);
        botonTransparente2.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambia el color del texto al pasar el ratón por encima
                botonTransparente2.setForeground(Color.BLACK); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaura el color del texto cuando el ratón sale del botón
                botonTransparente2.setForeground(Transparente); 
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
 
            }
        });
        
        this.add(botonTransparente);
        this.add(botonTransparente2);
    }    
}
