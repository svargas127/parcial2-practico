
package repository;

import domain.Venta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VentaRepository {

    private static final String FILE_PATH = "ventas.txt";

    public List<Venta> cargarVentas() {
        List<Venta> ventas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            ventas = (List<Venta>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de ventas no encontrado, iniciando lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    public void guardarVentas(List<Venta> ventas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(ventas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

