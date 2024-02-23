package com.raterostesonco.streamingservicessim.comportamiento;

import com.raterostesonco.streamingservicessim.Cliente;
import com.raterostesonco.streamingservicessim.Suscripcion;
import com.raterostesonco.streamingservicessim.servicios.*;
import com.raterostesonco.streamingservicessim.servicios.planes.*;
import com.raterostesonco.streamingservicessim.utilidades.Logger;

import java.util.ArrayList;

import static com.raterostesonco.streamingservicessim.comportamiento.Main.*;

public enum Meses {

    PRIMERO(() -> {
        // Alicia
        contratarTodo(alicia);

        // Bob
        contratarTodo(bob);

        // Cesar
        cesar.contratarServicio(ThisneyPlus.getInstance(), PlanesThisneyPlus.INICIAL);
        cesar.contratarServicio(HVOMax.getInstance(), PlanesHVOMax.NORMAL);

        // Diego
        diego.contratarServicio(HVOMax.getInstance(), PlanesHVOMax.NORMAL);
        diego.contratarServicio(Momazon.getInstance(), PlanesMomazon.PREMIUM);
        diego.contratarServicio(Spootify.getInstance(), PlanesSpootify.NORMAL);

        // Erika
        erika.contratarServicio(Memeflix.getInstance(), PlanesMemeflix.TetraDispositivo);
        erika.contratarServicio(Spootify.getInstance(), PlanesSpootify.NORMAL);

        // Fausto
        fausto.contratarServicio(ThisneyPlus.getInstance(), PlanesThisneyPlus.INICIAL);
        fausto.contratarServicio(HVOMax.getInstance(), PlanesHVOMax.NORMAL);

    }),

    SEGUNDO(() -> {

    }),

    TERCERO(() -> {
        // Bob
        cancelarServicio(bob, Memeflix.getInstance());
        cancelarServicio(bob, HVOMax.getInstance());

        // Erika
        cancelarServicio(erika, HVOMax.getInstance());
        erika.contratarServicio(ThisneyPlus.getInstance(), PlanesThisneyPlus.INICIAL);

        // Fausto
        cancelarServicio(fausto, ThisneyPlus.getInstance());
        cancelarServicio(fausto, HVOMax.getInstance());
        fausto.contratarServicio(Memeflix.getInstance(), PlanesMemeflix.BioDispositivo);
    }),

    CUARTO(() -> {
        // Bob
        cancelarServicio(bob, Memeflix.getInstance());
        cancelarServicio(bob, Momazon.getInstance());

        // Fausto
        cancelarServicio(fausto, Memeflix.getInstance());
    }),

    QUINTO(() -> {
        // Fausto
        fausto.contratarServicio(ThisneyPlus.getInstance(), PlanesThisneyPlus.INICIAL);
        fausto.contratarServicio(HVOMax.getInstance(), PlanesHVOMax.NORMAL);
    }),

    SEXTO(() -> {
        // Diego
        diego.contratarServicio(ThisneyPlus.getInstance(), PlanesThisneyPlus.INICIAL);

        // Erika
        cancelarTodo(erika);

        // Fausto
        cancelarTodo(fausto);
    }),

    SEPTIMO(() -> {

        // Diego
        diego.contratarServicio(Memeflix.getInstance(), PlanesMemeflix.UniDispositivo);
        diego.cambiarPlan(Spootify.getInstance(), PlanesSpootify.PREMIUM);
        cancelarServicio(diego, Momazon.getInstance());

        // Cesar
        cesar.contratarServicio(Spootify.getInstance(), PlanesSpootify.PREMIUM);
    }),

    OCTAVO(() -> {

    }),

    NOVENO(() -> {

    }),

    DECIMO(() -> {
        // Erika
        erika.contratarServicio(Momazon.getInstance(), PlanesMomazon.PREMIUM);
        erika.contratarServicio(HVOMax.getInstance(), PlanesHVOMax.NORMAL);
        erika.contratarServicio(ThisneyPlus.getInstance(), PlanesThisneyPlus.NORMAL);
    });

    // Aquí se podrían seguir agregando meses de ser necesario, y automaticamente se simularían


    private final Ejecutable e;

    Meses(Ejecutable e) {
        this.e = e;
    }

    public void simular() {
        Logger.log(this, "Simulando el mes %s...".formatted(this.name()));
        e.ejecutar();
        cobroGlobal();
    }

    private void cobroGlobal() {
        HVOMax.getInstance().cobrarClientes();
        Memeflix.getInstance().cobrarClientes();
        Spootify.getInstance().cobrarClientes();
        ThisneyPlus.getInstance().cobrarClientes();
        Momazon.getInstance().cobrarClientes();
    }

    private static void cancelarServicio(Cliente cliente, Servicio servicio) {
        ArrayList<Suscripcion> suscripciones = cliente.darSuscripciones();
        for (Suscripcion s : suscripciones) {
            if(s.darServicio().equals(servicio)){
                s.cancelar();
                break;
            }
        }
    }

    private static void cancelarTodo(Cliente cliente) {
        cliente.darSuscripciones().forEach(Suscripcion::cancelar);
    }

    private static void contratarTodo(Cliente cliente) {
        cliente.contratarServicio(Memeflix.getInstance(), PlanesMemeflix.TetraDispositivo);
        cliente.contratarServicio(Momazon.getInstance(), PlanesMomazon.PREMIUM);
        cliente.contratarServicio(Spootify.getInstance(), PlanesSpootify.PREMIUM);
        cliente.contratarServicio(HVOMax.getInstance(), PlanesHVOMax.NORMAL);
        cliente.contratarServicio(ThisneyPlus.getInstance(), PlanesThisneyPlus.NORMAL);
    }
}
