package src.main.com.raterostesonco.streamingservicessim;

import java.util.ArrayList;


/**
   Esta es la clase que centraliza los sistemas de pago de cada uno de los distintos Servicios

   Para evitar que los Servicios tengan un acceso directo al saldo del Usuario, el src.main.com.raterostesonco.streamingservicessim.Banco será
   el intermediario entre los Servicios y el src.main.com.raterostesonco.streamingservicessim.Cliente para que no se pierda ese encapsulamiento.
   El src.main.com.raterostesonco.streamingservicessim.Banco debe de poder realizar los intentos de cobro al usuario correspondiente, y de la misma manera,
   reportarle al Servicio que hace la solicitud si el cobro fue exitoso o no.
   Implementa ligeramente el modelo Observer 
 */
public class Banco implements Notificador{

    ArrayList<Cliente> listaCuentas;

    public Banco(){
        listaCuentas = new ArrayList<>();
    }


    /**
       Elimina un Suscriptor del src.main.com.raterostesonco.streamingservicessim.Banco
    
       Este metodo no debería de ser implementado en el modelo actual, pues ningun cliente deja de ser parte
       de la lista del src.main.com.raterostesonco.streamingservicessim.Banco. Está aquí solamente como requisito para la interface que implementa src.main.com.raterostesonco.streamingservicessim.Banco (src.main.com.raterostesonco.streamingservicessim.Notificador)
     */
    @Override
    public void eliminarSuscriptor(Escuchador suscriptor) {
        
    }

    /**
       Agrega un suscriptor a la lista de Clientes del banco.

       Este metodo debe de ser llamado desde el constructor de src.main.com.raterostesonco.streamingservicessim.Cliente, pues toda instancia de src.main.com.raterostesonco.streamingservicessim.Cliente debe pertenecer a la lista
       de esta clase.
     */
    @Override
    public void agregarSuscriptor(Escuchador suscriptor) {
        // TODO Auto-generated method stub
        
    }

    /**
       En realidad, tampoco usaremos este metodo de la interfaz, pues src.main.com.raterostesonco.streamingservicessim.Banco no realiza avisos masivos a todos sus clientes, al menos
       bajo el modelo actual.
     */
    @Override
    public void notificar(String mensajeString) {
    }


    /**
       Intenta cobrar a un cliente la cantidad apropiada, y retorna un booleano que simboliza el éxito de esta operación
    
       Asi mismo, también debe de notificarle al cliente mediante su respecitvo método recibirMensaje()
     */
    public boolean cobrarCliente(Cliente cliente, double importe, String razon){
        
    }


}
