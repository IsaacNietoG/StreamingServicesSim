package servicios;

import java.util.ArrayList;
import planes.PlanesSpootify;

public class Spootify implements Servicio {
    PlanesSpootify planes; //Enum
    ArrayList<String> recomendaciones;
    ArrayList<Suscripcion> listaSuscripciones;
    Spootify instance;

    /**
       Constructor de la clase Spootify

       Nos interesa que sea privado para que Spootify tenga una unica instancia.
       Debemos de crear todos los atributos correspondientes, particularmente, aqui es donde debemos de colocar las recomendaciones
       correspondientes para este servicio
     */
    private Spootify(){
        recomendaciones.add("Recomendación 1");
        recomendaciones.add("Recomendación 2");
    }

    /**
       Este es el metodo al que debemos de llamar desde el exterior para referirnos a Spootify

       Funciona simple, si no existe una instancia, la crea, y si existe, retorna la unica instancia que debe de existir.
     */
    public Spootify getInstance(){
        if(instance == null){
            instance = new Spootify();
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
         String mensaje = recomendaciones.get((int) (Math.random() * recomendaciones.size()));
        notificar(mensaje);
    }

    /**
       Cobra masivamente a todos los clientes, debe ser activado "mensualmente" al igual que enviarRecomendacion

       Utiliza el metodo Suscripcion.facturar para esto mismo.
     */
    @Override
    public void cobrarClientes() {
        // TODO Auto-generated method stub
         for(Suscripcion suscripcion : listaSuscripciones){
            suscripcion.facturar();
        }
    }

    /**
       Retorna todos los planes posibles que se pueden contratar de este servicio.
     */
    @Override
    public List<List> darPlanes() {
        // TODO Auto-generated method stub
             return List.of(PlanesSpootify.values());
    }

    /**
       Recibe un usuario que desea suscribir un nuevo plan del servicio y le retorna la Suscripcion correspondiente, de la misma
       forma, este metodo agrega la Suscripcion creada a la lista interna del Servico (listaSuscripciones)

       Deberia de verificar que el Plan recibido es correspondiente al Servicio.
     */
    @Override
    public Suscripcion inscribirUsuario(Cliente usuario, Plan plan) {
        // TODO Auto-generated method stub
        if(plan instanceof PlanesSpootify){
            Suscripcion nuevaSuscripcion = new Suscripcion(usuario, this, plan);
            listaSuscripciones.add(nuevaSuscripcion);
            return nuevaSuscripcion;
        }
        return null;
    }

    /**
       Cambia el plan del Usuario
     */
    @Override
    public void cambiarPlanUsuario(Cliente cliente, Plan plan) {
        // TODO Auto-generated method stub
        
    }

    /**
       Elimina al Cliente de la lista interna del Servicio.

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
       for(Suscripcion suscripcion : listaSuscripciones){
            suscripcion.getCliente().recibirMensaje(mensaje);
        } 
    }
}