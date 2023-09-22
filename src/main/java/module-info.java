module com.example.agpsappchesnakova {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.agpsappchesnakova to javafx.fxml;
    exports com.example.agpsappchesnakova;
}