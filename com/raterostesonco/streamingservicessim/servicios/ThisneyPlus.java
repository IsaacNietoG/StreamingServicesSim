package src.main.com.raterostesonco.streamingservicessim.servicios;

import src.main.com.raterostesonco.streamingservicessim.Cliente;
import src.main.com.raterostesonco.streamingservicessim.Escuchador;
import src.main.com.raterostesonco.streamingservicessim.Suscripcion;
import src.main.com.raterostesonco.streamingservicessim.servicios.planes.Plan;
import src.main.com.raterostesonco.streamingservicessim.servicios.planes.PlanesThisneyPlus;
import servicios.planes.PlanesThisneyPlus;
import java.util.ArrayList;

package servicios;

/**
   Clase que modela el comportaamiento del servicio src.main.com.raterostesonco.streamingservicessim.servicios.ThisneyPlus

   La clase de src.main.com.raterostesonco.streamingservicessim.servicios.ThisneyPlus, al igual que el resto de clases de Servicios, debe ser un Singleton

   En particular con esta clase, debemos de considerar la particularidad que tiene en sus src.main.com.raterostesonco.streamingservicessim.servicios.planes. (La primera n cantidad de
   meses es gratis, luego se cobra) y deberemos implementar los metodos adecuandonos a este requerimento.
 */
public class ThisneyPlus implements Servicio {

    PlanesThisneyPlus planes; //Enum
    ArrayList<String> recomendaciones;
    ArrayList<Suscripcion> listaSuscripciones;
    ThisneyPlus instance;

    /**
       Constructor de la clase src.main.com.raterostesonco.streamingservicessim.servicios.ThisneyPlus

       Nos interesa que sea privado para que src.main.com.raterostesonco.streamingservicessim.servicios.ThisneyPlus tenga una unica instancia.
       Debemos de crear todos los atributos correspondientes, particularmente, aqui es donde debemos de colocar las recomendaciones
       correspondientes para este servicio
     */
    private ThisneyPlus(){
        recomendaciones = new ArrayList<>();
        listaSuscripciones = new ArrayList<>();
        recomendaciones.add("Recomendación 1");
        recomendaciones.add("Recomendación 2");
    }

    /**
       Este es el metodo al que debemos de llamar desde el exterior para referirnos a src.main.com.raterostesonco.streamingservicessim.servicios.ThisneyPlus

       Funciona simple, si no existe una instancia, la crea, y si existe, retorna la unica instancia que debe de existir.
     */
    public ThisneyPlus getInstance(){
        if(instance == null){
            instance = new ThisneyPlus();
        }else{
            return instance;
        }
    }

    /**
       Envia una recomendacion aleatoria mensualmente a todos los suscriptores del servicio

       Utiliza el modelo Observer implementado en Servicios para este propósito (ver notificar())
     */
    @Override
    public void enviarRecomendacion() {
        // TODO Auto-generated method stub
       Random rnd = new Random();
        String recomendacion = recomendaciones.get(rnd.nextInt(recomendaciones.size()));
        for (Suscripcion suscripcion : listaSuscripciones) {
            suscripcion.getCliente().recibirMensaje("ThisneyPlus recomienda: " + recomendacion);
        }  
        
    }

    /**
       Cobra masivamente a todos los clientes, debe ser activado "mensualmente" al igual que enviarRecomendacion

       Utiliza el metodo src.main.com.raterostesonco.streamingservicessim.Suscripcion.facturar para esto mismo.
     */
    @Override
    public void cobrarClientes() {
        // TODO Auto-generated method stub
        for (Suscripcion suscripcion : listaSuscripciones) {
            suscripcion.facturar();
        }
    }

    /**
       Retorna todos los src.main.com.raterostesonco.streamingservicessim.servicios.planes posibles que se pueden contratar de este servicio.

       Particularmente para src.main.com.raterostesonco.streamingservicessim.servicios.ThisneyPlus, debemos de tener en mente que en realidad solamente tiene un plan. (Consultar Planes)
     */
    @Override
    public List<List> darPlanes() {
        // TODO Auto-generated method stub
       List<Plan> planes = new ArrayList<>();
        planes.add(PlanesThisneyPlus.NORMAL);
        planes.add(PlanesThisneyPlus.PREMIUM);
        return planes;
    }

    /**
       Recibe un usuario que desea suscribir un nuevo plan del servicio y le retorna la src.main.com.raterostesonco.streamingservicessim.Suscripcion correspondiente, de la misma
       forma, este metodo agrega la src.main.com.raterostesonco.streamingservicessim.Suscripcion creada a la lista interna del Servico (listaSuscripciones)

       Deberia de verificar que el Plan recibido es correspondiente al Servicio.

       Particularmente en src.main.com.raterostesonco.streamingservicessim.servicios.ThisneyPlus, se espera que la unica opcion posible para un nuevo Usuario sea el plan inicial, y,
       que un usuario que se vuelve a suscribir no pueda volver a usar el plan inicial.
     */
    @Override
    public Suscripcion inscribirUsuario(Cliente usuario, Plan plan) {
        // TODO Auto-generated method stub
        Suscripcion nuevaSuscripcion = new Suscripcion(usuario, this, plan);
        listaSuscripciones.add(nuevaSuscripcion);
        usuario.agregarSuscripcion(nuevaSuscripcion);
        return nuevaSuscripcion;
    }

    /**
       Cambia el plan del Usuario

       Particularmente en src.main.com.raterostesonco.streamingservicessim.servicios.ThisneyPlus, debemos verificar que el src.main.com.raterostesonco.streamingservicessim.Cliente no pueda cambiarse al plan inicial cuando haya vencido el
       plazo correspondiente.
     */
    @Override
    public void cambiarPlanUsuario(Cliente cliente, Plan plan) {
        // TODO Auto-generated method stub
        
    }

    /**
       Elimina al src.main.com.raterostesonco.streamingservicessim.Cliente de la lista interna del Servicio.

       Este metodo es llamado desde la Suscripción correspondiente, que se cancela a sí misma, por lo que
       lo unico que debemos hacer en este metodo es eliminar el objeto Suscripción correspondiente de la lista
       del Servicio.
     */
    @Override
    public void eliminarSuscriptor(Escuchador suscriptor) {
        // TODO Auto-generated method stub
        listaSuscripciones.removeIf(s -> s.getCliente().equals(suscriptor));
    }

    /**
       Agrega un nuevo Suscriptor a la lista interna.

       En teoria, este metodo es llamado desde inscribirUsuario(). Pero, debido a que no agregamos a los Escuchadores directamente,
       lo más probable es que estes metodo esté vacío.
     */
    @Override
    public void agregarSuscriptor(Escuchador suscriptor) {
        // TODO Auto-generated method stub
        
    }

    /**
       Para enviar un mensaje a todos los suscriptores de este servicio.

       Es usaddo para el envio de recomendaciones.
     */
    @Override
    public void notificar(String mensaje) {
        // TODO Auto-generated method stub
        for (Suscripcion suscripcion : listaSuscripciones) {
            suscripcion.getCliente().recibirMensaje(mensaje);
        }
    }
}
