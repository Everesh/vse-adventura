package cz.vse.jurj16_jfx_adv.main;

import cz.vse.jurj16_jfx_adv.logika.Prostor;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;

public class ListCellProstor extends ListCell<Prostor> {
    private final Map<String, String> prostorImg;

    public ListCellProstor(Map<String, String> prostorImg) {
        this.prostorImg = prostorImg;
    }

    @Override
    protected void updateItem(Prostor prostor, boolean b) {
        super.updateItem(prostor, b);
        if (b || prostor == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(prostor.getNazev());

            String imageUrl = prostorImg.get(prostor.getNazev());
            if (imageUrl != null) {
                ImageView iw = new ImageView(new Image(imageUrl));
                iw.setFitHeight(64);
                iw.setPreserveRatio(true);
                setGraphic(iw);
            } else {
                setGraphic(null);
            }
        }
    }
}
