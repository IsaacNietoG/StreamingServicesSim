package com.raterostesonco.streamingservicessim.servicios;

import com.raterostesonco.streamingservicessim.Cliente;
import com.raterostesonco.streamingservicessim.Escuchador;
import com.raterostesonco.streamingservicessim.Notificador;
import com.raterostesonco.streamingservicessim.Suscripcion;
import com.raterostesonco.streamingservicessim.servicios.planes.Plan;

import java.util.List;

/**
 * La interface que da los metodos esenciales de un Servicio en nuestra implementación
 * <p>
 * Tenemos varios metodos requeridos por esta interface que se encargan básicamente de dos
 * grandes aspectos:
 * <p>
 * 1. Para cumplir con src.main.com.raterostesonco.streamingservicessim.Notificador, requerimos que tenga una lista manejable de Escuchadores (Clientes)
 * y sus respectivos métodos para realizar las acciones masivas en esta lista, que para los requerimentos de
 * la práctica son: agregarSuscriptores, eliminarSuscriptores, notificar, enviarRecomendacion, cobrarClientes
 * <p>
 * 2. Para la parte personal, es decir, el trato entre Servicio y un solo src.main.com.raterostesonco.streamingservicessim.Cliente, tenemos otros metodos que son:
 * darPlanes, cobrarCliente, cambiarPlanUsuario
 * <p>
 * Esta interface debe de funcionar en abstracto como un intermediario, para la parte singular (2), entre el src.main.com.raterostesonco.streamingservicessim.Cliente y su
 * Suscripción. Y para la parte global (1) esencialmente como el sujeto del patrón Observer, que enviará y cobrará a sus
 * usuarios masivamente, y también enviará recomendaciones masivamente a los mismos.
 * <p>
 * Es importante señalar que, en la mayoría de los casos, no trabajaremos con una lista de Clientes directamente, en su lugar,
 * utilizaremos al objeto Suscripción.
 * Las clases Servicio no guardan a sus Clientes directamente en sus listas, en su lugar, para comunicarse con el src.main.com.raterostesonco.streamingservicessim.Cliente
 * primero recurren a encontrar sus referencias dentro de su lista de Suscripciones, este comportamiento, aunque
 * rompe un poco con el modelo Observer (ya que el Sujeto no está guardando a sus Observers directamente) nos permite
 * individualizar mejor las distintas características que tiene la suscripción de un usuario.
 */
public interface Servicio extends Notificador {


    default String getNombre() {
        return getClass().getSimpleName();
    }

    void enviarRecomendacion();

    void cobrarClientes();

    List<Plan> darPlanes();

    Suscripcion inscribirUsuario(Cliente usuario, Plan plan);

    void cambiarPlanUsuario(Cliente cliente, Plan plan);

    //Metodos de Notificador
    void eliminarSuscriptor(Suscripcion suscriptor);

    void agregarSuscriptor(Escuchador suscriptor);

    void notificar(String mensaje);

}
