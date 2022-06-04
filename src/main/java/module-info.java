module Laboratorio.B {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.sql;
    requires java.rmi;
    requires org.postgresql.jdbc;
    requires org.apache.commons.text;

    exports clientCV.centriVaccinali.interfacce;
    exports clientCV;

    opens clientCV.centriVaccinali.interfacce;
    exports clientCV.condivisi;
    exports serverCV;
}