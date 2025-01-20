package org.marcosjfx.marcosfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * La clase JavaDocView proporciona la funcionalidad para mostrar la documentación
 * generada por Javadoc dentro de un componente WebView de JavaFX. Permite al usuario
 * navegar a la documentación o regresar a la vista principal de la aplicación.
 */
public class JavaDocView {

    /**
     * Componente WebView que se utiliza para cargar y mostrar el contenido HTML de la documentación Javadoc.
     */
    public WebView webView;

    /**
     * Botón que permite regresar a la vista principal de la aplicación.
     */
    public Button goHomeButton;

    /**
     * Constructor de la clase JavaDocView.
     * Inicializa una nueva instancia del controlador que gestiona la visualización
     * de la documentación JavaDoc en un componente WebView.
     */
    public JavaDocView() {
    }

    /**
     * Este método se ejecuta automáticamente al inicializar la vista. Se utiliza para cargar
     * la página principal de la documentación de Javadoc en el WebView. Si el archivo de
     * documentación no existe, muestra un mensaje de error.
     */
    @FXML
    public void initialize() {
        WebEngine webEngine = webView.getEngine();
        File javadocFile = new File("src/main/resources/javadoc/index.html");
        if (javadocFile.exists()) {
            webEngine.load(javadocFile.toURI().toString());
        } else {
            webEngine.loadContent("<h1>¡Error!</h1><p>Javadoc no encontrado.</p>");
        }
    }

    /**
     * Cambia la vista actual a la vista principal de la aplicación.
     *
     * @param event el evento que dispara el cambio de vista.
     * @throws IOException si ocurre un error al cargar el archivo FXML de la vista principal.
     */
    public void switchToMainView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 800);
        stage.setScene(scene);
        stage.show();
    }

}
