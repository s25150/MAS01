import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class CarRent <T extends Person> implements Serializable {
    private static List<CarRent> extent = new ArrayList<>(); //ekstensja klasy
    private LocalDate startDateOfRent; //atrybut złożony
    private LocalDate endDateOfRent; //atrybut złożony
    private Car rentedCar; //atrybut złożony
    private T renter; //atrybut złożony
    private double totalCost; //atrybut pochodny

    public CarRent(LocalDate startDateOfRent, LocalDate endDateOfRent, Car rentedCar, T renter) {
        this.startDateOfRent = startDateOfRent;
        this.endDateOfRent = endDateOfRent;
        this.rentedCar = rentedCar;
        this.renter = renter;
        totalCost = getTotalCost(renter, rentedCar, startDateOfRent, endDateOfRent);
        addCarRent(this);
    }

    private double getTotalCost(T renter, Car car, LocalDate startDate, LocalDate endDate){
        int days = Period.between(startDate, endDate).getDays();
        if(renter.getDiscount()!=null){
            double discount = renter.getDiscount() * (car.getRentCost() * days);
            return (car.getRentCost() * days) - discount;
        }
        return  car.getRentCost() * days;
    }

    private static void addCarRent(CarRent carRent){
        extent.add(carRent);
    }


    private static void removeCarRent(CarRent carRent) {
        extent.remove(carRent);
    }

    public static void showExtent() {
        System.out.println("Extent of the class CarRent");

        for (CarRent cr : extent) {
            System.out.println(cr);
        }
    }

    @Override
    public String toString() {
        return "Start date of rent=" + startDateOfRent +
                ", end date=" + endDateOfRent + "\n" +
                ", rented car=" + rentedCar + "\n" +
                ", renter=" + renter.getName() + renter.getSurname() + "\n" +
                ", totalCost=" + totalCost;
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        extent = (ArrayList<CarRent>) stream.readObject();
    }
}
