import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private static List<Person> extent = new ArrayList<>(); //ekstensja klasy
    private String name; //atrybut prosty
    private String surname; //atrybut prosty
    private LocalDate birthDate; //atrybut złożony
    protected Double discount; //atrybut prosty

    private int idPerson; //atrybut prosty
    private static int id = 0;

    protected Person(String name, String surname, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.idPerson = ++id;
        addPerson(this);
    }

    //atrybut pochodny
    public int getAge(LocalDate birthDate){
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
    //metoda klasowa
    private static void addPerson(Person person) {
        extent.add(person);
    }


    private static void removePerson(Person person) {
        extent.remove(person);
    }

    public static void showExtent() {
        System.out.println("Extent of the class Person");

        for (Person p : extent) {
            System.out.println(p);
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Double getDiscount() {
        return discount;
    }

    protected void setDiscount(double d){
        discount = d;
        for (Person p : extent) {
            if(p == this){
                p.discount = d;
            }
        }

    }

    //przesłonięcie metody
    @Override
    public String toString() {
        return "PersonId= " + idPerson +
                ", name= " + name +
                ", surname= " + surname +
                ", age= " + getAge(birthDate);
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        extent = (ArrayList<Person>) stream.readObject();
    }
}
