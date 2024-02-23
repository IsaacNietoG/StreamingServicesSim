package com.raterostesonco.streamingservicessim;

import com.raterostesonco.streamingservicessim.servicios.Servicio;
import com.raterostesonco.streamingservicessim.servicios.planes.Plan;

/**
 * La clase que modela una Suscripción a un Servicio
 * <p>
 * Esta clase es cuanto menos curiosa, porque funciona como un intermediario entre
 * el Cliente y el Servicio (y a veces hasta Banco), para poder guardar los datos individuales de los planes de cada
 * usuario en una estructura propia.
 * <p>
 * Las clases Servicio no guardan a sus Clientes directamente en sus listas, en su lugar, para comunicarse con el Cliente
 * primero recurren a encontrar sus referencias dentro de su lista de Suscripciones, este comportamiento, aunque
 * rompe un poco con el modelo Observer (ya que el Sujeto no está guardando a sus Observers directamente) nos permite
 * individualizar mejor las distintas características que tiene la suscripción de un usuario.
 * De forma similar, aunque Banco sí guarda a sus Clientes directamente, para manejar el sistema de cobro individual
 * de cada Suscripción, el que se encarga es la misma Suscripción y es esta la que, después de ser activada por Servicio, llama
 * a Banco para cobrarle al Usuario.
 * <p>
 * Esta es la parte del proyecto que implementa Strategy, pues dependiendo del objeto Plan que componga a la instancia de Suscripción,
 * la misma deberá de cobrar de manera distinta.
 */
public class Suscripcion {

    private final Cliente cliente;
    private final Servicio servicio;
    private int mesesActivo;
    private int mesesTotales;
    private Plan plan;
    private boolean suscripcionActiva;

    /**
     * El constructor de la clase Suscripcion, este recibe los datos necesarios para crear
     * la Suscripción solicitada.
     * <p>
     * En este metodo se podria incluir una verificación de coherencia entre el Servicio y el Plan, para asegurarse de que
     * la Suscripción esté recibiendo una combinación válida entre estas dos, pero realmente puede que no sea necesario debido
     * a que la creación de Suscripción se realizará dentro del Servicio en cuestión, donde, en teoría, solo tendremos acceso
     * a los planes acordes.
     *
     * @param cliente El cliente al cual está asociada la Suscripción
     *                servicio   Servicio a cual está asociada la Suscripción
     *                plan       Plan de la suscripción
     */
    public Suscripcion(Cliente cliente, Servicio servicio, Plan plan) {
        this.cliente = cliente;
        this.servicio = servicio;
        this.plan = plan;
        this.mesesActivo = 0; // Inicialmente, la suscripción no ha estado activa ningún mes
        this.suscripcionActiva = true;
    }

    /**
     * Modifica el plan interno de la Suscripción. Importante señalar que este es el método principal que nos interesa a la hora de
     * implementar Strategy
     * Si incluimos una verificación de coherencia como la mencionada en el constructor de esta misma clase, lo más apropiado sería
     * volver a llamarla en este método.
     *
     * @param plan El nuevo plan que pasará a componer a la Suscripción
     */
    public void cambioPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * Cancela la suscripción del cliente al Servicio
     * <p>
     * Debe de ser el método que es llamado desde el lado del Cliente y es el que realiza todas las acciones necesarias para desactivar
     * un plan. Esto es:
     * 1, Cambiar su variable interna de suscripcionActiva a false
     * 2. Llamar a eliminarSuscriptor del Servicio correspondiente.
     * 3. Setear mesesActivo a 0
     */
    public void cancelar() {
        // TODO: Mala implementación
        this.suscripcionActiva = false;
        this.servicio.eliminarSuscriptor(cliente);
        this.mesesActivo = 0;
    }

    /**
     * Manda una orden de cobro al Cliente de la Suscripción
     * <p>
     * Esto se debe manejar enviando la solicitud al Banco con el debido mensaje (usando el metodo cobrarCliente)
     * De la misma forma, debe de manejar el caso en el que este cobro no puede ser exitoso y por lo tanto se debe de cancelar la
     * suscripción. Consultar Banco.
     */
    public void facturar() {
        // TODO: Mala implementación
        boolean cobroExitoso = Banco.getInstance().cobrarCliente(this.cliente, 2, "");
        if (!cobroExitoso) {
            cancelar(); // Si el cobro falla, cancelar la suscripción
        }
    }

    public Cliente darCliente() {
        return this.cliente;
    }

    public Servicio darServicio() {
        return this.servicio;
    }

    public Plan darPlan() {
        return this.plan;
    }

    public boolean esSuscriptor() {
        return this.suscripcionActiva;
    }

    public int darMesesActivo() {
        return this.mesesActivo;
    }

    public int darMesesTotales(){
        return this.mesesTotales;
    }

}
