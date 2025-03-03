module com.example.bigassproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.bigassproject to javafx.fxml;
    exports com.example.bigassproject;
}