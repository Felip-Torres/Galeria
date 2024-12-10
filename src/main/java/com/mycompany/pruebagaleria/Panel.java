

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.pruebagaleria;

import static com.mycompany.pruebagaleria.Main.Transparente;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
        
        imgPaths = new ArrayList<>();
        conexionAzure();
        
        imagenLabel = new JLabel();
        imagenLabel.setBounds(0, 0, getWidth(), getHeight());
        imagenLabel.setVisible(true);
        imagenLabel.setFocusable(false);
        add(imagenLabel);
        
        // Mostrar la primera imagen
        String nombre = imgPaths.get(index);
        showImage(nombre);
        
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

        add(botonIzquierda);
        add(botonDerecha);
        
        setComponentZOrder(imagenLabel, 1);
        setComponentZOrder(botonIzquierda, 0);
        setComponentZOrder(botonDerecha, 0);
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
    
    public void showImage(String url) {
        try {
            // Leer la imagen directamente desde la URL
            Image imagen = ImageIO.read(new URL(url));

            int panelwidth = imagen.getWidth(null);
            int panelheight = imagen.getHeight(null);
            double aspectRatio = (double) imagen.getWidth(null) / imagen.getHeight(null);

            if (imagen.getHeight(null) > imagenLabel.getHeight()) {
                panelheight = imagenLabel.getHeight();
                panelwidth = (int) (panelheight * aspectRatio);
            }
            if (imagen.getWidth(null) > imagenLabel.getWidth()) {
                panelwidth = imagenLabel.getWidth();
                panelheight = (int) (panelwidth / aspectRatio);
            }
            if (panelwidth < imagenLabel.getWidth() && panelheight < imagenLabel.getHeight()) {
                if (aspectRatio >= 1) {
                    panelwidth = imagenLabel.getWidth();
                    panelheight = (int) (panelwidth / aspectRatio);
                } else {
                    panelheight = imagenLabel.getHeight();
                    panelwidth = (int) (panelheight * aspectRatio);
                }
            }

            ImageIcon imgIcon = new ImageIcon(imagen.getScaledInstance(panelwidth, panelheight, Image.SCALE_SMOOTH));
            imagenLabel.setIcon(imgIcon);

            imagenLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar horizontalmente
            imagenLabel.setVerticalAlignment(SwingConstants.CENTER);
        } catch (Exception e) {
            System.err.println("Error al cargar imagen desde URL: " + url);
            e.printStackTrace();
        }
    }
    
    public void conexionAzure(){
        // Configurar Azure Blob Service
        String azureConnectionString = "DefaultEndpointsProtocol=https;AccountName=felip;AccountKey=7kWGzzXhJ/KvyMF+J9P83bfc9Uyy3CY9twJ15tmuU3H/fccHALUvrP0fdvhgG79qp7Me7vX8EEke+AStP3kgeQ==;EndpointSuffix=core.windows.net"; // Reemplaza con tu cadena de conexión
        String contenedor = "fotos"; // Reemplaza con el nombre de tu contenedor
        String carpetaDestino = "/imagenes"; // Carpeta local para descargar imágenes

        AzureBlobService azureBlobService = new AzureBlobService(azureConnectionString, contenedor);

        // Descargar imágenes desde Azure
        imgPaths = azureBlobService.obtenerUrlsImagenes("fondos");

        if (imgPaths.isEmpty()) {
            System.out.println("No hay imágenes disponibles.");
            System.exit(1);
        }
    }
}



