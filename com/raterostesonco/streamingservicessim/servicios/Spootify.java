package com.raterostesonco.streamingservicessim.servicios;

import com.raterostesonco.streamingservicessim.Cliente;
import com.raterostesonco.streamingservicessim.Escuchador;
import com.raterostesonco.streamingservicessim.Suscripcion;
import com.raterostesonco.streamingservicessim.servicios.planes.Plan;
import com.raterostesonco.streamingservicessim.servicios.planes.PlanesSpootify;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class Spootify implements Servicio {
    PlanesSpootify planes; //Enum
    private ArrayList<String> recomendaciones;
    private ArrayList<Suscripcion> listaSuscripciones;
    static Spootify instance;

    /**
     * Constructor de la clase Spootify
     * <p>
     * Nos interesa que sea privado para que Spootify tenga una unica instancia.
     * Debemos de crear todos los atributos correspondientes, particularmente, aqui es donde debemos de colocar las recomendaciones
     * correspondientes para este servicio
     */
    private Spootify() {
         recomendaciones = new ArrayList<>(Arrays.asList("Taylor Swift", "Peso Pluma", "The Weeknd",
                                                        "Natanael Cano", "Guason bebe", "Charlie Puth", "Justin Bieber", "Gorillaz", "Bad Bunny", "Lil Wayne",
                                                        "Kanye West", "Lana del Rey"));
        listaSuscripciones = new ArrayList<>();
    }

    /**
     * Este es el metodo al que debemos de llamar desde el exterior para referirnos a Spootify
     * <p>
     * Funciona simple, si no existe una instancia, la crea, y si existe, retorna la unica instancia que debe de existir.
     */
    public static Spootify getInstance() {
        if (instance == null) {
            instance = new Spootify();
        }
        return instance;
    }

    public String toString() {
        return getNombre();
    }

    /**
     * Envia una recomendacion aleatoria mensualmente a todos los suscriptores del servicio
     * <p>
     * Utiliza el modelo Observer implementado en Servicios para este propósito (ver notificar())
     */
    @Override
    public void enviarRecomendacion() {
        notificar("Recomendacion mensual de " + this + ": " + recomendaciones.get(new Random().nextInt(12)));
    }

    /**
     * Cobra masivamente a todos los clientes, debe ser activado "mensualmente" al igual que enviarRecomendacion
     * <p>
     * Utiliza el metodo src.main.com.raterostesonco.streamingservicessim.Suscripcion.facturar para esto mismo.
     */
    @Override
    public void cobrarClientes() {
        for(Suscripcion cliente : listaSuscripciones){
            cliente.facturar();
        }
        enviarRecomendacion();
    }

    /**
     * Retorna todos los src.main.com.raterostesonco.streamingservicessim.servicios.planes posibles que se pueden contratar de este servicio.
     */
    @Override
    public List<Plan> darPlanes() {
        ArrayList<Plan> listaPlanes = new ArrayList<>();
        for(PlanesSpootify plan : planes.values()){
            listaPlanes.add(plan);
        }
        return listaPlanes;
    }

    /**
     * Recibe un usuario que desea suscribir un nuevo plan del servicio y le retorna la src.main.com.raterostesonco.streamingservicessim.Suscripcion correspondiente, de la misma
     * forma, este metodo agrega la src.main.com.raterostesonco.streamingservicessim.Suscripcion creada a la lista interna del Servico (listaSuscripciones)
     * <p>
     * Deberia de verificar que el Plan recibido es correspondiente al Servicio.
     */
    @Override
    public Suscripcion inscribirUsuario(Cliente usuario, Plan plan) {
        ArrayList<Suscripcion> suscripcionesUsuario = usuario.darSuscripciones();
        Suscripcion previa = null;
        // Buscando suscripcion previa
        for (Suscripcion suscripcion : suscripcionesUsuario) {
            if (suscripcion.darServicio().equals(this)) {
                previa = suscripcion;
                break;
            }
        }

        // Si existe una suscripcion previa, retornar esa misma y volverla a agregar.
        if (previa != null) {
            listaSuscripciones.add(previa);
            suscripcionesUsuario.remove(previa);
            return previa;
        }
        Suscripcion retornar = new Suscripcion(usuario, this, plan);
        listaSuscripciones.add(retornar);
        return retornar;
    }

    /**
     * Cambia el plan del Usuario
     */
    @Override
    public void cambiarPlanUsuario(Cliente cliente, Plan plan) {
        Suscripcion suscripcionUsuario = null;
        for (Suscripcion suscripcion : this.listaSuscripciones) {
            if (suscripcion.darCliente().equals(cliente)) {
                suscripcionUsuario = suscripcion;
                break;
            }
        }
        if (suscripcionUsuario == null) {
            cliente.recibirMensaje("Suscripcion a Spootify no encontrada");
            return;
        }
        suscripcionUsuario.cambioPlan(plan);
    }

    /**
     * Elimina al src.main.com.raterostesonco.streamingservicessim.Cliente de la lista interna del Servicio.
     * <p>
     * Este metodo es llamado desde la Suscripción correspondiente, que se cancela a sí misma, por lo que
     * lo unico que debemos hacer en este metodo es eliminar el objeto Suscripción correspondiente de la lista
     * del Servicio.
     */
    @Override
    public void eliminarSuscriptor(Suscripcion suscriptor) {
        this.listaSuscripciones.remove(suscriptor);

    }

    /**
     * Agrega un nuevo Suscriptor a la lista interna.
     * <p>
     * En teoria, este metodo es llamado desde inscribirUsuario(). Pero, debido a que no agregamos a los Escuchadores directamente,
     * lo más probable es que estes metodo esté vacío.
     */
    @Override
    public void agregarSuscriptor(Escuchador suscriptor) {
    }

    /**
     * Para enviar un mensaje a todos los suscriptores de este servicio.
     * <p>
     * Es usaddo para el envio de recomendaciones.
     */
    @Override
    public void notificar(String mensaje) {
        for(Suscripcion suscriptor : listaSuscripciones){
            suscriptor.darCliente().recibirMensaje(mensaje);
        }
    }
}
