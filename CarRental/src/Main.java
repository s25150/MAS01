import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DataBase.readFile(); //trwałość ekstensji

        Car car1 = new Car("Fiat", "Punto", 2002, 5, 50);
        Car car2 = new Car("Honda", "Civic", 2015, 3, 120);
        Car.showExtent();

        Client client1 = new Client("Julia", "Beverly", LocalDate.of(2001, 1, 23));
        List<String> client2LikedBrands= new ArrayList<>();
        client2LikedBrands.add("Suzuki");
        Client client2 = new Client("Michael", "Moller", LocalDate.of(1994, 3, 11), client2LikedBrands);

        Client.showExtent();

        client1.addLikedBrands("Audi", "Mazda");
        Client.showExtent();
        client1.getLikedBrands();

        Employee emp1 = new Employee("Marcin", "Brzytwa", LocalDate.of(1966, 12, 11));
        Client client3 = new Client(emp1);
        Client.showExtent();
        Employee.showExtent();

        CarRent<Client> carrent1 = new CarRent<>(LocalDate.of(2002, 12, 11),LocalDate.of(2002, 12, 15), car1, client2);
        CarRent<Client> carrent2 = new CarRent<>(LocalDate.of(2023, 6, 23),LocalDate.of(2002, 6, 24), car2, client2);
        CarRent<Employee> carrent3 = new CarRent<>(LocalDate.of(2002, 12, 11),LocalDate.of(2002, 12, 15), car1, emp1);


        CarRent.showExtent();

        DataBase.writeFile(); //trwałość ekstensji



    }
}