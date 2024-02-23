package com.raterostesonco.streamingservicessim.servicios.planes;

public enum PlanesMemeflix implements Plan {

    UniDispositivo(120, 1), BioDispositivo(170, 2), TetraDispositivo(200, 4);

    private final int dispositivos;
    private final double precio;

    PlanesMemeflix(double precio, int dispositivos) {
        this.precio = precio;
        this.dispositivos = dispositivos;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
