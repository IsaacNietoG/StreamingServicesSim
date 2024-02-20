/**
   Interface para modelar los comportamientos que debe tener un suscriptor de un servicio
   El suscriptor debe tener una lista de Suscripciones, que serán los enlaces con los varios sujetos
   de observación que debe tener en esta implementación

   Esta es la parte Observer de nuestra implementación del patrón Observer
 */
public interface Suscriptor {

    /**
       Este es el método que debe activar el Sujeto para enviar mensajes al Suscriptor

       @param   mensaje    Una String que recibe el Suscriptor, la cual contiene el texto que el
                           Suscriptor recibe como mensaje.
     */
    public static void recibirMensaje(String mensaje){}

    /**
       Para desactivar una Suscripción, lo cual nos lleva a dejar de recibir mensajes del Servicio y
       cancelar la Suscripción (Suscripcion.cancelar)

       @param   cancelable    La suscripción que deseamos cancelar, contiene su metodo para esto
     */
    public static void cancelarServicio(Suscripcion cancelable){
        cancelable.cancelar();
    }

    /**
       Para activar un Servicio, este metodo debe llamar a Servicio.inscribirUsuario y guardar en la
       lista de Suscripciones interna la Suscripción devuelta.

       @param   servicio    El servicio que quiere contratar el Suscriptor
                plan        El plan que se desea contratar del Servicio
     */
    public static void inscribirServicio(Servicio servicio, Plan plan){}
}
