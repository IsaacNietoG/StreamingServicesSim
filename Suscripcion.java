import planes.Plan;

/**
   La clase que modela una Suscripción a un Servicio

   Esta clase es cuanto menos curiosa, porque funciona como un intermediario entre
   el Cliente y el Servicio (y a veces hasta Banco), para poder guardar los datos individuales de los planes de cada
   usuario en una estructura propia.

   Las clases Servicio no guardan a sus Clientes directamente en sus listas, en su lugar, para comunicarse con el Cliente
   primero recurren a encontrar sus referencias dentro de su lista de Suscripciones, este comportamiento, aunque
   rompe un poco con el modelo Observer (ya que el Sujeto no está guardando a sus Observers directamente) nos permite
   individualizar mejor las distintas características que tiene la suscripción de un usuario.
   De forma similar, aunque Banco sí guarda a sus Clientes directamente, para manejar el sistema de cobro individual
   de cada Suscripción, el que se encarga es la misma Suscripción y es esta la que, después de ser activada por Servicio, llama
   a Banco para cobrarle al Usuario.

   
 */
public class Suscripcion {

    private Cliente cliente;
    private Servicio servicio;
    private int mesesActivo;
    private int mesesTotales;
    private Plan plan;
    private boolean suscripcionActiva;

    /**
       El constructor de la clase Suscripcion, este recibe los datos necesarios para crear
       la Suscripción solicitada.
    
       En este metodo se podria incluir una verificación de coherencia entre el Servicio y el Plan, para asegurarse de que
       la Suscripción esté recibiendo una combinación válida entre estas dos, pero realmente puede que no sea necesario debido
       a que la creación de Suscripción se realizará dentro del Servicio en cuestión, donde, en teoría, solo tendremos acceso
       a los planes acordes.
     */
    public Suscripcion(Cliente cliente, Servicio servicio, Plan plan){
        
    }

    /**
       Modifica el plan interno
     */
    public void cambioPlan(Plan plan){
        
    }

    public void cancelar(){
        
    }

    public void facturar(){
        
    }

    public Cliente darCliente(){
        
    }

    public Servicio darServicio(){
        
    }

    public Plan darPlan(){
        
    }

    public boolean esSuscriptor(){
        
    }

    public int darMesesActivo(){
        
    }

}
