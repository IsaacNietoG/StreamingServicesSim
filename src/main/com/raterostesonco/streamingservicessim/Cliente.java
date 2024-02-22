import java.util.ArrayList;

/**
   La Clase Cliente de la cual se instanciarán todos los clientes de esta práctica.

   Posee varios métodos que nos permitirán realizar las interacciones necesarias entre el Cliente y el resto
   de los distintos actores de este proyecto. En principio, cuando lleguemos a trabajar en nuestro Uso, esta
   clase y las distintas clases de Servicios serán las únicas con las que vamos a interactuar.
 */
public class Cliente implements Escuchador{
    
    private String nombre;
    private ArrayList<Suscripcion> suscripciones;
    private double cuentaBanco;

     public Cliente(String nombre, double cuentaBanco) {
        this.nombre = nombre;
        this.suscripciones = new ArrayList<>();
        this.cuentaBanco = cuentaBanco;


    /**
       Este metodo se utiliza para recibir la lista de planes posibles que tiene un Servicio.

       De manera práctica, esto nos permite conocer la lista de opciones que puede tener el usuario para
       contratar una suscripción de ese servicio.

       @param   servicio    El servicio del cual queremos obtener su lista de posibles planes.
     */
    public void pedirPlanes(Servicio servicio){
        List<Plan> planes = servicio.getPlanes();
        if (planes != null && !planes.isEmpty()) {
            System.out.println("Planes disponibles para el servicio " + servicio.getNombre() + ":");
            for (Plan plan : planes) {
                System.out.println(plan);
            }
        } else {
            System.out.println("No hay planes disponibles para el servicio " + servicio.getNombre());
        }
    }

    /**
       Este metodo nos permite cancelar una suscripcion desde el lado del Cliente

       Deberia llamar al metodo cancelar() interno que posee la clase Suscripción, la cual
       es la que hará todo el trabajo del lado del Servicio.

       @param   suscripcion    La suscripcion que desea cancelar el usuario.
     */
    public void cancelarServicio(Suscripcion suscricipcion){
        suscripcion.cancelar();
        suscripciones.remove(suscripcion);
        System.out.println("La suscripción ha sido cancelada: " + suscripcion);
    }

    /**
       Descuenta el importe de la cuentaBancaria del Cliente.

       En la implementación actual, la unica forma en la que puede fracasar el pago de la transaccion
       es que no hayan fondos suficientes en la cuenta.

       Este método solamente descuenta el importe, la notificación hacia el cliente y el resto
       de aspectos del pago de Servicios es manejado por otras clases dentro de este programa.

       @param   importe     La cantidad de dinero que se desea cobrar de la cuentaBancaria interna
                            cliente.

       @return el éxito o fracaso de la transacción.
     */
    public boolean recibirCobro(double importe){
      if (cuentaBanco >= importe) {
            cuentaBanco -= importe;
            System.out.println("Cobro realizado exitosamente. Importe: " + importe);
            return true;
        } else {
            System.out.println("Fallo en el cobro. Fondos insuficientes.");
            return false;
        }  
    }


    /**
       Cambia el plan de la suscripción del cliente.

       Para esto, recurre al método interno cambiarPlanUsuario(Cliente, plan) que posee el Servicio,
       internamente, este método solo debe realizar la solicitud con el plan apropiado.

       @param   servicio    El servicio de la suscripción a la cual se desea cambiar de plan.
                plan        El plan al cual se desea cambiar la suscripción
     */
    public void cambiarPlan(Servicio servicio, Plan plan){
      servicio.cambiarPlanUsuario(this, plan);
        System.out.println("Plan cambiado a: " + plan + " para el servicio: " + servicio.getNombre());
    }  
    }


    /**
       Contrata una nueva Suscripción del Servicio designado, con el plan apropiado

       Llama al metodo inscribirUsuario(usuario, plan) del Servicio apropiado, el cual
       deberá retornar una instancia de Suscripción que nosotros del lado del Cliente guardaremos
       en la ArrayList correspondiente.

       @param   servicio    El servicio del cual se desea contratar una suscripción
                plan        El plan elegido para la suscripción
     */
    public void contratarServicio(Servicio servicio, Plan plan){
       Suscripcion nuevaSuscripcion = servicio.inscribirUsuario(this, plan);
        if (nuevaSuscripcion != null) {
            suscripciones.add(nuevaSuscripcion);
            System.out.println("Nueva suscripción agregada: " + nuevaSuscripcion);
        } else {
            System.out.println("No se pudo agregar la nueva suscripción para el servicio: " + servicio.getNombre());
        } 
    }

    public String darNombre(){
      return nombre;
    }

    public ArrayList<Suscripcion> darSuscripciones(){
        return suscripciones;
    }

    public double darSaldoBancario(){
        return cuentaBanco;
    }

    /**
       Recibe un mensaje de parte de alguno de los Sujetos en los que se encuentra listado el usuario.

       Bajo esta implementación, existen dos Sujetos que pueden enviarle mensajes al Cliente, los cuales son:
       - Servicios
       - Banco

       En cualquiera de los dos casos, estos deben dar un mensajeString claro, que este método lo unico que debería
       de hacer es imprimir en consola. Pero, debido a que todos los Clientes comparten consola, mi propuesta de uso
       sería anexar el nombre del Cliente que está recibiendo este mensaje.

       @param   mensajeString    El mensaje que se imprimirá en pantalla
     */
    public void recibirMensaje(String mensajeString){
        System.out.println("Mensaje para " + nombre + ": " + mensajeString);
    }

    }

    

