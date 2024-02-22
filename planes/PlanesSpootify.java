package servicios.planes;

public enum PlanesSpootify implements Plan {
    NORMAL(0), PREMIUM(80);

    double precio;

    PlanesSpootify(double precio) {
        this.precio = precio;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
