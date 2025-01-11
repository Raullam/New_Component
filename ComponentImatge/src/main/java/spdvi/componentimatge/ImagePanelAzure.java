package spdvi.componentimatge;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import spdvi.logica.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImagePanelAzure extends JPanel {
    private JFrame frame; // Referencia al JFrame principal
    private ImagePanel imagePanel;
    private JButton btnResize, btnClear, btnRotate, btnLeft, btnRight;
    private ArrayList<BufferedImage> bufferedImages = new ArrayList<>();
    private BufferedImage currentImage;
    private int currentIndex = 0;

    public ImagePanelAzure() {
        this(null); // Llama al constructor principal con un valor nulo por defecto
}
    public ImagePanelAzure(JFrame frame) {
        this.frame = frame; // Guardar la referencia al JFrame

        setLayout(new BorderLayout());

        // Crear el panel de la imagen
        imagePanel = new ImagePanel();
        LogicaJMenu.jMenus(imagePanel, frame, currentImage, bufferedImages, currentIndex);

        // Crear los botones
        btnResize = new JButton("Redimensionar");
        btnClear = new JButton("Limpiar Imagen");
        btnRotate = new JButton("Rotar Imagen");

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btnResize);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnRotate);

        // Añadir componentes al panel principal
        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Crear paneles para los botones laterales
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        btnLeft = new JButton("<-");
        btnLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(btnLeft);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(Box.createVerticalGlue());
        add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        btnRight = new JButton("->");
        btnRight.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(btnRight);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(Box.createVerticalGlue());
        add(rightPanel, BorderLayout.EAST);

        // Añadir funcionalidad a los botones
        btnResize.addActionListener(e -> RedimensionarImatge.redimensionarImagen(imagePanel, null));
        btnClear.addActionListener(e -> NetejarImatge.limpiarImagen(imagePanel));
        btnRotate.addActionListener(e -> RotarImatge.rotarImagen(imagePanel));
        btnLeft.addActionListener(e -> AvancarImatge.mostrarSiguienteImagen(null));
        btnRight.addActionListener(e -> RetrocedirImatge.mostrarImagenAnterior(null));
    }

    // Métodos adicionales para manejar imágenes
    public void setCurrentImage(BufferedImage image) {
        this.currentImage = image;
        imagePanel.loadImage(image); // Cargar imagen en el panel
    }

    public void setCurrentIndex(int index) {
        this.currentIndex = index;
        this.currentImage = bufferedImages.get(index);
        setCurrentImage(currentImage);
    }

    public ArrayList<BufferedImage> getBufferedImages() {
        return bufferedImages;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void addImage(BufferedImage image) {
        bufferedImages.add(image);
    }
}
