module com.example.dicv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;

    exports com.example.dicv2;
    opens com.example.dicv2 to javafx.fml, javafx.fxml;
    exports Control;
    opens Control to javafx.fml, javafx.fxml;
    exports Object;
    opens Object to javafx.fml, javafx.fxml;
}