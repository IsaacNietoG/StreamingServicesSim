package src.main.com.raterostesonco.streamingservicessim;

/**
   Interface para modelar el comportamiento deseado del notificador

   Esta interface nos modela el comportamiento más primitivo que debería de tener un sujeto en el modelo observer
   En nuestra implementación específica deberá ser implementado por src.main.com.raterostesonco.streamingservicessim.Banco y Servicio
 */
public interface Notificador {
    
    public void eliminarSuscriptor(Escuchador suscriptor);

    public void agregarSuscriptor(Escuchador suscriptor);

    public void notificar(String mensajeString);
}
