module JavaFxApplication {
    requires javafaker;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    opens sample;
    opens sample.PersonPackage;
}