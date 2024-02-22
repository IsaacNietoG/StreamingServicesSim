import planes.Plan;
import planes.PlanesThisneyPlus;
import servicios.planes.PlanesThisneyPlus;
import java.util.ArrayList;

package servicios;

/**
   Clase que modela el comportaamiento del servicio ThisneyPlus

   La clase de ThisneyPlus, al igual que el resto de clases de Servicios, debe ser un Singleton

   En particular con esta clase, debemos de considerar la particularidad que tiene en sus planes. (La primera n cantidad de
   meses es gratis, luego se cobra) y deberemos implementar los metodos adecuandonos a este requerimento.
 */
public class ThisneyPlus implements Servicio {

    PlanesThisneyPlus planes; //Enum
    ArrayList<String> recomendaciones;
    ArrayList<Suscripcion> listaSuscripciones;
    ThisneyPlus instance;

    /**
       Constructor de la clase ThisneyPlus

       Nos interesa que sea privado para que ThisneyPlus tenga una unica instancia.
       Debemos de crear todos los atributos correspondientes, particularmente, aqui es donde debemos de colocar las recomendaciones
       correspondientes para este servicio
     */
    private ThisneyPlus(){
        
    }

    /**
       Este es el metodo al que debemos de llamar desde el exterior para referirnos a ThisneyPlus

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
        
    }

    /**
       Cobra masivamente a todos los clientes, debe ser activado "mensualmente" al igual que enviarRecomendacion

       Utiliza el metodo Suscripcion.facturar para esto mismo.
     */
    @Override
    public void cobrarClientes() {
        // TODO Auto-generated method stub
        
    }

    /**
       Retorna todos los planes posibles que se pueden contratar de este servicio.

       Particularmente para ThisneyPlus, debemos de tener en mente que en realidad solamente tiene un plan. (Consultar Planes)
     */
    @Override
    public List<List> darPlanes() {
        // TODO Auto-generated method stub
            return null;
    }

    /**
       Recibe un usuario que desea suscribir un nuevo plan del servicio y le retorna la Suscripcion correspondiente, de la misma
       forma, este metodo agrega la Suscripcion creada a la lista interna del Servico (listaSuscripciones)

       Deberia de verificar que el Plan recibido es correspondiente al Servicio.

       Particularmente en ThisneyPlus, se espera que la unica opcion posible para un nuevo Usuario sea el plan inicial, y,
       que un usuario que se vuelve a suscribir no pueda volver a usar el plan inicial.
     */
    @Override
    public Suscripcion inscribirUsuario(Cliente usuario, Plan plan) {
        // TODO Auto-generated method stub
        
    }

    /**
       Cambia el plan del Usuario

       Particularmente en ThisneyPlus, debemos verificar que el Cliente no pueda cambiarse al plan inicial cuando haya vencido el
       plazo correspondiente.
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
        
    }
}
