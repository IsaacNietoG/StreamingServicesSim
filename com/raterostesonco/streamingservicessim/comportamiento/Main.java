package com.raterostesonco.streamingservicessim;

import com.raterostesonco.streamingservicessim.servicios.*;
import com.raterostesonco.streamingservicessim.servicios.planes.*;

public class Main {

    public static void main(String[] args) {
        // Instanciación de clientes
        Cliente alicia = new Cliente("Alicia Gómez", 15000),
                bob = new Cliente("Bob Hernández", 2400),
                cesar = new Cliente("César Chávez", 5000),
                diego = new Cliente("Diego Bravo", 9000),
                erika = new Cliente("Erika Doe", 10000),
                fausto = new Cliente("Fausto Wong", 5000);

        // Primer mes
        // Alicia va y contrata todos los servicios
        contratarTodo(alicia);

        // Bob hace lo mismo
        contratarTodo(bob);
        // TODO: Despues de tres meses cancela disney y hbo, en el cuarto baja meme y moma

        // Diego en cambio...
        diego.contratarServicio(HVOMax.getInstance(), PlanesHVOMax.NORMAL);
        diego.contratarServicio(Momazon.getInstance(), PlanesMomazon.PREMIUM);
        diego.contratarServicio(Spootify.getInstance(), PlanesSpootify.NORMAL);
        // TODO: SEXTO MES SE INSCRIBE A DISNEY, SEPTIMO A MEMEFLIX NORMAL, SE CAMBIA A PREMIUM EN SPOOTIFY Y CANCELA MOMAZON

        // Erika...
        erika.contratarServicio(Memeflix.getInstance(), PlanesMemeflix.TetraDispositivo);
        erika.contratarServicio(Spootify.getInstance(), PlanesSpootify.NORMAL);
        // TODO: Tercer mes cancela HBO y contrata Thisney+
        
    }

    private static void contratarTodo(Cliente cliente) {
        cliente.contratarServicio(Memeflix.getInstance(), PlanesMemeflix.TetraDispositivo);
        cliente.contratarServicio(Momazon.getInstance(), PlanesMomazon.PREMIUM);
        cliente.contratarServicio(Spootify.getInstance(), PlanesSpootify.PREMIUM);
        cliente.contratarServicio(HVOMax.getInstance(), PlanesHVOMax.NORMAL);
        cliente.contratarServicio(ThisneyPlus.getInstance(), PlanesThisneyPlus.PREMIUM);
    }
}
