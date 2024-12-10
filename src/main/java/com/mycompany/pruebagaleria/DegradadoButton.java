package com.mycompany.pruebagaleria;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;

class DegradadoButton extends JButton {

    private Color startColor;
    private Color endColor;
    private boolean botonVisible = false;

    public DegradadoButton(String text, Color startColor, Color endColor) {
        super(text);
        this.startColor = startColor;
        this.endColor = endColor;
        setOpaque(false); // Para habilitar el fondo personalizado
        setContentAreaFilled(false);
        setBorderPainted(false); // Elimina el borde por defecto
        setForeground(new Color(0, 0, 0, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (botonVisible) {
            Graphics2D g2d = (Graphics2D) g.create();
            // Crear el degradado
            GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), 0, endColor);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
        // Llamar al método original para pintar el texto
        super.paintComponent(g);
    }

    public void setVisibleOnHover(boolean visible) {
        botonVisible = visible;
        repaint();
    }
}
