package tiendaFursuit;

public class FursuitSuit extends Fursuit {

    private boolean garras;
    private EnumTipoPatas tipoPatas;
    private String colorBeans;
    private EnumTamano tamanoCola;

    public FursuitSuit(String raza, String colorPrincipal, String colorSecundario, String comentario, boolean garras, EnumTipoPatas tipoPatas, String colorBeans, EnumTamano tamanoCola) {
        super(raza, colorPrincipal, colorSecundario, comentario);
        this.garras = garras;
        this.tipoPatas = tipoPatas;
        this.colorBeans = colorBeans;
        this.tamanoCola = tamanoCola;
    }

    public boolean isGarras() {
        return garras;
    }

    public void setGarras(boolean garras) {
        this.garras = garras;
    }

    public EnumTipoPatas getTipoPatas() {
        return tipoPatas;
    }

    public void setTipoPatas(EnumTipoPatas tipoPatas) {
        this.tipoPatas = tipoPatas;
    }

    public String getColorBeans() {
        return colorBeans;
    }

    public void setColorBeans(String colorBeans) {
        this.colorBeans = colorBeans;
    }

    public EnumTamano getTamanoCola() {
        return tamanoCola;
    }

    public void setTamanoCola(EnumTamano tamanoCola) {
        this.tamanoCola = tamanoCola;
    }
    
    

}
