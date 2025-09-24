module cz.vse.jurj16_jfx_adv {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.vse.jurj16_jfx_adv to javafx.fxml;
    exports cz.vse.jurj16_jfx_adv;
}