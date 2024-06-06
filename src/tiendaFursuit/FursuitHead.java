package tiendaFursuit;

public class FursuitHead extends Fursuit {

    private String colorOjoIzquierdo;
    private String colorOjoDerecho;
    private EnumTamano tamanoOrejas;
    private EnumTamano tamanoCuernos;
    private boolean ventilador;

    public FursuitHead(String raza, String colorPrincipal, String colorSecundario, String comentario, String colorOjoDerecho, String colorOjoIzquierdo, EnumTamano tamanoOrejas, EnumTamano tamanoCuernos, boolean ventilador) {
        super(raza, colorPrincipal, colorSecundario, comentario);
        this.colorOjoDerecho = colorOjoDerecho;
        this.colorOjoIzquierdo = colorOjoIzquierdo;
        this.tamanoOrejas = tamanoOrejas;
        this.tamanoCuernos = tamanoCuernos;
        this.ventilador = ventilador;
    }

    public String getColorOjoIzquierdo() {
        return colorOjoIzquierdo;
    }

    public void setColorOjoIzquierdo(String colorOjoIzquierdo) {
        this.colorOjoIzquierdo = colorOjoIzquierdo;
    }

    public String getColorOjoDerecho() {
        return colorOjoDerecho;
    }

    public void setColorOjoDerecho(String colorOjoDerecho) {
        this.colorOjoDerecho = colorOjoDerecho;
    }

    public EnumTamano getTamanoOrejas() {
        return tamanoOrejas;
    }

    public void setTamanoOrejas(EnumTamano tamanoOrejas) {
        this.tamanoOrejas = tamanoOrejas;
    }

    public EnumTamano getTamanoCuernos() {
        return tamanoCuernos;
    }

    public void setTamanoCuernos(EnumTamano tamanoCuernos) {
        this.tamanoCuernos = tamanoCuernos;
    }

    public boolean isVentilador() {
        return ventilador;
    }

    public void setVentilador(boolean ventilador) {
        this.ventilador = ventilador;
    }
    
}
