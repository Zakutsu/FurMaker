package tiendaFursuit;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Maker implements Serializable {

    private String nombre;
    private int minutosTrabajoDiario;
    private Pedido pedido;
    private ArrayList<EnumMetodoPago> metodoPagos;
    private int precioHead;
    private int precioSuit;
    
    // He agregado una elipsis que me ha explicado un amigo previamente para poder agregar los distintos métodos de pago de una manera más sencilla
    public Maker(String nombre, int minutosTrabajoDiario, int precioHead, int precioSuit, EnumMetodoPago... metodoPagos) {
        this.nombre = nombre;
        this.minutosTrabajoDiario = minutosTrabajoDiario;
        this.pedido = null;
        this.metodoPagos = new ArrayList<EnumMetodoPago>(Arrays.asList(metodoPagos));
        this.precioHead = precioHead;
        this.precioSuit = precioSuit;
    }

    public Maker(String nombre, int minutosTrabajoDiario, int precioHead, int precioSuit, ArrayList<EnumMetodoPago> metodoPagos) {
        this.nombre = nombre;
        this.minutosTrabajoDiario = minutosTrabajoDiario;
        this.pedido = null;
        this.metodoPagos = metodoPagos;
        this.precioHead = precioHead;
        this.precioSuit = precioSuit;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getMinutosTrabajoDiario() {
        return minutosTrabajoDiario;
    }
    public void setMinutosTrabajoDiario(int minutosTrabajoDiario) {
        this.minutosTrabajoDiario = minutosTrabajoDiario;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public ArrayList<EnumMetodoPago> getMetodoPagos() {
        return metodoPagos;
    }
    public void setMetodoPagos(ArrayList<EnumMetodoPago> metodoPagos) {
        this.metodoPagos = metodoPagos;
    }
    public int getPrecioHead() {
        return precioHead;
    }
    public void setPrecioHead(int precioHead) {
        this.precioHead = precioHead;
    }
    public int getPrecioSuit() {
        return precioSuit;
    }
    public void setPrecioSuit(int precioSuit) {
        this.precioSuit = precioSuit;
    }

    // Fecha aproximada de la finalización del pedido
    public LocalDate getFechaAproximadaFinalizacionPedido() {
        LocalDate fechaAproximadaFinalizacionPedido = pedido.getFechaInicioMaker();

        int dias = (int) Math.ceil((double) pedido.getTiempoTotal() / this.minutosTrabajoDiario);
        return fechaAproximadaFinalizacionPedido.plusDays(dias);
    }
}
