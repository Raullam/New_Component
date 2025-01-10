/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import spdvi.componentimatge.ImagePanelAzure;

public class Main {
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        // Crear el JFrame principal
        JFrame frame = new JFrame("Adobat Photoshop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia de ImagePanelAzure con el frame principal
        ImagePanelAzure imagePanelAzure = new ImagePanelAzure(frame);
        frame.add(imagePanelAzure);

        // Configurar el tamaño y mostrar el JFrame
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    });
    }
}


