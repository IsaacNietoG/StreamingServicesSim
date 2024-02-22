package servicios;

import servicios.planes.Plan;

import java.util.List;

/**
   La interface que da los metodos esenciales de un Servicio en nuestra implementación

   Tenemos varios metodos requeridos por esta interface que se encargan básicamente de dos
   grandes aspectos:

   1. Para cumplir con Notificador, requerimos que tenga una lista manejable de Escuchadores (Clientes)
   y sus respectivos métodos para realizar las acciones masivas en esta lista, que para los requerimentos de
   la práctica son: agregarSuscriptores, eliminarSuscriptores, notificar, enviarRecomendacion, cobrarClientes

   2. Para la parte personal, es decir, el trato entre Servicio y un solo Cliente, tenemos otros metodos que son:
   darPlanes, cobrarCliente, cambiarPlanUsuario

   Esta interface debe de funcionar en abstracto como un intermediario, para la parte singular (2), entre el Cliente y su
   Suscripción. Y para la parte global (1) esencialmente como el sujeto del patrón Observer, que enviará y cobrará a sus
   usuarios masivamente, y también enviará recomendaciones masivamente a los mismos.

   Es importante señalar que, en la mayoría de los casos, no trabajaremos con una lista de Clientes directamente, en su lugar,
   utilizaremos al objeto Suscripción.
   Las clases Servicio no guardan a sus Clientes directamente en sus listas, en su lugar, para comunicarse con el Cliente
   primero recurren a encontrar sus referencias dentro de su lista de Suscripciones, este comportamiento, aunque
   rompe un poco con el modelo Observer (ya que el Sujeto no está guardando a sus Observers directamente) nos permite
   individualizar mejor las distintas características que tiene la suscripción de un usuario.
 */
public interface Servicio implements Notificador{

    void enviarRecomendacion();
    void cobrarClientes();
    void cobrarCliente(Cliente c);
    List<List> darPlanes();


    public static void main(String[] args) {

    }



}
