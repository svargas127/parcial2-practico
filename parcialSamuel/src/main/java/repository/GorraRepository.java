
package repository;

import domain.Gorra;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GorraRepository {

    private static final String FILE_PATH = "gorras.txt";

    public List<Gorra> cargarGorras() {
        List<Gorra> gorras = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            gorras = (List<Gorra>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de gorras no encontrado, iniciando lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gorras;
    }

    public void guardarGorras(List<Gorra> gorras) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(gorras);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


