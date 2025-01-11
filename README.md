# Component de Visualització i Manipulació d'Imatges amb Azure Blob Storage

## Introducció

Aquest projecte consisteix en un component simple en Java Swing que permet visualitzar, manipular i gestionar imatges, així com carregar-les a un servei d'emmagatzematge al núvol com **Azure Blob Storage**. La funcionalitat principal inclou carregar imatges des del sistema de fitxers, redimensionar-les, rotar-les, guardar-les, navegar entre elles i pujar-les a Azure. A més, la interfície permet interactuar amb les imatges mitjançant botons gràfics i atalls de teclat.

## Part 1: Disseny del Projecte

L'objectiu inicial era crear una aplicació senzilla per gestionar imatges, utilitzant la biblioteca **Swing** de Java per a la interfície gràfica. La nostra intenció era permetre als usuaris carregar imatges des del seu dispositiu, redimensionar-les, rotar-les i pujar-les a un servei de núvol com **Azure**.

### Problemes Inicials: Ús de `JFrame` i `JPanel`

Inicialment vam començar utilitzant **JFrame**, però ens vam adonar que per poder tractar l'element com un component dins d'un sistema Java, aquest hauria de ser un objecte de tipus **JComponent**, i el **JFrame** no compleix amb aquesta condició. Així que vam adaptar el nostre projecte per utilitzar **JPanel**, millorant la modularitat i flexibilitat.

## Part 2: Funcionalitats de l'Aplicació

### Càrrega d'Imatges

Una de les funcionalitats més importants és la càrrega d'imatges. Inicialment, es carregaven imatges manualment utilitzant una ruta fixa. Pero per millorar l'experiència de l'usuari, vam implementar un **JFileChooser** que permet seleccionar múltiples imatges des del sistema de fitxers. Aquestes imatges es guarden en una llista `imagePaths` i es mostren mitjançant el component **ImagePanel**.

### Navegació entre Imatges

Es va afegir la funcionalitat de **navegar entre imatges** mitjançant els botons "Següent" i "Anterior". Això es gestiona amb la variable `currentIndex`, que manté un índex de la imatge actual. També s'han implementat atalls de teclat per navegar entre les imatges amb les fletxes esquerra i dreta.

### Redimensionament i Rotació

L'usuari pot **redimensionar** i **rotar** les imatges. El redimensionament es fa mitjançant un quadre de diàleg on es poden introduir les noves dimensions (ample i altura). La rotació es limita a **90 graus** i es realitza mitjançant un mètode senzill.

### Pujar Imatges a Azure Blob Storage

Un dels aspectes més innovadors del projecte és la capacitat de pujar imatges a **Azure Blob Storage**. Després de carregar una imatge des del sistema de fitxers, l'usuari pot pujar-la a un contenidor de blobs a Azure mitjançant el servei **AzureBlobService**. Utilitzem el **BlobServiceClient** de Azure per carregar els bytes de la imatge i emmagatzemar-los al núvol.

### Funcionalitat de Teclat

Per millorar la interacció de l'usuari, es van afegir els següents atalls de teclat:

- **L**: Carregar una imatge.
- **R**: Redimensionar la imatge.
- **C**: Netejar la imatge.
- **T**: Rotar la imatge.
- **S**: Desar la imatge.
- **Fletxa dreta**: Mostrar la següent imatge.
- **Fletxa esquerra**: Mostrar la imatge anterior.

Aquests atalls es gestionen mitjançant un **KeyAdapter** que escolta els esdeveniments del teclat i executa les accions corresponents.

## Part 3: Problemes i Solucions

### Gestió de Components en Swing

Una dificultat important va ser organitzar els components dins de la finestra. Inicialment utilitzàvem **JFrame**, però vam decidir dividir la interfície en **JPanel** específics per millorar la modularitat i reutilització del codi. Això va millorar l'estructura de la interfície, però va requerir ajustaments en la comunicació entre els components.

### Integració amb Azure Blob Storage

Integrar l'aplicació amb **Azure Blob Storage** va ser un repte. Vam configurar el servei mitjançant el **BlobServiceClient** de Azure i vam haver d'adaptar la càrrega i pujada d'imatges per utilitzar les API d'Azure Storage Blob. A més, vam gestionar la pujada d'imatges amb **ByteArrayInputStream** per convertir les imatges en bytes abans de pujar-les.

### Redimensionament i Rotació d'Imatges

El redimensionament i la rotació d'imatges va ser una tasca complicada, ja que Java no té una manera directa d'implementar-ho de forma eficient. Vam utilitzar **BufferedImage** i **Graphics2D** per gestionar aquests processos. El redimensionament es realitza amb el mètode **getScaledInstance**, i la rotació es fa amb **AffineTransform** per manipular l'angle d'inclinació.

## Conclusió

Aquest projecte ens ha permès aprendre i aplicar diversos conceptes de **Java**, com la creació d'interfícies gràfiques amb **Swing**, la manipulació d'imatges i la integració amb serveis al núvol com **Azure Blob Storage**. Tot i els reptes durant el desenvolupament, vam aconseguir resoldre'ls mitjançant investigació i proves, obtenint una aplicació funcional.

En el futur, seria interessant expandir la funcionalitat per integrar altres serveis al núvol o afegir operacions avançades de manipulació d'imatges, com l'aplicació de filtres o efectes especials.


Contribucions 🤝
Si vols contribuir a aquest projecte, fes un fork i crea un pull request amb els teus canvis. Assegura't de seguir l'estil de codi establert i proporcionar una descripció clara dels canvis realitzats.

Llicència 📝
Aquest projecte està licenciat sota la Llicència MIT. Consulta el fitxer LICENSE per obtenir més informació.






# 📷 Component de Visualització d'Imatges en Java

Aquest component de Java proporciona una interfície gràfica per gestionar i visualitzar imatges. Permet carregar, redimensionar, rotar, guardar i navegar entre diverses imatges dins d'un panell personalitzat. També inclou funcionalitats per interactuar amb un servei de **Azure Blob Storage** per a pujar imatges.

## Característiques

### **🖼️ ImagePanel**
`ImagePanel` és un panell personalitzat que permet:
- **📂 Carregar imatges** des d'una ruta local.
- **🔧 Redimensionar imatges** mantenint la proporció original.
- **🔄 Rotar imatges** en increments de 90 graus.
- **💾 Guardar imatges** en format PNG a una ruta especificada.
- **🧹 Eliminar imatges** i netejar el panell.

### **☁️ AzureBlobService**
`AzureBlobService` permet la càrrega d'imatges a **Azure Blob Storage**. Proporciona un mètode per pujar imatges a un contenidor de blobs a Azure.

### **🖥️ ImageViewerArray**
`ImageViewerArray` és el visor de galeria d'imatges, que permet:
- **📂 Càrrega múltiple d'imatges** des d'un selector de fitxers.
- **⏩ Navegar entre imatges** amb botons per avançar o retrocedir.
- **🔧 Redimensionar imatges** introduint manualment les noves dimensions.
- **🔄 Rotar imatges** en increments de 90 graus.
- **💾 Guardar imatges** a la ubicació especificada.
- **🧹 Eliminar imatges** carregades.

## Dependències

Aquest component utilitza les següents dependències:
- **Azure Storage SDK** per a la càrrega d'imatges a Azure Blob Storage:
  - `com.azure.storage.blob:azure-storage-blob`
  
- **Java Swing** per a la creació de la interfície gràfica.
- **Java AWT** per a la manipulació d'imatges.

## Ús

### 1. **Crear un objecte de `AzureBlobService`**
   Necessites proporcionar una cadena de connexió d'Azure Storage per utilitzar el servei de pujada de blobs.
    
   
   ```java
   AzureBlobService azureBlobService = new AzureBlobService("<your_connection_string>");
   ```
2. Carregar i visualitzar imatges
Pots carregar imatges utilitzant el botó "Cargar Imagen" a través d'un selector de fitxers.
Navega entre les imatges amb els botons "Adelante" i "Atrás".
```java
imagePanel.loadImage("/path/to/your/image.jpg");
```
3. Manipular imatges
Redimensiona les imatges amb el botó "Redimensionar", indicant l'amplada i alçada desitjades.
Rota les imatges en increments de 90 graus amb el botó "Rotar Imagen".
```java
imagePanel.resizeImage(800, 600); // Redimensionar a 800x600 píxels
imagePanel.rotateImage(90); // Rota la imatge 90 graus
```
4. Guardar imatges
Desa la imatge actual utilitzant el botó "Guardar Imagen".

```java
JFileChooser fileChooser = new JFileChooser();
int result = fileChooser.showSaveDialog(this);
if (result == JFileChooser.APPROVE_OPTION) {
    String outputPath = fileChooser.getSelectedFile().getAbsolutePath();
    imagePanel.saveImage(outputPath);
}
```
5. Càrrega d'imatges a Azure
Puja imatges a Azure Blob Storage cridant el mètode uploadBlob de AzureBlobService amb els bytes de la imatge.
```java
File imageFile = new File("/path/to/image.jpg");
byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
azureBlobService.uploadBlob("your-container-name", "image.jpg", imageBytes);
```
Botons interactius
A continuació es detallen les accions associades als botons:

📂 Cargar Imagen:	Carregar una imatge des del sistema de fitxers.	📂

🔧 Redimensionar:	Redimensionar la imatge a dimensions personalitzades.	🔧

🧹 Limpiar Imagen:	Netejar la imatge carregada i restablir el panell.	🧹

🔄 Rotar Imagen	:Rotar la imatge 90 graus.	🔄

💾 Guardar Imagen:	Desar la imatge en el sistema de fitxers.	💾

⏩ Adelante:	Passar a la següent imatge de la galeria.	⏩

⏪ Atrás:	Tornar a la imatge anterior de la galeria.	⏪

Exemples de codi
Exemple per carregar una imatge i mostrar-la en el panell:
```java
imagePanel.loadImage("/path/to/your/image.jpg");
```
Exemple per redimensionar la imatge:

```java
imagePanel.resizeImage(800, 600); // Redimensionar a 800x600 píxels
```
Exemple per rotar la imatge:
```java
imagePanel.rotateImage(90); // Rota la imatge 90 graus
```
Exemple per pujar una imatge a Azure Blob Storage:
```java
AzureBlobService azureBlobService = new AzureBlobService("<your_connection_string>");
File imageFile = new File("/path/to/image.jpg");
byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
azureBlobService.uploadBlob("your-container-name", "image.jpg", imageBytes);
```
Com començar
Clona el repositori o descarrega el codi.
Afegix les dependències d'Azure Storage al teu projecte (mitjançant Maven o Gradle).
Crea un objecte AzureBlobService amb la teva cadena de connexió d'Azure.
Afegeix el component a la teva aplicació Java i comença a interactuar amb les imatges.
