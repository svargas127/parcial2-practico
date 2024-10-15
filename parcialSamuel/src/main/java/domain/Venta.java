

package domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Venta implements Serializable {
    private LocalDate fecha;
    private int cantidad;
    private Gorra gorra;
    private double total;

    public Venta(LocalDate fecha, int cantidad, Gorra gorra) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.gorra = gorra;
        this.total = cantidad * gorra.getPrecio();
    }





    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Gorra getGorra() { return gorra; }
    public void setGorra(Gorra gorra) { this.gorra = gorra; }

    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Venta de " + cantidad + " unidades de la gorra " + gorra.getModelo() + " - Total: " + total + " Fecha: " + fecha;
    }
}
