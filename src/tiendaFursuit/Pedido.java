package tiendaFursuit;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido implements Serializable {

    private int id;
    private static int contadorId = 0;
    private String nombreCliente;
    private String direccion;
    private LocalDateTime fechaPedido;
    private LocalDate fechaInicioMaker;
    private LocalDate fechaFinalizacionMaker;
    private EnumMetodoPago metodoPago;
    private ArrayList<Fursuit> fursuits;

    public Pedido(String nombreCliente, String direccion, EnumMetodoPago metodoPago, ArrayList<Fursuit> fursuits) {
        this.id = contadorId++;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.fechaPedido = LocalDateTime.now();
        this.fechaInicioMaker = null;
        this.fechaFinalizacionMaker = null;
        this.metodoPago = metodoPago;
        this.fursuits = new ArrayList<Fursuit>();
    }

    public int getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public LocalDate getFechaInicioMaker() {
        return fechaInicioMaker;
    }

    public LocalDate getFechaFinalizacionMaker() {
        return fechaFinalizacionMaker;
    }

    public EnumMetodoPago getMetodoPago() {
        return metodoPago;
    }

    public ArrayList<Fursuit> getFursuits() {
        return fursuits;
    }

    public static void setContadorId(int contadorIdNuevo) {
        contadorId = contadorIdNuevo;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setFechaInicioMaker(LocalDate fechaInicioMaker) {
        this.fechaInicioMaker = fechaInicioMaker;
    }

    public void setFechaFinalizacionMaker(LocalDate fechaFinalizacionMaker) {
        this.fechaFinalizacionMaker = fechaFinalizacionMaker;
    }

    public void setMetodoPago(EnumMetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getTiempoTotal() {
        int tiempoTotal = 0;
        for (Fursuit fursuit : fursuits) {
            tiempoTotal += fursuit.getTiempoCreacionMin();
        }

        return tiempoTotal;
    }

    public LocalDate getFechaAproximadaFinalizacionPedido(Maker maker) {
        if (this.fechaFinalizacionMaker != null) {
            return this.fechaFinalizacionMaker;
        } else if (this.fechaInicioMaker != null) {
            int cantidadDias = getTiempoTotal() / maker.getMinutosTrabajoDiario();
            
            return this.fechaInicioMaker.plusDays(cantidadDias);
        } else {
            return null;
        }
    }

    public String getEstadoPedido() {
        if (this.fechaInicioMaker == null) {
            return "No empezado";
        } else if (this.fechaFinalizacionMaker != null) {
            return "Terminado";
        } else {
            return "En proceso";
        }
    }
}
