module pourroy.c195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens pourroy.c195 to javafx.fxml;
    opens pourroy.c195.model to javafx.fxml;

    exports pourroy.c195;
    exports pourroy.c195.model;
}