package com.paujoseluis.gestionbancaria;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class PrimaryController {

    @FXML
    private ImageView logo;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
