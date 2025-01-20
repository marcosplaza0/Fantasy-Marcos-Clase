package org.marcosjfx.marcosfx;

import javafx.scene.image.Image;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.marcosjfx.marcosfx.backend.DataBase;
import org.marcosjfx.marcosfx.backend.Player;

/**
 * Representa una tarjeta de jugador en la interfaz, mostrando su información
 * y permitiendo interacciones con la compra y venta de jugadores.
 */
public class PlayerCard {

    /**
     * Constructor que inicializa una tarjeta del jugador vacía.
     */
    public PlayerCard() {
    }

    /**
     * El contenedor principal de la tarjeta del jugador.
     */
    public AnchorPane PlayerPane;

    /**
     * Imagen del retrato del jugador.
     */
    public ImageView PlayerPortrait;

    /**
     * Etiqueta que muestra el nombre del jugador.
     */
    public Label NameLabel;

    /**
     * Etiqueta que muestra la posición del jugador.
     */
    public Label PositionLabel;

    /**
     * Etiqueta que muestra el equipo del jugador.
     */
    public Label TeamLabel;

    /**
     * Botón para comprar o vender al jugador.
     */
    public Button BuyButton;

    /**
     * Etiqueta que muestra el precio del jugador.
     */
    public Label PriceLabel;

    /**
     * Objeto que representa al jugador actual de la tarjeta.
     */
    private Player player;

    /**
     * Indica si el jugador está actualmente en el equipo o no.
     */
    private boolean inTeam = false;

    /**
     * Vista del mercado que interactúa con esta tarjeta del jugador.
     */
    private MarketView marketView;

    /**
     * Establece la información de la tarjeta del jugador y actualiza los elementos visuales.
     *
     * @param player     El jugador cuya información se mostrará en la tarjeta.
     * @param marketView La vista del mercado asociada a esta tarjeta.
     */
    public void setInformation(Player player, MarketView marketView) {

        this.player = player;
        this.marketView = marketView;

        DataBase dataBase = new DataBase();
        if(dataBase.isPlayerInTeam(player)) {
            inTeam = true;
            BuyButton.setText("Vender");
        }

        NameLabel.setText(player.getName());
        PositionLabel.setText(player.getPosition());
        TeamLabel.setText(player.getClubName());

        PriceLabel.setText(String.format("€%,.2f", (double) player.getMarketValue()));

        String urlImage = player.getPlayerImageURL();
        Image playerImage = new Image(urlImage, true);
        PlayerPortrait.setImage(playerImage);

    }

    /**
     * Maneja las acciones realizadas al hacer clic en el botón de compra o venta.
     * Actualiza el estado del jugador en el equipo, el botón y muestra mensajes relevantes.
     */
    public void onBuyButtonClicked() {

        DataBase dataBase = new DataBase();

        if(inTeam) {
            dataBase.removePlayerFromTeam(player);
            inTeam = false;
            BuyButton.setText("Comprar");
            marketView.MessageLabel.setText("La venta se realizo con exito");
            marketView.updateBudgetLabel();
        } else {
            int completionStatus = dataBase.addPlayerToTeam(player);
            if(completionStatus == 0) {
                marketView.MessageLabel.setText("No hay suficiente presupuesto");
            } else if (completionStatus == 1) {
                marketView.MessageLabel.setText("La compra se realizo con exito");
                inTeam = true;
                BuyButton.setText("Vender");
                marketView.updateBudgetLabel();
            }
            else {
                marketView.MessageLabel.setText("Hubo un error");
            }
        }

    }

}