package com.raterostesonco.streamingservicessim.comportamiento;

import com.raterostesonco.streamingservicessim.Cliente;
import com.raterostesonco.streamingservicessim.servicios.*;
import com.raterostesonco.streamingservicessim.servicios.planes.*;

public class Main {

    public static final Cliente alicia = new Cliente("Alicia Gómez", 15000),
            bob = new Cliente("Bob Hernández", 2400),
            cesar = new Cliente("César Chávez", 5000),
            diego = new Cliente("Diego Bravo", 9000),
            erika = new Cliente("Erika Doe", 10000),
            fausto = new Cliente("Fausto Wong", 5000);

    public static void main(String[] args) throws InterruptedException {
        for (Meses mes : Meses.values()) {
            mes.simular();
            Thread.sleep(1000);
        }
    }
}
