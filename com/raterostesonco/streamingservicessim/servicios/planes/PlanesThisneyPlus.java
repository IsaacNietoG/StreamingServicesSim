package com.raterostesonco.streamingservicessim.servicios.planes;

public enum PlanesThisneyPlus implements Plan {
    INICIAL(130), NORMAL(160);

    double precio;

    PlanesThisneyPlus(double precio) {
        this.precio = precio;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
