package com.raterostesonco.streamingservicessim.servicios;

import com.raterostesonco.streamingservicessim.Cliente;
import com.raterostesonco.streamingservicessim.Escuchador;
import com.raterostesonco.streamingservicessim.Suscripcion;
import com.raterostesonco.streamingservicessim.servicios.planes.Plan;
import com.raterostesonco.streamingservicessim.servicios.planes.PlanesMomazon;

import java.util.ArrayList;
import java.util.List;

public class Momazon implements Servicio {
    PlanesMomazon planes; //Enum
    ArrayList<String> recomendaciones;
    ArrayList<Suscripcion> listaSuscripciones;
    Momazon instance;

    /**
     * Constructor de la clase Momazon
     * <p>
     * Nos interesa que sea privado para que Momazon tenga una unica instancia.
     * Debemos de crear todos los atributos correspondientes, particularmente, aqui es donde debemos de colocar las recomendaciones
     * correspondientes para este servicio
     */
    private Momazon() {
    recomendaciones = new ArrayList<>(Arrays.asList("The Boys", "The good doctor", "The night Manager",
                                                        "Fuera de Rango", "La isla", "Borgia", "Viaje con los Derbèz", "Borgia", "FBI", "LOL",
                                                        "The office", "Casate con mi esposo"));
        listaSuscripciones = new ArrayList<>();
    }

    /**
     * Este es el metodo al que debemos de llamar desde el exterior para referirnos a Momazon
     * <p>
     * Funciona simple, si no existe una instancia, la crea, y si existe, retorna la unica instancia que debe de existir.
     */
    public Momazon getInstance() {
        if (instance == null) {
            instance = new Momazon();
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
        notificar(recomendaciones.get(new Random().nextInt(12)));
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
    }

    /**
     * Retorna todos los src.main.com.raterostesonco.streamingservicessim.servicios.planes posibles que se pueden contratar de este servicio.
     */
    @Override
    public List<Plan> darPlanes() {
    ArrayList<Plan> listaPlanes = new ArrayList<>();
        for(PlanesMomazon plan : planes.values()){
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
        plan = PlanesMomazon.INICIAL;
        for(Suscripcion suscripcion : suscripcionesUsuario){
            if(suscripcion.darServicio() == this && suscripcion.darMesesTotales() >= 3){
                usuario.recibirMensaje("Tus meses gratuitos de Momazon caducaron, migrando a plan normal");
                plan = PlanesMomazon.NORMAL;
                break;
            }
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
        // TODO Auto-generated method stub
    Suscripcion suscripcionUsuario = null;
        for (Suscripcion suscripcion : this.listaSuscripciones) {
            if (suscripcion.darCliente().equals(cliente)) {
                suscripcionUsuario = suscripcion;
                break;
            }
        }
        if(suscripcionUsuario == null){
            cliente.recibirMensaje("Suscripcion a Momazon no encontrada");
            return;
        }
        if(plan == PlanesMomazon.INICIAL){
            if(suscripcionUsuario.darMesesTotales() > 3){
                cliente.recibirMensaje("No puedes cambiarte a este plan, tu prueba gratuita ha vencido");
                return;
            }
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
    public void eliminarSuscriptor(Escuchador suscriptor) {
        for(Suscripcion suscripcion : listaSuscripciones){
            if(suscripcion.darCliente().equals(suscriptor)){
                listaSuscripciones.remove(suscripcion);
                return;
            }

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
