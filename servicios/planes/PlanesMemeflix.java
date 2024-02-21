import javax.xml.crypto.Data;

public enum PlanesMemeflix implements Plan {

    UniDispositivo(120, 1), BioDispositivo(170, 2), TetraDispositivo(200, 4);

    private int dispositivos;

    PlanesMemeflix(double precio, int dispositivos) {
        // this.precio = precio;
        this.dispositivos = dispositivos;
    }
}
