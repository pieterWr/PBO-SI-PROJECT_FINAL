module Final.Project {
    requires spring.context;
    requires spring.beans;
    requires java.sql;
    requires org.slf4j;
    opens foodOrderApp;
    opens foodOrderApp.config;
    opens foodOrderApp.entities;
    opens foodOrderApp.repositories;
    opens foodOrderApp.services;
    opens foodOrderApp.views;
}