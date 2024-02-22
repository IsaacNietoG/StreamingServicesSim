package com.raterostesonco.streamingservicessim;

/**
 * Interface para modelar el comportamiento básico que debe tener un Observer en nuestro patron.
 * <p>
 * Esta es la parte Observer de nuestra implementación del patrón Observer, la parte sujeto es
 * src.main.com.raterostesonco.streamingservicessim.Notificador
 */
public interface Escuchador {

    /**
     * Este es el método que debe activar el Sujeto para enviar mensajes al Suscriptor
     *
     * @param mensaje Una String que recibe el Suscriptor, la cual contiene el texto que el
     *                Suscriptor recibe como mensaje.
     */
    void recibirMensaje(String mensaje);

}
