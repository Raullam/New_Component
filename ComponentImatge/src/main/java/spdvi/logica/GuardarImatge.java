/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.logica;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import spdvi.componentimatge.ImagePanel;
import spdvi.componentimatge.ImagePanelAzure;

/**
 *
 * @author Rulox
 */
public class GuardarImatge {
    public static void guardarImagenPC(ImagePanelAzure imagePanelAzure, JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
    // Filtra solo archivos PNG y JPG
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes PNG y JPG", "png", "jpg");
    fileChooser.setFileFilter(filter);

    int result = fileChooser.showSaveDialog(frame);
    if (result == JFileChooser.APPROVE_OPTION) {
        String outputPath = fileChooser.getSelectedFile().getAbsolutePath();

        // Asegúrate de que el archivo tenga la extensión correcta
        if (!outputPath.endsWith(".png") && !outputPath.endsWith(".jpg")) {
            if (outputPath.endsWith(".jpeg")) {
                outputPath = outputPath.substring(0, outputPath.lastIndexOf('.')) + ".jpg";
            } else {
                outputPath += ".png"; // Por defecto se guarda como PNG
            }
        }

        imagePanelAzure.saveImage(outputPath);

    }
    }
    
}
