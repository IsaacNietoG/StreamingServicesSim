package com.raterostesonco.streamingservicessim.servicios.planes;

public enum PlanesThisneyPlus implements Plan {
    NORMAL(130), PREMIUM(160);

    double precio;

    PlanesThisneyPlus(double precio) {
        this.precio = precio;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
