package org.marcosjfx.marcosfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.marcosjfx.marcosfx.backend.DataBase;
import org.marcosjfx.marcosfx.backend.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Clase que representa la vista del mercado en la aplicación.
 * Esta clase actúa como controlador para la interfaz de mercado, gestionando las operaciones relacionadas
 * con la visualización y administración del mercado, como el manejo de jugadores, la actualización de presupuesto,
 * y la navegación entre diferentes vistas.
 * <p>
 * Esta clase utiliza JavaFX para construir la interfaz gráfica del usuario.
 */
public class MarketView implements Initializable {

    /**
     * Botón para regresar a la vista principal de la aplicación.
     */
    public Button GoMainView;

    /**
     * Etiqueta que actúa como el título principal para la vista del mercado.
     */
    public Label MarketTitle;

    /**
     * Panel utilizado para presentar los datos en la vista del mercado.
     */
    public AnchorPane DataPane;

    /**
     * Panel de desplazamiento que contiene la lista de jugadores disponibles en el mercado.
     */
    public ScrollPane PlayersPane;

    /**
     * Contenedor vertical que aloja las tarjetas de jugador en la vista del mercado.
     */
    public VBox PlayersVBox;

    /**
     * Etiqueta que muestra el presupuesto actual disponible en la vista del mercado.
     */
    public Label BudgetLabel;

    /**
     * Etiqueta que muestra mensajes o notificaciones dentro de la interfaz del mercado.
     */
    public Label MessageLabel;

    /**
     * Constructor vacío para la clase MarketView.
     * Inicializa un controlador de vista para la interfaz gráfica del mercado,
     * sin parámetros adicionales.
     */
    public MarketView() {}

    /**
     * Método inicial que se ejecuta al cargar la vista del mercado.
     * Aquí se inicializan y configuran los elementos dinámicos de la vista, como
     * la lista de jugadores cargados desde la base de datos.
     *
     * @param url            URL de la ubicación del archivo FXML.
     * @param resourceBundle Recursos utilizados para la internacionalización de la vista.
     */
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        try {
            
            DataBase dataBase = new DataBase();
            HashMap<Integer, Player> playerHashMap = dataBase.getInformation();

            for (Integer playerId : playerHashMap.keySet()) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("PlayerCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                PlayerCard playerCard = fxmlLoader.getController();

                playerCard.setInformation(playerHashMap.get(playerId), this);
                PlayersVBox.getChildren().add(anchorPane);
                updateBudgetLabel();
            }
        } catch (IOException e) {
            System.out.printf("Error %s\n", e.getMessage());
        }
    }


    /**
     * Cambia la vista actual a la vista principal.
     *
     * @param event El evento de acción que dispara esta transición, como un clic de botón.
     * @throws IOException Excepción que puede ocurrir si el archivo FXML no se encuentra o no se puede cargar.
     */
    public void switchToMainView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 800);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Actualiza el texto de la etiqueta relacionada con el presupuesto actual
     * en función de los datos recuperados de la base de datos.
     */
    public void updateBudgetLabel() {
        DataBase dataBase = new DataBase();
        BudgetLabel.setText(String.format("€%,.2f", (double) dataBase.getPurchaseBudget()));
    }

}

