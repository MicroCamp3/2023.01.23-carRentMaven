package pl.camp.micro.car.rent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.camp.micro.car.rent.configuration.AppConfiguration;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);
        Core core = context.getBean(Core.class);
        core.start();
    }
}
