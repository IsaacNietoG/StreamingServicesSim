package com.raterostesonco.streamingservicessim.servicios;

import com.raterostesonco.streamingservicessim.Cliente;
import com.raterostesonco.streamingservicessim.Escuchador;
import com.raterostesonco.streamingservicessim.Suscripcion;
import com.raterostesonco.streamingservicessim.servicios.planes.Plan;
import com.raterostesonco.streamingservicessim.servicios.planes.PlanesHVOMax;
import com.raterostesonco.streamingservicessim.servicios.planes.PlanesThisneyPlus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HVOMax implements Servicio {

    PlanesHVOMax planes; //Enum
    private ArrayList<String> recomendaciones;
    private ArrayList<Suscripcion> listaSuscripciones;
    static HVOMax instance;

    /**
     * Constructor de la clase HVOMax
     * <p>
     * Nos interesa que sea privado para que HVOMax tenga una unica instancia.
     * Debemos de crear todos los atributos correspondientes, particularmente, aqui es donde debemos de colocar las recomendaciones
     * correspondientes para este servicio
     */
    private HVOMax() {
        recomendaciones = new ArrayList<>(Arrays.asList("True Detective", "The Curious Case of Natalia Grace", "Murder in Boston: Roots, Rampage & Reckoning",
                                                        "Love Life", "Legendary", "Mrs. Fletcher", "Minx", "Gordita Chronicles", "Succession", "Euphoria",
                                                        "Mare of Easttown", "Curb Your Enthusiasm"));
        listaSuscripciones = new ArrayList<>();
    }

    /**
     * Este es el metodo al que debemos de llamar desde el exterior para referirnos a HVOMax
     * <p>
     * Funciona simple, si no existe una instancia, la crea, y si existe, retorna la unica instancia que debe de existir.
     */
    public static HVOMax getInstance() {
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
        notificar("Recomendacion mensual de " + this + ": " + recomendaciones.get(new Random().nextInt(12)));
    }

    /**
     * Cobra masivamente a todos los clientes, debe ser activado "mensualmente" al igual que enviarRecomendacion
     * <p>
     * Utiliza el metodo src.main.com.raterostesonco.streamingservicessim.Suscripcion.facturar para esto mismo.
     */
    @Override
    public void cobrarClientes() {
        for(int i = 0; i < listaSuscripciones.size(); i++) {
            listaSuscripciones.get(i).facturar();
            if (listaSuscripciones.get(i).darMesesTotales() > 3 && listaSuscripciones.get(i).darPlan().equals(PlanesThisneyPlus.INICIAL)) {
                listaSuscripciones.get(i).darCliente().recibirMensaje("Tu prueba gratuita ha vencido, te cambiaremos al plan normal");
                listaSuscripciones.get(i).cambioPlan(PlanesThisneyPlus.NORMAL);
            }
        }
        enviarRecomendacion();
    }

    /**
     * Retorna todos los src.main.com.raterostesonco.streamingservicessim.servicios.planes posibles que se pueden contratar de este servicio.
     * <p>
     * Particularmente para HVOMax, debemos de tener en mente que en realidad solamente tiene un plan. (Consultar Planes)
     */
    @Override
    public List<Plan> darPlanes() {
        ArrayList<Plan> listaPlanes = new ArrayList<>();
        for(PlanesHVOMax plan : planes.values()){
            listaPlanes.add(plan);
        }
        return listaPlanes;
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
        ArrayList<Suscripcion> suscripcionesUsuario = usuario.darSuscripciones();
        plan = PlanesHVOMax.INICIAL;
        Suscripcion anterior = null;
        //Buscar si hay una suscripcion previa en el usuario.
        for(Suscripcion suscripcion : suscripcionesUsuario){
            if(suscripcion.darServicio() == this){
                usuario.recibirMensaje("Bienvenido de vuelta a HVOMax, "+ suscripcion.darCliente().darNombre());
                anterior = suscripcion;
                break;
            }
        }
        //Caso en el que no se encontró una suscripcion previa, se da el plan Inicial
        if(anterior == null){
            usuario.recibirMensaje("Bienvenido a HVOMax, " + usuario.darNombre());
            Suscripcion retornar = new Suscripcion(usuario, this, plan);
            listaSuscripciones.add(retornar);
            return retornar;
        }

        //Caso en el que se encontró una suscripción previa, y además tenía más de 3 meses de antiguedad.
        if(anterior.darMesesTotales() > 3){
            usuario.recibirMensaje("Tu prueba gratuita de HVOMax ha caducado, inscribiendote a plan normal");
            anterior.cambioPlan(PlanesHVOMax.NORMAL);

        }
        //Final de ejecución, si se encontró una suscripción previa, se elimina de la lista pero se retorna otra vez.
        listaSuscripciones.add(anterior);
        suscripcionesUsuario.remove(anterior);
        return anterior;


    }

    /**
     * Cambia el plan del Usuario
     * <p>
     * Particularmente en HVOMax, debemos verificar que el src.main.com.raterostesonco.streamingservicessim.Cliente no pueda cambiarse al plan inicial cuando haya vencido el
     * plazo correspondiente.
     */
    @Override
    public void cambiarPlanUsuario(Cliente cliente, Plan plan) {
        Suscripcion suscripcionUsuario = null;

        //Busca la suscripcion del usuario en la lista
        for (Suscripcion suscripcion : this.listaSuscripciones) {
            if (suscripcion.darCliente().equals(cliente)) {
                suscripcionUsuario = suscripcion;
                break;
            }
        }

        //Caso donde no se encuentra la susscripcion
        if(suscripcionUsuario == null){
            cliente.recibirMensaje("Suscripcion a HVOMax no encontrada");
            return;
        }

        //Si se intenta cambiar al plan gratuito una vez vencida la prueba
        if(plan == PlanesHVOMax.INICIAL){
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

    public String toString(){
        return getNombre();
    }
}
