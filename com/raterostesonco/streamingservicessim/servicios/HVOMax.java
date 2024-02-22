package com.raterostesonco.streamingservicessim.servicios;

import com.raterostesonco.streamingservicessim.Cliente;
import com.raterostesonco.streamingservicessim.Escuchador;
import com.raterostesonco.streamingservicessim.Suscripcion;
import com.raterostesonco.streamingservicessim.servicios.planes.Plan;
import com.raterostesonco.streamingservicessim.servicios.planes.PlanesHVOMax;

import java.util.ArrayList;
import java.util.List;

public class HVOMax implements Servicio {

    PlanesHVOMax planes; //Enum
    ArrayList<String> recomendaciones;
    ArrayList<Suscripcion> listaSuscripciones;
    HVOMax instance;

    /**
     * Constructor de la clase HVOMax
     * <p>
     * Nos interesa que sea privado para que HVOMax tenga una unica instancia.
     * Debemos de crear todos los atributos correspondientes, particularmente, aqui es donde debemos de colocar las recomendaciones
     * correspondientes para este servicio
     */
    private HVOMax() {

    }

    /**
     * Este es el metodo al que debemos de llamar desde el exterior para referirnos a HVOMax
     * <p>
     * Funciona simple, si no existe una instancia, la crea, y si existe, retorna la unica instancia que debe de existir.
     */
    public HVOMax getInstance() {
        if (instance == null) {
            instance = new HVOMax();
        }
        return instance;
    }

    /**
     * Envia una recomendacion aleatoria mensualmente a todos los suscriptores del servicio
     * <p>
     * Utiliza el modelo Observer implementado en Servicios para este propósito (ver notificar())
     */
    @Override
    public void enviarRecomendacion() {
        // TODO Auto-generated method stub

    }

    /**
     * Cobra masivamente a todos los clientes, debe ser activado "mensualmente" al igual que enviarRecomendacion
     * <p>
     * Utiliza el metodo src.main.com.raterostesonco.streamingservicessim.Suscripcion.facturar para esto mismo.
     */
    @Override
    public void cobrarClientes() {
        // TODO Auto-generated method stub

    }

    /**
     * Retorna todos los src.main.com.raterostesonco.streamingservicessim.servicios.planes posibles que se pueden contratar de este servicio.
     * <p>
     * Particularmente para HVOMax, debemos de tener en mente que en realidad solamente tiene un plan. (Consultar Planes)
     */
    @Override
    public List<Plan> darPlanes() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Recibe un usuario que desea suscribir un nuevo plan del servicio y le retorna la src.main.com.raterostesonco.streamingservicessim.Suscripcion correspondiente, de la misma
     * forma, este metodo agrega la src.main.com.raterostesonco.streamingservicessim.Suscripcion creada a la lista interna del Servico (listaSuscripciones)
     * <p>
     * Deberia de verificar que el Plan recibido es correspondiente al Servicio.
     * <p>
     * Particularmente en HVOMax, se espera que la unica opcion posible para un nuevo Usuario sea el plan inicial, y,
     * que un usuario que se vuelve a suscribir no pueda volver a usar el plan inicial.
     */
    @Override
    public Suscripcion inscribirUsuario(Cliente usuario, Plan plan) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Cambia el plan del Usuario
     * <p>
     * Particularmente en HVOMax, debemos verificar que el src.main.com.raterostesonco.streamingservicessim.Cliente no pueda cambiarse al plan inicial cuando haya vencido el
     * plazo correspondiente.
     */
    @Override
    public void cambiarPlanUsuario(Cliente cliente, Plan plan) {
        // TODO Auto-generated method stub

    }

    /**
     * Elimina al src.main.com.raterostesonco.streamingservicessim.Cliente de la lista interna del Servicio.
     * <p>
     * Este metodo es llamado desde la Suscripción correspondiente, que se cancela a sí misma, por lo que
     * lo unico que debemos hacer en este metodo es eliminar el objeto Suscripción correspondiente de la lista
     * del Servicio.
     */
    @Override
    public void eliminarSuscriptor(Escuchador suscriptor) {
        // TODO Auto-generated method stub

    }

    /**
     * Agrega un nuevo Suscriptor a la lista interna.
     * <p>
     * En teoria, este metodo es llamado desde inscribirUsuario(). Pero, debido a que no agregamos a los Escuchadores directamente,
     * lo más probable es que estes metodo esté vacío.
     */
    @Override
    public void agregarSuscriptor(Escuchador suscriptor) {
        // TODO Auto-generated method stub

    }

    /**
     * Para enviar un mensaje a todos los suscriptores de este servicio.
     * <p>
     * Es usaddo para el envio de recomendaciones.
     */
    @Override
    public void notificar(String mensaje) {
        // TODO Auto-generated method stub

    }
}
