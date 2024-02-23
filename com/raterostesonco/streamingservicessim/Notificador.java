package com.raterostesonco.streamingservicessim;

/**
 * Interface para modelar el comportamiento deseado del notificador
 * <p>
 * Esta interface nos modela el comportamiento más primitivo que debería de tener un sujeto en el modelo observer
 * En nuestra implementación específica deberá ser implementado por Banco y Servicio
 */
public interface Notificador {

    void eliminarSuscriptor(Suscripcion suscriptor);

    void agregarSuscriptor(Escuchador suscriptor);

    void notificar(String mensajeString);
}
