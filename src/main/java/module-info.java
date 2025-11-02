module cz.vse.jurj16_jfx_adv {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens cz.vse.jurj16_jfx_adv.main to javafx.fxml;
    exports cz.vse.jurj16_jfx_adv.main;
}