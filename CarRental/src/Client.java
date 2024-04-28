import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client extends Person implements Serializable {
    private static List<Client> extent = new ArrayList<>(); //ekstensja klasy
    private List<String> likedBrands = new ArrayList<>(); //atrybut złożony, opcjonalny
    private int idClient;
    private static int id = 0;

    public Client(String name, String surname, LocalDate birthDate) {
        super(name, surname, birthDate);
        super.discount = null;
        idClient = ++id;
        addClient(this);
    }

    public Client(Employee employee) {
        super(employee.getName(), employee.getSurname(), employee.getBirthDate());
        super.discount = Employee.getEmployeeDiscount();
        idClient = ++id;
        addClient(this);
    }

    public Client(String name, String surname, LocalDate birthDate, List<String> likedBrands) {
        super(name, surname, birthDate);
        super.discount = null;
        idClient = ++id;
        this.likedBrands.addAll(likedBrands);
        addClient(this);
    }

    public void addLikedBrands(String... brands){
        this.likedBrands.addAll(List.of(brands));
    }

    //przeciążenie metody addLikedBrands
    public void addLikedBrands(List<String> brands){
        this.likedBrands.addAll(brands);
    }

    public void getLikedBrands() {
        System.out.println("Liked brands of " + this.getName() + ": ");
        for(String s : likedBrands){
            System.out.print(s + " ");
        }
        System.out.println();
    }

    //metoda klasowa
    private static void addClient(Client client){
        extent.add(client);
    }
    private static void removeClient(Client client) {
        extent.remove(client);
    }

    public static void showExtent() {
        System.out.println("Extent of the class Client");

        for (Client c : extent) {
            System.out.println(c);
        }
    }

    private int getIdClient() {
        return idClient;
    }

    //przesłonięcie metody
    @Override
    public String toString() {
        return "ClientId= " + getIdClient() +
                ", name= " + getName() +
                ", surname= " + getSurname() +
                ", age= " + getAge();
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        extent = (ArrayList<Client>) stream.readObject();
    }
}
