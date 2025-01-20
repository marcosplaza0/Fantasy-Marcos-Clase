/**
 * Módulo principal para la aplicación MarcosFX.
 * <p>
 * Este módulo define las dependencias y exportaciones necesarias para
 * la ejecución de la aplicación MarcosFX, una aplicación basada en JavaFX
 * que utiliza múltiples bibliotecas para controlar y gestionar la interfaz
 * gráfica, incluir gráficos, gestionar datos e interactuar con recursos externos.
 * </p>
 * <p>
 * Dependencias utilizadas:
 * <ul>
 *   <li><b>javafx.controls</b>: Biblioteca para controles y componentes UI.</li>
 *   <li><b>javafx.fxml</b>: Para el manejo y carga de archivos FXML.</li>
 *   <li><b>javafx.web</b>: Componente para integración con contenido web.</li>
 *   <li><b>org.controlsfx.controls</b>: Controles adicionales para JavaFX.</li>
 *   <li><b>com.dlsc.formsfx</b>: Manejo de formularios interactivos.</li>
 *   <li><b>net.synedra.validatorfx</b>: Validación de entradas de usuario.</li>
 *   <li><b>org.kordamp.ikonli.javafx</b>: Íconos personalizables.</li>
 *   <li><b>org.kordamp.bootstrapfx.core</b>: Para estilos y diseño de tipo Bootstrap en JavaFX.</li>
 *   <li><b>eu.hansolo.tilesfx</b>: Bibliotecas para la visualización basada en tableros (dashboards).</li>
 *   <li><b>com.almasb.fxgl.all</b>: Marco de trabajo para desarrollo de videojuegos.</li>
 *   <li><b>java.net.http</b>: Utilizado para gestión de solicitudes web HTTP y conexiones.</li>
 *   <li><b>com.fasterxml.jackson.databind</b>: Procesamiento de datos en formato JSON.</li>
 * </ul>
 * <p>
 * Paquetes principales:
 * <ul>
 *   <li><b>org.marcosjfx.marcosfx</b>: Contiene las clases principales del programa.</li>
 * </ul>
 */
module org.marcosjfx.marcosfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens org.marcosjfx.marcosfx to javafx.fxml;
    exports org.marcosjfx.marcosfx;
}