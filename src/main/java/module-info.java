module com.example.laboratorio_b {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens clientCV to javafx.fxml;
    exports clientCV;
    exports clientCV.centriVaccinali;
    opens clientCV.centriVaccinali to javafx.fxml;
    exports clientCV;
    opens clientCV to javafx.fxml;
    exports clientCV.centriVaccinali.grafica;
    opens clientCV.centriVaccinali.grafica to javafx.fxml;
    exports clientCV.condivisi;
    opens clientCV.condivisi to javafx.fxml;
}