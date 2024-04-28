import java.io.*;

public class DataBase {

    public static void readFile() {
        try {
            File f = new File("File.txt");
            if (f.exists() && !f.isDirectory()) {
                var in = new ObjectInputStream(new FileInputStream(f));
                Car.readExtent(in);
                CarRent.readExtent(in);
                Person.readExtent(in);
                Client.readExtent(in);
                Employee.readExtent(in);
                in.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(){
        try{
            var out = new ObjectOutputStream(new FileOutputStream("File.txt"));
            Car.writeExtent(out);
            CarRent.writeExtent(out);
            Person.writeExtent(out);
            Client.writeExtent(out);
            Employee.writeExtent(out);
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
