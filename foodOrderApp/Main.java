package foodOrderApp;

import foodOrderApp.config.Database;
import foodOrderApp.views.MenuView;
import foodOrderApp.views.TerminalView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "foodOrderApp")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        MenuView terminalView = context.getBean(TerminalView.class);
        terminalView.run();
    }

    @Bean
    Database database() {
        Database database = new Database("food_order_db", "root", "", "localhost", "3306");
        database.setup();
        return database;
    }
}