package com.raterostesonco.streamingservicessim;

import java.util.ArrayList;

/**
 * Esta es la clase que centraliza los sistemas de pago de cada uno de los distintos Servicios
 * <p>
 * Para evitar que los Servicios tengan un acceso directo al saldo del Usuario, el Banco será
 * el intermediario entre los Servicios y el Cliente para que no se pierda ese encapsulamiento.
 * El Banco debe de poder realizar los intentos de cobro al usuario correspondiente, y de la misma manera,
 * reportarle al Servicio que hace la solicitud si el cobro fue exitoso o no.
 * Implementa el modelo Observer y Singleton
 */
public class Banco implements Notificador {

    private final String PREFIJO = "BANCO";

    private static Banco instance;

    ArrayList<Cliente> listaCuentas;

    public static Banco getInstance() {
        if(instance == null) {
            instance = new Banco();
        }
        return instance;
    }

    public Banco() {
        listaCuentas = new ArrayList<>();
    }


    /**
     * Elimina un Suscriptor del Banco
     * <p>
     * Este metodo no debería de ser implementado en el modelo actual, pues ningun cliente deja de ser parte
     * de la lista del Banco. Está aquí solamente como requisito para la interface que implementa Banco (Notificador)
     */
    @Override
    public void eliminarSuscriptor(Escuchador suscriptor) {
        listaCuentas.remove(suscriptor);
    }

    /**
     * Agrega un suscriptor a la lista de Clientes del banco.
     * <p>
     * Este metodo debe de ser llamado desde el constructor de Cliente, pues toda instancia de Cliente debe pertenecer a la lista
     * de esta clase.
     */
    @Override
    public void agregarSuscriptor(Escuchador suscriptor) {
        // TODO Auto-generated method stub

    }

    /**
     * En realidad, tampoco usaremos este metodo de la interfaz, pues Banco no realiza avisos masivos a todos sus clientes, al menos
     * bajo el modelo actual.
     */
    @Override
    public void notificar(String mensajeString) {
        System.out.println("[%s] %s".formatted(PREFIJO, mensajeString));
    }


    /**
     * Intenta cobrar a un cliente la cantidad apropiada, y retorna un booleano que simboliza el éxito de esta operación
     * <p>
     * Asi mismo, también debe de notificarle al cliente mediante su respecitvo método recibirMensaje()
     */
    public boolean cobrarCliente(Cliente cliente, double importe, String razon) {
        // TODO
        return false;
    }


}
