package cz.vse.jurj16_jfx_adv.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private TextArea vystup;

    @FXML
    private TextField vstup;

    public void odesliVstup(ActionEvent actionEvent) {
        vystup.appendText(vstup.getText() + '\n');
        vstup.clear();
    }
}
