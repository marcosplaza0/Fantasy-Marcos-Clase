package org.marcosjfx.marcosfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Clase principal de la aplicación que extiende {@link Application} y sirve como punto de entrada para la interfaz gráfica.
 * <p>
 * Gestiona el inicio de la ventana principal y configura los elementos primarios de la escena de JavaFX.
 */
public class Main extends Application {

    /**
     * Método principal que se ejecuta al iniciar la aplicación JavaFX.
     *
     * @param stage El escenario principal proporcionado por JavaFX donde se monta la interfaz gráfica.
     * @throws RuntimeException Sí ocurre algún error durante la carga del archivo FXML.
     */
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
            Scene scene = new Scene(root, 1300, 800);

            stage.setTitle("Marcos Plaza Piqueras Fantasy");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
