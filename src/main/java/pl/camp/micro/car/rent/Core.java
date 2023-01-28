package pl.camp.micro.car.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.camp.micro.car.rent.authentication.Authenticator;
import pl.camp.micro.car.rent.authentication.IAuthenticator;
import pl.camp.micro.car.rent.database.CarRepository;
import pl.camp.micro.car.rent.database.ICarRepository;
import pl.camp.micro.car.rent.gui.GUI;
import pl.camp.micro.car.rent.gui.IGUI;
import pl.camp.micro.car.rent.model.User;

@Component
public class Core {
    @Autowired
    private IGUI gui;
    private final ICarRepository carRepository;
    @Autowired
    private IAuthenticator authenticator;
    //private static final Core instance = new Core();


    public Core(@Autowired ICarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void start() {
        int loginCounter = 0;
        boolean run = false;
        while(!run && loginCounter < 3) {
            User user = gui.getLoginAndPassword();
            run = this.authenticator.authenticate(user.getLogin(), user.getPassword());
            loginCounter++;
        }

        while(run) {
            String choose = gui.showMenu();
            switch(choose) {
                case "1":
                    gui.listCars();
                    break;
                case "2":
                    gui.showRentResult(carRepository.rentCar(gui.getPlate()));
                    break;
                case "3":
                    run = false;
                    break;
                default:
                    System.out.println("Incorrect choose !!");
                    break;
            }
        }
    }

    /*public static Core getInstance() {
        return instance;
    }*/
}
