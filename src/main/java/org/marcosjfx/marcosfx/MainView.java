package org.marcosjfx.marcosfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import java.util.Objects;

/**
 * La clase MainView actúa como controlador para la interfaz principal de la aplicación.
 * Gestiona la navegación entre vistas, la visualización de gráficos de barras y otras interacciones de usuario.
 */
public class MainView implements Initializable {
    /**
     * Constructor de la clase MainView.
     * Inicializa una nueva instancia del controlador principal.
     */
    public MainView() {
    }

    /**
     * Contenedor principal que representa el panel de inicio de la aplicación.
     */
    public AnchorPane HomePane;

    /**
     * Contenedor asociado al panel de equipo de la aplicación.
     */
    public AnchorPane TeamPane;

    /**
     * Imagen mostrada en el banner superior de la aplicación.
     */
    public ImageView bannerImage;

    /**
     * Botón para acceder a la vista del mercado.
     */
    public Button MarketGoButton;

    /**
     * Gráfico de barras que muestra información sobre los precios de equipos.
     */
    public BarChart clubsBarChart;

    /**
     * Botón para acceder a la vista de la documentación JavaDoc.
     */
    public Button JavaDocGoButton;

    /**
     * Método que inicializa los elementos del controlador.
     *
     * @param url            URL utilizada para localizar recursos.
     * @param resourceBundle Conjunto de recursos internacionalizados.
     */
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        addInfoClubsBarChart();
    }

    /**
     * Cambia a la vista del mercado cargando el archivo FXML correspondiente.
     *
     * @param event Evento de acción que activa el cambio de vista.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    public void switchToMarketView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MarketView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 800);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cambia a la vista de documentación JavaDoc cargando el archivo FXML correspondiente.
     *
     * @param event Evento de acción que activa el cambio de vista.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    public void switchToJavaDocView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("JavaDocView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 800);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Carga información desde un archivo CSV y la utiliza para poblar el gráfico de barras con datos
     * sobre precios de equipos.
     */
    public void addInfoClubsBarChart() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/database/players.csv"))) {
            Map<String, Integer> pricesByTeam = new HashMap<>();
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                String team = columns[6].trim();
                int price = Integer.parseInt(columns[3].trim());
                pricesByTeam.put(team, pricesByTeam.getOrDefault(team, 0) + price);
            }
            clubsBarChart.getData().clear();
            for (Map.Entry<String, Integer> entry : pricesByTeam.entrySet()) {
                BarChart.Series<String, Number> series = new BarChart.Series<>();
                series.setName(entry.getKey());
                series.getData().add(new BarChart.Data<>(entry.getKey(), entry.getValue()));
                clubsBarChart.getData().add(series);
            }
        } catch (IOException e) {
            System.out.printf("Error %s\n", e.getMessage());
        }
    }


}
