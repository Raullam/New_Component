/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package spdvi;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JMenuItem;
import spdvi.logica.LogicaJMenu;  // Importar la clase LogicaJMenu
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import spdvi.logica.LogicaJMenu;
import spdvi.componentimatge.ImagePanelAzure;
import spdvi.logica.GuardarImatge;
import spdvi.logica.GuardarImatgeAzure;
import spdvi.serveis.AzureBlobService;
import spdvi.componentimatge.ImagePanel

// Importar la clase LogicaJMenu
/**
 *
 * @author 34666
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    private LogicaJMenu logic;

    public NewJFrame() {
        initComponents();
        setIconImage(loadIconImage("src/main/resources/imatges/2.png"));
        jMenus(this);  // Pasa el JFrame actual a la lógica

        GuardarImatgeAzure.addActionListener(e -> {
            // Aquí usas currentImage
        });
    

    }
    
     public void jMenus(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        // Crear un JMenu para la navegación
        JMenu navigationMenu = new JMenu("Navegació");
        JMenu navigationMenu2 = new JMenu("Sobre noltros");
        JMenu navigationMenu3 = new JMenu("LookAndFeel");

        // SOBRE NOLTROS
        JMenuItem coneixnos = new JMenuItem("Coneix-nos!");
        coneixnos.addActionListener(e -> showAboutUs(frame)); // Acción para mostrar la información en el mismo frame
        navigationMenu2.add(coneixnos);

        // LOOK AND FEEL
        for (String theme : logic.temes) {
    JMenuItem laf = new JMenuItem(theme);
    laf.addActionListener(e -> {
        // Suponiendo que setLAF necesita un JPanel, obtenemos el JPanel del JFrame
        JPanel panel = (JPanel) frame.getContentPane();
        logic.lookNfeel.setLAF(theme, panel);
        SwingUtilities.updateComponentTreeUI(frame); // Actualiza el árbol de componentes
    });
    navigationMenu3.add(laf);
}

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

        // Agregar los JMenuItems al JMenu
        navigationMenu.add(subMenuCarregar); // Añadir el submenú debajo de "Carregar Imatge pc"
        navigationMenu.add(subMenuGuardar); // Añadir el submenú debajo de "Carregar Imatge pc"

        menuBar.add(navigationMenu);    // Navegació
        menuBar.add(navigationMenu2);   // Sobre noltros
        menuBar.add(navigationMenu3);   // Look and Feel

        // Establecer el JMenuBar en el JFrame
        frame.setJMenuBar(menuBar);

        // Asignar acciones a los elementos del menú


        guardarImatgeLocal.addActionListener(e -> GuardarImatge.guardarImagenPC(imagePanelAzure1, frame));
        guardarImatgeAzure.addActionListener(e -> GuardarImatgeAzure.selectAndSaveImage(null, null, null));
        //         guardarImatgeAzure.addActionListener(e -> GuardarImatgeAzure.selectAndSaveImage(currentImage, blobService, containerName));

        //carregarGaleriaAzure.addActionListener(e -> CarregarImatge.cargarImagen(containerName, connectionString, bufferedImages, imagePaths, currentIndex, imagePanelAzure1));
    }
     
      // Método para mostrar la información "Conocenos!" en el mismo JFrame
    public static void showAboutUs(JFrame frame) {
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
        JLabel dev2 = new JLabel("Magi Aristondo");
        dev2.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado
        JLabel dev3 = new JLabel("Joan Miquel");
        dev3.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado
        JLabel dev4 = new JLabel("Raül Lama");
        dev4.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado
        JLabel cifp = new JLabel("CIFP PAU CASESNOVES INCA, MALLORCA.");
        cifp.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado

        // Crear un botón debajo del icono CIFP
        JButton button = new JButton("Tornar al panell d'edició");
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado

        // Agregar un ActionListener al botón si es necesario
        button.addActionListener(e -> goBackToImageEditingPanel(frame));

        // Redimensionar la imagen a un tamaño más pequeño (ejemplo: 50x50 píxeles)
        Image scaledImage = logoPau.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Ajustar el tamaño

        // Crear el JLabel con la imagen redimensionada
        JLabel iconLabel2 = new JLabel(new ImageIcon(scaledImage));
        iconLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centrado de la imagen

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
        frame.getContentPane().removeAll(); // Limpiar el contenido actual
        frame.getContentPane().add(aboutUsPanel, BorderLayout.CENTER); // Añadir el panel "Sobre Nosotros"
        frame.revalidate(); // Revalidar el JFrame para aplicar los cambios
        frame.repaint(); // Redibujar el JFrame
    }

    private static void goBackToImageEditingPanel(JFrame frame) {
        // Crear un nuevo ImagePanelAzure, que puede ser la ventana principal de edición
        ImagePanelAzure newImagePanel = new ImagePanelAzure();
        frame.setVisible(false);  // Ocultar la ventana actual
        newImagePanel.setVisible(true);  // Mostrar la nueva ventana
    }
    public static Image loadIconImage(String imagePath) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    public class GuardarImatgeAzure {
    
    public static void selectAndSaveImage(BufferedImage currentImage,AzureBlobService blobService, String containerName) {
        // Abrir el JFileChooser para seleccionar la imagen
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona una imagen");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes JPG & PNG", "jpg", "png"));
        
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Cargar la imagen seleccionada
                currentImage = ImageIO.read(selectedFile);

                // Obtener el nombre del archivo para usarlo como el blobName
                String fileName = selectedFile.getName(); // Nombre del archivo, como "imagen.jpg"
                String blobName = fileName; // Usar el nombre del archivo como blobName
                // O si prefieres asegurarte de que el nombre sea único, puedes agregar un prefijo único
                // String blobName = UUID.randomUUID().toString() + "_" + fileName;

                // Subir la imagen a Azure Blob Storage
                byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());  // Leer los bytes del archivo seleccionado
                blobService.uploadBlob(containerName, blobName, imageBytes);  // Subir los bytes al blob

                JOptionPane.showMessageDialog(null, "Imagen subida correctamente");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar o subir la imagen: " + e.getMessage());
            }
        }
    }
    
    
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
