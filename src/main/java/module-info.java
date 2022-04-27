module Laboratorio.B {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.sql;

    exports clientCV.centriVaccinali.interfacce;
    exports clientCV;

    opens clientCV.centriVaccinali.interfacce;
}