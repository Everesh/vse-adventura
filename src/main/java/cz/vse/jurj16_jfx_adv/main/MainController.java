package cz.vse.jurj16_jfx_adv.main;

import cz.vse.jurj16_jfx_adv.logika.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MainController implements Pozorovatel {
    @FXML
    private ImageView hrac;

    @FXML
    private ListView<Prostor> panelVychodu;

    @FXML
    private Button tlacitkoOdesly;

    @FXML
    private TextArea vystup;

    @FXML
    private TextField vstup;

    private IHra hra = new Hra();

    private ObservableList<Prostor> seznamVychodu = FXCollections.observableArrayList();

    private Map<String, Point2D> souradniceProstoru = new HashMap<>();
    private Map<String, String> prostorImg = new HashMap<>();

    @FXML
    private void initialize() {
        vystup.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        panelVychodu.setItems(seznamVychodu);

        hra.getHerniPlan().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> {
            aktualizujSeznamVychodu();
            aktualizujPolohuHrace();
        });
        hra.registruj(ZmenaHry.KONEC_HRY, () -> aktualizujKonecHry());
        aktualizujSeznamVychodu();
        initPlayerPositions();
        initProstorImg();
        panelVychodu.setCellFactory(param -> new ListCellProstor(prostorImg));
    }

    private void initPlayerPositions() {
        souradniceProstoru.put("moje_cela", new Point2D(100, 60));
        souradniceProstoru.put("severní_koridor_žaláře", new Point2D(155, 70));
        souradniceProstoru.put("oubliette", new Point2D(155, 25));
        souradniceProstoru.put("cela_1", new Point2D(215, 60));
        souradniceProstoru.put("střední_koridor_žaláře", new Point2D(155, 130));
        souradniceProstoru.put("cela_2", new Point2D(100, 120));
        souradniceProstoru.put("žalářníkova_stanice", new Point2D(215, 120));
        souradniceProstoru.put("?", new Point2D(53, 134));
        souradniceProstoru.put("jižní_koridor_žaláře", new Point2D(155, 190));
        souradniceProstoru.put("cela_3", new Point2D(100, 190));
        souradniceProstoru.put("hlavní_koridor_hradu", new Point2D(200, 200));
        souradniceProstoru.put("koruní_sál", new Point2D(320, 140));
        souradniceProstoru.put("strážní_stanice", new Point2D(340, 260));
        souradniceProstoru.put("králova_komnata", new Point2D(275, 260));
    }

    private void initProstorImg() {
        prostorImg.put("moje_cela", getClass().getResource("prostory/cela.png").toExternalForm());
        prostorImg.put("severní_koridor_žaláře",getClass().getResource("prostory/koridor.png").toExternalForm());
        prostorImg.put("oubliette", getClass().getResource("prostory/oubliette.png").toExternalForm());
        prostorImg.put("cela_1", getClass().getResource("prostory/cela.png").toExternalForm());
        prostorImg.put("střední_koridor_žaláře", getClass().getResource("prostory/koridor.png").toExternalForm());
        prostorImg.put("cela_2", getClass().getResource("prostory/cela.png").toExternalForm());
        prostorImg.put("žalářníkova_stanice", getClass().getResource("prostory/satanice.png").toExternalForm());
        prostorImg.put("?", getClass().getResource("prostory/questionmark.png").toExternalForm());
        prostorImg.put("jižní_koridor_žaláře", getClass().getResource("prostory/koridor.png").toExternalForm());
        prostorImg.put("cela_3", getClass().getResource("prostory/cela.png").toExternalForm());
        prostorImg.put("hlavní_koridor_hradu", getClass().getResource("prostory/koridor_hradu.png").toExternalForm());
        prostorImg.put("koruní_sál", getClass().getResource("prostory/KoruniSal.png").toExternalForm());
        prostorImg.put("strážní_stanice", getClass().getResource("prostory/satanice.png").toExternalForm());
        prostorImg.put("králova_komnata", getClass().getResource("prostory/komnata.png").toExternalForm());
    }

    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
    }

    private void aktualizujPolohuHrace() {
        String prostor = hra.getHerniPlan().getAktualniProstor().getNazev();
        if (!souradniceProstoru.containsKey(prostor)) return;

        hrac.setLayoutX(souradniceProstoru.get(prostor).getX());
        hrac.setLayoutY(souradniceProstoru.get(prostor).getY());
    }

    private void aktualizujKonecHry() {
        if (hra.konecHry()) {
            vstup.setDisable(true);
            tlacitkoOdesly.setDisable(true);
            panelVychodu.setDisable(true);
        }
    }

    public void odesliVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        vstup.clear();
        zpracujPrikaz(prikaz);
    }

    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> " + prikaz + '\n');
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek + "\n\n");
    }

    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Skutečně si přejete ukončit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @Override
    public void aktualizuj() {}

    @FXML
    private void klikPanelVychodu(MouseEvent mouseEvent) {
        Prostor cil = panelVychodu.getSelectionModel().getSelectedItem();
        if(cil == null) return;

        String prikaz = PrikazJdi.NAZEV + " " + cil.getNazev();
        zpracujPrikaz(prikaz);
    }
}
