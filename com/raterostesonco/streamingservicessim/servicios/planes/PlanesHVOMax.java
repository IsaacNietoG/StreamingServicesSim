package com.raterostesonco.streamingservicessim.servicios.planes;

public enum PlanesHVOMax implements Plan {

    INICIAL(0), NORMAL(140);

    double precio;

    PlanesHVOMax(double precio) {
        this.precio = precio;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
