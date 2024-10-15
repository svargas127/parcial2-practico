// src/application/Main.java
package application;

import domain.Gorra;
import domain.Venta;
import repository.GorraRepository;
import repository.VentaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static GorraRepository gorraRepo = new GorraRepository();
    private static VentaRepository ventaRepo = new VentaRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Gorra> gorras = gorraRepo.cargarGorras();
        List<Venta> ventas = ventaRepo.cargarVentas();

        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrar nueva gorra");
            System.out.println("2. Actualizar datos de una gorra");
            System.out.println("3. Registrar nueva venta");
            System.out.println("4. Eliminar venta");
            System.out.println("5. Mostrar lista de gorras");
            System.out.println("6. Mostrar ventas por gorra");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    registrarNuevaGorra(scanner, gorras);
                    break;
                case 2:
                    actualizarGorra(scanner, gorras);
                    break;
                case 3:
                    registrarNuevaVenta(scanner, gorras, ventas);
                    break;
                case 4:
                    eliminarVenta(scanner, ventas);
                    break;
                case 5:
                    mostrarGorras(gorras);
                    break;
                case 6:
                    mostrarVentasPorGorra(scanner, gorras, ventas);
                    break;
                case 7:
                    gorraRepo.guardarGorras(gorras);
                    ventaRepo.guardarVentas(ventas);
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
    }

    private static void registrarNuevaGorra(Scanner scanner, List<Gorra> gorras) {
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Talla: ");
        String talla = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();

        Gorra nuevaGorra = new Gorra(modelo, color, talla, precio, stock);
        gorras.add(nuevaGorra);
        System.out.println("Gorra registrada exitosamente.");
    }

    private static void actualizarGorra(Scanner scanner, List<Gorra> gorras) {
        mostrarGorras(gorras);
        System.out.print("Seleccione el índice de la gorra a actualizar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        if (indice >= 0 && indice < gorras.size()) {
            Gorra gorra = gorras.get(indice);
            System.out.println("Actualizando la gorra: " + gorra);

            System.out.print("Nuevo modelo (actual: " + gorra.getModelo() + "): ");
            gorra.setModelo(scanner.nextLine());
            System.out.print("Nuevo color (actual: " + gorra.getColor() + "): ");
            gorra.setColor(scanner.nextLine());
            System.out.print("Nueva talla (actual: " + gorra.getTalla() + "): ");
            gorra.setTalla(scanner.nextLine());
            System.out.print("Nuevo precio (actual: " + gorra.getPrecio() + "): ");
            gorra.setPrecio(scanner.nextDouble());
            System.out.print("Nuevo stock (actual: " + gorra.getStock() + "): ");
            gorra.setStock(scanner.nextInt());

            System.out.println("Gorra actualizada exitosamente.");
        } else {
            System.out.println("Índice no válido.");
        }
    }

    private static void registrarNuevaVenta(Scanner scanner, List<Gorra> gorras, List<Venta> ventas) {
        mostrarGorras(gorras);
        System.out.print("Seleccione el índice de la gorra para la venta: ");
        int indice = scanner.nextInt();
        if (indice >= 0 && indice < gorras.size()) {
            Gorra gorra = gorras.get(indice);
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            Venta nuevaVenta = new Venta(LocalDate.now(), cantidad, gorra);
            ventas.add(nuevaVenta);
            System.out.println("Venta registrada exitosamente.");
        } else {
            System.out.println("Índice no válido.");
        }
    }

    private static void eliminarVenta(Scanner scanner, List<Venta> ventas) {
        mostrarVentas(ventas);
        System.out.print("Seleccione el índice de la venta a eliminar: ");
        int indice = scanner.nextInt();
        if (indice >= 0 && indice < ventas.size()) {
            ventas.remove(indice);
            System.out.println("Venta eliminada exitosamente.");
        } else {
            System.out.println("Índice no válido.");
        }
    }

    private static void mostrarGorras(List<Gorra> gorras) {
        System.out.println("\n--- Lista de Gorras ---");
        for (int i = 0; i < gorras.size(); i++) {
            System.out.println(i + ". " + gorras.get(i));
        }
    }

    private static void mostrarVentas(List<Venta> ventas) {
        System.out.println("\n--- Lista de Ventas ---");
        for (int i = 0; i < ventas.size(); i++) {
            System.out.println(i + ". " + ventas.get(i));
        }
    }

    private static void mostrarVentasPorGorra(Scanner scanner, List<Gorra> gorras, List<Venta> ventas) {
        mostrarGorras(gorras);
        System.out.print("Seleccione el índice de la gorra para mostrar sus ventas: ");
        int indice = scanner.nextInt();
        if (indice >= 0 && indice < gorras.size()) {
            Gorra gorra = gorras.get(indice);
            System.out.println("\n--- Ventas de la gorra: " + gorra.getModelo() + " ---");
            for (Venta venta : ventas) {
                if (venta.getGorra().equals(gorra)) {
                    System.out.println(venta);
                }
            }
        } else {
            System.out.println("Índice no válido.");
        }
    }
}

