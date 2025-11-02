package cz.vse.jurj16_jfx_adv.main;

import cz.vse.jurj16_jfx_adv.logika.Vec;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;

public class ListCellVec extends ListCell<Vec> {
    private final Map<String, String> vecImg;

    public ListCellVec(Map<String, String> vecImg) {
        this.vecImg = vecImg;
    }
    @Override
    protected void updateItem(Vec vec, boolean empty) {
        super.updateItem(vec, empty);

        if (empty || vec == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(vec.getNazev());

            String imageUrl = vecImg.get(vec.getNazev());
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
