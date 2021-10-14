module com.example.dicv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;


    opens com.example.dicv2 to javafx.fxml;
    exports com.example.dicv2;
}