package spdvi.componentimatge;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import spdvi.logica.AvancarImatge;
import spdvi.logica.NetejarImatge;
import spdvi.logica.RedimensionarImatge;
import spdvi.logica.RetrocedirImatge;
import spdvi.logica.RotarImatge;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import spdvi.logica.LogicaJMenu;

public class ImagePanelAzure extends JPanel {

    private static ImagePanel imagePanel;
    private JButton btnResize, btnClear, btnRotate, btnLeft, btnRight;
    private int currentIndex = 0; // Índice de la imagen actual
    private final ArrayList<BufferedImage> bufferedImages = new ArrayList<>();
    private BufferedImage currentImage;

    public ImagePanelAzure() {
        setLayout(new BorderLayout());

        // Cargar y establece el favicon

        // Crear el panel de la imagen
        imagePanel = new ImagePanel();
        
        LogicaJMenu.jMenus(imagePanel,this, currentImage, bufferedImages, currentIndex);

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

        // Añadir componentes al frame
        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
 
        // Crear paneles para los botones laterales
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        btnLeft = new JButton("<-");  // Botón izquierdo
        btnLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalGlue()); // Espaciado para centrar
        leftPanel.add(btnLeft);
        leftPanel.add(Box.createVerticalStrut(20)); // Separación entre botones
        leftPanel.add(Box.createVerticalGlue()); // Espaciado para centrar

        // Añadir el panel izquierdo al frame
        add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        btnRight = new JButton("->"); // Botón derecho
        btnRight.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(Box.createVerticalGlue()); // Espaciado para centrar
        rightPanel.add(btnRight);
        rightPanel.add(Box.createVerticalStrut(20)); // Separación entre botones
        rightPanel.add(Box.createVerticalGlue()); // Espaciado para centrar

        // Añadir el panel derecho al frame
        add(rightPanel, BorderLayout.EAST);

        // Establecer el JMenuBar en la ventana (JFrame)

        // Añadir funcionalidad a los botones
        //btnResize.addActionListener(e -> RedimensionarImatge.redimensionarImagen(imagePanel, this));
        btnClear.addActionListener(e -> NetejarImatge.limpiarImagen(imagePanel));
        btnRotate.addActionListener(e -> RotarImatge.rotarImagen(imagePanel));

        // Asignar las mismas acciones a los botones de los lados
        btnLeft.addActionListener(e -> AvancarImatge.mostrarSiguienteImagen(this)); // Retroceder
        btnRight.addActionListener(e -> RetrocedirImatge.mostrarImagenAnterior(this)); // Avanzar

        // Hacer que el frame escuche teclas
        setFocusable(true);
    }
    
    
    // Método para cargar el favicon
    private Image loadIconImage(String imagePath) {
        try {
            File iconFile = new File(imagePath);
            if (!iconFile.exists()) {
                System.out.println("Error: El archivo no existe en la ruta especificada: " + imagePath);
                return null;
            }
            BufferedImage iconImage = ImageIO.read(iconFile);
            if (iconImage == null) {
                System.out.println("Error: No se pudo leer la imagen. Asegúrese de que el formato sea compatible.");
            } else {
                return iconImage.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
            }
        } catch (IOException e) {
            System.out.println("Error al intentar leer la imagen: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Métodos para obtener el índice y la lista de imágenes
    public int getCurrentIndex() {
        return currentIndex;
    }

    public ArrayList<BufferedImage> getBufferedImages() {
        return bufferedImages;
    }

    // Método para actualizar el índice y la imagen
    public void setCurrentIndex(int index) {
        this.currentIndex = index;
        this.currentImage = bufferedImages.get(index);
    }

    // Método para cargar una imagen
    public void setCurrentImage(BufferedImage image) {
        this.currentImage = image;
        imagePanel.loadImage(image); // Cargar imagen en el panel
        adjustHeightToImage(image);
    }

    public void adjustHeightToImage(BufferedImage image) {
        if (image != null) {
            // Ajustar el alto del JFrame al tamaño de la imagen
            this.setSize(this.getWidth(), image.getHeight()); // Mantener el ancho y ajustar solo el alto
            this.setPreferredSize(new Dimension(this.getWidth(), image.getHeight())); // Establecer solo el alto
            this.revalidate();
            this.repaint();
        }
    }
    
     // Sobrescribe el método paintComponent para dibujar la imagen en el panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage != null) {
            g.drawImage(currentImage, 0, 0, this);  // Dibuja la imagen
        }
    }

    // Método para guardar la imagen en un archivo
    // Método para guardar la imagen en un archivo
public void saveImage(String outputPath) {
    try {
        // Verifica que la imagen no sea nula
        if (currentImage != null) {
            File outputFile = new File(outputPath);
            // Guarda la imagen en el archivo (puede ser PNG o JPG según el tipo de archivo)
            ImageIO.write(currentImage, "PNG", outputFile);  // Puedes cambiar "PNG" a "JPG" si es necesario
        } else {
            System.out.println("No hay imagen para guardar.");
        }
    } catch (IOException e) {
        e.printStackTrace();  // Maneja los errores si no se puede guardar la imagen
    }
}

    // Método para obtener la imagen que se está mostrando (esto puede variar dependiendo de cómo gestionas la imagen)
    public BufferedImage getImage() {
        // Aquí deberías obtener la imagen que se muestra en tu ImagePanelAzure
        // Este es un ejemplo genérico, tendrías que adaptarlo según cómo gestiones la imagen
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB); // Retorna una imagen de ejemplo
    }

}
