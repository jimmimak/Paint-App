import java.io.*;
import java.util.*;

//Static methodoi gia diaxeirhsh arxeiwn
public class FileSystem {
    //Apothikeuei th lista sxhmatwn se arxeio
    static void saveToFile(File file, ArrayList<Shape> shapelist) throws FileNotFoundException, IOException{
        FileOutputStream out = new FileOutputStream(file);
        ObjectOutputStream oout = new ObjectOutputStream(out);
        oout.writeObject(shapelist);
        oout.close();
    }
    
    //Diabazei th lista sxhmatwn apo to arxeio
    static ArrayList<Shape> readFromFile(File file) throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Shape> shapelist;
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream oin = new ObjectInputStream(in);
        shapelist = (ArrayList<Shape>)oin.readObject();
        oin.close();
        
        return shapelist;
    }
}
