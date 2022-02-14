module com.gui.coursesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires mysql.connector.java;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires spring.web;
    requires spring.core;
    requires spring.context;
    requires javax.persistence;
    requires java.base;


    opens com.gui.coursesystem to javafx.fxml;
    exports com.gui.coursesystem;
    exports com.gui.coursesystem.fxControllers;
    opens com.gui.coursesystem.fxControllers to javafx.fxml;
    exports com.gui.coursesystem.ds to org.hibernate.orm.core, javax.persistence;
    opens com.gui.coursesystem.ds to org.hibernate.orm.core;
    exports com.gui.coursesystem.GSONSerializable to org.hibernate.orm.core;
    opens com.gui.coursesystem.webControllers to java.base;
}