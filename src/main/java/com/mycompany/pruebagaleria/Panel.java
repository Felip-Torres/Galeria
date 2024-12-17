

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.pruebagaleria;

import static com.mycompany.pruebagaleria.Main.Transparente;
import java.awt.Component;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
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
public class Panel extends JPanel {

    private Color transp = new Color(0, 0, 0, 0);
    private Color opaco = new Color(255, 255, 255, 255);
    private ArrayList<String> imgPaths = new ArrayList<>();
    private int index = 0;
    private final JLabel imagenLabel;

    public Panel() {
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
        DegradadoButton botonDerecha = new DegradadoButton(">", transp, opaco);
        DegradadoButton botonIzquierda = new DegradadoButton("<", opaco, transp);

        botonIzquierda.setBounds(0, 0, 50, this.getHeight());  // Posición y tamaño
        botonIzquierda.setBackground(Transparente);  // Fondo transparente
        botonIzquierda.setBorderPainted(false);  // Elimina el borde
        botonIzquierda.setForeground(Transparente);
        botonIzquierda.setOpaque(false);
        botonIzquierda.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambia el color del texto al pasar el ratón por encima
                botonIzquierda.setVisibleOnHover(true);
                botonIzquierda.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaura el color del texto cuando el ratón sale del botón
                botonIzquierda.setVisibleOnHover(false);
                botonIzquierda.setForeground(transp);
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (index > 0) {
                    index--;
                    String name = imgPaths.get(index);
                    showImage(name);
                }else{
                    index = imgPaths.size()-1;
                }
            }
        });

        botonDerecha.setBounds(this.getWidth() - 50, 0, 50, this.getHeight());  // Posición y tamaño
        botonDerecha.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambia el color del texto al pasar el ratón por encima
                botonDerecha.setVisibleOnHover(true);
                botonDerecha.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaura el color del texto cuando el ratón sale del botón
                botonDerecha.setVisibleOnHover(false);
                botonDerecha.setForeground(transp);
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

    public void addImageDirectory(String dirPath) {
        File carpeta = new File(dirPath);

        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] files = carpeta.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile() && imagenValida(f.getName())) {
                        imgPaths.add(f.getAbsolutePath());
                    }
                }
                if (!imgPaths.isEmpty()) {
                    showImage(imgPaths.get(index));
                }
            }
        }
    }

    public void addImage(String imgPath) {
        File img = new File(imgPath);
        if (img.exists() && !img.isDirectory()) {
            imgPaths.add(img.getAbsolutePath());
        }
        if (!imgPaths.isEmpty()) {
            showImage(imgPaths.get(index));
        }
    }

    public void removeImage(int indice) {
        imgPaths.remove(indice);
    }

    private static String compruebaExtension(String archivo) {
        int dotIndex = archivo.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < archivo.length() - 1) {
            return archivo.substring(dotIndex + 1).toLowerCase();
        }
        return null;
    }

    private void showImageLocal(String img) {
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
    }
    
    public void showImage(String url) {
        try {
            // Leer la imagen directamente desde la URL
            Image imagen = ImageIO.read(new URL(url));

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

// AÑADIR EL MÉTODO PARA AJUSTAR EL TAMAÑO DE LOS COMPONENTES DENTRO DEL PANEL AQUÍ
public void adjustComponentsSize(int frameWidth, int frameHeight) {
    // Ajustar el tamaño del Panel
    setSize(frameWidth, frameHeight);

    // Ajustar el tamaño del JLabel que muestra la imagen manteniendo la relación de aspecto
    if (imagenLabel.getIcon() != null) {
        double aspectRatio = (double) imagenLabel.getIcon().getIconWidth() / imagenLabel.getIcon().getIconHeight();
        int newWidth = frameWidth;
        int newHeight = (int) (newWidth / aspectRatio);

        if (newHeight > frameHeight) {
            newHeight = frameHeight;
            newWidth = (int) (newHeight * aspectRatio);
        }

        imagenLabel.setBounds((frameWidth - newWidth) / 2, (frameHeight - newHeight) / 2, newWidth, newHeight);
    } else {
        imagenLabel.setBounds(0, 0, getWidth(), getHeight());
    }

    // Ajustar el tamaño y la posición de los botones
    for (Component comp : getComponents()) {
        if (comp instanceof DegradadoButton) {
            DegradadoButton button = (DegradadoButton) comp;
            if (button.getText().equals(">")) {
                button.setBounds(this.getWidth() - 50, 0, 50, this.getHeight());
            } else if (button.getText().equals("<")) {
                button.setBounds(0, 0, 50, this.getHeight());
            }
        }
    }

    // Volver a mostrar la imagen actual con el nuevo tamaño
    if (!imgPaths.isEmpty()) {
        showImage(imgPaths.get(index));
    }
}
}



