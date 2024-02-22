package com.raterostesonco.streamingservicessim.servicios.planes;

public enum PlanesMomazon implements Plan {

    NORMAL(110), PREMIUM(150);

    double precio;

    PlanesMomazon(double precio) {
        this.precio = precio;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
