/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebagaleria;

import static com.mycompany.pruebagaleria.Main.Transparente;
import static com.mycompany.pruebagaleria.PruebaGaleria.imagenValida;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author Alumne
 */
public class Panel extends JPanel{
    ArrayList<String> imgPaths;
    int index = 0;
    JLabel imagenLabel;
    
    public Panel(){
        setSize(400, 400);
        setLayout(null);
        setBorder(new LineBorder(Color.BLACK));
        setBackground(Color.BLACK);
        
        imagenLabel = new JLabel();
        imagenLabel.setBounds(0, 0, getWidth(), getHeight());
        imagenLabel.setVisible(true);
        imagenLabel.setFocusable(false);
        add(imagenLabel);
        // Crear el botón
        JButton botonIzquierda = new JButton("<");
        JButton botonDerecha = new JButton(">");
        
        botonIzquierda.setBounds(0, 0, 50, this.getHeight());  // Posición y tamaño
        botonIzquierda.setBackground(Transparente);  // Fondo transparente
        botonIzquierda.setBorderPainted(false);  // Elimina el borde
        botonIzquierda.setForeground(Transparente);
        botonIzquierda.setOpaque(false);
        botonIzquierda.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambia el color del texto al pasar el ratón por encima
                botonIzquierda.setForeground(Color.WHITE); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaura el color del texto cuando el ratón sale del botón
                botonIzquierda.setForeground(Transparente); 
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (index > 0) {
                    index--;
                    String name = imgPaths.get(index);
                    showImage(name);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
 
            }
        });
        
        botonDerecha.setBounds(this.getWidth()-50, 0, 50, this.getHeight());  // Posición y tamaño
        botonDerecha.setBackground(Transparente);  // Fondo transparente
        botonDerecha.setBorderPainted(false);  // Elimina el borde
        botonDerecha.setForeground(Transparente);
        botonDerecha.setOpaque(false);
        botonDerecha.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambia el color del texto al pasar el ratón por encima
                botonDerecha.setForeground(Color.WHITE); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaura el color del texto cuando el ratón sale del botón
                botonDerecha.setForeground(Transparente); 
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (index < imgPaths.size()-1) {
                    index++;
                    String name = imgPaths.get(index);
                    showImage(name);
                }
            }
        });
        
        JButton boton = new JButton("Cargar imagenes");
        boton.setBounds(200, 200, 100, 50);
        boton.setVisible(true);
        boton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {                                              
            imgPaths = new ArrayList<>();
            String dirCarpeta = "";
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int seleccionado = fileChooser.showOpenDialog(imagenLabel);
            if(seleccionado != JFileChooser.CANCEL_OPTION) {
                File directorio = fileChooser.getSelectedFile();
                dirCarpeta = fileChooser.getSelectedFile().getPath();
                File carpeta = new File(dirCarpeta);

                if (carpeta.exists() && carpeta.isDirectory()) {
                    File[] files = carpeta.listFiles();
                    if (files != null) {
                        for (File f : files) {
                            if(f.isFile() && imagenValida(f.getName())) imgPaths.add(f.getAbsolutePath());
                        }
                    }
                }
                String nombre = imgPaths.get(index);
                    showImage(nombre);
                }
           }                     
        });
        
        add(boton);
        add(botonIzquierda);
        add(botonDerecha);
        
        setComponentZOrder(imagenLabel, 1);
        setComponentZOrder(botonIzquierda, 0);
        setComponentZOrder(botonDerecha, 0);
        setComponentZOrder(boton, 2);
    }    
    
    // VALIDACION FORMATO IMAGENES
    public static boolean imagenValida(String archivo) {
        String extension = compruebaExtension(archivo);
        if (extension != null && extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpeg")) return true;
        else return false;
    }
    public static String compruebaExtension(String archivo) {
        int dotIndex = archivo.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < archivo.length()-1) {
            return archivo.substring(dotIndex+1).toLowerCase();
        }
        return null;
    }
    
    public void showImage(String img) {
        Image imagen = new ImageIcon(img).getImage();
        
        int panelwidth = imagen.getWidth(this);
        int panelheight = imagen.getHeight(this);
        double aspectRatio = (double) imagen.getWidth(this) / imagen.getHeight(this);

        if (imagen.getHeight(this) > imagenLabel.getHeight()) {
            panelheight = imagenLabel.getHeight();
            panelwidth = (int) (panelheight * aspectRatio);
        }
        if (imagen.getWidth(this) > imagenLabel.getWidth()) {
            panelwidth = imagenLabel.getWidth();
            panelheight = (int) (panelwidth / aspectRatio);
        }
        if(panelwidth < imagenLabel.getWidth() && panelheight < imagenLabel.getHeight()){
            if(aspectRatio>=1){
                panelwidth = imagenLabel.getWidth();
                panelheight = (int)(panelwidth / aspectRatio);
            }else{
                panelheight = imagenLabel.getHeight();
                panelwidth = (int) (panelheight * aspectRatio);
            }
        }
        
        ImageIcon imgIcon = new ImageIcon(imagen.getScaledInstance(panelwidth, panelheight, Image.SCALE_SMOOTH));
        imagenLabel.setIcon(imgIcon);
        
        
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar horizontalmente
        imagenLabel.setVerticalAlignment(SwingConstants.CENTER); 
    }
}
