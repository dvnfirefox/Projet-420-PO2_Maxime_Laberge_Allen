module com.example.projet420po2_maxime_laberge_allen {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.projet420po2_maxime_laberge_allen to javafx.fxml;
    exports com.example.projet420po2_maxime_laberge_allen;
}