package pl.camp.micro.car.rent.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.camp.micro.car.rent.database.CarRepository;
import pl.camp.micro.car.rent.database.ICarRepository;
import pl.camp.micro.car.rent.model.Car;
import pl.camp.micro.car.rent.model.User;

import java.util.Scanner;

@Component
public class GUI implements IGUI {
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private ICarRepository carRepository;
    //private static final GUI instance = new GUI();


    @Override
    public String showMenu() {
        System.out.println("1. List cars");
        System.out.println("2. Rent car");
        System.out.println("3. Exit");

        return scanner.nextLine();
    }

    @Override
    public void listCars() {
        for(Car car : this.carRepository.getCars()) {
            System.out.println(car + " dostepny: " +
                    (this.carRepository.getRent().contains(car.getPlate()) ? "Nie" : "Tak"));
        }
    }

    @Override
    public String getPlate() {
        System.out.println("Podaj nr rejestracyjny:");
        return scanner.nextLine();
    }

    @Override
    public void showRentResult(boolean rentResult) {
        if(rentResult) {
            System.out.println("Wypozyczono !!");
        } else {
            System.out.println("Nie ma takiego auta lub jest wypozyczone !!");
        }
    }

    @Override
    public User getLoginAndPassword() {
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Password: ");
        return new User(login, scanner.nextLine());
    }

    /*public static GUI getInstance() {
        return instance;
    }*/
}
