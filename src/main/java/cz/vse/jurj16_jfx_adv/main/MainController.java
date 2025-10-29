package cz.vse.jurj16_jfx_adv.main;

import cz.vse.jurj16_jfx_adv.logika.Hra;
import cz.vse.jurj16_jfx_adv.logika.IHra;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class MainController {
    @FXML
    private Button tlacitkoOdesly;

    @FXML
    private TextArea vystup;

    @FXML
    private TextField vstup;

    private IHra hra = new Hra();

    @FXML
    private void initialize() {
        vystup.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vstup.requestFocus();
            }
        });
    }

    public void odesliVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        vystup.appendText("> " + vstup.getText() + '\n');
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek + "\n\n");
        vstup.clear();

        if (hra.konecHry()) {
            vstup.setDisable(true);
            tlacitkoOdesly.setDisable(true);
        }
    }

    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Skutečně si přejete ukončit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}
