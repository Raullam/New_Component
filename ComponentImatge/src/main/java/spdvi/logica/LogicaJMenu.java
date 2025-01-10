package spdvi.logica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import spdvi.componentimatge.ImagePanel;
import spdvi.componentimatge.ImagePanelAzure;
import spdvi.serveis.AzureBlobService;

/**
 *
 * @author Rulox
 */
public class LogicaJMenu {
        private static final ArrayList<String> imagePaths = new ArrayList<>(); // pasar a la clase logicaJMenu
        private static final String connectionString = "DefaultEndpointsProtocol=https;AccountName=alejandrostorage1;AccountKey=lE5g6+hiDokS8nYgZ9RGcXexPo6wqGWMrho4IiKYEU+9CAJysciPs2q+VHDsoWQ41bfFMAcCmG+h+ASto4i3KQ==;EndpointSuffix=core.windows.net"; // pasar a la clase logicaJMenu
        private static final AzureBlobService blobService = new AzureBlobService(connectionString); // pasar a LogicaJMenu
        private static final String containerName = "images"; //pasar a logicaJMenu



    public static void jMenus(ImagePanel imagePanel, JFrame thiss, BufferedImage currentImage, ArrayList<BufferedImage> bufferedImages, int currentIndex) {
        // Crear un JMenuBar
        JMenuBar menuBar = new JMenuBar();

        // Crear un JMenu para la navegación
        JMenu navigationMenu = new JMenu("Navegació");
        JMenu navigationMenu2 = new JMenu("Sobre noltros");

        JMenuItem coneixnos = new JMenuItem("Conocenos!");
        coneixnos.addActionListener(e -> showAboutUs(thiss)); // Acción para mostrar la información en el mismo frame
        navigationMenu2.add(coneixnos);

        // Crear el submenú debajo de "Carregar Imatge pc"
        JMenu subMenuCarregar = new JMenu("Carregar");
        JMenuItem carregarImatgeLocal = new JMenuItem("Imatge local");
        JMenuItem carregarImatgeAzure = new JMenuItem("Imatge d'Azure");
        JMenuItem carregarGaleriaLocal = new JMenuItem("Galeria local");
        JMenuItem carregarGaleriaAzure = new JMenuItem("Galeria d'Azure");

        // Añadir las sub-opciones al submenú
        subMenuCarregar.add(carregarImatgeLocal);
        subMenuCarregar.add(carregarImatgeAzure);
        subMenuCarregar.add(carregarGaleriaLocal);
        subMenuCarregar.add(carregarGaleriaAzure);

        JMenu subMenuGuardar = new JMenu("Guardar");
        JMenuItem guardarImatgeLocal = new JMenuItem("Imatge local");
        JMenuItem guardarImatgeAzure = new JMenuItem("Imatge d'Azure");
        JMenuItem guardarGaleriaLocal = new JMenuItem("Galeria local");
        JMenuItem guardarGaleriaAzure = new JMenuItem("Galeria d'Azure");

        subMenuGuardar.add(guardarImatgeLocal);
        subMenuGuardar.add(guardarImatgeAzure);
        subMenuGuardar.add(guardarGaleriaLocal);
        subMenuGuardar.add(guardarGaleriaAzure);

        menuBar.add(navigationMenu);
        menuBar.add(navigationMenu2);

        //
        // Agregar los JMenuItems al JMenu
        navigationMenu.add(subMenuCarregar); // Añadir el submenú debajo de "Carregar Imatge pc"
        navigationMenu.add(subMenuGuardar); // Añadir el submenú debajo de "Carregar Imatge pc"

        menuBar.add(navigationMenu);
        menuBar.add(navigationMenu2);

        // Establecer el JMenuBar en la ventana (JFrame)
        thiss.setJMenuBar(menuBar);

        // Asignar acciones a los elementos del menú
        guardarImatgeLocal.addActionListener(e -> GuardarImatge.guardarImagenPC(imagePanel, thiss));
        guardarImatgeAzure.addActionListener(e -> GuardarImatgeAzure.selectAndSaveImage(currentImage, blobService, containerName));
        carregarGaleriaAzure.addActionListener(e -> CarregarImatge.cargarImagen(containerName, connectionString, bufferedImages, imagePaths, currentIndex, imagePanel));

    }

    // Método para mostrar la información "Conocenos!" en el mismo JFrame
    private static void showAboutUs(JFrame thiss) {
        // Crear un panel para mostrar la información
        JPanel aboutUsPanel = new JPanel();
        aboutUsPanel.setLayout(new BoxLayout(aboutUsPanel, BoxLayout.Y_AXIS));

        // Cargar el icono que se usó de favicon
        Image iconImage = loadIconImage("src/main/resources/imatges/2.png");
        Image logoPau = loadIconImage("src/main/resources/imatges/paucasesnoves.png");

        // Crear el JLabel con el icono centrado
        JLabel iconLabel = new JLabel(new ImageIcon(iconImage));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado
        JLabel nomApp = new JLabel("Adobat Photoshop");
        nomApp.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado

        // Crear el JLabel con los nombres de los desarrolladores
        JLabel developersLabel = new JLabel("Desenvolupadors:");
        developersLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado

        // Crear un JLabel con los nombres de los desarrolladores
        JLabel dev1 = new JLabel("Alejandro Player");
        dev1.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado
        // Crear un JLabel con los nombres de los desarrolladores
        JLabel dev2 = new JLabel("Magi Aristondo");
        dev2.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado
        // Crear un JLabel con los nombres de los desarrolladores
        JLabel dev3 = new JLabel("Joan Miquel");
        dev3.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado
        // Crear un JLabel con los nombres de los desarrolladores
        JLabel dev4 = new JLabel("Raül Lama");
        dev4.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado
        JLabel cifp = new JLabel("CIFP PAU CASESNOVES INCA, MALLORCA.");
        cifp.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado

        // Crear un botón debajo del icono CIFP
        JButton button = new JButton("Tornar al panell d'edició");
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado

        // Agregar un ActionListener al botón si es necesario
        button.addActionListener(e -> goBackToImageEditingPanel(thiss));

// Redimensionar la imagen a un tamaño más pequeño (ejemplo: 50x50 píxeles)
        Image scaledImage = logoPau.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Ajustar el tamaño

// Crear el JLabel con la imagen redimensionada
        JLabel iconLabel2 = new JLabel(new ImageIcon(scaledImage));
        iconLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centrado de la imagen
// Centrado

        // Añadir todo al panel
        aboutUsPanel.add(Box.createVerticalGlue());
        aboutUsPanel.add(iconLabel);
        aboutUsPanel.add(nomApp);

        aboutUsPanel.add(Box.createVerticalStrut(20)); // Espaciado
        aboutUsPanel.add(developersLabel);
        aboutUsPanel.add(Box.createVerticalStrut(20)); // Espaciado

        aboutUsPanel.add(dev1);
        aboutUsPanel.add(dev2);
        aboutUsPanel.add(dev3);
        aboutUsPanel.add(dev4);
        aboutUsPanel.add(Box.createVerticalStrut(20)); // Espaciado

        aboutUsPanel.add(cifp);

        aboutUsPanel.add(iconLabel2);
        aboutUsPanel.add(Box.createVerticalGlue());
        aboutUsPanel.add(button); // Agregar el botón aquí

        // Reemplazar el contenido actual del JFrame con el panel de "Sobre Nosotros"
        thiss.getContentPane().removeAll(); // Limpiar el contenido actual
        thiss.getContentPane().add(aboutUsPanel, BorderLayout.CENTER); // Añadir el panel "Sobre Nosotros"
        thiss.revalidate(); // Revalidar el JFrame para aplicar los cambios
        thiss.repaint(); // Redibujar el JFrame
    }

    // Método para cargar el favicon
    private static Image loadIconImage(String imagePath) {
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

    private static void goBackToImageEditingPanel(JFrame thiss) {
        // Crear un nuevo ImagePanelAzure, que puede ser la ventana principal de edición
        ImagePanelAzure newImagePanel = new ImagePanelAzure(thiss);

        // Cerrar el panel actual (sobre nosotros) y abrir el panel de edición
        newImagePanel.setVisible(true);
        thiss.setVisible(false);  // Ocultar la ventana actual
        thiss.dispose();  // Liberar recursos de la ventana anterior
    }
}
