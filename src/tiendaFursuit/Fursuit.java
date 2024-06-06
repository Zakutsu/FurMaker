package tiendaFursuit;

public class Fursuit {

    private String raza;
    private String colorPrincipal;
    private String colorSecundario;
    private String comentario;
    private int tiempoCreacionMin;

    public Fursuit(String raza, String colorPrincipal, String colorSecundario, String comentario) {
        this.raza = raza;
        this.colorPrincipal = colorPrincipal;
        this.colorSecundario = colorSecundario;
        this.comentario = comentario;
        this.tiempoCreacionMin = 0;
    }

    public String getRaza() {
        return this.raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColorPrincipal() {
        return colorPrincipal;
    }

    public void setColorPrincipal(String colorPrincipal) {
        this.colorPrincipal = colorPrincipal;
    }

    public String getColorSecundario() {
        return colorSecundario;
    }

    public void setColorSecundario(String colorSecundario) {
        this.colorSecundario = colorSecundario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getTiempoCreacionMin() {
        return tiempoCreacionMin;
    }

    public void setTiempoCreacionMin(int tiempoCreacionMin) {
        this.tiempoCreacionMin = tiempoCreacionMin;
    }

}
